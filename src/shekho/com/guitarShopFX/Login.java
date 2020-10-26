package shekho.com.guitarShopFX;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shekho.com.guitarShopFX.DAL.Database;
import shekho.com.guitarShopFX.Models.*;
import shekho.com.guitarShopFX.UI.Windows.Home;

public class Login extends Application {

    public static void main(String[] args){launch(args);}
    @Override
    public void start(Stage window) {

        try{
            Database db = new Database();

            window.setTitle("GuitarShop FX - Login");
            Image image = new Image("resources/css/images/guitarImage.png");
            window.getIcons().add(image);
            window.setWidth(400);
            window.setHeight(400);


            BorderPane layout = new BorderPane();
            layout.setPadding(new Insets(40));

            HBox top = new HBox();
            top.setPadding(new Insets(20));
            Text txtLogin = new Text("Login");
            txtLogin.setId("lblLogin");
            top.getChildren().add(txtLogin);

            DropShadow dropShadow = new DropShadow();
            dropShadow.setOffsetX(7);
            dropShadow.setOffsetY(7);


            txtLogin.setEffect(dropShadow);


            GridPane center = new GridPane();
            center.setPadding(new Insets(10));
            center.setHgap(20);
            center.setVgap(20);



            Label lblUserName = new Label("Username");
            TextField txtUserName = new TextField();
            Label lblPassword = new Label("Password");
            PasswordField pf = new PasswordField();
            Button btnLogin = new Button("Login");
            btnLogin.setPrefWidth(100);
            Label lblMessage = new Label();

            center.add(lblUserName, 0, 1);
            center.add(txtUserName, 1, 1);
            center.add(lblPassword, 0, 2);
            center.add(pf, 1, 2);
            center.add(btnLogin, 1, 3);
            center.add(lblMessage, 1, 4);

            btnLogin.setOnAction(actionEvent -> {

                String usernameInput = txtUserName.getText();
                String passwordInput = pf.getText();

                if(!usernameInput.isEmpty() && !passwordInput.isEmpty()){

                    User user = db.validateAuthentication(usernameInput,passwordInput);

                    if(user != null){

                        Home home = new Home(db,user);
                        home.getWindow().initModality(Modality.APPLICATION_MODAL);
                        home.getWindow().showAndWait();
                    }else{
                        lblMessage.setText("The username of the password isn't correct");
                    }
                }else{
                    lblMessage.setText("don't leave the fields empty!");
                }

                txtUserName.setText("");
                pf.setText("");

            });

           /* User user = new User("ryan","ryan777","Ryan",
                    "Erfmann","06-12345678","ryan.erfmann@hotmail.com", Role.MANAGER);
            Home home = new Home(db,user);
            home.getWindow().show();
            window.close();*/

            layout.setTop(top);
            layout.setCenter(center);

            Scene scene = new Scene(layout);
            scene.getStylesheets().add("resources/css/style.css");

            window.setScene(scene);
            window.show();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
