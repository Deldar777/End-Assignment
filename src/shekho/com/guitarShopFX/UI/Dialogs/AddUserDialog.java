package shekho.com.guitarShopFX.UI.Dialogs;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import shekho.com.guitarShopFX.DAL.Database;
import shekho.com.guitarShopFX.Models.*;

public class AddUserDialog {

    private final Stage window;
    public Stage getWindow() {
        return window;
    }

    public AddUserDialog(Database db){

        window = new Stage();
        window.setTitle("GuitarShop FX - Add User");
        Image image = new Image("resources/css/images/guitarImage.png");
        window.getIcons().add(image);

        VBox layout = new VBox();
        layout.setPadding(new Insets(40));
        layout.setSpacing(40);

        Label lblAddUser = new Label("Add User");
        lblAddUser.setId("headerLbl");
        Label lblWaring = new Label();
        lblWaring.setId("lblWarning");

        GridPane userLayout = new GridPane();
        userLayout.setVgap(10);
        userLayout.setHgap(10);

        Label lblUsername = new Label("Username");
        TextField txtUsername = new TextField();
        txtUsername.setPromptText("Username");
        Label lblPassword = new Label("Password");
        TextField txtPassword = new TextField();
        txtPassword.setPromptText("Password");
        Label lblFirstName = new Label("First Name");
        TextField txtFirstName = new TextField();
        txtFirstName.setPromptText("First Name");
        Label lblLastName = new Label("Last Name");
        TextField txtLastName = new TextField();
        txtLastName.setPromptText("Last Name");
        Label lblPhoneNumber = new Label("Phone Number");
        TextField txtPhoneNumber = new TextField();
        txtPhoneNumber.setPromptText("0681234567");
        Label lblEmail = new Label("Email");
        TextField txtEmail = new TextField();
        txtEmail.setPromptText("example@hotmail.com");

        Label lblRole = new Label("Role");
        ChoiceBox<Role> cbRole = new ChoiceBox<>();
        cbRole.getItems().addAll(Role.MANAGER,Role.SALES);
        cbRole.setValue(Role.SALES);

        HBox buttonLayout = new HBox();
        Button btnAdd = new Button("Add");
        btnAdd.setPrefWidth(150);
        buttonLayout.setAlignment(Pos.CENTER);

        userLayout.add(lblUsername,0,0);
        userLayout.add(txtUsername,1,0);
        userLayout.add(lblPassword,0,1);
        userLayout.add(txtPassword,1,1);
        userLayout.add(lblFirstName,0,2);
        userLayout.add(txtFirstName,1,2);
        userLayout.add(lblLastName,0,3);
        userLayout.add(txtLastName,1,3);
        userLayout.add(lblPhoneNumber,0,4);
        userLayout.add(txtPhoneNumber,1,4);
        userLayout.add(lblEmail,0,5);
        userLayout.add(txtEmail,1,5);
        userLayout.add(lblRole,0,6);
        userLayout.add(cbRole,1,6);
        userLayout.add(btnAdd,1,7);

        btnAdd.setOnAction(actionEvent -> {
            lblWaring.setText("");

            String username = txtUsername.getText();
            String password = txtPassword.getText();
            String firstName = txtFirstName.getText();
            String lastName = txtLastName.getText();
            String phoneNumber  = txtPhoneNumber.getText();
            String email = txtEmail.getText();
            Role role = cbRole.getValue();

            if(!username.isEmpty() && !password.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty() &&
                     !phoneNumber.isEmpty() && !email.isEmpty()){

                User user = new User(username,password,firstName,lastName,phoneNumber,email,role);
                db.getUsers().add(user);
                window.close();

            }else{
                lblWaring.setText("Not all fields are filled!");
            }
        });
        layout.getChildren().addAll(lblAddUser,userLayout,lblWaring);

        Scene scene = new Scene(layout);
        scene.getStylesheets().add("resources/css/style.css");
        window.setScene(scene);
    }

}
