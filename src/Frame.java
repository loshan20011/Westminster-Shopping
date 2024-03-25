import Interfaces.ShoppingManager;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class Frame extends JFrame {
    private ArrayList<Product> productArrayList;
    private ArrayList<Product> clothingArrayList = new ArrayList<>();
    private ArrayList<Product> electronicsArrayList = new ArrayList<>();
    private JPanel productPanel;  // Reference to the product panel for updates
    private JPanel detailsPanel;
    private JTable table;
    String selectedOption = "All";

    public Frame() {
        WestminsterShoppingManager.shoppingManager.readFile();

        productArrayList = new ArrayList<>(WestminsterShoppingManager.productArrayList);
        setLayout(new BorderLayout());

        JPanel topPanel = createTopPanel();
        productPanel = createProductPanel("All");
        detailsPanel = createDetailsPanel(productArrayList.get(0));

        add(topPanel, BorderLayout.NORTH);
        add(productPanel, BorderLayout.CENTER);
        add(detailsPanel, BorderLayout.SOUTH);
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 3));
        topPanel.add(new JLabel("Select Product Category"));

        JComboBox<String> productTypes = new JComboBox<>(new String[]{"All", "Clothing", "Electronics"});
        topPanel.add(productTypes);

        JButton shoppingCartButton = new JButton("Shopping cart");
        shoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createCart();
            }
        });
        JButton sortProductsButton = new JButton("Sort Products");
        topPanel.add(shoppingCartButton);
        topPanel.add(sortProductsButton);

        productTypes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedOption = (String) productTypes.getSelectedItem();
                chooseArray();
                updateProductPanel(selectedOption);
            }
        });

        sortProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Collections.sort(productArrayList);
                chooseArray();
                updateProductPanel(selectedOption);
            }
        });

        return topPanel;
    }

    private JPanel createProductPanel(String arrayType) {
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new GridLayout(1, 1));

        if (arrayType.equals("All")) {
            ProductTableModel tableModel = new ProductTableModel(productArrayList);
            table = new JTable(tableModel);
        } else if (arrayType.equals("Electronics")) {
            ProductTableModel tableModel = new ProductTableModel(electronicsArrayList);
            table = new JTable(tableModel);
        } else {
            ProductTableModel tableModel = new ProductTableModel(clothingArrayList);
            table = new JTable(tableModel);
        }

        table.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer()
        {
            Color originalColor = null;
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if ((value.equals("1"))) {
                    renderer.setBackground(Color.RED);
                }

                return renderer;
            }
        });

        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Get the selected row and handle it
                    int selectedRow = table.getSelectedRow();
                    handleTableRowSelection(selectedRow);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        productPanel.add(scrollPane);
        return productPanel;
    }

    private void updateProductPanel(String arrayType) {
        remove(productPanel);  // Remove the existing panel
        productPanel = createProductPanel(arrayType);  // Create a new panel based on the selected type
        add(productPanel, BorderLayout.CENTER);  // Add the new panel to the frame
        revalidate();  // Refresh the UI
        repaint();
    }

    private void chooseArray() {
        // Clear arrays before adding products
        clothingArrayList.clear();
        electronicsArrayList.clear();

        for (Product product : productArrayList) {
            if (product instanceof Electronics electronics) {
                electronicsArrayList.add(electronics);
            } else if (product instanceof Clothing clothing) {
                clothingArrayList.add(clothing);
            }
        }
    }

    private JPanel createDetailsPanel(Product product) {
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(8, 1));
        detailsPanel.add(new JLabel("Selected Product - Details"));

        detailsPanel.add(new JLabel("Product ID: " + product.getProductID()));
        detailsPanel.add(new JLabel("Category: " + product.getProductType()));
        detailsPanel.add(new JLabel("Name: " + product.getProductName()));
        detailsPanel.add(new JLabel("Item Available: " + product.getAvailableProductsCount()));

        if (product instanceof Electronics electronics) {
            detailsPanel.add(new JLabel("Size: " + electronics.getproductBrand()));
            detailsPanel.add(new JLabel("Colour: " + electronics.getproductWarrantyPeriod()));
        } else if (product instanceof Clothing clothing) {
            detailsPanel.add(new JLabel("Size: " + clothing.getproductSize()));
            detailsPanel.add(new JLabel("Colour: " + clothing.getproductColour()));
        }

        JButton addToCartButton = new JButton("Add to cart");
        detailsPanel.add(addToCartButton);

        return detailsPanel;
    }

    private void handleTableRowSelection(int selectedRow) {
        // Your logic to handle the selected row
        if (selectedRow >= 0 && selectedRow < productArrayList.size()) {
            Product selectedProduct = productArrayList.get(selectedRow);
            // Do something with the selected product
            remove(detailsPanel);
            detailsPanel = createDetailsPanel(selectedProduct);
            add(detailsPanel, BorderLayout.SOUTH);
            revalidate();  // Refresh the UI
            repaint();
        }
    }

    private void createCart(){
        Frame cartFrame = new Frame();
        cartFrame.setTitle("Shopping Cart");
        cartFrame.setVisible(true);
        cartFrame.setSize(500,500);

        JPanel cartPanel = new JPanel();
        JPanel detailsPanel = new JPanel();

        createCartTable(cartPanel);
    }

    private void createCartTable(JPanel cartPanel){

//        CartTableModel cartTableModel = new CartTableModel();
        JTable table = new JTable();

    }
}
