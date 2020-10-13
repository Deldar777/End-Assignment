package shekho.com.guitarShopFX.UI.Scenes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import shekho.com.guitarShopFX.DAL.Database;
import shekho.com.guitarShopFX.Models.Article;
import shekho.com.guitarShopFX.Models.Customer;
import shekho.com.guitarShopFX.Models.Order;

import java.time.LocalDate;
import java.util.List;

public class OrderListScene {

    private ObservableList<Order> olOrders;
    private ObservableList<Article> olArticles;
    private Scene scene;

    public Scene getScene() {
        return scene;
    }

    public OrderListScene(Database db){

        olOrders = FXCollections.observableArrayList(db.getOrders());

        VBox layout = new VBox();
        layout.setPadding(new Insets(20));
        layout.setSpacing(40);

        Label lblOrderList = new Label("Order List");
        Label lblDetails = new Label("Details");
        lblOrderList.setId("lblWelcome");
        lblDetails.setId("lblWelcome");

        TableView<Order> tbOrder = new TableView<>();
        tbOrder.setEditable(true);
        tbOrder.getSelectionModel().setCellSelectionEnabled(false);
        tbOrder.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        TableColumn orderNumberCol = new TableColumn("Order #");
        orderNumberCol.setMinWidth(100);
        orderNumberCol.setCellValueFactory(new PropertyValueFactory<Order, String>("orderNumber"));

        TableColumn dateCol = new TableColumn("Date");
        dateCol.setMinWidth(100);
        dateCol.setCellValueFactory(new PropertyValueFactory<Order, String>("date"));

        TableColumn customerNameCol = new TableColumn("Customer Name");
        customerNameCol.setMinWidth(100);
        customerNameCol.setCellValueFactory(new PropertyValueFactory<Order, String>("name"));

        TableColumn cityCol = new TableColumn("City");
        cityCol.setMinWidth(100);
        cityCol.setCellValueFactory(new PropertyValueFactory<Order, String>("city"));

        TableColumn phoneNumberCol = new TableColumn("Phone #");
        phoneNumberCol.setMinWidth(100);
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<Order, String>("phone"));

        TableColumn emailCol = new TableColumn("E-mail Address");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(new PropertyValueFactory<Order, String>("email"));

        TableColumn countCol = new TableColumn("Count");
        countCol.setMinWidth(50);
        countCol.setCellValueFactory(new PropertyValueFactory<Order,String>("count"));

        TableColumn totalCol = new TableColumn("Total");
        totalCol.setMinWidth(100);
        totalCol.setCellValueFactory(new PropertyValueFactory<Order,String>("totalPrice"));

        tbOrder.getColumns().addAll(orderNumberCol,dateCol,customerNameCol,cityCol,phoneNumberCol,emailCol,countCol,totalCol);
        tbOrder.setItems(olOrders);


        TableView<Article> tbArticles = new TableView<>();
        tbArticles.setEditable(true);
        tbArticles.getSelectionModel().setCellSelectionEnabled(false);
        tbArticles.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        TableColumn idCol = new TableColumn("uuid");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(new PropertyValueFactory<Article, String>("id"));

        TableColumn brandCol = new TableColumn("Brand");
        brandCol.setMinWidth(100);
        brandCol.setCellValueFactory(new PropertyValueFactory<Article, String>("brand"));

        TableColumn modelCol = new TableColumn("Model");
        modelCol.setMinWidth(100);
        modelCol.setCellValueFactory(new PropertyValueFactory<Article, String>("Model"));

        TableColumn acousticCol = new TableColumn("Acoustic");
        acousticCol.setMinWidth(100);
        acousticCol.setCellValueFactory(new PropertyValueFactory<Article, String>("acoustic"));

        TableColumn typeCol = new TableColumn("Type");
        typeCol.setMinWidth(100);
        typeCol.setCellValueFactory(new PropertyValueFactory<Article, String>("type"));

        TableColumn priceCol = new TableColumn("Price");
        priceCol.setMinWidth(100);
        priceCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("price"));

        TableColumn quantityCol = new TableColumn("Quantity");
        quantityCol.setMinWidth(100);
        quantityCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("number"));

        tbArticles.getColumns().addAll(idCol,brandCol,modelCol,acousticCol,typeCol,priceCol,quantityCol);

        tbOrder.setRowFactory(stv ->{
            TableRow<Order> row = new TableRow<>();
            row.setOnMouseClicked(event ->{
                if(event.getClickCount() == 2 && !row.isEmpty()){
                    Order order = row.getItem();
                    List<Article> lArticle= order.getArticles();
                    olArticles = FXCollections.observableArrayList(lArticle);
                    tbArticles.setItems(olArticles);
                }
            });

            return row;
        });

        layout.getChildren().addAll(lblOrderList,tbOrder,lblDetails,tbArticles);
        scene = new Scene(layout);
    }
}
