class RideBookingFacade {
    private LocationService locationService;
    private PricingService pricingService;
    private DriverMatchingService driverService;
    private PaymentService paymentService;
    private NotificationService notificationService;

    // Constructor khởi tạo các hệ thống con
    public RideBookingFacade() {
        this.locationService = new LocationService();
        this.pricingService = new PricingService();
        this.driverService = new DriverMatchingService();
        this.paymentService = new PaymentService();
        this.notificationService = new NotificationService();
    }

    // Phương thức gộp (Mặt tiền) cho Client sử dụng
    public boolean bookRide(String pickup, String destination, String paymentMethod, String promoCode) {
        System.out.println("\n=== BẮT ĐẦU QUÁ TRÌNH ĐẶT XE ===");
        
        // 1. Tính khoảng cách
        double distance = locationService.getDistance(pickup, destination);
        
        // 2. Tính tiền
        double fare = pricingService.calculateFare(distance, promoCode);
        System.out.printf("-> Tổng tiền dự kiến: %,.0f VNĐ\n", fare);
        
        // 3. Tìm xe
        String driver = driverService.findDriver(pickup);
        if (driver == null || driver.isEmpty()) {
            System.out.println("Rất tiếc, hiện tại không có tài xế nào rảnh. Vui lòng thử lại sau!");
            return false;
        }
        
        // 4. Giữ tiền/Thanh toán
        boolean paymentStatus = paymentService.processPayment(fare, paymentMethod);
        
        // 5. Thông báo
        if (paymentStatus) {
            String msg = "Tài xế " + driver + " đang đến " + pickup + " để đón bạn. Vui lòng chuẩn bị!";
            notificationService.sendMessage(msg);
            System.out.println("=== ĐẶT XE THÀNH CÔNG ===\n");
            return true;
        } else {
            System.out.println("=== ĐẶT XE THẤT BẠI DO LỖI THANH TOÁN ===\n");
            return false;
        }
    }
}