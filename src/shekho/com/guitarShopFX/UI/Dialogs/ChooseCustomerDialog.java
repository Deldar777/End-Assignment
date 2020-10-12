package shekho.com.guitarShopFX.UI.Dialogs;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import shekho.com.guitarShopFX.DAL.Database;
import shekho.com.guitarShopFX.Models.Customer;

public class ChooseCustomerDialog {

    private ObservableList<Customer> customers;
    private Customer customer;
    private Stage window;
    private Scene scene;

    public Stage getWindow() {
        return window;
    }
    public Customer getCustomer() {
        return customer;
    }

    public ChooseCustomerDialog(Database db){

        customers = FXCollections.observableArrayList(db.getCustomers());

        window = new Stage();
        window.setTitle("GuitarShop FX - Search Customer");
        Image image = new Image("resources/css/images/guitarImage.png");
        window.getIcons().add(image);



        VBox layout = new VBox();
        layout.setPadding(new Insets(10,0,0,10));
        layout.setSpacing(5);

        Label lblCustomerList = new Label("Customer List");
        lblCustomerList.setId("lblWelcome");

        TableView<Customer> customerTable = new TableView<>();
        customerTable.setEditable(true);
        customerTable.getSelectionModel().setCellSelectionEnabled(false);
        customerTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));

        TableColumn streetAddressCol = new TableColumn("Street Address");
        streetAddressCol.setMinWidth(100);
        streetAddressCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("streetAddress"));

        TableColumn cityCol = new TableColumn("City");
        cityCol.setMinWidth(100);
        cityCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("city"));

        TableColumn phoneNumberCol = new TableColumn("Phone #");
        phoneNumberCol.setMinWidth(100);
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("phoneNumber"));

        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));


        customerTable.setRowFactory(stv ->{
            TableRow<Customer> row = new TableRow<>();
            row.setOnMouseClicked(event ->{
                if(event.getClickCount() == 2 && !row.isEmpty()){
                    customer = row.getItem();
                    window.close();
                }
            });

            return row;
        });

        //pass the data to a filtered list
        FilteredList<Customer> flCustomer = new FilteredList(customers,C -> true);
        customerTable.getColumns().addAll(firstNameCol,lastNameCol,streetAddressCol,cityCol,phoneNumberCol,emailCol);
        customerTable.setItems(flCustomer);

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("First Name","Last Name","Email");
        choiceBox.setValue("First Name");

        TextField userInputTxt = new TextField();
        userInputTxt.setPromptText("Search here!");
        userInputTxt.setOnKeyReleased(keyEvent ->
        {
            switch(choiceBox.getValue()){
                case "First Name":
                    flCustomer.setPredicate(C -> C.getFirstName().toLowerCase()
                            .contains(userInputTxt.getText().toLowerCase().trim()));
                    break;
                case "Last Name":
                    flCustomer.setPredicate(C -> C.getLastName().toLowerCase()
                            .contains(userInputTxt.getText().toLowerCase().trim()));
                    break;
                case "Email":
                    flCustomer.setPredicate(C -> C.getEmail().toLowerCase()
                            .contains(userInputTxt.getText().toLowerCase().trim()));
                    break;
            }
        });

        choiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->
        {
            if (newVal != null)
            {
                userInputTxt.setText("");
                flCustomer.setPredicate(null);
            }
        });
        HBox searchBarHb = new HBox(choiceBox,userInputTxt);
        searchBarHb.setAlignment(Pos.CENTER);
        searchBarHb.setPadding(new Insets(20));

        layout.getChildren().addAll(lblCustomerList,customerTable,searchBarHb);
        scene = new Scene(layout);
        scene.getStylesheets().add("resources/css/style.css");
        window.setScene(scene);

    }
}
