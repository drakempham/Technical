Đúng bài này nên bắt đầu bằng **Kaggle Notebook end-to-end**: load data → train model đơn giản → tạo `submission.csv` → submit.

Bài này có `train.csv`, `test.csv`, `sample_submission.csv`, target là `Irrigation_Need` với class `Low / Medium / High`. Dataset khoảng 630k train rows và 270k test rows, nên đủ lớn nhưng vẫn hợp để học tabular ML. ([Kaggle][1])

## Bước 1: Vào competition

Vào trang competition **Predicting Irrigation Need**.

Sau đó bấm:

```text
Code → New Notebook
```

Kaggle sẽ tự attach dataset của competition vào notebook. Nếu chưa có, ở panel bên phải chọn:

```text
Add Input → Competition Data
```

---

## Bước 2: Kiểm tra file input

Tạo cell đầu tiên:

```python
import os

for dirname, _, filenames in os.walk("/kaggle/input"):
    for filename in filenames:
        print(os.path.join(dirname, filename))
```

Bạn sẽ thấy đại loại:

```text
/kaggle/input/playground-series-s6e4/train.csv
/kaggle/input/playground-series-s6e4/test.csv
/kaggle/input/playground-series-s6e4/sample_submission.csv
```

---

## Bước 3: Load data

```python
import pandas as pd

train = pd.read_csv("/kaggle/input/playground-series-s6e4/train.csv")
test = pd.read_csv("/kaggle/input/playground-series-s6e4/test.csv")
sample_submission = pd.read_csv("/kaggle/input/playground-series-s6e4/sample_submission.csv")

print(train.shape)
print(test.shape)
print(sample_submission.shape)

train.head()
```

Ý nghĩa:

```text
train = data có label Irrigation_Need
test = data chưa có label, mình cần dự đoán
sample_submission = format file cần nộp
```

---

## Bước 4: Xem target

```python
train["Irrigation_Need"].value_counts(normalize=True)
```

Bạn sẽ thấy class không đều. Thường `High` ít hơn nhiều, nên sau này mới cần quan tâm class imbalance. Nhưng lần đầu cứ làm baseline trước.

---

## Bước 5: Tách feature và label

```python
target = "Irrigation_Need"

X = train.drop(columns=["id", target])
y = train[target]

X_test = test.drop(columns=["id"])
```

Hiểu đơn giản:

```text
X = input features
y = đáp án cần học
X_test = input cần predict
```

---

## Bước 6: Tạo baseline model đơn giản

Dùng `RandomForestClassifier` trước cho dễ hiểu. Model này xử lý tabular khá ổn, không cần tuning nhiều.

```python
from sklearn.model_selection import train_test_split
from sklearn.compose import ColumnTransformer
from sklearn.preprocessing import OrdinalEncoder
from sklearn.pipeline import Pipeline
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score, classification_report

categorical_cols = X.select_dtypes(include=["object"]).columns.tolist()
numeric_cols = X.select_dtypes(exclude=["object"]).columns.tolist()

preprocess = ColumnTransformer(
    transformers=[
        ("cat", OrdinalEncoder(handle_unknown="use_encoded_value", unknown_value=-1), categorical_cols),
        ("num", "passthrough", numeric_cols),
    ]
)

model = Pipeline(
    steps=[
        ("preprocess", preprocess),
        ("classifier", RandomForestClassifier(
            n_estimators=100,
            random_state=42,
            n_jobs=-1,
            class_weight="balanced"
        )),
    ]
)
```

Ý nghĩa:

```text
OrdinalEncoder:
chuyển categorical text thành số

RandomForest:
model baseline để predict Low / Medium / High

class_weight="balanced":
giúp model để ý hơn tới class ít như High
```

---

## Bước 7: Chia validation để test trước khi submit

```python
X_train, X_valid, y_train, y_valid = train_test_split(
    X,
    y,
    test_size=0.2,
    random_state=42,
    stratify=y
)

model.fit(X_train, y_train)

valid_pred = model.predict(X_valid)

print("Validation accuracy:", accuracy_score(y_valid, valid_pred))
print(classification_report(y_valid, valid_pred))
```

Nếu cell này chạy xong, nghĩa là pipeline của bạn đã đúng.

---

## Bước 8: Train lại trên toàn bộ train data

Sau khi thấy model chạy ổn, train lại bằng toàn bộ data:

```python
model.fit(X, y)
```

---

## Bước 9: Predict test set

```python
test_pred = model.predict(X_test)

test_pred[:10]
```

Kết quả sẽ là:

```text
['Low', 'Medium', 'Low', 'High', ...]
```

---

## Bước 10: Tạo submission.csv

```python
submission = sample_submission.copy()
submission["Irrigation_Need"] = test_pred

submission.head()
```

Check format phải đúng:

```text
id,Irrigation_Need
630000,Low
630001,High
630002,Low
```

Sau đó save:

```python
submission.to_csv("submission.csv", index=False)
```

---

## Bước 11: Submit lên Kaggle

Ở Kaggle Notebook:

```text
Save Version → Save & Run All
```

Sau khi notebook chạy xong:

```text
Notebook Output → submission.csv → Submit to Competition
```

Hoặc vào competition page:

```text
Submit Predictions → chọn submission.csv
```

---

## Bước 12: Sau khi submit lần đầu

Lúc này bạn đã hiểu workflow Kaggle. Sau đó mới nên cải thiện:

```text
1. Đọc tab Code public
2. Xem notebook LightGBM / XGBoost / CatBoost baseline
3. So sánh validation score và leaderboard score
4. Thử đổi model từ RandomForest sang LightGBM/CatBoost
5. Thêm feature engineering
```

Với bạn, thứ tự học tốt nhất là:

```text
First submission:
RandomForest baseline

Second submission:
LightGBM / XGBoost baseline

Third submission:
Feature engineering + class imbalance handling

Fourth submission:
Ensemble nhiều model
```

Mục tiêu đầu tiên không phải điểm cao, mà là **chạy được full loop từ notebook đến submission**.

[1]: https://www.kaggle.com/code/nina2025/ps-s6e4-eos-voting-2-1-2/input?scriptVersionId=311372372&utm_source=chatgpt.com "PS-s6e4 | EoS voting 2.1.2"
