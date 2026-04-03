# MML Core Knowledge — Toán nền tảng cho Machine Learning

> **Nguồn:** Mathematics for Machine Learning (Deisenroth, Faisal, Ong)
> **Mục đích:** Tham chiếu nhanh các khái niệm cốt lõi hay dùng nhất trong ML thực chiến.

---

## Bản đồ tổng thể

```
Ch.2 Linear Algebra ──→ Ch.3 Analytic Geometry ──→ Ch.4 Matrix Decompositions
         │                                                      │
         └──────────────────────┬───────────────────────────────┘
                                ↓
                     Ch.5 Vector Calculus
                                ↓
              Ch.6 Probability & Distributions
                                ↓
                  Ch.7 Continuous Optimization
```

---

## Ch.2 — Linear Algebra

### Vector & Matrix

| Khái niệm | Ký hiệu | Ý nghĩa trong ML |
|:----------|:--------|:-----------------|
| Vector cột | $\mathbf{x} \in \mathbb{R}^n$ | Input feature, embedding |
| Dot product | $\mathbf{u}^\top \mathbf{v} = \sum u_i v_i$ | Tích vô hướng, tính similarity |
| Ma trận | $\mathbf{W} \in \mathbb{R}^{m \times n}$ | Weight matrix trong neural net |
| Nhân ma trận | $(\mathbf{AB})_{ij} = \sum_k A_{ik}B_{kj}$ | Forward pass, linear transform |
| Transpose | $(\mathbf{A}^\top)_{ij} = A_{ji}$ | Backprop, attention |
| Nghịch đảo | $\mathbf{A}^{-1}\mathbf{A} = \mathbf{I}$ | Tồn tại khi $\det(\mathbf{A}) \neq 0$ |

**Loại ma trận đặc biệt:**

| Loại | Tính chất | Dùng trong |
|:-----|:----------|:-----------|
| Symmetric | $\mathbf{A} = \mathbf{A}^\top$ | Covariance matrix, Hessian |
| Orthogonal | $\mathbf{A}^\top\mathbf{A} = \mathbf{I}$, tức $\mathbf{A}^{-1} = \mathbf{A}^\top$ | Rotation, SVD components |
| Positive Definite (PD) | $\mathbf{x}^\top\mathbf{A}\mathbf{x} > 0$ với mọi $\mathbf{x} \neq 0$ | Covariance matrix, Cholesky |
| Diagonal | Chỉ có giá trị trên đường chéo | Scaling, eigendecomp đơn giản |

### Không gian & Rank

- **Column space** (image): tập hợp tất cả $\mathbf{Ax}$ — chiều không gian đầu ra
- **Null space** (kernel): tập $\mathbf{x}$ sao cho $\mathbf{Ax} = 0$
- **Rank**: số chiều độc lập tuyến tính → $\text{rank}(\mathbf{A}) + \dim(\text{null}) = n$

> **ML link:** Rank thiếu (rank-deficient) → ma trận không nghịch đảo được → linear regression dễ overfit / có vô số nghiệm.

### Norms

$$\|\mathbf{x}\|_p = \left(\sum_i |x_i|^p\right)^{1/p}$$

| Norm | Tên | ML use |
|:-----|:----|:-------|
| $\|\mathbf{x}\|_1$ | Manhattan | LASSO regularization, sparse features |
| $\|\mathbf{x}\|_2$ | Euclidean | L2 regularization, distance, MSE |
| $\|\mathbf{x}\|_\infty$ | Max | Clipping, robustness |
| $\|\mathbf{A}\|_F = \sqrt{\sum_{ij} A_{ij}^2}$ | Frobenius | Weight decay trên ma trận |

---

## Ch.3 — Analytic Geometry

### Inner Product & Góc

$$\langle \mathbf{x}, \mathbf{y} \rangle = \mathbf{x}^\top \mathbf{y} = \|\mathbf{x}\|\|\mathbf{y}\|\cos\theta$$

$$\cos\theta = \frac{\mathbf{x}^\top \mathbf{y}}{\|\mathbf{x}\|_2\|\mathbf{y}\|_2} \quad \text{(Cosine similarity)}$$

> **ML link:** Embeddings dùng cosine similarity để đo độ tương đồng semantic. Hai vector vuông góc ($\cos\theta = 0$) → hoàn toàn không liên quan.

### Projection

Chiếu $\mathbf{x}$ lên subspace $U$ có basis $\mathbf{B}$:

$$\pi_U(\mathbf{x}) = \mathbf{B}(\mathbf{B}^\top\mathbf{B})^{-1}\mathbf{B}^\top \mathbf{x}$$

- **Projection matrix**: $\mathbf{P} = \mathbf{B}(\mathbf{B}^\top\mathbf{B})^{-1}\mathbf{B}^\top$
- Tính chất: $\mathbf{P}^2 = \mathbf{P}$ (idempotent)

> **ML link:** PCA = chiếu dữ liệu lên subspace variance cao nhất. Least squares = chiếu $\mathbf{y}$ lên column space của $\mathbf{X}$.

### Khoảng cách điểm tới hyperplane

Hyperplane: $\{\mathbf{x} : \mathbf{w}^\top\mathbf{x} = b\}$

$$d(\mathbf{x}_0, \text{plane}) = \frac{|\mathbf{w}^\top\mathbf{x}_0 - b|}{\|\mathbf{w}\|_2}$$

> **ML link:** SVM tối đa hóa khoảng cách này (margin).

---

## Ch.4 — Matrix Decompositions

### Determinant & Trace

| Đại lượng | Công thức | Ý nghĩa |
|:----------|:----------|:--------|
| $\det(\mathbf{A})$ | Product of eigenvalues | $\neq 0$ → khả nghịch |
| $\text{tr}(\mathbf{A})$ | $\sum_i A_{ii}$ = sum of eigenvalues | Xuất hiện trong KL divergence, Gaussian |

### Eigenvalues & Eigenvectors

$$\mathbf{A}\mathbf{v} = \lambda\mathbf{v}$$

- $\lambda$: eigenvalue — "hệ số co giãn" theo hướng $\mathbf{v}$
- $\mathbf{v}$: eigenvector — hướng không đổi sau phép biến đổi $\mathbf{A}$

**Eigendecomposition** (chỉ với ma trận vuông diagonalizable):

$$\mathbf{A} = \mathbf{P}\mathbf{\Lambda}\mathbf{P}^{-1}$$

Trong đó $\mathbf{\Lambda} = \text{diag}(\lambda_1, \ldots, \lambda_n)$, cột của $\mathbf{P}$ là eigenvectors.

> **ML link:** Symmetric PD matrix → eigenvalues dương hết → Hessian PD → cực tiểu thật sự (không phải saddle point).

### SVD — Singular Value Decomposition

$$\mathbf{A} = \mathbf{U}\mathbf{\Sigma}\mathbf{V}^\top$$

| Thành phần | Kích thước | Ý nghĩa |
|:-----------|:-----------|:--------|
| $\mathbf{U}$ | $m \times m$, orthogonal | Left singular vectors (hướng output) |
| $\mathbf{\Sigma}$ | $m \times n$, diagonal | Singular values $\sigma_1 \geq \sigma_2 \geq \ldots \geq 0$ |
| $\mathbf{V}^\top$ | $n \times n$, orthogonal | Right singular vectors (hướng input) |

**Truncated SVD (rank-$k$ approximation):**

$$\mathbf{A} \approx \mathbf{U}_k\mathbf{\Sigma}_k\mathbf{V}_k^\top \quad \text{(Eckart-Young theorem: best rank-k approx)}$$

> **ML link:** PCA dùng SVD. Recommender systems (matrix factorization). NLP (LSA). Compression ảnh. Pseudo-inverse $\mathbf{A}^+ = \mathbf{V}\mathbf{\Sigma}^+\mathbf{U}^\top$.

### Cholesky Decomposition

Với $\mathbf{A}$ symmetric positive definite:

$$\mathbf{A} = \mathbf{L}\mathbf{L}^\top$$

$\mathbf{L}$: lower triangular matrix với diagonal dương.

> **ML link:** Sampling từ Gaussian multivariate $\mathcal{N}(\boldsymbol{\mu}, \mathbf{\Sigma})$: lấy $\mathbf{L} = \text{chol}(\mathbf{\Sigma})$, rồi $\mathbf{x} = \boldsymbol{\mu} + \mathbf{L}\mathbf{z}$ với $\mathbf{z} \sim \mathcal{N}(0, \mathbf{I})$.

---

## Ch.5 — Vector Calculus

### Đạo hàm & Chain Rule

$$\frac{d}{dx}[f(g(x))] = f'(g(x)) \cdot g'(x)$$

**Chain rule nhiều bước (backprop):**

$$\frac{\partial L}{\partial w} = \frac{\partial L}{\partial z_n} \cdot \frac{\partial z_n}{\partial z_{n-1}} \cdots \frac{\partial z_2}{\partial z_1} \cdot \frac{\partial z_1}{\partial w}$$

### Gradient — Đạo hàm riêng

$$\nabla_\mathbf{w} L = \begin{bmatrix} \frac{\partial L}{\partial w_1} \\ \vdots \\ \frac{\partial L}{\partial w_n} \end{bmatrix}$$

- Gradient chỉ hướng **Loss tăng dốc nhất**
- Gradient Descent đi theo hướng **ngược lại**: $\mathbf{w} \leftarrow \mathbf{w} - \eta \nabla L$

### Jacobian

Khi $f: \mathbb{R}^n \to \mathbb{R}^m$:

$$\mathbf{J} = \frac{\partial \mathbf{f}}{\partial \mathbf{x}} \in \mathbb{R}^{m \times n}, \quad J_{ij} = \frac{\partial f_i}{\partial x_j}$$

> **ML link:** Backprop qua linear layer: $\frac{\partial L}{\partial \mathbf{x}} = \mathbf{J}^\top \frac{\partial L}{\partial \mathbf{y}}$.

### Hessian — Đạo hàm bậc 2

$$\mathbf{H}_{ij} = \frac{\partial^2 L}{\partial w_i \partial w_j}$$

| Eigenvalue của $\mathbf{H}$ | Ý nghĩa |
|:---------------------------|:--------|
| Tất cả dương | Cực tiểu thực sự (bowl shape) |
| Tất cả âm | Cực đại thực sự |
| Trái dấu | Saddle point — nguy hiểm cho training |

### Gradient của các dạng hay gặp

| Hàm | Gradient |
|:----|:---------|
| $\mathbf{a}^\top \mathbf{x}$ | $\mathbf{a}$ |
| $\mathbf{x}^\top \mathbf{A} \mathbf{x}$ | $(\mathbf{A} + \mathbf{A}^\top)\mathbf{x}$ |
| $\|\mathbf{x}\|_2^2$ | $2\mathbf{x}$ |
| $\|\mathbf{Ax} - \mathbf{b}\|_2^2$ | $2\mathbf{A}^\top(\mathbf{Ax} - \mathbf{b})$ |
| $\lambda\|\mathbf{w}\|_2^2$ (L2 reg) | $2\lambda\mathbf{w}$ |

---

## Ch.6 — Probability & Distributions

### Các quy tắc cơ bản

$$P(A \cup B) = P(A) + P(B) - P(A \cap B)$$

$$P(A \mid B) = \frac{P(A \cap B)}{P(B)}$$

$$P(A) = \sum_B P(A \mid B) P(B) \quad \text{(Law of Total Probability)}$$

### Bayes' Theorem

$$\underbrace{P(\theta \mid \mathcal{D})}_{\text{Posterior}} = \frac{\underbrace{P(\mathcal{D} \mid \theta)}_{\text{Likelihood}} \cdot \underbrace{P(\theta)}_{\text{Prior}}}{\underbrace{P(\mathcal{D})}_{\text{Evidence}}}$$

> **ML link:** Bayesian learning = cập nhật prior thành posterior khi thấy dữ liệu. MAP = MLE + prior. Naive Bayes, Bayesian NN.

### Gaussian Distribution

**Univariate:**

$$p(x) = \frac{1}{\sigma\sqrt{2\pi}} \exp\!\left(-\frac{(x-\mu)^2}{2\sigma^2}\right)$$

**Multivariate $\mathcal{N}(\boldsymbol{\mu}, \mathbf{\Sigma})$:**

$$p(\mathbf{x}) = \frac{1}{(2\pi)^{d/2}|\mathbf{\Sigma}|^{1/2}} \exp\!\left(-\frac{1}{2}(\mathbf{x}-\boldsymbol{\mu})^\top\mathbf{\Sigma}^{-1}(\mathbf{x}-\boldsymbol{\mu})\right)$$

- $\boldsymbol{\mu}$: mean vector
- $\mathbf{\Sigma}$: covariance matrix (symmetric PD)
- $(\mathbf{x}-\boldsymbol{\mu})^\top\mathbf{\Sigma}^{-1}(\mathbf{x}-\boldsymbol{\mu})$: **Mahalanobis distance**

### Expectation & Variance

$$\mathbb{E}[X] = \int x\, p(x)\, dx, \quad \mathbb{E}[\mathbf{x}] = \boldsymbol{\mu}$$

$$\text{Var}(X) = \mathbb{E}[(X-\mu)^2] = \mathbb{E}[X^2] - (\mathbb{E}[X])^2$$

$$\text{Cov}(\mathbf{x}) = \mathbb{E}[(\mathbf{x}-\boldsymbol{\mu})(\mathbf{x}-\boldsymbol{\mu})^\top] = \mathbf{\Sigma}$$

### Divergence & Entropy

**KL Divergence** (đo độ khác nhau giữa 2 phân phối):

$$D_{KL}(P \| Q) = \sum_x P(x) \log \frac{P(x)}{Q(x)} \geq 0$$

**Cross-Entropy** (classification loss):

$$H(P, Q) = -\sum_x P(x) \log Q(x) = H(P) + D_{KL}(P \| Q)$$

> Minimize cross-entropy = minimize KL divergence (vì $H(P)$ là hằng số với nhãn thật $P$).

### Phân phối hay dùng trong ML

| Phân phối | PMF/PDF | Dùng trong |
|:----------|:--------|:-----------|
| Bernoulli | $p^k(1-p)^{1-k}$ | Binary classification |
| Categorical | $\prod p_k^{[y=k]}$ | Multi-class, softmax output |
| Gaussian | Xem trên | Regression, noise, VAE |
| Beta | $\propto x^{\alpha-1}(1-x)^{\beta-1}$ | Prior cho probability |
| Dirichlet | $\propto \prod x_i^{\alpha_i-1}$ | Prior cho categorical (LDA) |

---

## Ch.7 — Continuous Optimization

### Gradient Descent

$$\mathbf{w}_{t+1} = \mathbf{w}_t - \eta \nabla_\mathbf{w} L(\mathbf{w}_t)$$

**Điều kiện tối ưu bậc 1:** $\nabla L(\mathbf{w}^*) = 0$

**Điều kiện đủ (bậc 2):** $\mathbf{H}(\mathbf{w}^*) \succ 0$ (positive definite) → cực tiểu thật sự.

### Convexity

Hàm $f$ **lồi (convex)** khi:

$$f(\lambda\mathbf{x} + (1-\lambda)\mathbf{y}) \leq \lambda f(\mathbf{x}) + (1-\lambda)f(\mathbf{y}), \quad \lambda \in [0,1]$$

- Mọi cực tiểu địa phương = cực tiểu toàn cục
- $f$ lồi ⟺ $\mathbf{H} \succeq 0$ (PSD) với mọi điểm

> **ML link:** MSE loss convex với linear model → GD hội tụ về nghiệm tối ưu. Cross-entropy với logistic regression cũng convex.

### Normal Equation (Least Squares)

$$\mathbf{X}^\top\mathbf{X}\hat{\mathbf{w}} = \mathbf{X}^\top\mathbf{y} \implies \hat{\mathbf{w}} = (\mathbf{X}^\top\mathbf{X})^{-1}\mathbf{X}^\top\mathbf{y}$$

> Nghiệm closed-form của linear regression (không cần GD). Dùng SVD khi $\mathbf{X}^\top\mathbf{X}$ gần singular.

### Regularization

**L2 (Ridge):** $L_{reg} = L + \lambda\|\mathbf{w}\|_2^2$ → Gradient thêm $2\lambda\mathbf{w}$ → weight decay

**L1 (LASSO):** $L_{reg} = L + \lambda\|\mathbf{w}\|_1$ → Nghiệm sparse (nhiều weight = 0)

---

## Kết nối ML ↔ Toán — Quick Map

| Kỹ thuật ML | Toán nền tảng |
|:------------|:--------------|
| Linear Regression | Projection, Normal Equation, Least Squares |
| PCA | SVD, Eigendecomposition, Projection |
| Backpropagation | Chain Rule, Jacobian, Gradient |
| Gradient Descent | Vector Calculus, Convexity |
| Logistic Regression | Sigmoid, Cross-Entropy, MLE |
| Bayesian Inference | Bayes' Theorem, Gaussian, KL Divergence |
| Attention (Transformer) | Dot product, Softmax, Inner product |
| VAE | Gaussian, KL Divergence, Reparameterization |
| SVM | Projection, Lagrange multipliers, Margin |
| K-Means / KNN | Euclidean distance, Norms |

---

## Cheat Sheet — Các công thức phải nhớ

$$\nabla_\mathbf{w}\|\mathbf{Xw}-\mathbf{y}\|^2 = 2\mathbf{X}^\top(\mathbf{Xw}-\mathbf{y})$$

$$\frac{d}{dx}\sigma(x) = \sigma(x)(1-\sigma(x))$$

$$\frac{d}{dx}\ln(x) = \frac{1}{x}$$

$$\frac{d}{dx}e^x = e^x$$

$$\text{softmax}(z_k) = \frac{e^{z_k}}{\sum_j e^{z_j}}, \quad \frac{\partial \text{softmax}_i}{\partial z_j} = \text{softmax}_i(\delta_{ij} - \text{softmax}_j)$$

$$\mathbf{A} = \mathbf{U\Sigma V}^\top \implies \mathbf{A}^+ = \mathbf{V\Sigma}^+\mathbf{U}^\top \quad \text{(pseudo-inverse)}$$
