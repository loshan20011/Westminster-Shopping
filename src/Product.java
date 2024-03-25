import java.util.Random;
import static java.lang.String.valueOf;

public abstract class Product implements Comparable<Product> {
    // INSTANCE VARIABLES OF THE PRODUCT CLASS TO STORE VALUES
    private String productID;                                   // STRING TO HOLD BOTH CHARACTERS AND NUMBERS
    private String productName;
    private int availableProductsCount;
    private double productPrice;
    private String productType;

    /**
     * Constructor with all instance variables of the class included as parameter
     * @param productName - Name of the product
     * @param productPrice - Price of the product
     * @param productType - Type of the product
     */
    public Product(String productName, double productPrice, String productType, int availableProductsCount){
        super();                                                             // INHERITS THE CONSTRUCTOR OF PARENT CLASS
        this.productName = productName;
        this.productPrice = productPrice;
        this.productType = productType;
        this.availableProductsCount = availableProductsCount;
        productID = createProductID();                                      // INVOKING A METHOD TO GENERATE A PRODUCT ID
    }

    public Product(String productID, String productName, double productPrice, String productType, int availableProductsCount){
        super();                                                             // INHERITS THE CONSTRUCTOR OF PARENT CLASS
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productType = productType;
        this.availableProductsCount = availableProductsCount;
    }

    // GETTER OF THE CLASS TO GET THE VALUE

    public String getProductID() {
        return productID;                                                   // THIS METHOD RETURNS THE PRODUCT ID
    }

    public String getProductName() {
        return productName;                                                // THIS METHOD RETURNS THE PRODUCT NAME
    }

    public int getAvailableProductsCount() {
        return availableProductsCount;                                     // THIS METHOD RETURNS THE COUNT OF AVAILABLE ITEMS
    }

    public double getProductPrice() {
        return productPrice;                                               // THIS METHOD RETURNS THE PRICE OF THE PRODUCT
    }

    public String getProductType() {
        return productType;                                                // THIS METHOD RETURNS THE TYPE OF THE PRODUCT
    }

    // SETTERS OF THE CLASS TO SET VALUES

    public void setProductID(String productID) {
        this.productID = productID;                                        // THIS METHOD SET VALUE FOR THE PRODUCT ID
    }

    public void setProductName(String productName) {
        this.productName = productName;                                    // THIS METHOD SET VALUE FOR THE PRODUCT NAME
    }

    public void setAvailableProductsCount(int availableProductsCount) {
        this.availableProductsCount = availableProductsCount;              // THIS METHOD SET VALUE FOR THE COUNT OF AVAILABLE PRODUCTS
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;                                  // THIS METHOD SET VALUE FOR THE PRODUCT PRICE
    }

    public void setProductType(String productType) {
        this.productType = productType;                                    // THIS METHOD SET VALUE FOR THE PRODUCT TYPE
    }

    @Override
    public String toString() {
        return "Product [" +
                " productID = '" + productID + '\'' +
                ", productType = '" + productType + '\''+
                ", productName = '" + productName + '\'' +
                ", productCount = " + availableProductsCount +
                ", productPrice = " + productPrice ;
    }

    /**
     * Method to create a six character long productID
     * @return generated product ID
     */
    private static String createProductID() {
        String characters1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";           // DEFINE THE CHARACTERS THAT CAN BE USED
        String characters2 = "abcdefghijklmnopqrstuvwxyz";           // DEFINE THE CHARACTERS THAT CAN BE USED
        String numbers = "0123456789";                           // DEFINE THE NUMBERS THAT CAN BE USED

        StringBuilder randomString = new StringBuilder();                                               // CREATING A STRING BUILDER TO STORE THE GENERATED STRING

        // GENERATE THE RANDOM STRING BY APPENDING RANDOM CHARACTERS AND NUMBERS

        randomString.append(generateID(characters1,2));
        randomString.append(generateID(characters2, 2));
        randomString.append(generateID(numbers, 4));

        return valueOf(randomString);
    }

    /**
     * Generates random letter with size of two
     * @param list - list of characters
     * @param length - the for loop to loop
     * @return - the generated string
     */
    public static String generateID(String list, int length){
                                                                                         // LENGTH OF NEW GENERATED PRODUCT ID
        Random random = new Random();                                                                   // CREATE A RANDOM OBJECT TO CREATE RANDOM INDICES
        StringBuilder randomString = new StringBuilder();                                               // CREATING A STRING BUILDER TO STORE THE GENERATED STRING

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(list.length());
            char randomChar = list.charAt(randomIndex);
            randomString.append(randomChar);
        }
        return valueOf(randomString);
    }

    @Override
    public int compareTo(Product product){
        return this.productID.compareTo(product.getProductID());
    }
}
