public class Clothing extends Product{
    // INSTANCE VARIABLES OF THE PRODUCT CLASS TO STORE VALUES
    private String productSize;
    private String productColour;

    /**
     * Constructor with all instance variables of the class included as parameter
     *
     * @param productName            - Name of the product
     * @param productPrice           - Price of the product
     * @param productType            - Type of the product
     * @param productColour                 - productColour of the product
     * @param productSize                   - productSize of the product
     */
    public Clothing(String productName, double productPrice, String productType, String productSize, String productColour, int availableProductsCount) {
        super(productName, productPrice, productType, availableProductsCount);
        this.productSize = productSize;
        this.productColour = productColour;
    }

    public Clothing(String productType, String productID, String productName, double productPrice, String productColour, String productSize, int availableProductsCount) {
        super(productID, productName, productPrice, productType, availableProductsCount);
        this.productSize = productSize;
        this.productColour = productColour;
    }

    @Override
    public String toString() {
        return super.toString()+
                ", productSize = '" + productSize + '\'' +
                ", productColour = '" + productColour + '\'' +
                " ]";
    }

    // GETTERS FOR THE ELECTRONIC CLASS VARIABLES
    public String getproductSize() {
        return productSize;                                        // THIS METHOD RETURNS THE productSize
    }

    public String getproductColour() {
        return productColour;                                      // THIS METHOD RETURNS THE productColour
    }

    public void setproductSize(String productSize) {
        this.productSize = productSize;                                   // THIS METHOD SET VALUE FOR THE productSize
    }

    public void setproductColour(String productColour) {
        this.productColour = productColour;                               // THIS METHOD SET VALUE FOR THE productColour
    }
}
