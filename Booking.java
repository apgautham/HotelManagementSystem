public class Booking{
  int id;
  Customer customer;
  int personCount;
  List<Room> rooms;
  long checkinTime=-1;
  long checkoutTime=-1;
  Enum BookingStatus{IN_PROGRESS, SUCCESS, FAILURE};
}
