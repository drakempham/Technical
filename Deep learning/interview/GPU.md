# GPU Architecture Notes

Based on the Hopper H100 architecture diagram in `Deep learning/rebuilding-cublas/img/GPU-architecture.jpg`.

## Big picture

The GPU hierarchy in the diagram is:

`GPU chip` -> `GPCs` -> `SMs` -> `SMSP partitions` -> execution units and local memory

The key idea is:

- `HBM3 / global memory` is large but slow
- `L2 cache` is shared and faster
- `Shared memory / L1` is small but very fast
- `Registers` are the fastest and private to threads

For compute:

- `FP32 / INT32 cores` do normal arithmetic
- `Tensor Cores` do matrix-heavy operations much faster
- `TMA + shared memory + Tensor Cores` are a big part of Hopper performance

## Quick memory hierarchy table

| Property | L1 / Shared Memory | L2 Cache | HBM3 (VRAM) |
| --- | --- | --- | --- |
| Location | Inside each `SM` | Shared across the whole GPU | Off-chip memory |
| Capacity | About `164 KB` per `SM` in the note | `50 MB` | Up to `80 GB` |
| Latency | Lowest, very fast | Medium | Highest, slowest |
| Purpose | Hold data currently being computed | Reduce HBM traffic and share reusable data | Store large models, activations, dataset batches |

## Main architecture keywords

### `H100`

NVIDIA Hopper-generation data-center GPU.

### `Die`

The physical silicon chip.

### `GPC` - Graphics Processing Cluster

A mid-level cluster that groups several `SM`s together.

### `SM` - Streaming Multiprocessor

The main compute block of an NVIDIA GPU. CUDA thread blocks are scheduled onto `SM`s.

Each `SM` contains:

- execution units
- `Tensor Cores`
- register files
- instruction logic
- access to `shared memory` and `L1`

### `SMSP partition`

A sub-partition inside an `SM`. Hopper divides an `SM` into multiple partitions so it can schedule and execute more work in parallel.

## Instruction and compute terms

### `L1 Instruction Cache`

A very small, very fast cache that stores instructions close to the execution units.

### `Instruction Queue`

A queue of ready instructions waiting to be issued to hardware units.

### `Register File`

Very fast on-chip storage used by threads for temporary values. Registers are the fastest storage available to a thread, but they are limited.

### `FP32 cores`

Units specialized for `32-bit floating-point` arithmetic.

### `INT32 cores`

Units specialized for `32-bit integer` arithmetic such as indexing and address calculations.

### `FP64` / `DP64`

Units for `64-bit floating-point` arithmetic, more common in scientific and HPC workloads.

### `Tensor Cores`

Specialized hardware for matrix multiply and tensor operations. These are critical for deep learning performance.

### `4th-Gen Tensor Cores`

The Hopper generation of `Tensor Cores`, optimized for high-throughput matrix computation.

### `SFU` - Special Function Unit

Handles special math functions such as `sin`, `cos`, `exp`, reciprocal, and similar operations.

### `LD/ST units`

`Load/Store` units responsible for moving data between registers and memory.

## Memory hierarchy terms

### `Shared Memory`

Programmer-managed on-chip memory shared by threads in the same block. It is much faster than global memory and is commonly used for tiling and data reuse.

### `L1 Data Cache`

Fast cache close to the `SM` for recently accessed data.

### `256 KB Shared Memory / L1 Data Cache (Configurable)`

In Hopper, part of this on-chip memory can be configured between shared memory and `L1` cache usage.

### `SMEM`

Short for `shared memory`.

### `GMEM`

Short for `global memory`, meaning the large GPU memory space backed by `HBM`.

### `L2 Cache`

A larger cache shared across many `SM`s. It sits between the compute units and `HBM3`, reducing slow memory traffic.

### `50 MiB L2 Cache`

The total `L2` cache shown in the diagram.

### `Constant Memory`

A small read-only memory space optimized for broadcast when many threads read the same value.

### `CMA (64 KiB)`

The constant-memory area shown in the diagram.

### `HBM3`

`High Bandwidth Memory` generation 3. This is the large off-chip GPU memory.

### `HBM3 Stack`

One stacked memory package. The diagram labels one stack as `16 GB`.

## Data movement and synchronization

### `DMA`

`Direct Memory Access`, meaning hardware-assisted data movement without using normal arithmetic units for every copy step.

### `Synchronization`

Mechanisms that coordinate computation and memory movement so data is ready before the next stage uses it.

### `TMA` - Tensor Memory Accelerator

A Hopper hardware feature that efficiently moves multi-dimensional tensor tiles between `GMEM` and `SMEM`.

This is especially useful in high-performance kernels such as:

- `GEMM`
- attention
- tiled matrix kernels

### `Data swizzling`

A rearrangement of data layout during transfer to reduce bank conflicts and improve access efficiency.

### `Partition Crossbar`

An internal routing fabric used to move data between GPU partitions and memory/cache structures.

### `Near partition` / `Far partition`

These notes refer to locality. Access to a nearer partition is usually lower-latency than access to a farther partition.

## Interconnect and system terms

### `NVLink`

NVIDIA's high-bandwidth GPU-to-GPU interconnect.

### `NVLink Switch Fabric`

A switching fabric that connects multiple GPUs over `NVLink`.

### `PCIe`

The standard host interconnect used by some GPU variants.

### `SXM`

NVIDIA's server GPU module form factor, usually with higher power limits and stronger interconnect support than `PCIe`.

## Performance vocabulary

### `GEMM`

`General Matrix Multiply`, the core operation behind dense layers, projections, and many deep learning kernels.

### `Latency`

How long one operation or one memory access takes.

### `Bandwidth`

How much data can be transferred per second.

### `Cache`

A smaller, faster memory that stores recently used data to reduce slow memory access.

### `Coherent cache`

A cache system that keeps data views consistent across different parts of the GPU according to the architecture's coherence rules.

### `Global atomic`

Hardware support for atomic read-modify-write operations on shared/global data, such as `atomicAdd`.

## Practical mental model

For CUDA and cuBLAS-style kernels, the important flow is:

`HBM3 -> L2 -> Shared Memory -> Registers -> Tensor Cores`

That is the basic performance story:

1. move tiles from `HBM3` into faster levels
2. reuse them from `L2` and `Shared Memory`
3. load fragments into registers
4. compute with `Tensor Cores`
5. synchronize and write results back efficiently

## Most important terms for GEMM and FlashAttention

- `SM`
- `Tensor Cores`
- `Register File`
- `Shared Memory`
- `TMA`
- `L2 Cache`
- `HBM3`
- `DMA`
- `Synchronization`
