class PricingService {
    public double calculateFare(double distance, String promoCode) {
        double baseFare = 12000;
        double pricePerKm = 10000;
        double total = baseFare + (distance * pricePerKm);
        
        if (promoCode != null && promoCode.equals("SINHVIENBK")) {
            System.out.println("[Pricing] Áp dụng mã khuyến mãi " + promoCode + ": Giảm 20%");
            total *= 0.8;
        }
        return total;
    }
}