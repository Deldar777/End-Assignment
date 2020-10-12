package shekho.com.guitarShopFX.UI.Windows;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import shekho.com.guitarShopFX.UI.Scenes.CreateOrderScene;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Home {

    private Stage window;
    public Stage getWindow() {
        return window;
    }
    private Scene scene;

    public Home(Database db, User user){

        window = new Stage();
        window.setTitle("GuitarShop FX - Dashboard");
        Image image = new Image("resources/css/images/guitarImage.png");
        window.getIcons().add(image);
        window.setWidth(1200);
        window.setHeight(800);

        VBox layout = new VBox();
        layout.setPadding(new Insets(0,10,40,10));
        layout.setSpacing(40);

        MenuBar menuBar = new MenuBar();
        menuBar.setPrefHeight(20);
        Menu menuHome = new Menu("Home");
        Menu menuSales = new Menu("Sales");
        MenuItem listOrdersItem = new MenuItem("Orders");
        MenuItem maintainItem = new MenuItem("Maintain Stock");
        MenuItem createOrderItem = new MenuItem("Create Order");

        //determine which menuitem add to menubar depends on the user role
        if(user.getRole() == Role.MANAGER){
            menuSales.getItems().add(maintainItem);
        }else {
            menuSales.getItems().add(createOrderItem);
        }
        menuSales.getItems().add(listOrdersItem);
        menuBar.getMenus().addAll(menuHome,menuSales);


        createOrderItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CreateOrderScene createOrderScene = new CreateOrderScene(db);
                layout.getChildren().remove(1);
                layout.getChildren().add(createOrderScene.getScene().getRoot());
            }
        });

        VBox labelsLayout = new VBox();
        labelsLayout.setSpacing(50);

        Label lblWelcome = new Label("Welcome "+user.getFullName());
        Label lblUserRole = new Label("Your role is: "+user.getRole());
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        Label lblTime = new Label("Today is: "+ time);

        labelsLayout.getChildren().addAll(lblWelcome,lblUserRole,lblTime);

        menuBar.setId("backGround");
        lblWelcome.setId("lblWelcome");

        layout.getChildren().addAll(menuBar,labelsLayout);

        scene = new Scene(layout);
        scene.getStylesheets().add("resources/css/style.css");
        window.setScene(scene);
    }
}
