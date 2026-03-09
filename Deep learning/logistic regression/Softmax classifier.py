import numpy as np

# "Dùng gradient để biết weights/bias đang lệch theo hướng nào, rồi điều chỉnh ngược lại — lặp nhiều lần cho đến khi ít lệch nhất."
def softmax(z):
    exp_z = np.exp(z - np.max(z))  # Subtract max to prevent overflow
    return exp_z / np.sum(exp_z, axis=1, keepdims=True)

def cross_entropy_loss(predicted, actual):
    m = actual.shape[0]  # Number of samples
    log_likelihood = -np.log(predicted[range(m), actual])
    loss = np.sum(log_likelihood) / m
    return loss

class SoftmaxClassifier:
    def __init__(self, learning_rate=0.01, num_classes=3, num_features=2):
        self.learning_rate = learning_rate
        self.weights = np.random.randn(num_features, num_classes)
        self.bias = np.zeros((1, num_classes))
        
    def train(self, X, y, epochs=1000):
        for epoch in range(epochs):
            # Forward pass
            logits = np.dot(X, self.weights) + self.bias
            probabilities = softmax(logits)
            
            # Compute loss
            loss = cross_entropy_loss(probabilities, y)
            
            # Backward pass (Gradient Descent)
            m = X.shape[0]
            grad_logits = probabilities
            grad_logits[range(m), y] -= 1  # Gradient of loss with respect to logits
            grad_logits /= m
            
            # Update weights and bias
            self.weights -= self.learning_rate * np.dot(X.T, grad_logits)
            self.bias -= self.learning_rate * np.sum(grad_logits, axis=0, keepdims=True)
            
            if epoch % 100 == 0:
                print(f"Epoch {epoch} - Loss: {loss}")
    
    def predict(self, X):
        logits = np.dot(X, self.weights) + self.bias
        probabilities = softmax(logits)
        return np.argmax(probabilities, axis=1)

# Example usage
# Sample dataset (X: input features, y: class labels)
X = np.array([[1, 2], [2, 1], [3, 1], [1, 3], [2, 3], [3, 2]])
y = np.array([0, 0, 1, 1, 2, 2])  # 3 classes

# Initialize and train the classifier
classifier = SoftmaxClassifier(learning_rate=0.1, num_classes=3, num_features=2)
classifier.train(X, y, epochs=1000)

# Predict
predictions = classifier.predict(X)
print("Predictions:", predictions)