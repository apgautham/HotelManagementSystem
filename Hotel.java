public class Hotel{
  String name;
  List<Room> rooms;
  List<Booking> bookings;
  Map<Customer, Booking> customerVsBooking;

  //setters and getters assumed

public List<Room> getAvailbleRooms(){
  List<Room> availableRooms = new ArrayList<>();
  for(Room room: rooms){
    if(room.isAvailable()){
      availableRooms.add(room);
    }
  }
  return availableRooms;
}

synchronized public book(Customer customer, int noOfPerson, long checkinTime, long checkoutTime){
  int bookingID = getBookingID();//generate random ID based on some logic like get current Max (bookingID)+1
  BookingStatus bookingStatus = BookingStatus.IN_PROGRESS;
  Booking booking = new Booking(bookingID, customer, noOfPerson, bookingStatus);// have not allocated rooms until yet
  
  int roomsRequired = getNumerOfRoomsRequired(noOfPerson); //Handle logic of how many rooms require for how many person
  if(getAvailableRooms().size()<roomsRequired){
    System.out.println("Unable to allocate rooms since we have only "+getAavailableRooms().size());
    bookingStatus = BookingStatus.FAILURE;
    booking.setStatus(bookingStatus);
    return;
  }
    List<Room> availableRooms = getAvailableRooms();
    List<Room> filledRooms = new ArrayList<>();
    for(int i=0;i<roomsRequired;i++){
      Room room = availableRooms.get(i);
      room.filled();
      filledRooms.add(room);
    }
    bookingStatus = BookingStatus.SUCESS;
    booking.setStatus(bookingStatus);
    booking.setRooms(filledRooms);
}

public void cancelBooking(Customer customer){
  Booking booking = customerVsBooking.get(customer);
  booking.setStatus(BookingStatus.CANCELLED);
  List<Room> rooms = booking.getRooms();
  for(Room room : rooms){
    room.available();
  }
}

public void checkIn(Customer customer){
  Booking booking = customerVsBooking.get(customer);
  if(booking==null || booking.getStatus()!=Booking.CONFIRMED){
    System.out.println("Booking is not confirmed or no such booking available. Please book rooms first !!");
  }
  booking.setCheckinTime(System.currentTimeMillies());
  booking.setStatus(Booking.CHECKED_IN);
}

public void checkOut(Customer customer){
  Booking booking = customerVsBooking.get(customer);
  booking.setCheckinTime(System.currentTimeMillies());
  booking.setStatus(Booking.CHECKED_OUT);
}

public void generateInvoice(Customer customer){
  Booking booking = customerVsBooking.get(customer);
  //validations like if checkout not happened, checkout first
  Invoice invoice = new Invoice(booking);
  invoice.generateInvoice();
}

public void processPayment(PaymentMode.Cash, Invoice invoice){
  //based on invoice, process payment
  //we can make the payment mode as separate interface like Cash or Card and implement processPayment in child classes instead of having ENUMs.
  System.out.println("payment processed");
}
}
