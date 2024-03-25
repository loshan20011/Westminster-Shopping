
import Interfaces.ShoppingManager;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class WestminsterShoppingManager implements ShoppingManager {
    static WestminsterShoppingManager shoppingManager;
    static Scanner input = new Scanner(System.in);                                     // INSTANTIATING TO GET USER INPUTS
    static ArrayList<Product> productArrayList = new ArrayList<>();             // ARRAY LIST TO STORE ALL CREATED PRODUCTS
    static String[] clothingSizes = {"XXXS", "XXS", "XS", "S", "M", "L", "XL", "XXL", "XXXL"};        // ARRAY TO VALIDATE USER SIZE INPUT

    static boolean sameProductAvailable = false;                                // VARIABLE TO STORE BOOLEAN VALUE IF SAME PRODUCT AVAILABLE IN FILE

    public static void main(String[] args) {
        shoppingManager = new WestminsterShoppingManager();
        shoppingManager.openMenu();
    }

    public void openMenu() {
        int option = 10;
        do {
            try {
                System.out.println("""
                        Choose an option from the menu!!!\s
                        1 - Add a new product\s
                        2 - Delete a product\s
                        3 - Print the list of the products\s
                        4 - Save in a file\s
                        5 - Read & Re-arrange the file\s
                        6 - Clear the list\s
                        7 - Open GUI\s
                        0 - Exit
                        Enter the number here:\s""");

                option = Integer.parseInt(input.next());                                           // OPTION VARIABLE TO CHOOSE AN OPTION IN THE MENU

                switch (option) {
                    case 1:
                        shoppingManager.addNewProduct();                                            // INVOKING METHOD TO ADD PRODUCTS
                        break;
                    case 2:
                        shoppingManager.promptingDeleteProductID();
                        break;
                    case 3:
                        shoppingManager.printList();
                        break;
                    case 4:
                        shoppingManager.createFile();
                        break;
                    case 5:
                        shoppingManager.readFile();
                        break;
                    case 6:
                        shoppingManager.clearList();
                        break;
                    case 7:
                        shoppingManager.openGUI();
                        break;
                    case 0:
                        System.out.println("Thank you for using our platform, Have a nice day..");
                        break;
                    default:
                        System.out.println("Wrong number entered!");
                }
            } catch (NumberFormatException error) {
                System.out.println("Invalid input! Please enter a number.");

            } catch (InputMismatchException error) {
                System.out.println("Invalid input!");
            }
        } while (option != 0);
    }


    public void addNewProduct() {
        String productType;

        if (productArrayList.size() < 50) {                                     // MAX LIMIT PRODUCTS CAN BE ADDED IS FIFTY
            while (true) {
                try {
                    System.out.print("\nChoose your product type \n\t1 - for clothing \n\t2 - for electronics \n\t0 - go to back\nEnter the number here:  ");
                    String productTypeInput = input.next();  // READ THE INPUT AS A STRING TO AVOID INPUT LOOP

                    if (productTypeInput.equals("0")) {
                        break;
                    }

                    int productTypeNo = Integer.parseInt(productTypeInput);         // CONVERT THE STRING INPUT TO INT SO IF THERE IS AN ERROR IT WILL BE ENCOUNTERED IN TRY CATCH

                    if (productTypeNo == 1 || productTypeNo == 2) {
                        String productName = validateStringInput("name", "Apple");

                        if (!productName.equals("0")) {
                            double productPrice = validatePrice();

                            if (!(productPrice == 0)) {
                                int itemCount = validateItemCount();

                                if (itemCount != 0) {
                                    switch (productTypeNo) {

                                        case 1:
                                            productType = "Clothing";
                                            String productSize = validateStringInput("size", "XXS");

                                            if (!productSize.equals("0")) {
                                                String productColor = validateStringInput("colour", "Red");

                                                if (!productColor.equals("0")) {
                                                    Clothing cloth = new Clothing(productName, productPrice, productType, productColor, productSize, itemCount);
                                                    productArrayList.add(cloth);
                                                    System.out.println("Product added successfully\n");
                                                    return;
                                                }
                                            }
                                            break;

                                        case 2:
                                            productType = "Electronics";
                                            String productBrand = validateStringInput("brand", "Asus");

                                            if (!productBrand.equals("0")) {
                                                String productWarrantyPeriod = validateWarranty();

                                                if (!productWarrantyPeriod.equals("0")) {
                                                    Electronics electronic = new Electronics(productName, productPrice, productType, productBrand, productWarrantyPeriod, itemCount);
                                                    productArrayList.add(electronic);
                                                    System.out.println("Product added successfully\n");
                                                    return;
                                                }
                                            }
                                            break;
                                    }
                                }
                            }
                        }
                    } else {
                        System.out.println("Wrong number entered!");
                    }
                } catch (NumberFormatException error) {
                    System.out.println("Invalid input! Please enter a number.");
                } catch (InputMismatchException error) {
                    System.out.println("Invalid input!");
                }
            }
        } else {
            System.out.println("Already fifty product added to the list, kindly remove and add!");
        }
    }

    /**
     * Method to prompt the user input and to validate the input
     *
     * @param wording - print the following message when requesting input from the user, as multiple inputs are required
     * @return user's input
     */
    public static String validateStringInput(String wording, String example) {

        while (true) {
            // TRY CATCH TO HANDLE WRONG INPUTS
            try {
                if (wording.equals("size")) {                                                                    // THIS PRINTS ONLY WHEN CHECKING SIZE
                    System.out.println("Sizes are ('XXXS', 'XXS', 'XS', 'S', 'M', 'L', 'XL', 'XXL', 'XXXL')");
                }

                System.out.print("Enter your product " + wording + " Example - " + example + " (Enter 0 to go back): ");
                String userInput = input.next();

                if (userInput.equals("0")) {
                    return userInput;
                }

                if (!containsString(userInput)) {                                                      // INVOKING THE METHOD TO CHECK ALL STRING
                    System.out.println("Product " + wording + " cannot contain special characters or numbers!");
                } else {
                    if (wording.equals("size")) {
                        if (!validateSize(userInput)) {                                                  // INVOKING SIZE VALIDATOR
                            System.out.println("Wrong size input!");
                            continue;
                        }
                    }
                    return userInput;
                }
            } catch (
                    InputMismatchException error) {                                                     // ERROR AND EXCEPTION HANDLER
                System.out.println("Invalid input!");
            }
        }
    }

    /**
     * Method to check whether user input contains only string
     *
     * @param userInput - String which need to be validated
     * @return - true if there is only string and false if there is numbers or special characters
     */
    public static boolean containsString(String userInput) {
        for (int i = 0; i < userInput.length(); i++) {                                              // LOOPS THROUGH THE STRING
            char letter = userInput.charAt(i);
            if (!Character.isLetter(letter)) {                                                      // INVOKING A METHOD TO CHECK THE CHAR IS LETTER OR NOT
                return false;
            }
        }
        return true;
    }

    /**
     * Method to check validate product price
     *
     * @return product price
     */
    public static double validatePrice() {
        while (true) {
            // TRY CATCH TO HANDLE WRONG INPUTS
            try {
                System.out.print("Enter your product price (Enter 0 to go back): ");
                String productPriceString = input.next();

                double productPrice = Double.parseDouble(productPriceString);

                if (productPrice == 0) {
                    return productPrice;
                } else {
                    return Math.round(productPrice * 100.0) / 100.0;                                     // ROUND THE PRICE INTO TWO DECIMAL POINTS
                }

            } catch (NumberFormatException error) {
                System.out.println("Invalid input! Enter a number!");

            } catch (
                    InputMismatchException error) {                                                     // ERROR AND EXCEPTION HANDLER
                System.out.println("Invalid input!");
            }
        }
    }

    public static int validateItemCount() {
        while (true) {
            // TRY CATCH TO HANDLE WRONG INPUTS
            try {
                System.out.print("Enter your product count (Enter 0 to go back): ");
                String productItemString = input.next();

                // ROUND THE PRICE INTO TWO DECIMAL POINTS
                return Integer.parseInt(productItemString);

            } catch (NumberFormatException error) {
                System.out.println("Invalid input! Enter a number!");

            } catch (
                    InputMismatchException error) {                                                     // ERROR AND EXCEPTION HANDLER
                System.out.println("Invalid input!");
            }
        }
    }

    /**
     * Method to validate the size of the clothing
     *
     * @param size - size given by the user
     * @return boolean if size input is correct
     */
    public static boolean validateSize(String size) {
        size = size.toUpperCase();                                          // CONVERTS USER'S INPUT TO UPPERCASE
        for (String element : clothingSizes) {
            if (size.equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to get and validate the warranty period
     *
     * @return the string of validity period
     */
    public static String validateWarranty() {
        while (true) {
            // TRY CATCH TO HANDLE WRONG INPUTS
            try {
                int productWarrantyYears = validateWarrantyYearAndMonth("years", "25");

                if (productWarrantyYears != -1) {
                    int productWarrantyMonths = validateWarrantyYearAndMonth("months", "12");

                    if (productWarrantyMonths == -1) {
                        return "0";
                    } else {
                        return (productWarrantyYears + " years & " + productWarrantyMonths + " months");
                    }
                } else {
                    return "0";
                }
            } catch (NumberFormatException error) {
                System.out.println("Invalid input! Enter a number!");

            } catch (
                    InputMismatchException error) {                                                     // ERROR AND EXCEPTION HANDLER
                System.out.println("Invalid input!");
            }
        }
    }

    public static int validateWarrantyYearAndMonth(String wording, String max) {
        while (true) {
            // TRY CATCH TO HANDLE WRONG INPUTS
            try {
                System.out.print("Enter your product warranty period in " + wording + " within 0 - " + max + " (Enter -1 to go back): ");
                int productWarrantyPeriod = Integer.parseInt(input.next());

                if (wording.equals("months") && (productWarrantyPeriod >= 0 && productWarrantyPeriod <= 12)) {
                    return productWarrantyPeriod;
                } else if (wording.equals("years") && (productWarrantyPeriod >= 0 && productWarrantyPeriod <= 25)) {
                    return productWarrantyPeriod;
                } else if (productWarrantyPeriod == -1) {
                    return -1;
                } else {
                    System.out.println("invalid " + wording + "!");
                }

            } catch (NumberFormatException error) {
                System.out.println("Invalid input! Enter a number!");

            } catch (
                    InputMismatchException error) {                                                     // ERROR AND EXCEPTION HANDLER
                System.out.println("Invalid input!");
            }
        }
    }

    public void promptingDeleteProductID() {
        while (true) {
            // TRY CATCH TO HANDLE WRONG INPUTS
            try {
                if (productArrayList.isEmpty()) {
                    System.out.println("No products added");
                    break;
                } else {
                    System.out.print("Enter your product ID you want to delete (Enter 0 to go back): ");
                    String productID = input.next();

                    if (productID.equals("0")) {
                        return;
                    } else {
                        boolean productAvailable = false;
                        for (Product product : productArrayList) {
                            if (productID.equals(product.getProductID())) {
                                productAvailable = true;
                                String deleteOption = shoppingManager.deleteProductOption();

                                if (deleteOption.equalsIgnoreCase("yes")) {
                                    productArrayList.remove(product);
                                    System.out.println(product);
                                    System.out.println("Product removed successfully..\n\n" + productArrayList.size() +
                                            " products left in the system\nRemaining products are");
                                    shoppingManager.printList();
                                    return;


                                } else if (deleteOption.equalsIgnoreCase("no")) {
                                    int deleteCount = deleteProductCount();

                                    if (deleteCount != 0) {
                                        if (productID.equals(product.getProductID())) {
                                            if (product.getAvailableProductsCount() > deleteCount) {
                                                int finalCount = product.getAvailableProductsCount() - deleteCount;
                                                product.setAvailableProductsCount(finalCount);
                                                System.out.println("Remaining count of the product is " + finalCount);
                                            } else if (product.getAvailableProductsCount() < deleteCount) {
                                                System.out.println("Only " + product.getAvailableProductsCount() + " available!");
                                                System.out.println(product.getAvailableProductsCount() + " has removed.");
                                                productArrayList.remove(product);
                                            } else {
                                                System.out.println(product.getAvailableProductsCount() + " items has been removed.");
                                                productArrayList.remove(product);
                                            }
                                            System.out.println(productArrayList.size() + " products left in the system\nRemaining products are");
                                            shoppingManager.printList();
                                            return;
                                        }
                                    } else {
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            }
                        }
                        if (!productAvailable) {
                            System.out.println("Product ID is wrong");
                        }
                    }
                }
            } catch (NumberFormatException error) {
                System.out.println("Invalid input!");

            } catch (
                    InputMismatchException error) {                                                     // ERROR AND EXCEPTION HANDLER
                System.out.println("Invalid input!");
            }
        }
    }

    public String deleteProductOption() {
        while (true) {
            // TRY CATCH TO HANDLE WRONG INPUTS
            try {
                System.out.println("Do you want to delete entire product Yes / No (Enter 0 to go back): ");
                String deleteOption = input.next();

                if (deleteOption.equalsIgnoreCase("Yes") || deleteOption.equalsIgnoreCase("No") || deleteOption.equalsIgnoreCase("0")) {
                    return deleteOption;
                } else {
                    System.out.println("Entered wrong choice!");
                }

            } catch (NumberFormatException error) {
                System.out.println("Invalid input!");

            } catch (
                    InputMismatchException error) {                                                     // ERROR AND EXCEPTION HANDLER
                System.out.println("Invalid input!");
            }
        }
    }

    public int deleteProductCount() {
        while (true) {
            // TRY CATCH TO HANDLE WRONG INPUTS
            try {
                System.out.println("Enter the item count you want to delete (Enter 0 to go back): ");
                String itemCount = input.next();
                return Integer.parseInt(itemCount);

            } catch (NumberFormatException error) {
                System.out.println("Invalid input!");

            } catch (
                    InputMismatchException error) {                                                     // ERROR AND EXCEPTION HANDLER
                System.out.println("Invalid input!");
            }
        }
    }

    public void printList() {
        if (!productArrayList.isEmpty()) {
            Collections.sort(productArrayList);

            for (Product product : productArrayList) {
                System.out.println(product);
            }
            System.out.println();
        } else {
            System.out.println("No products added!");
        }
    }

    /**
     * Method to save the products in the file
     */
    public void createFile() {
        try {
            File file = new File("product-data.txt");

            if (file.createNewFile()) {
                System.out.println("File created successfully");
            } else {
                System.out.println("File already exists");
            }

            try {
                FileWriter fileWriter = new FileWriter("product-data.txt");
                BufferedWriter writer = new BufferedWriter(fileWriter);

                for (Product product : productArrayList) {
                    if (product instanceof Clothing cloth) {
                        writer.write(product.getProductType() + ",");
                        writer.write(product.getProductID() + ",");
                        writer.write(product.getProductName() + ",");
                        writer.write(product.getProductPrice() + ",");
                        writer.write(cloth.getproductSize() + ",");
                        writer.write(cloth.getproductColour() + ",");
                        writer.write(cloth.getAvailableProductsCount() + ",");
                    } else {
                        Electronics electronics = (Electronics) product;
                        writer.write(product.getProductType() + ",");
                        writer.write(product.getProductID() + ",");
                        writer.write(product.getProductName() + ",");
                        writer.write(product.getProductPrice() + ",");
                        writer.write(electronics.getproductBrand() + ",");
                        writer.write(electronics.getproductWarrantyPeriod() + ",");
                        writer.write(electronics.getAvailableProductsCount() + ",");
                    }
                    writer.newLine();
                }
                System.out.println("Saved successfully\n");
                writer.close();

            } catch (IOException error) {
                error.printStackTrace();
            }

        } catch (IOException outerError) {
            outerError.printStackTrace();
        }
    }


    public void readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("product-data.txt"))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] productDetailsArray = line.split(",");

                for (Product product : productArrayList) {
                    if (product.getProductID().equals(productDetailsArray[1])) {
                        sameProductAvailable = true;
                        break;
                    }
                }
                if (!sameProductAvailable) {
                    Product newProduct = createProduct(productDetailsArray);
                    productArrayList.add(newProduct);
                }
            }
//            printList();

        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    private static Product createProduct(String[] productDetailsArray) {
        if (productDetailsArray[0].equals("Clothing")) {
            return new Clothing(productDetailsArray[0], productDetailsArray[1], productDetailsArray[2], Double.parseDouble(productDetailsArray[3]), productDetailsArray[4], productDetailsArray[5], Integer.parseInt(productDetailsArray[6]));
        } else {
            return new Electronics(productDetailsArray[0], productDetailsArray[1], productDetailsArray[2], Double.parseDouble(productDetailsArray[3]), productDetailsArray[4], productDetailsArray[5], Integer.parseInt(productDetailsArray[6]));
        }
    }

    public void clearList() {
        while (true) {
            if (!productArrayList.isEmpty()) {
                System.out.println("Are your sure you want to clear the list Yes/No: ");
                String answer = input.next();

                if (answer.equalsIgnoreCase("yes")) {
                    productArrayList.clear();
                    System.out.println("All products removed");
                    return;
                } else if (answer.equalsIgnoreCase("no")) {
                    break;
                } else{
                    System.out.println("wrong input!");
                }
            } else {
                System.out.println("No products available...");
                return;
            }
        }
    }

    public void openGUI() {
        Frame frame = new Frame();
        frame.setTitle("Westminster Shopping Centre");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700);
        frame.setVisible(true);
    }
}