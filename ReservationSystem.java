//This Online Reservation System will include all the necessary fields which are required during
//online reservation system. This Online Reservation System will be easy to use and can be used by
//any person. The basic idea behind this project is to save data in a central database which can be
//accessed by any authorize person to get information and saves time and burden which are being
//faced by their customers.

//Modules:
//Login Form – To access this Online Reservation System, each user should have a valid login id and
//password. After providing the correct login id and password, users will able to access the main
//system.

//Reservation System – Under reservation form users will have to fill the necessary details such as
//their basic details, train number, train name will automatically come in the box, class type, date of
//journey, from (place) to destination and after that, users will have to press insert button.

//Cancellation Form – If passengers want to cancel their tickets then they have to provide their
//PNR number and after submitting it, this will display the entire information related to that
//particular PNR number. If users want to confirm their cancellation, in this case they have to press
//OK button.

//CODE:

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class User {
    private String userId;
    private String password;

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }
}

class Reservation {
    private int id;
    private String name;
    private String date;
    private int numberOfGuests;

    public Reservation(int id, String name, String date, int numberOfGuests) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.numberOfGuests = numberOfGuests;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }
}

class ReservationSystem {
    private List<Reservation> reservations = new ArrayList<>();
    private int nextId = 1;

    public Reservation makeReservation(String name, String date, int numberOfGuests) {
        Reservation reservation = new Reservation(nextId++, name, date, numberOfGuests);
        reservations.add(reservation);
        return reservation;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public Reservation getReservationById(int id) {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == id) {
                return reservation;
            }
        }
        return null;
    }

    public boolean cancelReservation(int id) {
        Reservation reservation = getReservationById(id);
        if (reservation != null) {
            reservations.remove(reservation);
            return true;
        }
        return false;
    }
}

class ReservationSystemUI {
    private ReservationSystem reservationSystem = new ReservationSystem();
    private List<User> users = new ArrayList<>();

    public ReservationSystemUI() {
        // Add some sample users (you can replace this with your actual user management)
        users.add(new User("us1", "pass1"));
        users.add(new User("us2", "pass2"));
    }

    public User authenticateUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("                              WELCOME TO ONLINE RESERVATION SYSTEM                    ");
        System.out.print(" Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print(" Enter Password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUserId().equals(userId) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void start() {
        User authenticatedUser = null;
        while (authenticatedUser == null) {
            authenticatedUser = authenticateUser();
            if (authenticatedUser == null) {
                System.out.println("Invalid User ID or Password. Please try again.");
            }
        }

        System.out.println("Welcome, " + authenticatedUser.getUserId() + "!");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Make a reservation");
            System.out.println("2. View all reservations");
            System.out.println("3. Cancel a reservation");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Date_of_travel: ");
                    String date = scanner.nextLine();
                    System.out.print("Number of guests: ");
                    int numberOfGuests = scanner.nextInt();
                    scanner.nextLine();

                    Reservation reservation = reservationSystem.makeReservation(name, date, numberOfGuests);
                    System.out.println("Reservation made with ID " + reservation.getId());
                    break;
                case 2:
                    System.out.println("Reservations:");
                    for (Reservation r : reservationSystem.getReservations()) {
                        System.out.println("Reservation ID => " + r.getId());
                        System.out.println("Name Of the customer => " + r.getName());
                        System.out.println("Number Of Guests => " + r.getNumberOfGuests());
                        System.out.println("Date_of_Travel =>  " + r.getDate());
                    }
                    break;
                case 3:
                    System.out.print("Reservation ID to cancel: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    if (reservationSystem.cancelReservation(id)) {
                        System.out.println("Reservation canceled");
                    } else {
                        System.out.println("Reservation not found");
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        ReservationSystemUI obj = new ReservationSystemUI();
        obj.start();
    }
}