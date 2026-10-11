# Bài 1 — Khảo sát Insertion Sort

Đo thực tế ngày 11/10/2026 trên Windows, Intel Core 7 245HX, OpenJDK 25.0.4.1.
Biên dịch với `--release 8`. Mỗi trường hợp khởi động JVM bằng 20 lượt sắp xếp trước khi đo.
Mỗi lần đo dùng mảng mới; chỉ lời gọi `InsertionSort.sort` nằm giữa hai lần gọi
`System.nanoTime()`. Đọc file, tạo mảng, kiểm tra kết quả và ghi CSV đều nằm ngoài khoảng đo.

File, ngẫu nhiên, giảm dần: 10 lượt mỗi kích thước. Tăng dần và bằng nhau: 100 lượt.
Dữ liệu ngẫu nhiên có seed cố định theo kích thước và lượt chạy để Bài 2 dùng lại chính xác.
Trung bình được tính bằng tổng thời gian chia số lượt trong Java.

## Kết quả

Đơn vị: mili giây. `runs.csv` lưu từng lượt bằng nano giây; `summary.csv` lưu trung bình.

| Dữ liệu | 1.000 | 2.000 | 4.000 | 8.000 |
|---|---:|---:|---:|---:|
| File Princeton | 0.053210 | 0.175570 | 0.619330 | 2.332030 |
| Ngẫu nhiên | 0.039170 | 0.141180 | 0.553730 | 3.246290 |
| Tăng dần | 0.000970 | 0.001896 | 0.002176 | 0.005591 |
| Giảm dần | 0.081550 | 0.355630 | 1.116030 | 4.761300 |
| Bằng nhau | 0.000590 | 0.001115 | 0.002548 | 0.003693 |

## Nhận xét

- Tăng dần và bằng nhau không phải dịch phần tử vì điều kiện là `values[j] > value`.
  Mỗi phần tử chỉ kiểm tra ngắn, nên thời gian có xu hướng tăng tuyến tính, Θ(n).
- Ngẫu nhiên và file số nguyên chưa sắp xếp cần nhiều lần dịch; thời gian tăng nhanh,
  phù hợp với Θ(n²) trung bình. File 8.000 phần tử mất khoảng 44 lần thời gian file 1.000.
- Giảm dần phải dịch toàn bộ đoạn trước mỗi lần chèn, tổng n(n−1)/2 lần dịch, Θ(n²).
  Ở 8.000 phần tử đây là trường hợp chậm nhất trong phép đo này.
- Các tỷ lệ không khớp hoàn toàn lý thuyết do JIT, cache, tác vụ hệ điều hành và nhiễu đo.
  Trường hợp tăng dần/bằng nhau chỉ mất vài micro giây nên chi phí đồng hồ có ảnh hưởng.
  Không kết luận tốc độ tuyệt đối hay thứ tự giữa hai trường hợp rất nhanh từ một lần khảo sát.

## Kiểm thử

`InsertionSortTest`: 208 ca đạt, đối chiếu toàn bộ mảng với kết quả tham chiếu.
Có mảng rỗng, một phần tử, tăng/giảm dần, bằng nhau, số âm, trùng lặp, biên `int`
và 200 mảng ngẫu nhiên. Mỗi lượt benchmark cũng kiểm tra mảng đã tăng dần.
