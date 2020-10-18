package shekho.com.guitarShopFX.UI.Dialogs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import shekho.com.guitarShopFX.DAL.Database;
import shekho.com.guitarShopFX.Models.Customer;


public class AddCustomer {

    private Stage window;
    public Stage getWindow() {
        return window;
    }

    public AddCustomer(Database db){

        window = new Stage();
        window.setTitle("GuitarShop FX - Add Customer");
        Image image = new Image("resources/css/images/guitarImage.png");
        window.getIcons().add(image);

        VBox layout = new VBox();
        layout.setPadding(new Insets(40));
        layout.setSpacing(40);

        Label lblAddCustomer = new Label("Add Customer");
        lblAddCustomer.setId("headerLbl");
        Label lblWaring = new Label();
        lblWaring.setId("lblWarning");

        GridPane customerLayout = new GridPane();
        customerLayout.setVgap(10);
        customerLayout.setHgap(10);

        Label lblFirstName = new Label("First Name");
        TextField txtFirstName = new TextField();
        txtFirstName.setPromptText("First Name");

        Label lblLastName = new Label("Last Name");
        TextField txtLastName = new TextField();
        txtLastName.setPromptText("Last Name");

        Label lblAddress = new Label("Address");
        TextField txtAddress = new TextField();
        txtAddress.setPromptText("Address");

        Label lblCity = new Label("City");
        TextField txtCity = new TextField();
        txtCity.setPromptText("City");

        Label lblPhoneNumber = new Label("Phone Number");
        TextField txtPhoneNumber = new TextField();
        txtPhoneNumber.setPromptText("0681234567");

        Label lblEmail = new Label("Email");
        TextField txtEmail = new TextField();
        txtEmail.setPromptText("example@hotmail.com");

        HBox buttonLayout = new HBox();
        Button btnAdd = new Button("Add");
        btnAdd.setPrefWidth(150);
        buttonLayout.setAlignment(Pos.CENTER);

        customerLayout.add(lblFirstName,0,0);
        customerLayout.add(txtFirstName,1,0);
        customerLayout.add(lblLastName,0,1);
        customerLayout.add(txtLastName,1,1);
        customerLayout.add(lblAddress,0,2);
        customerLayout.add(txtAddress,1,2);
        customerLayout.add(lblCity,0,3);
        customerLayout.add(txtCity,1,3);
        customerLayout.add(lblPhoneNumber,0,4);
        customerLayout.add(txtPhoneNumber,1,4);
        customerLayout.add(lblEmail,0,5);
        customerLayout.add(txtEmail,1,5);
        customerLayout.add(btnAdd,1,6);

        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblWaring.setText("");

                String firstName = txtFirstName.getText();
                String lastName = txtLastName.getText();
                String address = txtAddress.getText();
                String city = txtCity.getText();
                String phoneNumber  = txtPhoneNumber.getText();
                String email = txtEmail.getText();

                if(!firstName.isEmpty() && !lastName.isEmpty() && !address.isEmpty()
                && !city.isEmpty() && !phoneNumber.isEmpty() && !email.isEmpty()){

                    Customer customer = new Customer(firstName,lastName,address,city,phoneNumber,email);
                    db.getCustomers().add(customer);
                    window.close();

                }else{
                    lblWaring.setText("Not all fields are filled!");
                }
            }
        });



        layout.getChildren().addAll(lblAddCustomer,customerLayout,lblWaring);

        Scene scene = new Scene(layout);
        scene.getStylesheets().add("resources/css/style.css");
        window.setScene(scene);
    }

}
