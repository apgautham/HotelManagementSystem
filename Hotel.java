public class Hotel{
  String name;
  List<Room> rooms;
  List<Booking> bookings;
  Map<Customer, Booking> customerVsBooking;

getAvailbleRooms() : List<Room>

book(Customer, noOfPerson, checkinTime, checkoutTime)

cancelBooking(Customer)

checkIn(Customer)

checkOut(Customer)

generateInvoice(Customer)

processPayment(Cash, Invoice)
}
