# ML Interview — Section 1: Fundamentals

---

## 1. AI vs ML vs Data Science

| Aspect | AI | ML | Data Science |
|--------|----|----|-------------|
| **Definition** | Build systems that mimic human intelligence | Subset of AI that learns patterns from data | Extract insights from data |
| **Scope** | Reasoning, planning, NLP, robotics | Classification, regression, clustering | Collection, cleaning, analysis, visualization |
| **Techniques** | Expert systems, NLP, ML, deep learning | Decision trees, neural networks | Statistics, ML, visualization |
| **Example** | Chatbots, self-driving cars | Spam detection, recommendations | Sales trends, customer segmentation |

> **ML** = subset of **AI** that learns from data. **Data Science** is broader — uses ML but also statistics and domain knowledge.

---

## 2. Overfitting & Underfitting

**Overfitting**: Model memorizes training data (including noise) → high train accuracy, low test accuracy.

**Underfitting**: Model too simple → poor accuracy on both train and test.

| Problem | Signs | Fixes |
|---------|-------|-------|
| **Overfitting** | Train loss ↓↓, Val loss ↑ | Early stopping, Dropout, L1/L2, simpler model |
| **Underfitting** | Both losses high | More complex model, more features, more epochs |

*In our nanoGPT*: train loss `1.66` vs val loss `1.82` → slight overfitting → fix by increasing `dropout` from `0.0` to `0.2`.

---

## 3. Regularization

Adds a **penalty term** to the loss function to prevent overfitting by discouraging large weights.

| Type | Penalty | Effect |
|------|---------|--------|
| **L1 (Lasso)** | `λ Σ|w|` | Can shrink weights to **zero** → feature selection |
| **L2 (Ridge)** | `λ Σw²` | Reduces weights, keeps all features |
| **Elastic Net** | L1 + L2 | Balances both; good for correlated features |
| **Dropout** | Random neuron drops | Prevents over-reliance on specific nodes |

*In our nanoGPT*: **AdamW = Adam + Weight Decay** (a form of L2). `dropout=0.0` is currently off.

---

## 4. Lasso vs Ridge

```
Lasso Loss = MSE + λ Σ|wᵢ|     → some weights → 0 (feature selection)
Ridge Loss = MSE + λ Σwᵢ²      → weights small but non-zero
```

- Use **Lasso** when many irrelevant features exist
- Use **Ridge** when all features are useful but prone to overfitting
- Use **Elastic Net** when features are correlated

---

## 5. Model Evaluation Techniques

| Technique | Use Case |
|-----------|---------|
| **Train-Test Split** (80/20) | Quick baseline evaluation |
| **K-Fold Cross-Validation** | Reduces bias, more reliable |
| **Confusion Matrix** | Classification — see TP, TN, FP, FN |
| **Accuracy** | % correct predictions (misleading on imbalanced data) |
| **Precision** | `TP / (TP + FP)` — avoid false positives |
| **Recall** | `TP / (TP + FN)` — avoid false negatives |
| **F1-Score** | `2 × (P × R) / (P + R)` — balance precision & recall |
| **ROC-AUC** | Classification — ability to distinguish classes |
| **MSE / MAE / RMSE** | Regression error metrics |

---

## 6. Confusion Matrix

```
                 Predicted +    Predicted -
Actual +     |    TP         |    FN        |
Actual -     |    FP         |    TN        |
```

- **TP** = correctly predicted positive
- **TN** = correctly predicted negative
- **FP** = negative predicted as positive (false alarm)
- **FN** = positive predicted as negative (missed)

---

## 7. Precision vs Recall vs F1

```
Precision = TP / (TP + FP)    → "Of all I predicted positive, how many were right?"
Recall    = TP / (TP + FN)    → "Of all actual positives, how many did I catch?"
F1        = 2 × (P × R) / (P + R)
```

| Metric | Prioritize when... |
|--------|-------------------|
| **Precision** | False positives are costly (e.g. spam filter) |
| **Recall** | False negatives are costly (e.g. disease detection) |
| **F1** | Need balance between both |

---

## 8. Loss Functions

| Loss | Formula | Use Case |
|------|---------|---------|
| **MSE** | `(1/n) Σ(yᵢ - ŷᵢ)²` | Regression, penalizes large errors |
| **MAE** | `(1/n) Σ|yᵢ - ŷᵢ|` | Regression, robust to outliers |
| **Huber** | MSE + MAE hybrid | Regression, outlier-resistant |
| **Cross-Entropy** | `-Σ y log(ŷ)` | Classification ← *used in nanoGPT* |
| **Hinge** | `max(0, 1 - y·ŷ)` | SVM classification |
| **KL Divergence** | Measures distribution difference | Probabilistic models |

*In nanoGPT*: `F.cross_entropy(logits, targets)` → average over all `B×T` positions.

---

## 9. AUC-ROC

```
ROC Curve: TPR (Recall) vs FPR at different thresholds
AUC = Area Under the ROC Curve

AUC = 1.0  → Perfect classifier
AUC = 0.5  → Random guessing
AUC < 0.5  → Worse than random
```

---

## 10. Is Accuracy Always a Good Metric?

**No** — misleading on imbalanced datasets.

Example: 95% class A, 5% class B → predicting always A gives 95% accuracy but fails completely on class B.

→ Use **Precision, Recall, F1** or **AUC-ROC** for imbalanced data.
