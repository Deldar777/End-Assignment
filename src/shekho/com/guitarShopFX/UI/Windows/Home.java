package shekho.com.guitarShopFX.UI.Windows;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import shekho.com.guitarShopFX.DAL.Database;
import shekho.com.guitarShopFX.Models.Role;
import shekho.com.guitarShopFX.Models.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Home {

    private Stage window;
    public Stage getWindow() {
        return window;
    }

    public Home(Database db, User user){

        window = new Stage();
        window.setTitle("GuitarShop FX - Dashboard");
        Image image = new Image("resources/css/images/guitarImage.png");
        window.getIcons().add(image);
        window.setWidth(1000);
        window.setHeight(600);

        VBox layout = new VBox();
        layout.setPadding(new Insets(0,10,40,10));
        layout.setSpacing(40);

        MenuBar menuBar = new MenuBar();
        menuBar.setPrefHeight(20);
        Menu menuHome = new Menu("Home");
        Menu menuSales = new Menu("Sales");
        MenuItem listOrdersItem = new MenuItem("Orders");
        MenuItem maintainItem = new MenuItem("Maintain Stock");
        MenuItem makeOrderItem = new MenuItem("Make Order");

        //determine which menuitem add to menubar depends on the user role
        if(user.getRole() == Role.MANAGER){
            menuSales.getItems().add(maintainItem);
        }else {
            menuSales.getItems().add(makeOrderItem);
        }
        menuSales.getItems().add(listOrdersItem);
        menuBar.getMenus().addAll(menuHome,menuSales);

        Label lblWelcome = new Label("Welcome "+user.getFullName());
        Label lblUserRole = new Label("Your role is: "+user.getRole());
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        Label lblTime = new Label("Today is: "+ time);


        menuBar.setId("menuBar");
        lblWelcome.setId("lblWelcome");

        layout.getChildren().addAll(menuBar,lblWelcome,lblUserRole,lblTime);

        Scene scene = new Scene(layout);
        scene.getStylesheets().add("resources/css/style.css");
        window.setScene(scene);
    }
}
