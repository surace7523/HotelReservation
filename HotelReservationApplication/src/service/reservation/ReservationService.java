
package service.reservation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import model.customer.Customer;
import model.reservation.Reservation;
import model.room.IRoom;

public class ReservationService {
    private static ReservationService instance = new ReservationService();
    private Map<String, IRoom> rooms = new HashMap();
    private Map<String, Collection<Reservation>> reservations = new HashMap();

    private ReservationService() {
    }

    public static ReservationService getInstance() {
        return instance;
    }

    public void addRoom(IRoom room) {
        this.rooms.put(room.getRoomNumber(), room);
    }

    public IRoom getARoom(String roomId) {
        return (IRoom)this.rooms.get(roomId);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        ((Collection)this.reservations.computeIfAbsent(customer.getEmail(), (k) -> {
            return new HashSet();
        })).add(reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        List<IRoom> availableRooms = new ArrayList(this.rooms.values());
        Iterator var5 = this.reservations.values().iterator();

        while(var5.hasNext()) {
            Collection<Reservation> reservationList = (Collection)var5.next();
            Iterator var7 = reservationList.iterator();

            while(var7.hasNext()) {
                Reservation reservation = (Reservation)var7.next();
                if (checkInDate.before(reservation.getCheckOutDate()) && checkOutDate.after(reservation.getCheckInDate())) {
                    availableRooms.remove(reservation.getRoom());
                }
            }
        }

        return availableRooms;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        return (Collection)this.reservations.get(customer.getEmail());
    }

    public void printAllReservation() {
        Iterator var2 = this.reservations.values().iterator();

        while(var2.hasNext()) {
            Collection<Reservation> reservationList = (Collection)var2.next();
            Iterator var4 = reservationList.iterator();

            while(var4.hasNext()) {
                Reservation reservation = (Reservation)var4.next();
                System.out.println(reservation);
            }
        }

    }
}
