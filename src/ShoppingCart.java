// IMPORT UTIL PACKAGE TO USE ARRAYLIST
import java.util.ArrayList;

public class ShoppingCart {
    // INSTANCE VARIABLES OF THE CLASS TO STORE PRODUCT VALUES
    private static ArrayList<Product> productsList;

    /**
     * Constructor to create a list to add products to the cart
     */
    public ShoppingCart(){
        productsList = new ArrayList<>();
    }

    /**
     * Method to add products to the product list
     * @param product       - Product object
     */
    public void addProduct(Product product){
        productsList.add(product);
    }

    /**
     * Method to remove products from the product list
     * @param productID     - The ID of the product
     */
    public void removeProduct (String productID){
        if (productsList.isEmpty()){                                            // CHECKING IS THAT THE ARRAYLIST EMPTY
            System.out.println("Cart is already empty! ");
        } else {
            for (int i = 0; i < productsList.size(); i++) {
                if (productsList.get(i).getProductID().equals(productID)){      // CHECKING WHETHER PRODUCT ID AND GIVEN ID SAME
                    productsList.remove(i);
                    return;
                }
            }
        }
    }

    /**
     * Calculating the total of the product list
     */
    public void calculateTotal(){
        double total = 0;                                                       // CREATING A VARIABLE TO CALCULATE THE TOTAL
        if (productsList.isEmpty()){                                            // CHECKING IS THAT THE ARRAYLIST EMPTY
            System.out.println("Cart is already empty");
        } else {
            for (Product product:productsList) {
                total += product.getProductPrice();                             // ADDING ALL THE PRICES
            }
            System.out.println("Total cost of the products is: "+total);
        }
    }
}
