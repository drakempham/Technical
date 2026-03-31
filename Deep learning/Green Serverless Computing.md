# Green Serverless AI Training via Continuous Power-Elasticity and Carbon-Momentum Scheduling

## 1. Vision and Research Objective

The explosive growth of large-language models and transformer architectures has made AI training one of the most carbon-intensive compute workloads globally. Traditional serverless computing provides fine-grained auto-scaling, but its underlying abstractions were designed for latency-sensitive microservices, not complex, stateful, and energy-hungry AI iteration loops. 

Our ambition is to architect a fundamentally new serverless orchestration layer that elevates **Carbon Intensity** and **Energy Elasticity** to first-class constraints. We aim to break away from the traditional binary "Warm vs. Cold" container lifecycle, replacing it with a continuous, hardware-aware degradation model. By coupling this with a physics-inspired scheduling mechanism, we seek to dynamically shift, compress, and scale AI workloads in perfect synchronization with grid carbon fluctuations, breaking the linear correlation between AI model complexity and data center carbon footprints.

---

## 2. The Foundational Energy Model for Serverless AI

Before introducing our continuous elasticity framework, we ground our research in the foundational mathematics of serverless energy consumption. For each function invocation in a standard model, the total energy naturally decomposes as:

$$ E_{\text{total}} = E_{\text{work}} + E_{\text{overhead}} $$

where $E_{\text{work}}$ is the energy dedicated to useful AI computation, and $E_{\text{overhead}}$ aggregates the energy wasted on cold starts, container lifecycles, and idle intervals. 

If a training task step requires $F$ floating-point operations (FLOPs) on hardware with an energy efficiency of $\eta$ (FLOPs/Joule), the ideal work energy is:

$$ E_{\text{work}} = \frac{F}{\eta} $$

### The Cold Start Penalty
During a "cold start", the environment must allocate resources, load heavy ML dependencies (PyTorch, CUDA), and initialize model weights. The energy overhead per cold start is modeled as:

$$ E_{cs} = P_{cs} \cdot T_{cs} $$

where $T_{cs}$ is the cold-start duration and $P_{cs}$ is the average power draw during initialization. The fundamental challenge arises because, for highly partitioned AI tasks, $E_{cs}$ frequently overshadows $E_{\text{work}}$. 

### The Break-Even Trade-off
To mitigate cold starts, traditional platforms maintain $k$ warm, idle instances consuming constant background power $P_{idle}$. Over a time interval $T$, these warm instances cost $E_{warm} = k \cdot P_{idle} \cdot T$. 

The traditional break-even point dictates that keeping the instances warm is only greener than cold-starting them when the arrival rate $\lambda$ satisfies:

$$ \lambda \ge \lambda_{min} = \frac{k \cdot P_{idle}}{E_{cs}} $$

---

## 3. Beyond the Binary: The Continuous Hibernation-Aware Energy Model

Current research and existing industry standards stop at the foundational model, relying on rigid thresholds (like $\lambda_{min}$) to decide between keeping a container 100% warm or shutting it down completely. This binary approach ignores the technical reality of modern memory hierarchies and intermediate system states.

We propose developing upon the foundational model by abandoning binary thresholding in favor of the **Energy-Latency Elasticity Index (ELEI)** framework. Instead of a binary state $\in \{0, 1\}$, we define a continuous container readiness state $\psi(t) \in [0, 1]$, where $\psi=1$ implies fully active GPU/CPU RAM and $\psi = 0$ represents deep-sleep storage (e.g., NVMe/EFS snapshotted).

### Non-Linear Restitution Energy
Building upon the base definition $E_{cs}$, the energy required to restore an AI container from an arbitrary state $\psi$ back to full readiness is formulated non-linearly:

$$ E_{restore}(\psi) = E_{cs} \cdot (1 - \psi)^{\alpha} $$

where $\alpha > 1$ represents a hardware-specific acceleration factor. This captures the efficiency of restoring memory-mapped tensor caches via mechanisms like CRIU (Checkpoint/Restore In Userspace) over a brutal full cold-boot from object storage.

### Decay Power Function
Conversely, developing upon the base $P_{idle}$, the idle power consumption at state $\psi$ decays according to:

$$ P_{idle}(\psi) = P_{peak\_idle} \cdot \psi^{\beta} $$

where $\beta < 1$ indicates that marginally reducing the readiness state (e.g., evicting CPU caches but keeping main GPU memory allocated) yields massive early power savings before hitting the deep-sleep floor.

Our green orchestrator dynamically solves for the continuous decay path $\psi^*(t)$ during idle intervals to minimize the time-integral of power, guaranteeing that $E_{restore}$ remains within the bounds of a renewable energy surplus budget.

---

## 4. Carbon-Momentum ($\mathcal{M}_c$) Scheduling

Unlike HTTP web requests, AI micro-batches possess a unique characteristic: they are highly deferrable but require massive execution burst capability. Current carbon-aware schedulers merely trigger execution when the absolute carbon intensity ($C(t)$ in $gCO_2/kWh$) drops below a predefined static threshold.

We introduce a novel **Carbon-Momentum** scheduling algorithm inspired by physical pressure dynamics and gradient descent. Our system accumulates "Compute Pressure" $\Pi(t)$ as unprocessed training data batches queue up. Execution is not triggered by a static threshold, but by a continuous probability function prioritizing the **gradient** (time-derivative) of carbon intensity:

$$ \mathbb{P}(\text{burst exec at } t) = \sigma \left( w_1 \cdot \Pi(t) - w_2 \cdot \frac{dC(t)}{dt} - w_3 \cdot C(t) \right) $$

**Detail & Insight:** By weighting the time-derivative $\frac{dC(t)}{dt}$ negatively, the system structurally anticipates valleys in grid intensity. It triggers massive serverless scaling bursts *while the grid is actively getting greener* (e.g., catching wind or solar overproduction spikes in real-time) rather than reacting after grid batteries have already normalized the spot costs.

---

## 5. Sub-Millisecond Preemption via Checkpoint-Momentum

Because Carbon-Momentum scheduling yields highly irregular and abrupt execution bursts, the traditional concept of training "epochs" fails. A serverless training pipeline might be provisioned with thousands of concurrent GPU functions for 4 minutes during a wind-energy spike, and zero for the next hour.

To support this aggressive elasticity, we detail a zero-copy, ephemeral checkpointing architecture. Instead of pushing monolithic `.pt` weights to S3 at epoch boundaries, the system computes an intra-batch gradient momentum snapshot. 

Upon receiving a preemption interrupt (driven by a sudden spike in $C(t)$), the container dumps the exact CUDA kernel state variables directly to NVMe-backed EFS using direct memory access (DMA) within 50ms, freezing the model mid-forward-pass. 

When the Carbon-Momentum function triggers again, the containers resume at a $\psi=0.85$ readiness state, continuing the tensor contractions with theoretically zero dropped FLOPs and no redundant data loading phases.
