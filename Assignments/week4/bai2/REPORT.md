# Bài 2 — Khảo sát Selection Sort

Đo thực tế ngày 11/10/2026, cùng máy/JDK, loại dữ liệu, kích thước, seed và số lượt
như Bài 1. Mỗi trường hợp có 20 lượt khởi động trước khi đo. Chỉ `SelectionSort.sort`
nằm trong khoảng đo `System.nanoTime()`. CSV lưu từng lượt và trung bình tính trong Java.
Hai thuật toán chạy trong hai tiến trình JVM riêng; không bảo đảm cùng tải hệ thống.

## So sánh trực tiếp

Đơn vị: mili giây. I = Insertion Sort, S = Selection Sort.

| Dữ liệu | n | I (ms) | S (ms) |
|---|---:|---:|---:|
| File Princeton | 1000 | 0.053210 | 0.478300 |
| File Princeton | 2000 | 0.175570 | 1.862620 |
| File Princeton | 4000 | 0.619330 | 4.848230 |
| File Princeton | 8000 | 2.332030 | 32.756790 |
| Ngẫu nhiên | 1000 | 0.039170 | 0.467440 |
| Ngẫu nhiên | 2000 | 0.141180 | 2.347900 |
| Ngẫu nhiên | 4000 | 0.553730 | 8.029960 |
| Ngẫu nhiên | 8000 | 3.246290 | 32.778930 |
| Tăng dần | 1000 | 0.000970 | 0.581578 |
| Tăng dần | 2000 | 0.001896 | 2.187038 |
| Tăng dần | 4000 | 0.002176 | 9.871142 |
| Tăng dần | 8000 | 0.005591 | 23.200906 |
| Giảm dần | 1000 | 0.081550 | 0.244870 |
| Giảm dần | 2000 | 0.355630 | 1.022540 |
| Giảm dần | 4000 | 1.116030 | 3.986010 |
| Giảm dần | 8000 | 4.761300 | 16.045440 |
| Bằng nhau | 1000 | 0.000590 | 0.249639 |
| Bằng nhau | 2000 | 0.001115 | 1.027925 |
| Bằng nhau | 4000 | 0.002548 | 4.002358 |
| Bằng nhau | 8000 | 0.003693 | 23.779033 |

## Nhận xét

- Selection Sort luôn duyệt phần chưa sắp xếp để tìm nhỏ nhất: n(n−1)/2 phép so sánh,
  Θ(n²) ở cả năm loại dữ liệu, chỉ O(n) lần đổi chỗ. Tăng dần hoặc bằng nhau cũng
  không giúp thuật toán bỏ qua việc tìm nhỏ nhất.
- Ngẫu nhiên: từ 1.000 lên 8.000, Selection tăng khoảng 70 lần; Insertion tăng khoảng
  83 lần trong phép đo này. Xu hướng tăng nhanh phù hợp với độ phức tạp bậc hai;
  tỷ lệ cụ thể bị ảnh hưởng bởi JIT, cache và tải máy.
- File: cả hai tăng nhanh khi n tăng. Insertion nhanh hơn Selection ở cả bốn kích thước;
  tại 8.000, lần lượt khoảng 2,33 ms và 32,76 ms.
- Tăng dần và bằng nhau: Insertion chỉ kiểm tra mỗi phần tử, Θ(n), nên nhanh hơn rất nhiều.
  Selection vẫn phải so sánh toàn bộ các cặp thuộc phần chưa sắp xếp.
- Giảm dần: Insertion phải dịch n(n−1)/2 phần tử; Selection vẫn so sánh n(n−1)/2 lần.
  Dù Selection đổi chỗ ít hơn, ở 8.000 phần tử nó vẫn chậm hơn (16,05 ms so với 4,76 ms).
  Ít lần ghi không bảo đảm nhanh hơn, vì cách truy cập và chi phí từng phép toán khác nhau.
- Cùng độ phức tạp không có nghĩa cùng thời gian; Selection còn có khác biệt giữa các
  loại dữ liệu do nhánh so sánh và cập nhật chỉ số nhỏ nhất. Không suy ra Selection luôn
  nhanh hơn trên dữ liệu giảm dần hoặc luôn chậm hơn trên mọi máy chỉ từ khảo sát này.

## Kiểm thử

208 ca cho cả `SelectionSort` và `Selection` phiên bản `Comparable[]`: đều đạt.
Bao gồm mảng rỗng, một phần tử, số âm, trùng lặp, biên `int` và 200 mảng ngẫu nhiên.
Mỗi lượt benchmark kiểm tra mảng tăng dần. Đã đối chiếu đủ 920 lượt/20 dòng trung bình
ở mỗi bài; trung bình CSV khớp tổng các lượt đo chia số lượt.

`Selection.java` cũ có phương thức khai báo lồng trong `sort`, lời gọi `compareTo` sai,
thiếu kiểu tham số/biến tạm. Đã thay bằng hai vòng `for` và phép đổi chỗ cơ bản.
