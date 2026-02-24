public class Client {
    public static void main(String[] args) {
        RideBookingFacade bookingFacade = new RideBookingFacade();
        bookingFacade.bookRide("Bách Khoa", "Dĩ An", "Ví điện tử", "SINHVIENBK");
    }
}
