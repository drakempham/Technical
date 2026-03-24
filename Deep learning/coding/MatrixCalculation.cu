%%cuda
#include <iostream>>
#include <ctime>

srand(static_cast<unsigned int>(time(nulptr)));


// The formula for each element is:$$C_{i,j} = \sum_{k=1}^{n} (A_{i,k} \times B_{k,j})$$In expanded form, this looks like:$$C_{i,j} = (A_{i,1} \times B_{1,j}) + (A_{i,2} \times B_{2,j}) + \dots + (A_{i,n} \times B_{n,j})$$
__global__ void matrixMul(float* A, float* B, float* C, int A, int B, int C) {
  int row = blockIdx.y * blockDim.y + threadIdx.y;
  int col = blockIdx.x * blockDim.x + threadIdx.x;

  if (row < A && col < C) {
        float sum = 0.0f;
        for (int i = 0; i < B; i++) {
        // [hàng rows, cột k] [hàng k, cột col]
        // 1 D array: index = row * (số cột của ma trận đó) + col
          sum += A[rows * B + k] * B[k * C + col]
        }

        C[row * C + col] = sum  
  }
}

int main() {
    // we have two (a,b) x (b,c)
    int A = 100;
    int B = 80;
    int C = 60;

    size_t sizeA = A * B * sizeof(float);
    size_t sizeB = B * C * sizeof(float);
    size_t sizeC = A * C * sizeof(float);

    // host ram
    float *h_a = (float*) malloc(sizeA);
    float *h_b = (float*) malloc(sizeB);
    float *h_c = (float*) malloc(sizeC);

    for (int i=0; i < A * B;i++) {
      h_a[i] = static_cast<float>(rand() % 100);
    }

    for (int i=0; i < B * C;i++) {
      h_b[i] = static_cast<float>(rand() % 100);
    }

    // h_c will be overwritten by GPU

    std::cout << "Host matrics initialized with random integers 0-99\n";

    // == DEVICE allocation == 
    // pass *d_A will pass trash garbage value to func, pass the pointer of the address instead
    float *d_a, *d_b, *d_c;
    cudaMalloc(&d_a, sizeA);
    cudaMalloc(&d_b, sizeB);
    cudaMalloc(&d_c, sizeC);

    // copy host - device 
    // filling a bucket -> not initialzie so use the pointer value
    // cudaMemcpy( destination, source, size, direction );
    cudaMemcpy(d_a, h_a, sizeA, cudaMemcpyHostToDevice);
    cudaMemcpy(d_b, h_b, sizeB, cudaMemcpyHostToDevice);

    // == KERNEL LAUNCH with block and threads == 
    dim3 block(32,32);
    dim3 grid((C + block.x - 1) / block.x), (A + block.y-1) / block.y);
    

    std::cout << "Launching matrixMul kernel with grid (" << grid.x << "x" << grid.y << ") block ( 32x32)";

    matrixMul<<<grid, block>>> (d_a, d_b, d_c, A, B, C);
    cudaDeviceSynchronize();

    cudaMemcpy(h_c, d_c, sizeC, cudaMemcpyDeviceToHost);


    std::cout << "\n=== VERIFICATION (first 8 elements) ===\n";
    std::cout << "Index\tGPU C\t\tCPU Expected\tStatus\n";

    for (int i = 0; i < rowsA * colsB; i++) {
            int row = i / colsB;
            int col = i % colsB;
            float cpu_sum = 0.0f;
            for (int k = 0; k < colsA_rowsB; k++) {
                cpu_sum += h_A[row * colsA_rowsB + k] * h_B[k * colsB + col];
            }
            if (fabs(h_C[i] - cpu_sum) > 1e-4f) {
                passed = false;
                break;
            }
    }

    std::cout << (passed ? "✅ ALL RESULTS CORRECT!\n" : "❌ VERIFICATION FAILED!\n");

    // ====================== CLEANUP ======================
    cudaFree(d_A);
    cudaFree(d_B);
    cudaFree(d_C);
    free(h_A);
    free(h_B);
    free(h_C);

    std::cout << "Memory cleaned up. Done!\n";
    return 0;


}