import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class CartTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Product", "Quantity", "Price"};
    private final ArrayList<Product> cartList;
    private final ArrayList<Integer> quantityList;

    public CartTableModel(ArrayList<Product> cartList, ArrayList<Integer> quantityList){
        this.cartList = cartList;
        this.quantityList = quantityList;
    }

    @Override
    public int getRowCount() {
        return cartList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp = null;
        switch (columnIndex){
            case 0:
                temp = cartList.get(rowIndex).getProductName();
                break;
            case 1:
                temp = quantityList.get(rowIndex);
                break;
            case 2:
                temp = cartList.get(rowIndex).getProductPrice();
                break;
        }
        return temp;
    }
    public String getColumnName(int col){
        return columnNames[col];
    }

}
