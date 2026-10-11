# Sorting lab — Tuần 4

Mỗi bài nằm trong `bai1` đến `bai8`. Chạy lệnh từ `Assignments/week4`, cần JDK 8 trở lên.
Project đã có `../../lib/algs4.jar`; `BenchmarkData` dùng `edu.princeton.cs.algs4.In`.
Bốn file trong `algs4-data` được trích nguyên vẹn, kiểm tra CRC từ
[ZIP của Princeton](https://algs4.cs.princeton.edu/code/algs4-data.zip).
Đường dẫn đều tương đối, không cần thêm dependency.

## Bài 1

```powershell
New-Item -ItemType Directory -Force build/bai1 | Out-Null
javac --release 8 -cp ../../lib/algs4.jar -d build/bai1 BenchmarkData.java bai1/*.java
java -cp 'build/bai1;../../lib/algs4.jar' InsertionSortTest
java -cp 'build/bai1;../../lib/algs4.jar' InsertionBenchmark
```

Trên Linux/macOS đổi `;` trong classpath thành `:` và dùng `mkdir -p build/bai1`.
`Arrays.sort` chỉ dùng trong lớp kiểm thử để đối chiếu, không dùng trong thuật toán hoặc benchmark.

## Bài 2

```powershell
javac --release 8 -cp ../../lib/algs4.jar -d build/bai2 BenchmarkData.java Selection.java bai2/*.java
java -cp 'build/bai2;../../lib/algs4.jar' SelectionSortTest
java -cp 'build/bai2;../../lib/algs4.jar' SelectionBenchmark
```

Đọc bảng thực nghiệm và phân tích tại `bai1/REPORT.md`, `bai2/REPORT.md`.
