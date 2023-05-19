package model;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Company {
    // attributes
    private String name;
    private String nit;
    private String address;
    /*
     * userIDs and productsCount is used to save the latest position in the array
     * to a new item
     * so instead of looping through the array, the id attribute is the position,
     * going from complexity O(n) to O(1)
     */
    private int userIDs;
    private String userList;
    private String productsList;

    // constants
    public static final double PAYMONTH = 5; // USD

    // relations
    private ArrayList<User> users;
    // As this is a single-threaded program, Hashmap (Not syncronized) will perform
    // faster than HashTable(Syncronized)
    private HashMap<String, Product> products;

    // constructor
    public Company(String name, String nit, String address) {
        this.name = name;
        this.nit = nit;
        this.address = address;
        users = new ArrayList<>();
        userIDs = 1;
        userList = "";
        productsList = "";
        products = new HashMap<>();
        users.add(new BaseUser("name", "email", userIDs - 1, Calendar.getInstance()));
    }

    // getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public int getUserIDs() {
        return userIDs;
    }

    public void setUserIDs(int userIDs) {
        this.userIDs = userIDs;
    }

    public String getUserList() {
        return userList;
    }

    public void setUserList(String userList) {
        this.userList = userList;
    }

    public HashMap<String, Product> getProducts() {
        return products;
    }

    public void setProducts(HashMap<String, Product> products) {
        this.products = products;
    }

    public String getProductsList() {
        return productsList;
    }

    public void setProductsList(String productsList) {
        this.productsList = productsList;
    }

    // methods

    public boolean confirmOperation(char response) {
        boolean confirmation = false;
        try {
            response = Character.toUpperCase(response);
            confirmation = switch (response) {
                case 'Y' -> true;
                case 'N' -> false;
                default -> false;
            };
        } catch (Exception e) {
            confirmation = false;
        }
        return confirmation;
    }

    // User-related
    public boolean userExists(int id) {
        if (id <= 0 || id > userIDs)
            return false;
        return true;
    }

    public void addUserToList() {
        userList += users.get(userIDs).toString() + '\n';
    }

    public boolean addUser(String name, String email) {
        boolean done = false;
        User newUser = new BaseUser(name, email, userIDs, Calendar.getInstance());
        users.add(newUser);
        addUserToList();
        userIDs++;
        done = true;
        return done;
    }

    public String displayUser(int userID) {
        // early return
        if (!userExists(userID)) {
            return "User not found";
        }
        return users.get(userID).toString();
    }

    public String displayUserName(int userID) {
        // early return
        if (!userExists(userID)) {
            return "User not found";
        }
        return users.get(userID).getName();
    }

    public boolean user2Premium(int userID, String nickname, String avatar, String card) {
        var done = false;
        var user = users.get(userID);
        User newPremiumUser = new PremiumUser(user.getName(), user.getEmail(), user.getID(), user.getInitDate(),
                nickname, avatar, card, Calendar.getInstance().get(Calendar.MONTH), new double[12]);
        users.set(userID, newPremiumUser);
        if (users.get(userID) instanceof PremiumUser) {
            done = true;
        }
        return done;
    }

    // Product-related

    public String generateCode(String symbols) {
        String code = "";
        for (int i = 0; i < 3; i++) {
            code += symbols.charAt(ThreadLocalRandom.current().nextInt(0, symbols.length()));
        }
        return code;
    }

    public void addProductToList(String id) {
        productsList += products.get(id).toString() + '\n';
    }

    private BookGenre getBookGenre(int intGenre) {
        return switch (intGenre) {
            case 1 -> BookGenre.SCIENCE_FICTION;
            case 2 -> BookGenre.FANTASY;
            case 3 -> BookGenre.HISTORICAL_NOVEL;
            default -> null;
        };
    }

    public boolean registerBook(String name, Calendar publicationDate, int pages, String cover, double price,
            String review, int genre) {
        boolean done = false;
        String id = generateCode("ABCDEF1234567890");
        Product newBook = new Book(id, name, publicationDate, pages, cover, price, review, getBookGenre(genre), 0, 0);
        products.put(id, newBook);
        if (products.get(id) instanceof Book) {
            done = true;
        }
        addProductToList(id);
        return done;
    }

    public IssuanceFrequency getIssuanceFrequency(int intFreq) {
        return switch (intFreq) {
            case 1 -> IssuanceFrequency.YEARLY;
            case 2 -> IssuanceFrequency.MONTHLY;
            case 3 -> IssuanceFrequency.WEEKLY;
            case 4 -> IssuanceFrequency.DAILY;
            default -> null;
        };
    }

    public static MagazineCategory getMagazineCategory(int intCategory) {
        return switch (intCategory) {
            case 1 -> MagazineCategory.MISCELLANY;
            case 2 -> MagazineCategory.DESIGN;
            case 3 -> MagazineCategory.SCIENTIFIC;
            default -> null;
        };
    }

    public boolean registerMagazine(String name, Calendar publicationDate, int pages, String cover, double price,
            int category, int freq) {
        boolean done = false;
        String id = generateCode("ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
        Product newMagazine = new Magazine(id, name, publicationDate, pages, cover, price, getMagazineCategory(category), getIssuanceFrequency(freq), 0);
        products.put(id, newMagazine);
        if (products.get(id) instanceof Magazine) {
            done = true;
        }
        addProductToList(id);
        return done;
    }
}