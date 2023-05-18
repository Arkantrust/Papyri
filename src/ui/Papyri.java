package ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import model.Company;

public class Papyri {
    private Scanner in;
    private Company readX;

    public Papyri() {
        in = new Scanner(System.in);
        /*
         * During development this inputs arent required
         * TODO: Uncomment this code block
         * System.out.print("Company name: ");
         * String name = in.nextLine();
         * System.out.print("Company nit: ");
         * String nit = in.nextLine();
         * System.out.print("Company address: ");
         * String address = in.nextLine();
         * readX = new Company(name, nit, address);
         */
        readX = new Company("ReadX", "9008675399", "Cl 57 #23 - 35");
    }

    public Calendar readDate(String requiredDate) {
        boolean run = true;
        Calendar date = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        while (run) {
            System.out.print("Enter " + requiredDate + " (dd-mm-yyyy): ");
            String dateString = in.nextLine();

            try {
                date.setTime(dateFormat.parse(dateString));
                run = false;
            } catch (Exception e) {
                System.out.println("Invalid date format.");
            }
        }
        return date;
    }

    // -----------------------------------User Management-------------------------------------------------------
    public void registerUser() {
        System.out.print("Enter name: ");
        String name = in.nextLine();
        System.out.print("Enter email: ");
        String email = in.nextLine();

        if (readX.addUser(name, email)) {
            System.out.println("User registered succesfully!");
        } else {
            System.out.println("Something went wrong. Try again later.");
        }
        System.out.println();
        System.out.println(readX.displayUser(readX.getUserIDs() - 1));
    }

    public void searchUser() {
        System.out.print("User's ID: ");
        var id = Integer.valueOf(in.nextLine());
        System.out.println(readX.displayUser(id));
    }

    public void showUsers() {
        System.out.println(readX.getUserList());
    }

    public void upgradeUserToPremium() {
        System.out.print("User's ID: ");
        var id = Integer.valueOf(in.nextLine());
        // Verify if user exists
        if (!readX.userExists(id)) {
            System.out.println("User not found.");
            return;
        }
        // Confirming upgrade
        System.out.print(readX.displayUserName(id) + " will be upgraded to premium. Which costs $5. Continue? Y/N ");
        char confirmation = in.nextLine().charAt(0);
        if (!readX.confirmOperation(confirmation)) {
            System.out.println(readX.displayUserName(id) + " will remain as basic");
            System.out.println();
            // early return exits (cancels) the method so the user is not upgraded
            return;
        }
        System.out.print("Nickname: ");
        String nickname = in.nextLine();
        System.out.print("Avatar: ");
        String avatar = in.nextLine();
        System.out.print("Credit/Debit Card number: ");
        String card = in.nextLine();
        if (readX.user2Premium(id, nickname, avatar, card)) {
            System.out.println(readX.displayUserName(id) + " is now a premium user.");
        } else {
            System.out.println("Something went wrong. Try again later.");
        }
    }

    public void openUserManagement() {
        System.out.println("---------------------------------------User Management---------------------------------------");
        boolean run = true;
        while (run) {
            System.out.println("1. Register user");
            System.out.println("2. Search user (ID)");
            System.out.println("3. Show users");
            System.out.println("4. Upgrade user to premium");
            System.out.println("0. Back");
            System.out.print("> ");
            int select = Integer.valueOf(in.nextLine());
            switch (select) {
                case 0 -> run = false;
                case 1 -> registerUser();
                case 2 -> searchUser();
                case 3 -> showUsers();
                case 4 -> upgradeUserToPremium();
            }
        }
    }

    // ---------------------------Product Management-------------------------------------------------------------

    public void registerBook() {
        System.out.print("Enter name: ");
        String name = in.nextLine();
        Calendar publicationDate = readDate("publication date");
        System.out.print("Enter number of pages: ");
        int pages = Integer.valueOf(in.nextLine());
        System.out.print("Enter URL to cover: ");
        String coverURL = in.nextLine();
        System.out.print("Enter price: ");
        double price = Double.valueOf(in.nextLine());
        System.out.print("Enter review: ");
        String review = in.nextLine();
        System.out.println("Enter genre: ");
        System.out.println("1. Science fiction");
        System.out.println("2. Fantasy");
        System.out.println("3. Historical novel");
        System.out.print("> ");
        int genre = Integer.valueOf(in.nextLine());

        if (readX.registerBook(name, publicationDate, pages, coverURL, price, review, genre)) {
            System.out.println("Book registered succesfully!");
        } else {
            System.out.println("Something went wrong. Try again later.");
        }
    }
    /*
     * System.out.println("2. Generate payment");
     * System.out.println("3. Generate surprise");
     */

    public void openProductManagement() {
        System.out.println("---------------------------------------Product Management---------------------------------------");
        boolean run = true;
        while (run) {
            System.out.println("1. Register book");
            System.out.println("2. Register magazine");
            System.out.println("3. Edit product");
            System.out.println("4. Delete product");
            System.out.println("0. Back");
            System.out.print("> ");
            int select = Integer.valueOf(in.nextLine());
                switch (select) {
                    case 0 -> run = false;
                    case 1 -> registerBook();
                }
        }
    }

    public static void main(String[] args) {
        System.out.println("---------------------------------------Papyri---------------------------------------");
        Papyri objPapyri = new Papyri();
        boolean run = true;
        int select = 0;

        while (run) {
            System.out.println("---------------------------------------Home---------------------------------------");
            System.out.println("1. Manage users");
            System.out.println("2. Manage Products");
            System.out.println("0. Exit");
            System.out.print("> ");
            try {
                select = Integer.valueOf(objPapyri.in.nextLine());
                switch (select) {
                    case 0 -> run = false;
                    case 1 -> objPapyri.openUserManagement();
                    case 2 -> objPapyri.openProductManagement();
                }
            } catch (Exception e) {
                System.out.println("Invalid input.");
            }
        }
    }
}
