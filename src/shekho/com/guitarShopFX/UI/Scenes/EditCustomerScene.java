package shekho.com.guitarShopFX.UI.Scenes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import shekho.com.guitarShopFX.DAL.Database;
import shekho.com.guitarShopFX.Models.Article;
import shekho.com.guitarShopFX.Models.Customer;
import shekho.com.guitarShopFX.Models.Order;
import shekho.com.guitarShopFX.UI.Dialogs.AddCustomer;
import shekho.com.guitarShopFX.UI.Dialogs.ConfirmOrderDialog;

import java.util.List;

public class EditCustomerScene {

    private Scene scene;
    private List<Customer> customers;
    private ObservableList<Customer> olCustomers;

    public Scene getScene() {
        return scene;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public EditCustomerScene(Database db){

        customers = db.getCustomers();
        olCustomers = FXCollections.observableArrayList(customers);

        VBox layout = new VBox();
        layout.setPadding(new Insets(20));
        layout.setSpacing(40);

        Label lblCustomer = new Label("Customers");
        lblCustomer.setId("headerLbl");

        TableView<Customer> tvCustomers = new TableView<>();
        tvCustomers.setEditable(true);
        tvCustomers.getSelectionModel().setCellSelectionEnabled(false);
        tvCustomers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


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


        tvCustomers.getColumns().addAll(firstNameCol,lastNameCol,streetAddressCol,cityCol,phoneNumberCol,emailCol);
        tvCustomers.setItems(olCustomers);


        HBox buttonsLayout = new HBox();
        buttonsLayout.setSpacing(20);
        buttonsLayout.setAlignment(Pos.CENTER);

        Button addBtn = new Button("Add");
        addBtn.setPrefWidth(100);
        Button deleteBtn = new Button("Delete");
        deleteBtn.setPrefWidth(100);
        Label lblWarning = new Label();
        lblWarning.setId("lblWarning");
        buttonsLayout.getChildren().addAll(addBtn,deleteBtn);

        deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Customer customer = tvCustomers.getSelectionModel().getSelectedItem();
                if(customer != null){
                    db.getCustomers().remove(customer);
                    customers = db.getCustomers();
                    olCustomers = FXCollections.observableArrayList(customers);
                    tvCustomers.setItems(olCustomers);
                }else{
                    lblWarning.setText("You did not choose any customer! Choose a customer and then press delete");
                }

            }
        });

        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AddCustomer ac = new AddCustomer(db);
                ac.getWindow().initModality(Modality.APPLICATION_MODAL);
                ac.getWindow().showAndWait();

                customers = db.getCustomers();
                olCustomers = FXCollections.observableArrayList(customers);
                tvCustomers.setItems(olCustomers);
            }
        });
        layout.getChildren().addAll(lblCustomer,tvCustomers,buttonsLayout,lblWarning);
        scene = new Scene(layout);
    }
}
