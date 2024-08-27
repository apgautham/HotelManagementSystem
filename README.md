Customer

+Name

+Address

+MobileNumber

Room

+roomNumber

+occupied

Booking

+id

+Customer

+personCount

+List<Room>

+checkinTime

+checkoutTime

+bookingStatus

Hotel

+List<Room>

+name

+List<Booking>

+Map<Customer,Booking>

+getAvailbleRooms() : List<Room>

+book(Customer, noOfPerson, checkinTime, checkoutTime)

+cancelBooking(Customer)

+checkIn(Customer)

+checkOut(Customer)

+generateInvoice(Customer)

+processPayment(Cash, Invoice)

Payment

+Invoice

+paymentMode

+paymentStatus

processPayment(Cash, Invoice)

Invoice

+Booking

+totalAmount

+generateInvoice()

Enum paymentMode{

Cash,CreditCard,DebitCard

}

Enum paymentStatus{

IN_PROGRESS, SUCESS, FAILURE

}

