# Linear Algebra

## 1. Vectors

A **vector** is an ordered array of numbers representing a point or direction in space.

$$\mathbf{v} = \begin{bmatrix} v_1 \\ 
v_2 \\ \vdots \\ v_n \\ \end{bmatrix}
\in \mathbb{R}$$

- **Row vector**: $\mathbf{v}^T = [v_1, v_2, \ldots, v_n]$
- **Column vector** (default convention)
- **Dot product**: $\mathbf{u} \cdot \mathbf{v} = \sum_{i=1}^n u_i v_i = \mathbf{u}^T \mathbf{v}$

---

## 2. Matrices

A **matrix** is a 2D array of numbers of shape $m \times n$ (m rows, n columns).

$$A = \begin{bmatrix} a_{11} & a_{12} & \cdots & a_{1n} \\ a_{21} & a_{22} & \cdots & a_{2n} \\ \vdots & & \ddots & \vdots \\ a_{m1} & a_{m2} & \cdots & a_{mn} \end{bmatrix}$$

### Common Matrix Types

| Type | Description |
|------|-------------|
| Square matrix | $m = n$ |
| Identity matrix ($I$) | 1s on diagonal, 0s elsewhere |
| Diagonal matrix | Non-zero values only on diagonal |
| Symmetric matrix | $A = A^T$ |
| Orthogonal matrix | $A^T A = I$, so $A^{-1} = A^T$ |

---

## 3. Matrix Operations

### Transpose
Flip rows and columns: $(A^T)_{ij} = A_{ji}$

### Addition / Subtraction
Element-wise; requires same shape:
$$(A + B)_{ij} = A_{ij} + B_{ij}$$

### Scalar Multiplication
$$(cA)_{ij} = c \cdot A_{ij}$$

### Matrix Multiplication
For $A \in \mathbb{R}^{m \times k}$ and $B \in \mathbb{R}^{k \times n}$, result $C \in \mathbb{R}^{m \times n}$:
$$C_{ij} = \sum_{l=1}^k A_{il} \cdot B_{lj}$$

> **Note**: Matrix multiplication is **not commutative** — $AB \neq BA$ in general.

### Inverse
For square matrix $A$: $A^{-1}$ satisfies $A A^{-1} = I$.
- Exists only if $A$ is **non-singular** (i.e., $\det(A) \neq 0$).

### Hadamard (Element-wise) Product
$$(A \odot B)_{ij} = A_{ij} \cdot B_{ij}$$

---

## 4. Vector Norms

A **norm** measures the "size" or "length" of a vector. For $\mathbf{v} \in \mathbb{R}^n$:

### $L^p$ Norm (General)

$$\|\mathbf{v}\|_p = \left( \sum_{i=1}^n |v_i|^p \right)^{1/p}$$

### $L^1$ Norm (Manhattan / Taxicab)

$$\|\mathbf{v}\|_1 = \sum_{i=1}^n |v_i|$$

### $L^2$ Norm (Euclidean)

$$\|\mathbf{v}\|_2 = \sqrt{\sum_{i=1}^n v_i^2}$$

This is the standard "straight-line" length of a vector.

### $L^\infty$ Norm (Max Norm)


$$\|\mathbf{v}\|_\infty = \max_i |v_i|$$

### Norm Properties

1. **Non-negativity**: $\|\mathbf{v}\| \geq 0$, and $\|\mathbf{v}\| = 0 \Leftrightarrow \mathbf{v} = \mathbf{0}$
2. **Scalar scaling**: $\|c\mathbf{v}\| = |c| \cdot \|\mathbf{v}\|$
3. **Triangle inequality**: $\|\mathbf{u} + \mathbf{v}\| \leq \|\mathbf{u}\| + \|\mathbf{v}\|$

---

## 5. Distance Metrics

### Euclidean Distance ($L^2$)

The straight-line distance between two points $\mathbf{u}, \mathbf{v} \in \mathbb{R}^n$:

$$d_E(\mathbf{u}, \mathbf{v}) = \|\mathbf{u} - \mathbf{v}\|_2 = \sqrt{\sum_{i=1}^n (u_i - v_i)^2}$$

- Sensitive to large differences in individual dimensions.
- Used in: KNN, K-Means, PCA.

### Manhattan Distance ($L^1$)

The distance travelling only along axes ("city block" distance):

$$d_M(\mathbf{u}, \mathbf{v}) = \|\mathbf{u} - \mathbf{v}\|_1 = \sum_{i=1}^n |u_i - v_i|$$

- More robust to outliers than Euclidean distance.
- Used in: sparse data, LASSO regularization, robust statistics.

### Quick Comparison

| Property | Euclidean ($L^2$) | Manhattan ($L^1$) |
|---|---|---|
| Formula | $\sqrt{\sum (u_i - v_i)^2}$ | $\sum \|u_i - v_i\|$ |
| Sensitivity to outliers | Higher | Lower |
| Geometry | Circle (unit ball) | Diamond (unit ball) |
| Common use | General distance, KNN | Sparse features, LASSO |
