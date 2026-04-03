# Analytic Geometry for ML

## 1. Truc giac tong quat

Neu linear algebra tra loi cau hoi "vector va ma tran la gi, va thao tac dai so voi chung ra sao", thi analytic geometry tra loi:

- hai vector giong nhau den muc nao
- mot vector dai bao nhieu
- hai vector lech nhau goc bao nhieu
- mot diem cach mot duong / mot mat phang bao xa
- lam sao chieu mot vector len mot khong gian con

Trong machine learning, nhieu bai toan thuc chat duoc viet lai thanh bai toan hinh hoc:

- tim diem gan nhat: `k-NN`, clustering
- tim huong giai thich du lieu tot nhat: `PCA`
- tim phep chieu tot nhat: least squares, linear regression
- do muc do tuong dong: cosine similarity, retrieval, embeddings

---

## 2. Norm: do dai cua vector

### 2.1 Dinh nghia

Mot **norm** la ham gan cho moi vector `x` mot do dai `||x||`, thoa 3 tinh chat:

1. **Positive definiteness**
   `||x|| >= 0`, va `||x|| = 0` khi va chi khi `x = 0`
2. **Absolute homogeneity**
   `||alpha x|| = |alpha| ||x||`
3. **Triangle inequality**
   `||x + y|| <= ||x|| + ||y||`

### 2.2 Hai norm quan trong

**Manhattan norm**

$$
\|x\|_1 = \sum_i |x_i|
$$

Hinh dung: di chuyen tren ban co o vuong, chi duoc di ngang/doc.

**Euclidean norm**

$$
\|x\|_2 = \sqrt{x^\top x} = \sqrt{\sum_i x_i^2}
$$

Day la "do dai thong thuong" trong hinh hoc Euclid, va cung la norm mac dinh neu sach khong noi khac.

### 2.3 Vi sao norm quan trong trong ML

- `L2` do khoang cach theo duong thang, rat pho bien trong `k-NN`, `k-means`, regression
- `L1` uu tien nghiem sparse, xuat hien trong `LASSO`
- choice of norm thay doi hinh hoc cua bai toan toi uu

Vi du:

$$
\min_w \|Xw - y\|_2^2
$$

la least squares, trong khi:

$$
\min_w \|Xw - y\|_2^2 + \lambda \|w\|_1
$$

khuyen khich nhieu phan tu cua `w` bang 0.

---

## 3. Distance: do xa giua hai diem

Khoang cach giua hai vector `x` va `y` duoc dinh nghia tu norm:

$$
d(x, y) = \|x - y\|
$$

Voi `L2`:

$$
d_2(x, y) = \sqrt{\sum_i (x_i - y_i)^2}
$$

Voi `L1`:

$$
d_1(x, y) = \sum_i |x_i - y_i|
$$

### Truc giac

- Neu `x` va `y` la 2 diem du lieu, thi `x - y` la vector noi tu `y` den `x`
- norm cua vector nay chinh la khoang cach

### Lien he ML

- `k-NN`: tim diem huan luyen co khoang cach nho nhat
- clustering: gan diem vao tam cum gan nhat
- anomaly detection: diem qua xa cac diem khac co the la outlier

---

## 4. Inner product: do "cung huong" cua hai vector

### 4.1 Dinh nghia

Inner product chuan trong `R^n` la:

$$
\langle x, y \rangle = x^\top y = \sum_i x_i y_i
$$

No do muc do hai vector "dong huong" voi nhau.

### 4.2 Dien giai hinh hoc

$$
x^\top y = \|x\|_2 \|y\|_2 \cos \theta
$$

voi `theta` la goc giua hai vector.

Suy ra:

- `x^T y > 0`: goc nho hon 90 do, hai vector cung huong
- `x^T y = 0`: vuong goc
- `x^T y < 0`: nguoc huong

### 4.3 Tai sao dieu nay hay

Inner product vua la cong cu dai so, vua la cong cu hinh hoc:

- dai so: tong cua cac tich thanh phan
- hinh hoc: norm nhan cosine

No la cau noi giua linear algebra va analytic geometry.

### 4.4 Lien he ML

- linear model dung `w^T x + b`
- similarity search dung `x^T y`
- attention dung dot-product similarity
- kernels mo rong inner product sang khong gian dac trung cao hon

---

## 5. Angle va cosine similarity

Tu cong thuc:

$$
\cos \theta = \frac{x^\top y}{\|x\|_2 \|y\|_2}
$$

ta do duoc goc giua hai vector.

### Truc giac

- norm chi do do lon
- cosine chi do huong

Hai vector co the rat dai/ngan khac nhau nhung van cung huong, khi do cosine van gan 1.

### Lien he ML

Cosine similarity rat pho bien trong:

- word embeddings
- sentence embeddings
- retrieval
- recommendation

Vi no do "giong nhau ve huong" hon la "giong nhau ve do lon".

---

## 6. Orthogonality: vuong goc

Hai vector `x` va `y` la **orthogonal** neu:

$$
x^\top y = 0
$$

### Truc giac

Neu mot vector khong co thanh phan nao theo huong cua vector kia, thi inner product bang 0.

### Vi sao quan trong

Orthogonality giup tach thong tin thanh cac thanh phan doc lap ve hinh hoc.

### Lien he ML

- trong `PCA`, cac principal components truc giao voi nhau
- trong least squares, residual truc giao voi column space cua `X`
- trong optimization, gradient vuong goc voi contour level set

---

## 7. Projection: chieu mot vector len mot huong

### 7.1 Chieu len mot vector

Cho vector `u != 0`. Phep chieu cua `x` len huong `u` la:

$$
\mathrm{proj}_u(x) = \frac{x^\top u}{u^\top u} u
$$

Neu `u` da duoc chuan hoa (`||u|| = 1`) thi:

$$
\mathrm{proj}_u(x) = (x^\top u) u
$$

### 7.2 Tach vector thanh 2 phan

Moi vector `x` co the tach thanh:

$$
x = \hat{x} + r
$$

trong do:

- `hat{x}` la phan nam trong huong `u`
- `r = x - hat{x}` la phan du vuong goc voi `u`

Va:

$$
u^\top r = 0
$$

### Truc giac

Projection tra loi cau hoi:

> Trong vector `x`, co bao nhieu "thong tin" nam theo huong `u`?

### Lien he ML

- least squares: tim projection cua `y` len column space cua `X`
- PCA: projection du lieu len cac huong co phuong sai lon nhat
- feature extraction: giu lai thanh phan theo nhung huong quan trong

---

## 8. Projection len subspace

Khong gian con `S` co the duoc sinh boi cac cot cua ma tran `A`:

$$
S = \mathrm{span}(a_1, a_2, \dots, a_k)
$$

Neu muon tim diem trong `S` gan `x` nhat, ta dang tim projection cua `x` len `S`.

### Phat bieu bai toan

Tim `\hat{x}` sao cho:

$$
\hat{x} \in S
$$

va

$$
\|x - \hat{x}\|_2
$$

nho nhat.

### Tinh chat hinh hoc quan trong

Tai nghiem toi uu, residual:

$$
r = x - \hat{x}
$$

vuong goc voi moi vector trong `S`.

Noi cach khac:

$$
r \perp S
$$

Day la y tuong trung tam cua least squares.

---

## 9. Least squares va regression

Cho bai toan:

$$
\min_w \|Xw - y\|_2^2
$$

Ta co:

- `Xw` la tat ca cac vector trong column space cua `X`
- ta dang tim `Xw` gan `y` nhat

Tuc la:

> Linear regression = projection cua `y` len column space cua `X`

Dieu kien toi uu:

$$
X^\top (y - Xw) = 0
$$

Hay:

$$
X^\top X w = X^\top y
$$

Do chinh la **normal equation**.

### Truc giac

Model tuyen tinh khong "bat duoc" moi `y`, nen no chi tim diem trong khong gian ma model co the bieu dien, sao cho diem do gan `y` nhat theo `L2`.

---

## 10. PCA duoi goc nhin hinh hoc

PCA tim mot huong `u` sao cho khi chieu du lieu len huong do, phuong sai duoc giu lai la lon nhat.

Neu `||u|| = 1`, projection cua diem `x` len `u` la:

$$
(x^\top u)u
$$

PCA tim `u` de toi da hoa:

$$
\mathrm{Var}(x^\top u)
$$

### Truc giac

- projection len huong "tot" giu lai nhieu thong tin
- projection len huong "xau" lam mat thong tin

Principal components la cac huong truc giao, giai thich lan luot nhieu phuong sai nhat.

---

## 11. Classification va similarity

Trong classification, nhat la linear classification:

$$
f(x) = w^\top x + b
$$

Vector `w` la phap tuyen cua hyperplane quyet dinh.

### Truc giac

- `w^T x + b = 0`: diem nam tren mat phang quyet dinh
- `w^T x + b > 0`: nam mot phia
- `w^T x + b < 0`: nam phia con lai

Khoang cach co huong tu diem `x` den hyperplane la:

$$
\frac{w^\top x + b}{\|w\|_2}
$$

Cong thuc nay giai thich tai sao norm cua `w` anh huong truc tiep den margin trong SVM.

---

## 12. Tong ket nhanh

Analytic geometry dua linear algebra vao cach nhin khong gian:

- norm: vector dai bao nhieu
- distance: hai diem xa bao nhieu
- inner product: hai vector giong nhau ve huong ra sao
- angle / cosine: do do tuong dong ve huong
- orthogonality: thong tin tach biet nhau
- projection: tim thanh phan cua mot vector nam trong mot huong / khong gian con

### Mot cau chot cho ML

Rat nhieu bai toan ML co the duoc doc lai bang ngon ngu hinh hoc:

- regression = projection
- PCA = tim huong giu nhieu phuong sai nhat
- retrieval = do cosine similarity
- classification = cat khong gian bang hyperplane

Neu nho duoc mot cau, hay nho cau nay:

> Machine learning rat nhieu luc chi la hinh hoc cua du lieu trong khong gian nhieu chieu.

---

## 13. Flash notes

- `||x||_1`: tong do lon tung thanh phan
- `||x||_2`: do dai Euclid
- `d(x, y) = ||x - y||`
- `x^T y = ||x|| ||y|| cos theta`
- `x^T y = 0` <=> `x` vuong goc `y`
- `proj_u(x) = (x^T u / u^T u) u`
- linear regression = projection cua `y` len `col(X)`
- PCA = projection tot nhat theo tieu chi phuong sai
