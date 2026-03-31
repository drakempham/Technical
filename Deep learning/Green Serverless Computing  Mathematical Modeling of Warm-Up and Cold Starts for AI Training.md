# Green Serverless Computing: Mathematical Modeling of Warm-Up and Cold Starts for AI Training

## Overview

Green serverless computing seeks to preserve the elasticity and simplicity of serverless platforms while explicitly optimizing for energy efficiency — a critical concern for AI training workloads that are both compute- and energy-intensive. A key determinant of the net energy impact is how the system handles **warm-up** (keep-alive) and **cold starts** for functions and containers.

This document develops and interprets mathematical models for:
- cold-start overhead energy,
- warm-pool energy and break-even thresholds,
- utilization and queueing behavior,
- and practical tuning strategies for AI-oriented serverless workloads.

---

## Warm Start vs Cold Start

In serverless platforms, each function invocation is served either by a **warm** instance (already initialized and idle) or a **cold start** (requiring full provisioning and initialization).

### What happens during a cold start

A cold start involves four sequential phases:

```
[1] Provision container          200–500ms   allocate VM/container, network, storage
        ↓
[2] Load runtime + dependencies  500ms–2s    Python, torch, numpy, transformers...
        ↓
[3] Load model weights           2–30s       read from S3 → RAM/GPU
        ↓                                    (BERT=400MB, GPT-2=1.5GB, LLaMA=13GB+)
[4] JIT compile / warm-up        1–5s        PyTorch compiles CUDA kernels on first run
        ↓
    READY                                    Total: 5–40 seconds
```

### What happens during a warm start

A warm instance already completed all four phases. On the next invocation, it skips directly to execution:

```
Request → [WARM instance] → Execute (~25ms)
```

### The fundamental trade-off

| | Warm Instance | Cold Start |
|---|---|---|
| Latency | ~ms | 5–40s |
| Energy per request | Low (no init overhead) | High (init cost) |
| Background energy | Consumes idle power 24/7 | Zero when not running |
| Best for | High-frequency, latency-sensitive | Infrequent, deferrable tasks |

Longer keep-alive times reduce cold-start frequency but increase background idle energy. Shorter keep-alive times save idle energy but increase cold-start frequency. The goal is to find the **optimal balance**.

---

## Energy Model for Serverless Functions

For each invocation, total energy decomposes as:

$$
E_{\text{total}} = E_{\text{work}} + E_{\text{overhead}} \quad (1)
$$

where `E_work` is the energy for useful computation, and `E_overhead` aggregates energy from cold starts, container lifecycle operations, and idle intervals.

If a task requires $F$ floating-point operations (FLOPs) on hardware with energy efficiency $\eta$ (FLOPs per joule), the ideal work energy is:

$$
E_{\text{work}} = \frac{F}{\eta} \quad (2)
$$

For modern AI accelerators, $\eta$ is several orders of magnitude higher than CPUs, so the primary challenge becomes keeping `E_overhead` small relative to `E_work` — especially for short-running, fine-grained functions where the init cost dominates.

---

## Mathematical Model of Cold-Start Overhead

### Energy cost per cold start

Let:
- $T_{\text{cs}}$: cold-start duration (seconds),
- $P_{\text{cs}}$: average power draw during initialization (watts).

The **energy overhead per cold start** is:

$$
E_{\text{cs}} = P_{\text{cs}} \cdot T_{\text{cs}} \quad (3)
$$

### Total cold-start energy across N invocations

For $N$ total invocations, if a fraction $f$ of them experience cold starts, the total cold-start energy is:

$$
E_{\text{cs,total}} = f \cdot N \cdot E_{\text{cs}} = f \cdot N \cdot P_{\text{cs}} \cdot T_{\text{cs}} \quad (4)
$$

### Relative cold-start overhead

Let `Ē_work` be the average work energy per invocation. The **relative overhead** is:

$$
\rho_{\text{cs}} = \frac{E_{\text{cs,total}}}{N \cdot \bar{E}_{\text{work}}} = f \cdot \frac{E_{\text{cs}}}{\bar{E}_{\text{work}}} \quad (5)
$$

**Key insight:** For short or lightweight functions (small `Ē_work`), the overhead ratio `ρ_cs` becomes large — cold-start energy dominates over useful work energy. This is the core problem with naive fine-grained serverless AI workloads.

### Two levers to reduce cold-start impact

**Lever 1 — Reduce `E_cs` (make each cold start cheaper):**

| Technique | What it reduces | Effect |
|---|---|---|
| Lighter runtimes, smaller packages | `T_cs` | Less to load |
| Quantization (INT8/FP16) | `T_cs` | Model 2–4× smaller |
| ONNX export | `T_cs` + removes JIT step | Pre-compiled graph |
| EFS instead of S3 | `T_cs` | 8× faster model loading |
| Container snapshotting (CRIU) | `T_cs` | Restore from checkpoint |

**Lever 2 — Reduce `f` (make cold starts less frequent):**

- Maintain warm pools (pre-initialized instances)
- Increase keep-alive (TTL) duration
- Route requests to reuse existing containers

---

## Warm-Pool Energy and Break-Even Analysis

To reduce cold starts, platforms keep a pool of pre-initialized (warm) instances. This trades idle energy for lower cold-start frequency.

### Warm-pool energy cost

Let:
- $k$: number of warm instances,
- $P_{\text{idle}}$: idle power per warm instance (watts),
- $T$: time interval of interest (seconds).

The **energy cost of keeping `k` instances warm** over `T`:

$$
E_{\text{warm}} = k \cdot P_{\text{idle}} \cdot T \quad (6)
$$

### Expected cold-start cost without a warm pool

With mean arrival rate `λ` (invocations per second) and no warm pool (all invocations experience cold starts):

$$
E_{\text{cs,no-pool}} \approx \lambda \cdot T \cdot E_{\text{cs}} \quad (7)
$$

### Break-even condition

The warm pool is **energy-efficient** when its cost is less than the cold-start cost it avoids:

$$
E_{\text{warm}} \le E_{\text{cs,no-pool}}
$$

$$
k \cdot P_{\text{idle}} \cdot T \le \lambda \cdot T \cdot E_{\text{cs}}
$$

Simplifying (the `T` cancels):

$$
\boxed{ \lambda \ge \lambda_{\text{min}} = \frac{k \cdot P_{\text{idle}}}{E_{\text{cs}}} } \quad (8)
$$

**Interpretation:**
- When `λ ≥ λ_min`: keeping `k` warm instances saves energy — cold starts are more expensive than idle power.
- When `λ < λ_min`: it is greener to shut down and accept cold starts — idle power exceeds the cost of occasional cold starts.

### Visualizing the break-even

```
Energy
  │
  │  E_cs,no-pool = λ·T·E_cs       ← grows with λ (more cold starts)
  │         /
  │        /
  │       /  ← break-even point at λ_min
  │______/___________  E_warm = k·P_idle·T   ← flat line (fixed idle cost)
  │
  └─────────────────────────────────────► λ (arrival rate)
         λ_min

  Left of λ_min:  cold start cheaper → shut down warm pool
  Right of λ_min: warm pool cheaper  → keep instances warm
```

---

## Queueing Model: Utilization and Scaling

Serverless platforms can be approximated by **M/M/c queues** where:
- arrivals follow a Poisson process with rate $\lambda$,
- each instance serves at rate $\mu$ (exponential service times),
- there are $c$ concurrent warm instances.

### Utilization per instance

$$
\rho = \frac{\lambda}{c \cdot \mu} \quad (9)
$$

The system is stable only when `ρ < 1`. If `ρ → 1`, the queue grows unbounded and latency spikes.

### Total energy over window T

Energy has two components:

$$
E_{\text{total}}(c) = \underbrace{e_{\text{dyn}} \cdot \lambda \cdot T}_{\text{dynamic (active compute)}} + \underbrace{e_{\text{idle}} \cdot c \cdot T}_{\text{static (idle warm instances)}} \quad (10)
$$

where:
- `e_dyn` = energy per unit of service time (active compute),
- `e_idle` = idle energy per instance per unit time.

**Green optimization:** choose the smallest `c` that satisfies latency constraints, then check that `E_total(c)` is minimized. There is no benefit to keeping more warm instances than needed to meet the SLO.

### Warm-pool sizing with latency SLO

For user-facing functions with tail-latency requirements (e.g., p99 < 500ms):

1. Use M/M/c formulas to compute tail latency as a function of `c` and `λ`, `μ`.
2. Choose the **smallest** `c` such that tail latency meets the SLO.
3. Plug that `c` into equation (10) to evaluate energy.
4. Confirm no smaller `c` satisfies the SLO.

This combines performance modeling with energy modeling, avoiding both over-provisioning (waste) and under-provisioning (SLO violations).

---

## Batching: Amortizing Cold-Start Cost

If cold-start cost `E_cs` is amortized over `B` samples within a single invocation, the overhead per sample becomes:

$$
\rho_{\text{cs,per-sample}} = f \cdot \frac{E_{\text{cs}} / B}{\bar{E}_{\text{work,per-sample}}} \quad (11)
$$

Increasing `B` (batch size) directly reduces `ρ_cs_per_sample`, making each cold start cheaper relative to the useful work it enables.

**Example:** A cold start costs `E_cs = 12 J`. For `B = 1` sample: overhead = 12 J/sample. For `B = 100` samples: overhead = 0.12 J/sample — a **100× reduction** in overhead per unit of useful work.

**Trade-off:** Larger `B` increases per-invocation latency and memory usage, so `B` must be bounded by SLO and hardware constraints.

---

## Concrete Example: Tuning Warm-Up for a Data-Preprocessing Function

Consider a Python-based data-preprocessing serverless function (AWS Lambda) that imports ML libraries and processes one batch in ~200ms.

**Profiled parameters:**

| Parameter | Value |
|---|---|
| Cold-start duration `T_cs` | 400 ms |
| Average cold-start power `P_cs` | 30 W |
| Idle power per warm instance `P_idle` | 5 W |
| Execution time per batch | 200 ms |

**Step 1 — Compute cold-start energy per event (from eq. 3):**

$$
E_{\text{cs}} = P_{\text{cs}} \cdot T_{\text{cs}} = 30 \times 0.4 = 12 \text{ J}
$$

**Step 2 — Compute break-even arrival rate for k=1 warm instance (from eq. 8):**

$$
\lambda_{\text{min}} = \frac{k \cdot P_{\text{idle}}}{E_{\text{cs}}} = \frac{1 \times 5}{12} \approx 0.42 \text{ req/s}
$$

**Step 3 — Decision rule:**

```
Monitor λ(t) over a 5-minute sliding window:

  λ(t) ≥ 0.42 req/s  →  keep 1 warm instance  (saves energy + reduces latency)
  λ(t) < 0.42 req/s  →  scale to zero          (idle energy > cold-start cost)
```

**Implementation options:**
- **AWS Lambda**: dynamically adjust `provisioned_concurrent_executions` based on moving-average `λ(t)`
- **Knative / OpenFaaS**: set `min-scale` and `scale-to-zero` parameters driven by this `λ_min` threshold

---

## Warm-Up Strategies: From Naive to Optimized

### Naive approach (anti-patterns)

- Always keep many instances warm regardless of traffic → wastes idle energy
- Never keep any instances warm → every request pays cold-start cost
- Use per-sample granularity (one function call per sample) → cold-start overhead dominates

### Simple analytical policy

1. Profile each function: estimate `E_cs`, `P_idle`, and `λ(t)`
2. Compute `λ_min = k · P_idle / E_cs`
3. Maintain `k` warm instances only when `λ(t) ≥ λ_min`; otherwise scale to zero

### Scheduled warm-up pings (lightweight alternative)

For functions where full provisioned concurrency is too costly, use a scheduled ping to keep containers alive:

```python
# Cron job: ping every 5 minutes to prevent container termination
def handler(event, context):
    if event.get("action") == "warmup":
        return {"status": "warm"}   # return immediately, skip model work

    # Normal execution path
    model = get_cached_model()      # already loaded from previous warm start
    return model.predict(event["data"])
```

### Global-scope model caching

Load model weights once at container startup, reuse across all warm invocations:

```python
model = None  # loaded once per container lifetime

def get_model():
    global model
    if model is None:
        model = load_model()    # only on cold start
    return model

def handler(event, context):
    m = get_model()             # instant on warm start
    return m.predict(event["data"])
```

### Model optimization to minimize cold-start duration

| Technique | Cold-start time | Notes |
|---|---|---|
| FP32 (baseline) | ~8–12s | Default PyTorch |
| INT8 Quantization | ~2–3s | 4× smaller model |
| ONNX export | ~1–2s | Pre-compiled, no JIT |
| EFS mount (vs S3) | ~0.8–1s | Local filesystem speed |
| Container snapshot (CRIU) | ~0.3–0.5s | Restore memory state directly |

**Combined effect:**

```
Baseline cold start:  [deps 1.5s] + [S3 load 8s]  + [JIT 3s]   = 12.5s
After optimization:   [deps 0.3s] + [EFS 0.8s]    + [ONNX 0s]  =  1.1s
                                                                    ↑ 11× faster
```

---

## Checkpoint-Resume: Protecting Training Progress

For AI **training** (stateful, long-running), cold starts mid-training can destroy progress. The solution is periodic checkpointing to persistent storage:

```python
import torch, boto3

s3 = boto3.client('s3')

def save_checkpoint(model, optimizer, epoch):
    state = {
        'epoch': epoch,
        'model_state_dict': model.state_dict(),
        'optimizer_state_dict': optimizer.state_dict(),
    }
    torch.save(state, '/tmp/checkpoint.pt')
    s3.upload_file('/tmp/checkpoint.pt', 'my-bucket', 'checkpoint_latest.pt')

def load_checkpoint(model, optimizer):
    try:
        s3.download_file('my-bucket', 'checkpoint_latest.pt', '/tmp/checkpoint.pt')
        state = torch.load('/tmp/checkpoint.pt')
        model.load_state_dict(state['model_state_dict'])
        optimizer.load_state_dict(state['optimizer_state_dict'])
        return state['epoch'] + 1
    except:
        return 0   # start from scratch

def train(model, optimizer, dataloader):
    start_epoch = load_checkpoint(model, optimizer)
    for epoch in range(start_epoch, 100):
        for batch in dataloader:
            training_step(model, batch)
        save_checkpoint(model, optimizer, epoch)   # save after every epoch
```

**Result:** If the container dies at epoch 47, the next container resumes from epoch 47 — zero progress lost.

---

## Practical Guidelines

1. **Profile first.** Measure `T_cs`, `P_cs`, `P_idle`, and `λ(t)` per function before making warm-pool decisions.

2. **Compute break-even thresholds.** Use equation (8) to determine `λ_min` and dynamically toggle provisioned concurrency.

3. **Minimize cold-start duration.** Apply quantization, ONNX export, EFS mounting, and global-scope caching to reduce `T_cs` and `P_cs`.

4. **Batch aggressively.** Increase invocation batch size `B` to amortize cold-start cost across more useful work (equation 11).

5. **Cache in global scope.** Load model weights once per container lifetime; never reload inside the handler function.

6. **Checkpoint training state.** Save to S3 after every epoch so cold starts mid-training have zero cost on progress.

7. **Size warm pools with queueing models.** Use M/M/c analysis to find the minimum `c` that satisfies latency SLOs, then verify energy with equation (10).

8. **Adapt dynamically.** Workloads change — recompute `λ(t)` and update warm-pool size continuously, or use autoscaler policies tied to the `λ_min` threshold.
