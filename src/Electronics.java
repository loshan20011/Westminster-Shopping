public class Electronics extends Product{

    // INSTANCE VARIABLES OF THE ELECTRONICS CLASS TO STORE VALUES
    private String productBrand;
    private String productWarrantyPeriod;

    /**
     * Constructor with all instance variables of the class included as parameter
     *
     * @param productName            - Name of the product
     * @param productPrice           - Price of the product
     * @param productType            - Type of the product
     * @param productBrand           - productBrand name of the Electronic
     * @param productWarrantyPeriod  - Warranty period given for the Electronic
     */
    public Electronics(String productName, double productPrice, String productType, String productBrand, String productWarrantyPeriod, int availableProductsCount) {
        super(productName, productPrice, productType, availableProductsCount);               // INHERITS CONSTRUCTOR OF PARENT CLASS
        this.productWarrantyPeriod = productWarrantyPeriod;
        this.productBrand = productBrand;
    }

    public Electronics(String productType, String productID, String productName, double productPrice, String productBrand, String productWarrantyPeriod, int availableProductsCount) {
        super(productID, productName, productPrice, productType, availableProductsCount);
        this.productBrand = productBrand;
        this.productWarrantyPeriod = productWarrantyPeriod;
    }

    // GETTERS FOR THE ELECTRONIC CLASS VARIABLES
    public String getproductBrand() {
        return productBrand;                                                                                   // THIS METHOD RETURNS THE productBrand NAME
    }

    public String getproductWarrantyPeriod() {
        return productWarrantyPeriod;                                                                          // THIS METHOD RETURNS THE WARRANTY PERIOD
    }

    // SETTERS FOR THE ELECTRONIC CLASS VARIABLES
    public void setproductBrand(String productBrand) {
        this.productBrand = productBrand;                                                                             // THIS METHOD SET VALUE FOR THE productBrand NAME
    }

    public void setproductWarrantyPeriod(String productWarrantyPeriod) {
        this.productWarrantyPeriod = productWarrantyPeriod;                                                           // THIS METHOD SET VALUE FOR THE WARRANTY PERIOD
    }

    @Override
    public String toString() {
        return super.toString()+
                ", productBrand = '" + productBrand + '\'' +
                ", productWarrantyPeriod = '" + productWarrantyPeriod + '\'' +
                " ]";
    }
}
