cudaError_t cudaMalloc(void **dev_Ptr, size_t size)
CUDA runtime:
- Ask GPU to allocate size bytes in device global memory (GPU)
- Return the address of the allocate address: curr_address
- assign that address to dev_Ptr  -> &dev_Ptr = curr_address{]}

thread
- each thread is responsible for computing one cell of matrix C
                                              (i.e. one cell C[row][col])

blockDim(1024*1024) // (1024, 1, 1) 1 dimension only
- Total threads/blocks = 1024
- Threads are numbered linearly
- The kernel manual map these thread in to (row, col) using division & module: 
  - uint row    = blockIdx.y * BLOCK_SIZE + (threadIdx.x / BLOCK_SIZE);
  - uint column = blockIdx.x * BLOCK_SIZE + (threadIdx.x % BLOCK_SIZE);

gridDim((K+blocksize - 1) / blocksize, (m + blockSize-1) / blockSize) // gridDim.x = ceil ( x/32), gridDim.y = ceil ( y/32), z= 1
- 2 demensional 1024 * 1024
- gridDim.x covers column C (K)
- gridDim.y covers row C (M)

Quick visual
Output matrix C: M rows × K columns

           columns (K)
             ┌───────────────┐
             │               │
   rows   y  │   gridDim.y   │
   (M)   ───►│   blocks vert.│
             │               │
             └───────┬───────┘
                     │
                     ▼
               gridDim.x
             blocks horiz.