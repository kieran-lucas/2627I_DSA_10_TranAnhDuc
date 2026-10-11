# Bài 8 — Sort và LinkedList

## Insertion Sort

Có thể dùng LinkedList, nhưng không phải lúc nào cũng tốt hơn mảng.
Khi đã có iterator tại vị trí cần chèn, thêm hoặc xóa một node mất O(1),
không cần dịch các phần tử như mảng. Tuy nhiên, tìm vị trí chèn vẫn mất O(n),
nên sắp xếp toàn bộ vẫn có thể mất O(n²).

Với mảng, dịch phần tử mất O(n) mỗi lần chèn trong trường hợp xấu nhất.
Bù lại, truy cập theo chỉ số mất O(1), dữ liệu nằm liên tiếp trong bộ nhớ.
Insertion Sort trên mảng đơn giản và thường phù hợp hơn cho bài tập này.
Mảng đã tăng dần hoặc bằng nhau chỉ cần O(n) vì không phải dịch phần tử.
LinkedList cũng có thể đạt O(n) trên dữ liệu đã tăng dần nếu duyệt đúng cách
và nhận ra phần tử tiếp theo đã ở đúng vị trí.

## Selection Sort

Không nên đổi sang LinkedList chỉ để tăng tốc Selection Sort.
Mỗi lượt vẫn phải duyệt phần chưa sắp xếp để tìm phần tử nhỏ nhất,
tổng cộng n(n−1)/2 phép so sánh, tức O(n²) khi duyệt bằng iterator.

Selection Sort trên mảng chỉ đổi chỗ hai phần tử mỗi lượt, không dịch cả đoạn.
Vì vậy ưu điểm chèn/xóa của LinkedList ít có ích ở đây.
LinkedList còn tốn bộ nhớ cho các liên kết và thường có khả năng tận dụng cache kém hơn.

## Chi phí thao tác

| Thao tác | Mảng | LinkedList của Java |
|---|---|---|
| Truy cập theo chỉ số | O(1) | O(n) trong trường hợp xấu nhất |
| Tìm giá trị khi chưa biết vị trí | O(n) | O(n) |
| Duyệt toàn bộ | O(n) | O(n) bằng iterator |
| Chèn/xóa ở giữa | O(n), phải dịch phần tử | O(1) khi iterator đã ở vị trí cần thao tác |
| Tìm vị trí theo chỉ số rồi chèn/xóa | O(n) | O(n) để tìm, sau đó O(1) để nối liên kết |
| Bộ nhớ | Gọn, các phần tử nằm liên tiếp | Thêm bộ nhớ cho node và liên kết |

Mảng có kích thước cố định; chèn ở đây giả sử có chỗ trống hoặc có mảng mới để chứa kết quả.

## Chỉ số và iterator

Với LinkedList, mỗi lần gọi `get(i)` hoặc `set(i, value)` phải duyệt từ đầu hoặc cuối
đến vị trí đó. Nếu dùng chúng trong các vòng lặp lồng nhau như khi làm trên mảng,
thời gian có thể tăng tới O(n³).

Iterator giữ vị trí hiện tại, nên mỗi bước `next()` hoặc `previous()` mất O(1).
`ListIterator` còn hỗ trợ `add()`, `remove()` và `set()` tại vị trí đang duyệt.
Chi phí chèn O(1) không bao gồm thời gian di chuyển iterator đến vị trí chèn.

Tham khảo đặc điểm truy cập theo chỉ số trong [tài liệu Java LinkedList](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/LinkedList.html).
