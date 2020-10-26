package shekho.com.guitarShopFX.UI.Scenes;


import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import shekho.com.guitarShopFX.DAL.Database;
import shekho.com.guitarShopFX.Models.*;
import shekho.com.guitarShopFX.UI.Dialogs.AddCustomerDialog;


import java.util.List;

public class EditCustomerScene {

    private final Scene scene;
    private List<Customer> customers;
    private ObservableList<Customer> olCustomers;

    public Scene getScene() {
        return scene;
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


        TableColumn<Customer,String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn<Customer,String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn<Customer,String> streetAddressCol = new TableColumn<>("Street Address");
        streetAddressCol.setMinWidth(100);
        streetAddressCol.setCellValueFactory(new PropertyValueFactory<>("streetAddress"));
        TableColumn<Customer,String> cityCol = new TableColumn<>("City");
        cityCol.setMinWidth(100);
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        TableColumn<Customer,String> phoneNumberCol = new TableColumn<>("Phone #");
        phoneNumberCol.setMinWidth(100);
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        TableColumn<Customer,String> emailCol = new TableColumn<>("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));


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

        deleteBtn.setOnAction(actionEvent -> {
            Customer customer = tvCustomers.getSelectionModel().getSelectedItem();
            if(customer != null){

                db.getCustomers().remove(customer);
                customers = db.getCustomers();
                olCustomers = FXCollections.observableArrayList(customers);
                tvCustomers.setItems(olCustomers);
            }else{
                lblWarning.setText("You did not choose any customer! Choose a customer and then press delete");
            }

        });

        addBtn.setOnAction(actionEvent -> {
            AddCustomerDialog ac = new AddCustomerDialog(db);
            ac.getWindow().initModality(Modality.APPLICATION_MODAL);
            ac.getWindow().showAndWait();

            customers = db.getCustomers();
            olCustomers = FXCollections.observableArrayList(customers);
            tvCustomers.setItems(olCustomers);
        });
        layout.getChildren().addAll(lblCustomer,tvCustomers,buttonsLayout,lblWarning);
        scene = new Scene(layout);
    }
}
