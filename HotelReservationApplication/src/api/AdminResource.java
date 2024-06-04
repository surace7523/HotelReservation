package api;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import model.customer.Customer;
import model.room.IRoom;
import service.customer.CustomerService;
import service.reservation.ReservationService;

public class AdminResource {
    private static AdminResource instance = new AdminResource();
    private CustomerService customerService = CustomerService.getInstance();
    private ReservationService reservationService = ReservationService.getInstance();

    private AdminResource() {
    }

    public static AdminResource getInstance() {
        return instance;
    }

    public Customer getCustomer(String email) {
        return this.customerService.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms) {
        Iterator var3 = rooms.iterator();

        while(var3.hasNext()) {
            IRoom room = (IRoom)var3.next();
            this.reservationService.addRoom(room);
        }

    }

    public Collection<IRoom> getAllRooms() {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        calendar.setTimeInMillis(Long.MAX_VALUE);
        Date futureDate = calendar.getTime();
        return this.reservationService.findRooms(currentDate, futureDate);
    }

    public Collection<Customer> getAllCustomers() {
        return this.customerService.getAllCustomers();
    }

    public void displayAllReservations() {
        this.reservationService.printAllReservation();
    }
}
