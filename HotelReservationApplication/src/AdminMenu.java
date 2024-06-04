//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import api.AdminResource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import model.customer.Customer;
import model.room.IRoom;
import model.room.Room;
import model.room.enums.RoomType;

public class AdminMenu {
    public AdminMenu() {
    }

    public static void displayAdminMenu() {
        boolean keepRunning = true;
        Scanner scanner = new Scanner(System.in);

        while(keepRunning) {
            try {
                displayMenuOptions();
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        seeAllCustomers();
                        break;
                    case 2:
                        seeAllRooms();
                        break;
                    case 3:
                        seeAllReservations();
                        break;
                    case 4:
                        addARoom();
                        break;
                    case 5:
                        keepRunning = false;
                        break;
                    default:
                        System.out.println("Invalid option, please try again.");
                }
            } catch (Exception var3) {
                System.out.println("Invalid input, please enter a number between 1 and 5.");
            }
        }

    }

    private static void displayMenuOptions() {
        System.out.println("Admin Menu");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
        System.out.println("Please select a number for the menu option:");
    }

    private static void seeAllCustomers() {
        Collection<Customer> customers = AdminResource.getInstance().getAllCustomers();
        if (customers != null && !customers.isEmpty()) {
            Iterator var2 = customers.iterator();

            while(var2.hasNext()) {
                Customer customer = (Customer)var2.next();
                System.out.println(customer);
            }
        } else {
            System.out.println("No customers found.");
        }

    }

    private static void seeAllRooms() {
        Collection<IRoom> rooms = AdminResource.getInstance().getAllRooms();
        if (rooms != null && !rooms.isEmpty()) {
            Iterator var2 = rooms.iterator();

            while(var2.hasNext()) {
                IRoom room = (IRoom)var2.next();
                System.out.println(room);
            }
        } else {
            System.out.println("No rooms found.");
        }

    }

    private static void seeAllReservations() {
        AdminResource.getInstance().displayAllReservations();
    }

    private static void addARoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Room Number:");
        String roomNumber = scanner.nextLine();
        System.out.println("Enter Room Price:");
        Double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter Room Type (1 for SINGLE, 2 for DOUBLE):");
        RoomType roomType = Integer.parseInt(scanner.nextLine()) == 1 ? RoomType.SINGLE : RoomType.DOUBLE;
        IRoom room = new Room(roomNumber, price, roomType);
        List<IRoom> rooms = new ArrayList();
        rooms.add(room);
        AdminResource.getInstance().addRoom(rooms);
        System.out.println("Room added successfully.");
    }
}
