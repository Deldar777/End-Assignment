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
import shekho.com.guitarShopFX.Models.*;
import shekho.com.guitarShopFX.Models.Role;
import shekho.com.guitarShopFX.Models.User;
import shekho.com.guitarShopFX.UI.Scenes.*;

import java.io.*;
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
        window.setWidth(1000);
        window.setHeight(700);

        VBox layout = new VBox();
        layout.setPadding(new Insets(0,10,40,10));
        layout.setSpacing(40);

        MenuBar menuBar = new MenuBar();
        menuBar.setPrefHeight(20);

        Menu menuHome = new Menu("Home");
        Menu menuSales = new Menu("Sales");
        Menu menuStock = new Menu("Stock");
        Menu menuCustomers = new Menu("Customers");
        Menu menuArticles = new Menu("Articles");

        MenuItem listOrdersItem = new MenuItem("Orders");
        MenuItem createOrderItem = new MenuItem("Create Order");
        MenuItem manageStockItem = new MenuItem("Manage Stock");
        MenuItem dashBoardItem = new MenuItem("Dashboard");
        MenuItem editCustomer = new MenuItem("Edit customer");
        MenuItem editArticle = new MenuItem("Edit Article");
        MenuItem loadItem = new MenuItem("Load...");
        MenuItem saveItem = new MenuItem("Save...");

        menuStock.getItems().add(manageStockItem);
        menuSales.getItems().add(listOrdersItem);
        menuHome.getItems().addAll(dashBoardItem,loadItem,saveItem);
        menuCustomers.getItems().add(editCustomer);
        menuArticles.getItems().add(editArticle);
        menuBar.getMenus().addAll(menuHome,menuSales);

        //determine which menu and menuitem add to menubar depends on the user role
        if(user.getRole() == Role.MANAGER){
            menuBar.getMenus().addAll(menuStock,menuCustomers);
        }else {
            menuBar.getMenus().addAll(menuArticles);
            menuSales.getItems().add(createOrderItem);
        }

        saveItem.setOnAction(ActionEvent ->{
            try(FileOutputStream fos = new FileOutputStream(new File("articles.file"));
                ObjectOutputStream oos = new ObjectOutputStream(fos)){

                for (Article a:db.getArticles()
                ) {
                    oos.writeObject(a);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        loadItem.setOnAction(ActionEvent ->{
            try(ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(new File("articles.file"))))
            {
                while (true){

                    try{
                        Article a = (Article) ois.readObject();
                        db.getArticles().add(a);
                    }catch (EOFException eofe){
                        break;
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        createOrderItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CreateOrderScene createOrderScene = new CreateOrderScene(db);
                layout.getChildren().remove(1);
                layout.getChildren().add(createOrderScene.getScene().getRoot());
                window.setTitle("GuitarShop FX - Create an Order");
            }
        });

        listOrdersItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                OrderListScene ols = new OrderListScene(db);
                layout.getChildren().remove(1);
                layout.getChildren().add(ols.getScene().getRoot());
                window.setTitle("GuitarShop FX - View Order List");
            }
        });

        editCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                EditCustomerScene ecs = new EditCustomerScene(db);
                layout.getChildren().remove(1);
                layout.getChildren().add(ecs.getScene().getRoot());
                window.setTitle("GuitarShop FX - Edit Customer");
            }
        });
        editArticle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                EditArticleScene ecs = new EditArticleScene(db);
                layout.getChildren().remove(1);
                layout.getChildren().add(ecs.getScene().getRoot());
                window.setTitle("GuitarShop FX - Edit Article");
            }
        });

        manageStockItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ManageStockScene mss = new ManageStockScene(db);
                layout.getChildren().remove(1);
                layout.getChildren().add(mss.getScene().getRoot());
                window.setTitle("GuitarShop FX - Stock Maintenance");
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
        lblWelcome.setId("headerLbl");

        layout.getChildren().addAll(menuBar,labelsLayout);
        

        scene = new Scene(layout);
        scene.getStylesheets().add("resources/css/style.css");
        window.setScene(scene);

        dashBoardItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                layout.getChildren().remove(1);
                layout.getChildren().add(labelsLayout);
            }
        });
    }
}
