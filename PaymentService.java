class PaymentService {
    public boolean processPayment(double amount, String method) {
        System.out.printf("[Payment] Đang xử lý khoản thanh toán %,.0f VNĐ qua %s...\n", amount, method);
        return true; // Trả về true nếu thanh toán thành công
    }
}