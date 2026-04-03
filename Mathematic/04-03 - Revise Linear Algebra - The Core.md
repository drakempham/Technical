# 04/03 - Revise Linear Algebra: The Core

Here is a step-by-step breakdown of how these mathematical concepts are the core engines of Machine Learning (ML).

## 1. Vectors & Matrices: The Data Structures of ML

Think of vectors and matrices as the "containers" that hold your data and the "machines" that transform it.

**Step 1: Vectors (1D Arrays)**
*   **What it is:** A simple list or column of numbers. Example: `v = [2, 5, 1]`.
*   **ML Context:** A vector represents a single **data point**. If you are building an ML model to predict house prices, a single house might be represented as a vector: `[number_of_bedrooms, square_feet, age_in_years]`. 

**Step 2: Matrices (2D Grids)**
*   **What it is:** A grid or table of numbers (rows and columns). 
*   **ML Context:** A matrix usually represents two things:
    1.  **A Dataset:** Multiple data points stacked together. Each row is a vector (a single house), and each column is a feature (bedrooms, sq_ft).
    2.  **Weights (The Model):** In Neural Networks, matrices hold the "weights" or "rules" that the model has learned. 

**Step 3: Transpose ($V^T$ or $M^T$)**
*   **What it is:** Flipping a matrix over its diagonal. Rows become columns, and columns become rows. 
*   **ML Context:** Often used just to make dimensions match up properly so we can multiply them together. 

**Step 4: Addition**
*   **What it is:** Adding two vectors or matrices by adding the numbers in the exact same positions. `[1, 2] + [3, 4] = [4, 6]`.
*   **ML Context:** Used to add **Bias**. In an equation like `Output = (Weights * Inputs) + Bias`, the addition step shifts the result to fit the data better.

**Step 5: Multiplication (Matrix Multiplication)**
*   **What it is:** A specific way of multiplying rows of the first matrix with columns of the second matrix.
*   **ML Context:** This is the most important operation in Deep Learning! When you pass an input through a Neural Network layer, you are doing a massive matrix multiplication. It takes your input data and applies all the learned weights to it simultaneously to produce a new representation.

---

## 2. Dot Product & Norms: Measuring Similarity

If vectors are points in space, these operations tell us how far apart they are and what direction they point.

**Step 1: Norms (Length)**
*   **What it is:** The "length" or "magnitude" of a vector.
    *   **L1 Norm:** The sum of absolute values (like walking on city blocks).
    *   **L2 Norm (Euclidean):** The straight-line distance from the origin (calculated using the Pythagorean theorem).
*   **ML Context:** Used for **Regularization**. We punish an ML model if its "Weight Vector" gets too long. This prevents the model from relying too heavily on one specific feature (overfitting).

**Step 2: Dot Product**
*   **What it is:** You multiply matching elements of two vectors and add them all up. 
*   **ML Context:** The dot product measures **alignment**. 
    *   If two vectors point in the exact same direction, the dot product is a large positive number.
    *   If they are perpendicular, the dot product is exactly `0`.
    *   If they point in opposite directions, it is a large negative number.

**Step 3: Distance Metrics (Euclidean Distance)**
*   **What it is:** You subtract one vector from another, and take the L2 Norm (length) of the result.
*   **ML Context:** Used to answer: *"How physically close are point A and point B?"* Algorithms like K-Nearest Neighbors (KNN) use this to find the most similar data points.

**Step 4: Cosine Similarity**
*   **What it is:** It is the Dot Product of two vectors, divided by their lengths. It calculates the **angle** between two vectors, ignoring how long they are.
*   **ML Context:** Extremely important in Natural Language Processing (NLP). If you have a document with 10 words, and a document with 10,000 words about the exact same topic, their distance will be huge. But their **angle** will be exactly the same. Cosine similarity tells you they are about the same topic regardless of length.

### Deep Dive: How to Calculate Cosine Similarity

**The Goal:** Cosine similarity measures the angle between two vectors. It returns a value between **-1** (exact opposites) and **1** (perfectly aligned).

**The Formula:**
$$ \text{Cosine Similarity} = \frac{\mathbf{A} \cdot \mathbf{B}}{\|\mathbf{A}\| \times \|\mathbf{B}\|} $$

**Example Calculation:**
Imagine two vectors representing the ratings (out of 5) that two users gave to two movies:
*   **Vector A:** `[3, 4]` 
*   **Vector B:** `[4, 3]`

**Step 1: Calculate the Dot Product (Numerator: $\mathbf{A} \cdot \mathbf{B}$)**
1. Multiply first positions: `3 * 4 = 12`
2. Multiply second positions: `4 * 3 = 12`
3. Add together: `12 + 12 = 24`

**Step 2: Calculate Magnitude of Vector A (Denominator: $\|\mathbf{A}\|$)**
1. Square numbers in A: `3² = 9` and `4² = 16`
2. Add together: `9 + 16 = 25`
3. Take square root: `√25 = 5`

**Step 3: Calculate Magnitude of Vector B (Denominator: $\|\mathbf{B}\|$)**
1. Square numbers in B: `4² = 16` and `3² = 9`
2. Add together: `16 + 9 = 25`
3. Take square root: `√25 = 5`

**Step 4: Multiply the Magnitudes Together**
*   `Length of A * Length of B = 5 * 5 = 25`

**Step 5: Divide**
*   `Cosine Similarity = Dot Product / (Length of A * Length of B)`
*   `Cosine Similarity = 24 / 25 = 0.96`

**Conclusion:** A Cosine Similarity of 0.96 means the vectors are highly aligned (the users have highly similar tastes).

---

## 3. Eigenvalues & SVD: Finding What Matters

Data often has too many dimensions (features). These concepts help us compress data while keeping the important patterns.

**Step 1: The Core Idea of a Matrix**
*   Any matrix can be thought of as a mathematical action that "transforms" space. It stretches, squishes, or rotates vectors.

**Step 2: Eigenvectors and Eigenvalues**
*   **Eigenvector:** When a matrix transforms space, most vectors get knocked off their original line. An *eigenvector* is a special vector that stays on its exact same line during the transformation. It only gets stretched or shrunk.
*   **Eigenvalue:** This is the number that tells you *how much* that eigenvector got stretched or shrunk.
*   **ML Context:** Eigenvectors tell us the "core directions" of our data, and Eigenvalues tell us "how important" that direction is (how much variance/information it contains).

**Step 3: SVD (Singular Value Decomposition)**
*   **What it is:** A mathematical recipe that breaks *any* matrix down into three simpler matrices: a rotation, a stretching phase, and another rotation.
*   **ML Context:** SVD is the engine used for recommendation systems (like Netflix recommending movies) and data compression. It exposes the hidden "latent features" inside a massive matrix of user-item ratings.

---

## 4. Interview Answers

If an interviewer asks you to explain these concepts, here is the simplest, most effective way to answer.

### Question: "Explain how Embeddings work."
1.  **Define it:** An embedding is a way to take a complex piece of data (like a word, a sentence, or an image) and convert it into a dense vector (an array of numbers).
2.  **Explain the Geometry:** The magic of embeddings is that they translate "meaning" into "geometry." In an embedding space, things that are semantically similar are physically located close to each other. 
3.  **Give an Example:** For example, the vector for "Dog" will be very close in space to the vector for "Puppy", but far away from the vector for "Car". 
4.  **Mention the Math:** Because meanings are just vectors, we can do math on concepts. The famous example is: `Vector("King") - Vector("Man") + Vector("Woman")`. If you calculate this, the closest resulting vector in the space will be `Vector("Queen")`.

### Question: "Explain how PCA (Principal Component Analysis) works."
1.  **Define the Problem:** Imagine we have a dataset with 100 features (dimensions). Visualizing or training a model on 100 dimensions is slow and noisy because many features are highly correlated (e.g., "square feet" and "number of rooms" move together).
2.  **The Solution:** PCA is a Dimensionality Reduction technique. Its goal is to compress the 100 features into a smaller number (like 2 or 3) while keeping as much of the original "variance" (information) as possible.
3.  **How it works (The Math):** 
    *   PCA calculates a new set of axes (called Principal Components) that cut through the data. 
    *   The 1st Principal Component is drawn in the direction where the data is most spread out (highest variance).
    *   The 2nd Principal Component is drawn perpendicular to the first, capturing the next highest variance, and so on.
    *   Under the hood, PCA does this by calculating the **Eigenvectors** of the data's covariance matrix.
4.  **The Result:** We can drop the least important axes and project our data onto just the top 2 or 3 Principal Components. We lose a tiny bit of information, but we massively simplify the data.
