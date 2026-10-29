package org.example.controller;
import org.example.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Productcontroller {
    private final ObservableList<Product> Database = FXCollections.observableArrayList();

    @FXML 
    private TextField productIdField;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField productPriceField;
    @FXML
    private TextField productQuantityField;
    @FXML
    private TextField searchField;


    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> productIdColumn;
    @FXML 
    private TableColumn<Product, String> productNameColumn;
    @FXML 
    private TableColumn<Product, Integer> productPriceColumn;
    @FXML 
    private TableColumn<Product, Integer> productQuantityColumn;

    @FXML
    private Label statusLabel;

    @FXML
    private void addProduct(){

        String productId = productIdField.getText().trim();
        String name = productNameField.getText().trim();
        String productPrice = productPriceField.getText().trim();
        String productQuantity = productQuantityField.getText().trim();
        int id = 0;
        double price = 0;
        int quantity = 0;

        if(productId.isEmpty() || name.isEmpty() || productPrice.isEmpty()  || productQuantity.isEmpty()){
            statusLabel.setText("Fill in all the Required Fields");
            return;
        }
        if(!productId.matches("\\d{3}")){
            statusLabel.setText("Product ID should be 3 didgits..");
            return; 
        }
        try{
            id = Integer.parseInt(productId);
            price = Integer.parseInt(productPrice);
            quantity = Integer.parseInt(productQuantity);

          
           if(price < 0){
            statusLabel.setText("Invalid Price Range..");
            return;
           }
           if(quantity < 0){
            statusLabel.setText("Invalid Quantity..");
            return;

           }
           for(Product s: Database){
            if(id == s.getId()){
                statusLabel.setText("ID already Exists");
                return;
            }
            }
           
         Product products = new Product(id, name, price, quantity);
         Database.add(products);
         statusLabel.setText("Product Added..");
         clear();

        }catch(NumberFormatException e){
         statusLabel.setText("Error with DATA type.....");
         return;

        }
        

    }

    @FXML
    private void initialize() {
        productTable.setItems(Database);
        productIdColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getId()));
        productNameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getName()));
        productPriceColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getPrice()));
        productQuantityColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getStock()));
        
    }

    @FXML 
    private void upLoad(){
        Product selected = productTable.getSelectionModel().getSelectedItem();
        if(selected == null){
         statusLabel.setText("No Item selected..");
         return;

        }
        productIdField.setText(String.valueOf(selected.getId()));
        productNameField.setText(selected.getName());
        productPriceField.setText(String.valueOf(selected.getPrice()));
        productQuantityField.setText(String.valueOf(selected.getStock()));

    }
    @FXML
    private void upDate(){
        Product selected = productTable.getSelectionModel().getSelectedItem();
        if(selected == null){
         statusLabel.setText("No Item selected..");
         return;

        }

        String productId = productIdField.getText().trim();
        String name = productNameField.getText().trim();
        String productPrice = productPriceField.getText().trim();
        String productQuantity = productQuantityField.getText().trim();
        int id = 0;
        double price = 0;
        int quantity = 0;

        if(productId.isEmpty() || name.isEmpty() || productPrice.isEmpty()  || productQuantity.isEmpty()){
            statusLabel.setText("Fill in all the Required Fields");
            return;
        }
        
        if(!productId.matches("\\d{3}")){
            statusLabel.setText("Product ID should be 3 didgits..");
            return; 
        }
        try{
            id = Integer.parseInt(productId);
            price = Integer.parseInt(productPrice);
            quantity = Integer.parseInt(productQuantity);

          
           if(price < 0){
            statusLabel.setText("Invalid Price Range..");
            return;
           }
           if(quantity < 0){
            statusLabel.setText("Invalid Quantity..");
            return;

           }
                    

        }catch(NumberFormatException e){
            statusLabel.setText("Error DataType...");
            return;
        }  
        selected.setId(id);
        selected.setName(name);
        selected.setPrice(price);
        selected.setStock(quantity);
        
        productTable.refresh();
        clear();
        statusLabel.setText("Product Updated...");
       
       

    }
    @FXML
    private void delete(){
        Product selected = productTable.getSelectionModel().getSelectedItem();
        if(selected == null){
         statusLabel.setText("No Item selected..");
         return;

        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm delete");
        alert.setHeaderText("Delete Student");
        alert.setContentText("Are you sure you want to delete " + selected.getName() + "?");
            if(alert.showAndWait().orElse(ButtonType.CANCEL) != ButtonType.OK){
                return;
            }
        

        Database.remove(selected);
        productTable.getSelectionModel().clearSelection();
        statusLabel.setText("Student Deleted Successfully...");

    }
    @FXML
    private void clear(){
        productIdField.clear();
        productNameField.clear();
        productPriceField.clear();
        productQuantityField.clear();
    }
}
