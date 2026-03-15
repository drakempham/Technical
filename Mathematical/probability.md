# Probability

## 1. Basics of Probability Theory

**Probability** measures the likelihood of an event occurring, ranging from 0 (impossible) to 1 (certain).

### Key Definitions

- **Sample space** $\Omega$: the set of all possible outcomes.
- **Event** $A$: a subset of the sample space.
- **Probability of an event**:

$$P(A) = \frac{\text{Number of favorable outcomes}}{\text{Total number of outcomes}} \quad \text{(for equally likely outcomes)}$$

### Axioms of Probability (Kolmogorov)

1. $P(A) \geq 0$ for any event $A$
2. $P(\Omega) = 1$ (the sample space is certain)
3. **Additivity**: For mutually exclusive events $A \cap B = \emptyset$:

$$P(A \cup B) = P(A) + P(B)$$

### Complement Rule

$$P(A^c) = 1 - P(A)$$

### Addition Rule (General)

$$P(A \cup B) = P(A) + P(B) - P(A \cap B)$$

---

## 2. Types of Probability

### Marginal Probability

The probability of a single event, ignoring all other events.

$$P(A)$$

- Also called the **prior probability** — known without additional information.

### Joint Probability

The probability that **both** events $A$ and $B$ occur simultaneously.

$$P(A \cap B) = P(A, B)$$

For **independent** events:
$$P(A \cap B) = P(A) \cdot P(B)$$

### Conditional Probability

The probability of event $A$ **given** that event $B$ has already occurred.

$$P(A \mid B) = \frac{P(A \cap B)}{P(B)}, \quad P(B) > 0$$

> **Intuition**: Conditioning on $B$ restricts the sample space to outcomes where $B$ is true.

### Relationship Between the Three

$$P(A \cap B) = P(A \mid B) \cdot P(B) = P(B \mid A) \cdot P(A)$$

$$P(A) = \sum_B P(A \mid B) \cdot P(B) \quad \text{(Law of Total Probability)}$$

| Type | Formula | Meaning |
|---|---|---|
| Marginal | $P(A)$ | Probability of $A$ alone |
| Joint | $P(A \cap B)$ | Both $A$ and $B$ occur |
| Conditional | $P(A \mid B)$ | $A$ given $B$ is known |

---

## 3. Bayes' Theorem

**Bayes' theorem** updates the probability of a hypothesis given new evidence.

$$\boxed{P(A \mid B) = \frac{P(B \mid A) \cdot P(A)}{P(B)}}$$

### Components

| Term | Name | Meaning |
|---|---|---|
| $P(A)$ | **Prior** | Initial belief about $A$ before seeing $B$ |
| $P(B \mid A)$ | **Likelihood** | Probability of evidence $B$ given $A$ is true |
| $P(B)$ | **Marginal likelihood** | Total probability of evidence $B$ |
| $P(A \mid B)$ | **Posterior** | Updated belief about $A$ after seeing $B$ |

### Expanded Form (via Law of Total Probability)

$$P(A \mid B) = \frac{P(B \mid A) \cdot P(A)}{P(B \mid A) \cdot P(A) + P(B \mid A^c) \cdot P(A^c)}$$

### Example

A disease affects 1% of a population. A test has:
- 99% sensitivity: $P(\text{pos} \mid \text{disease}) = 0.99$
- 95% specificity: $P(\text{neg} \mid \text{no disease}) = 0.95$, so $P(\text{pos} \mid \text{no disease}) = 0.05$

What is $P(\text{disease} \mid \text{pos})$?

$$P(\text{disease} \mid \text{pos}) = \frac{0.99 \times 0.01}{0.99 \times 0.01 + 0.05 \times 0.99} \approx 0.167$$

> Only ~16.7% — despite a highly accurate test, a low prior (1%) drastically reduces the posterior. This is a classic illustration of **base rate neglect**.

### Applications

- **Naive Bayes classifier**: text classification, spam detection
- **Bayesian inference**: updating model parameters with observed data
- **Medical diagnosis**: interpreting test results given disease prevalence

---

## 4. Probability Distributions in ML

### 4.1 Discrete Distributions

| Distribution | PMF | Mean | Variance | Use Case |
|---|---|---|---|---|
| **Bernoulli** | $P(X=k) = p^k(1-p)^{1-k}$ | $p$ | $p(1-p)$ | Binary outcome (0/1) |
| **Binomial** | $P(X=k) = \binom{n}{k}p^k(1-p)^{n-k}$ | $np$ | $np(1-p)$ | $k$ successes in $n$ trials |
| **Categorical** | $P(X=k) = p_k$ | — | — | Multi-class classification |
| **Poisson** | $P(X=k) = \frac{\lambda^k e^{-\lambda}}{k!}$ | $\lambda$ | $\lambda$ | Count of events in fixed interval |

### 4.2 Continuous Distributions

| Distribution | PDF | Mean | Variance | Use Case |
|---|---|---|---|---|
| **Gaussian (Normal)** | $p(x) = \frac{1}{\sigma\sqrt{2\pi}}e^{-\frac{(x-\mu)^2}{2\sigma^2}}$ | $\mu$ | $\sigma^2$ | Regression, noise modeling |
| **Beta** | $p(x) = \frac{x^{\alpha-1}(1-x)^{\beta-1}}{B(\alpha,\beta)}$ | $\frac{\alpha}{\alpha+\beta}$ | — | Bayesian prior for probabilities |
| **Dirichlet** | $p(\mathbf{x}) \propto \prod x_i^{\alpha_i - 1}$ | $\frac{\alpha_i}{\sum_j \alpha_j}$ | — | Prior for categorical (LDA, topic models) |
| **Exponential** | $p(x) = \lambda e^{-\lambda x}$ | $\frac{1}{\lambda}$ | $\frac{1}{\lambda^2}$ | Time between events |

### 4.3 Key ML Formulas

**Softmax** — convert logits to probabilities (multi-class):
$$P(y=k \mid \mathbf{x}) = \frac{e^{z_k}}{\sum_j e^{z_j}}$$

**Sigmoid** — Bernoulli probability from a logit (binary):
$$\sigma(z) = \frac{1}{1+e^{-z}}$$

**KL Divergence** — measure difference between two distributions:
$$D_{KL}(P \| Q) = \sum_x P(x) \log \frac{P(x)}{Q(x)}$$

**Cross-Entropy Loss** — standard classification loss:
$$H(P, Q) = -\sum_x P(x) \log Q(x)$$

> **Note**: Cross-entropy = KL divergence + entropy of $P$. Minimizing cross-entropy loss is equivalent to minimizing KL divergence when $P$ is the true label distribution.
