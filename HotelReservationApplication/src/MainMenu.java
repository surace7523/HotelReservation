
import api.HotelResource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import model.customer.Customer;
import model.reservation.Reservation;
import model.room.IRoom;

public class MainMenu {
    public MainMenu() {
    }

    public static void main(String[] args) {
        boolean keepRunning = true;
        Scanner scanner = new Scanner(System.in);

        while(keepRunning) {
            try {
                displayMainMenu();
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        findAndReserveRoom();
                        break;
                    case 2:
                        seeMyReservations();
                        break;
                    case 3:
                        createAnAccount();
                        break;
                    case 4:
                        AdminMenu.displayAdminMenu();
                        break;
                    case 5:
                        keepRunning = false;
                        break;
                    default:
                        System.out.println("Invalid option, please try again.");
                }
            } catch (Exception var4) {
                System.out.println("Invalid input, please enter a number between 1 and 5.");
            }
        }

    }

    private static void displayMainMenu() {
        System.out.println("Welcome to the Hotel Reservation Application");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("Please select a number for the menu option:");
    }

    private static void findAndReserveRoom() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            System.out.println("Enter Check-In Date (dd/MM/yyyy):");
            Date checkInDate = dateFormat.parse(scanner.nextLine());
            System.out.println("Enter Check-Out Date (dd/MM/yyyy):");
            Date checkOutDate = dateFormat.parse(scanner.nextLine());
            Collection<IRoom> availableRooms = HotelResource.getInstance().findARoom(checkInDate, checkOutDate);
            if (availableRooms.isEmpty()) {
                System.out.println("No rooms available for the selected dates.");
            } else {
                Iterator var6 = availableRooms.iterator();

                while(var6.hasNext()) {
                    IRoom room = (IRoom)var6.next();
                    System.out.println(room);
                }

                System.out.println("Enter room number to reserve:");
                String roomNumber = scanner.nextLine();
                IRoom room = HotelResource.getInstance().getRoom(roomNumber);
                System.out.println("Enter your email:");
                String email = scanner.nextLine();
                Customer customer = HotelResource.getInstance().getCustomer(email);
                if (customer == null) {
                    System.out.println("No customer found with this email. Please create an account first.");
                } else {
                    HotelResource.getInstance().bookARoom(email, room, checkInDate, checkOutDate);
                    System.out.println("Room reserved successfully.");
                }
            }
        } catch (ParseException var9) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy.");
        }

    }

    private static void seeMyReservations() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your email:");
        String email = scanner.nextLine();
        Collection<Reservation> reservations = HotelResource.getInstance().getCustomersReservations(email);
        if (reservations != null && !reservations.isEmpty()) {
            Iterator var4 = reservations.iterator();

            while(var4.hasNext()) {
                Reservation reservation = (Reservation)var4.next();
                System.out.println(reservation);
            }
        } else {
            System.out.println("No reservations found for this email.");
        }

    }

    private static void createAnAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter First Name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter Last Name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter Email:");
        String email = scanner.nextLine();

        try {
            HotelResource.getInstance().createACustomer(email, firstName, lastName);
            System.out.println("Account created successfully.");
        } catch (IllegalArgumentException var5) {
            System.out.println("Error: " + var5.getMessage());
        }

    }
}
