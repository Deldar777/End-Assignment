package shekho.com.guitarShopFX.UI.Dialogs;

import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import shekho.com.guitarShopFX.DAL.Database;
import shekho.com.guitarShopFX.Models.*;
import java.util.*;

public class ConfirmOrderDialog {

    private final Stage window;
    public Stage getWindow() {
        return window;
    }

    public ConfirmOrderDialog(Database db, Customer customer, HashMap<Article,Integer> articles,int orderNumber){

        window = new Stage();
        window.setTitle("GuitarShop FX - Confirm Order");
        Image image = new Image("resources/css/images/guitarImage.png");
        window.getIcons().add(image);


        VBox layout = new VBox();
        layout.setPadding(new Insets(40,40,40,40));
        layout.setSpacing(20);

        GridPane customerLayout = new GridPane();
        customerLayout.setVgap(10);
        customerLayout.setHgap(10);

        Label lblCustomer = new Label("Customer");
        Label lblFullName = new Label(customer.getFullName());
        Label lblAddress = new Label(customer.getStreetAddress());
        Label lblCity = new Label(customer.getCity());
        Label lblPhone = new Label(customer.getPhoneNumber());
        Label lblEmail = new Label(customer.getEmail());

        customerLayout.add(lblCustomer,0,0);
        customerLayout.add(lblFullName,1,0);
        customerLayout.add(lblAddress,1,1);
        customerLayout.add(lblCity,1,2);
        customerLayout.add(lblPhone,1,3);
        customerLayout.add(lblEmail,1,4);

        HBox articleTitlesLayout = new HBox();
        articleTitlesLayout.setSpacing(60);

        Label lblQuantity = new Label("Qty");
        Label lblBrand = new Label("Brand");
        Label lblModel = new Label("Model");
        Label lblType = new Label("Type");
        Label lblPrice = new Label("Price");

        articleTitlesLayout.getChildren().addAll(lblQuantity,lblBrand,lblModel,lblType,lblPrice);
        layout.getChildren().addAll(customerLayout,articleTitlesLayout);

        double totalPrice = 0;


        for (Map.Entry<Article,Integer> entry:articles.entrySet()
        ) {

            totalPrice += entry.getKey().getPrice() * entry.getValue();
            HBox articlesInformationLayout = new HBox();
            articlesInformationLayout.setSpacing(60);

            Label lblQ = new Label(String.valueOf(entry.getValue()));
            Label lblB = new Label(entry.getKey().getBrand());
            Label lblM = new Label(entry.getKey().getModel());
            Label lblT = new Label(String.valueOf(entry.getKey().getType()));
            Label lblP = new Label(String.valueOf(entry.getKey().getPrice()));

            articlesInformationLayout.getChildren().addAll(lblQ,lblB,lblM,lblT,lblP);
            layout.getChildren().add(articlesInformationLayout);
        }

        HBox totalPriceLayout = new HBox();
        totalPriceLayout.setSpacing(10);

        Label lblTotalPriceTitle = new Label("Total price");
        Label lblTotalPrice = new Label(String.format("%.2f" ,totalPrice));

        totalPriceLayout.getChildren().addAll(lblTotalPriceTitle,lblTotalPrice);

        Button btnConfirm = new Button("Confirm");

        btnConfirm.setOnAction(actionEvent -> {

            Order order = new Order(customer,articles,orderNumber);
            db.setOrders(order);
            window.close();
        });

        layout.getChildren().addAll(totalPriceLayout,btnConfirm);
        Scene scene = new Scene(layout);
        scene.getStylesheets().add("resources/css/style.css");
        window.setScene(scene);
    }
}
