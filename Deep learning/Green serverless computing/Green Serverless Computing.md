# Green Serverless AI Training via Continuous Power-Elasticity and Carbon-Momentum Scheduling

## 1. Vision and Research Objective

The explosive growth of large-language models and transformer architectures has made AI training one of the most carbon-intensive compute workloads globally. The fundamental problem is that current deployment strategies rely on rigid, fixed resource allocations that force massive GPU clusters to remain fully powered regardless of grid conditions or true utilization. This inflexibility leads to severe energy waste and unnecessarily high greenhouse gas emissions, as complex, stateful AI training loops cannot seamlessly pause or shrink during periods of high carbon intensity on the power grid.

![Frontier AI Model Cost](frontier%20AI%20model%20cost.png)

Our target is to architect a novel serverless orchestration layer that elevates **Carbon Intensity** and **Energy Elasticity** to first-class constraints. We aim to develop a physics-inspired scheduling mechanism capable of dynamically shifting, compressing, and scaling AI workloads in perfect synchronization with real-time grid carbon fluctuations. Ultimately, our target is to break the linear correlation between AI model complexity and data center carbon footprints, ensuring that the next generation of AI training is inherently resource-efficient and environmentally sustainable.

---

## 2. The Foundational Energy Model for Serverless AI

Before introducing our continuous elasticity framework, we ground our research in the foundational mathematics of serverless energy consumption. For each function invocation in a standard model, the total energy naturally decomposes as:

$$ E*{\text{total}} = E*{\text{work}} + E\_{\text{overhead}} $$

where $E_{\text{work}}$ is the energy dedicated to useful AI computation, and $E_{\text{overhead}}$ aggregates the energy wasted on cold starts, container lifecycles, and idle intervals.

If a training task step requires $F$ floating-point operations (FLOPs) on hardware with an energy efficiency of $\eta$ (FLOPs/Joule), the ideal work energy is:

$$ E\_{\text{work}} = \frac{F}{\eta} $$

### The Cold Start Penalty

During a "cold start", the environment must allocate resources, load heavy ML dependencies (PyTorch, CUDA), and initialize model weights. The energy overhead per cold start is modeled as:

$$ E*{cs} = P*{cs} \cdot T\_{cs} $$

where $T_{cs}$ is the cold-start duration and $P_{cs}$ is the average power draw during initialization. The fundamental challenge arises because, for highly partitioned AI tasks, $E_{cs}$ frequently overshadows $E_{\text{work}}$.

### The Break-Even Trade-off

To mitigate cold starts, traditional platforms maintain $k$ warm, idle instances consuming constant background power $P_{idle}$. Over a time interval $T$, these warm instances cost $E_{warm} = k \cdot P_{idle} \cdot T$.

The traditional break-even point dictates that keeping the instances warm is only greener than cold-starting them when the arrival rate $\lambda$ satisfies:

$$
\lambda \ge \lambda_{\min} = \frac{k \cdot P_{\text{idle}}}{E_{cs}}
$$

```
 Energy (Joules)
    │
    │                                         ● E_B = λ·T·E_cs
    │                                      ●    (cold start cost)
    │                                   ●        grows linearly with λ
    │                                ●
    │                             ●
 E_A│· · · · · · · · · ·● · · ● · · · · · · ·  E_A = k·P_idle·T
    │              ●   ↑ break-even            (warm pool cost)
    │           ●      λ_min                   flat — fixed regardless of λ
    │        ●
    │     ●
    │  ●
    │
    └──────────────┬────────────────────────► λ (requests/sec)
                 λ_min

    ◄─────────────┤─────────────────────────►
     COLD CHEAPER │    WARM CHEAPER
     E_B < E_A    │    E_B > E_A
     → scale to 0 │    → keep k instances warm
```

---

## 3. Beyond the Binary: The Continuous Hibernation-Aware Energy Model

Current research and existing industry standards stop at the foundational model, relying on rigid thresholds (like $\lambda_{min}$) to decide between keeping a container 100% warm or shutting it down completely. This binary approach ignores the technical reality of modern memory hierarchies and intermediate system states.

We propose developing upon the foundational model by abandoning binary thresholding in favor of the **Energy-Latency Elasticity Index (ELEI)** framework. Instead of a binary state $\in \{0, 1\}$, we define a continuous container readiness state $\psi(t) \in [0, 1]$, where $\psi=1$ implies fully active GPU/CPU RAM and $\psi = 0$ represents deep-sleep storage (e.g., NVMe/EFS snapshotted).

### Non-Linear Restitution Energy

Building upon the base definition $E_{cs}$, the energy required to restore an AI container from an arbitrary state $\psi$ back to full readiness is formulated non-linearly:

$$ E*{restore}(\psi) = E*{cs} \cdot (1 - \psi)^{\alpha} $$

where $\alpha > 1$ represents a hardware-specific acceleration factor. This captures the efficiency of restoring memory-mapped tensor caches via mechanisms like CRIU (Checkpoint/Restore In Userspace) over a brutal full cold-boot from object storage.

#### Why CRIU Makes α > 1 Physically Justified

To understand why the restore cost is **non-linear**, we must contrast two fundamentally different restore paths.

**Path A — Full Cold Boot (ψ = 0 → 1): the baseline E_cs**

When a container is fully terminated, restoring it means re-executing every initialization step from scratch:

```
Step 1: Provision container + allocate GPU/CPU       ~300ms,  ~3J
Step 2: Load Python runtime + ML dependencies        ~1.5s,   ~8J
         (torch, CUDA kernels, numpy, transformers)
Step 3: Download model weights from S3               ~8s,    ~40J
         (e.g., 7GB LLaMA weights over network I/O)
Step 4: Load weights into GPU VRAM over PCIe bus     ~3s,    ~15J
Step 5: JIT-compile CUDA kernels (first inference)   ~3s,    ~10J
─────────────────────────────────────────────────────────────────
Total cold start:                                   ~16s,   ~76J
```

Every joule in Steps 2–5 is **redundant** if the model had not changed. The weights are identical — they are simply fetched again from scratch.

---

**Path B — CRIU Restore (ψ = 0.2 → 1): why it is cheaper**

CRIU (Checkpoint/Restore In Userspace) is a Linux mechanism that takes a **complete memory snapshot** of a running process — every byte in RAM, every open file descriptor, every CUDA kernel state — and writes it to disk. On restore, it maps this snapshot back into memory directly.

```
What CRIU saves to NVMe when ψ drops to 0.2:
┌────────────────────────────────────────────────┐
│  Python heap + stack         (already loaded)  │
│  PyTorch tensor allocations  (already loaded)  │
│  CUDA kernel compiled state  (already compiled)│
│  Model weights layout in VRAM (already placed) │
│  Optimizer state             (already computed)│
└────────────────────────────────────────────────┘
         ↓  DMA write to NVMe SSD (~50ms)

What CRIU does on restore (ψ = 0.2 → 1):
┌────────────────────────────────────────────────┐
│  mmap() the snapshot file                      │
│  → OS maps NVMe pages into process address     │
│    space using virtual memory — no copy yet    │
│  → Pages loaded on-demand as GPU accesses them │
│    (copy-on-access, not copy-on-restore)       │
└────────────────────────────────────────────────┘
         ↓  Resume in ~200ms, no S3 download
```

**Memory-mapped tensor caches** means the model weights are not copied — the OS page table points directly at the NVMe snapshot. The GPU pulls only the pages it needs, when it needs them, over the PCIe bus. Steps 2, 3, and 5 from the cold boot are **completely eliminated**.

```
CRIU restore from ψ = 0.2:
Step 1: Re-provision container                       ~300ms,  ~3J
Step 2: (SKIPPED — Python + deps already in snapshot)    0J
Step 3: (SKIPPED — weights already in snapshot)          0J
Step 4: mmap NVMe snapshot → GPU VRAM (on-demand)    ~200ms,  ~4J
Step 5: (SKIPPED — CUDA kernels already compiled)        0J
─────────────────────────────────────────────────────────────────
Total CRIU restore:                                  ~500ms,  ~7J

vs Full cold boot:                                   ~16s,   ~76J
                                                     ↑ 30× faster, 10× cheaper
```

This physical reality — that most of the cold-start cost is in loading and compiling, not in execution — is precisely what forces $\alpha > 1$. A container at $\psi = 0.2$ has already paid for the expensive steps; it only needs to re-map memory. The restore cost does not scale linearly with $(1 - \psi)$; it collapses super-linearly as $\psi$ increases past the threshold where VRAM eviction is the only remaining cost.

#### Origin and Mathematical Proof of the Restitution Equation

The traditional binary model assumes a step function: $E_{restore}$ is either $0$ (if warm) or $E_{cs}$ (if cold). However, modern virtualization (e.g., Firecracker microVMs) and memory mapping allow for intermediate hibernation states.

**Boundary Conditions Proof:**
The proposed equation perfectly satisfies both theoretical extremes of the foundational model:
1. **Fully Cold ($\psi = 0$):** $E_{restore}(0) = E_{cs} \cdot (1 - 0)^{\alpha} = E_{cs}$. 
2. **Fully Warm ($\psi = 1$):** $E_{restore}(1) = E_{cs} \cdot (1 - 1)^{\alpha} = 0$.

**The Non-Linear Acceleration Factor ($\alpha > 1$):**
In AI systems, restoring a state is deeply non-linear due to the memory hierarchy.
- A slight decay in readiness (e.g., $\psi$ drops from $1.0$ to $0.8$) might just mean evicting L1/L2 CPU caches but keeping the massive 10GB transformer weights pinned in GPU VRAM. Restoring CPU caches is computationally cheap.
- A severe decay (e.g., $\psi$ drops to $0.1$) means evicting GPU VRAM to an NVMe SSD. Restoring this triggers a massive energy surge across the PCIe bus.

Because of this physical hardware hierarchy, the energy penalty drops exponentially as $\psi$ increases. This physical curvature is mapped by $\alpha$ (typically $\alpha \ge 2$ for memory-intensive tensor workloads).

```text
       E_restore (Joules)
 E_cs ┼─●
      │  \    ← α = 1 (Linear transfer - Theoretical, non-physical)
      │   \
      │    ●
      │     \      __← α = 2 (Non-linear RESTITUTION - Real SSD/RAM hierarchy)
      │      \ . ⁻ 
      │       ● 
      │        .
      │          . 
      │             .    
      │                 .      
    0 ┼───────────────────────●────► ψ (Readiness State)
      0.0         0.5        1.0
      (Cold)                 (Warm)
```

**Mathematical Insight:** If an orchestrator maintains $\psi = 0.8$ with $\alpha = 2$ - like clean the CPU cache and pausing network connection, the required restore energy is $E_{cs} \cdot (1 - 0.8)^2 = E_{cs} \cdot 0.04$. By sacrificing only 20% of readiness (saving significant idle power), the system effectively eliminates **96%** of the cold-start energy spike. This mathematically proves that continuous deep-sleep architectures are vastly superior to the standard binary Warm/Cold toggles.
<!-- ### Decay Power Function

Conversely, developing upon the base $P_{idle}$, the idle power consumption at state $\psi$ decays according to:

$$ P*{idle}(\psi) = P*{peak_idle} \cdot \psi^{\beta} $$

where $\beta < 1$ indicates that marginally reducing the readiness state (e.g., evicting CPU caches but keeping main GPU memory allocated) yields massive early power savings before hitting the deep-sleep floor.

Our green orchestrator dynamically solves for the continuous decay path $\psi^*(t)$ during idle intervals to minimize the time-integral of power, guaranteeing that $E_{restore}$ remains within the bounds of a renewable energy surplus budget.

---

## 4. Carbon-Momentum ($\mathcal{M}_c$) Scheduling

Unlike HTTP web requests, AI micro-batches possess a unique characteristic: they are highly deferrable but require massive execution burst capability. Current carbon-aware schedulers merely trigger execution when the absolute carbon intensity ($C(t)$ in $gCO_2/kWh$) drops below a predefined static threshold.

We introduce a novel **Carbon-Momentum** scheduling algorithm inspired by physical pressure dynamics and gradient descent. Our system accumulates "Compute Pressure" $\Pi(t)$ as unprocessed training data batches queue up. Execution is not triggered by a static threshold, but by a continuous probability function prioritizing the **gradient** (time-derivative) of carbon intensity:

$$ \mathbb{P}(\text{burst exec at } t) = \sigma \left( w_1 \cdot \Pi(t) - w_2 \cdot \frac{dC(t)}{dt} - w_3 \cdot C(t) \right) $$

**Detail & Insight:** By weighting the time-derivative $\frac{dC(t)}{dt}$ negatively, the system structurally anticipates valleys in grid intensity. It triggers massive serverless scaling bursts _while the grid is actively getting greener_ (e.g., catching wind or solar overproduction spikes in real-time) rather than reacting after grid batteries have already normalized the spot costs.

---

## 5. Sub-Millisecond Preemption via Checkpoint-Momentum

Because Carbon-Momentum scheduling yields highly irregular and abrupt execution bursts, the traditional concept of training "epochs" fails. A serverless training pipeline might be provisioned with thousands of concurrent GPU functions for 4 minutes during a wind-energy spike, and zero for the next hour.

To support this aggressive elasticity, we detail a zero-copy, ephemeral checkpointing architecture. Instead of pushing monolithic `.pt` weights to S3 at epoch boundaries, the system computes an intra-batch gradient momentum snapshot.

Upon receiving a preemption interrupt (driven by a sudden spike in $C(t)$), the container dumps the exact CUDA kernel state variables directly to NVMe-backed EFS using direct memory access (DMA) within 50ms, freezing the model mid-forward-pass.

When the Carbon-Momentum function triggers again, the containers resume at a $\psi=0.85$ readiness state, continuing the tensor contractions with theoretically zero dropped FLOPs and no redundant data loading phases. -->
