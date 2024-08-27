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

checkIn(Customer)

checkOut(Customer)

generateInvoice(Customer)

processPayment(Cash, Invoice)
}
