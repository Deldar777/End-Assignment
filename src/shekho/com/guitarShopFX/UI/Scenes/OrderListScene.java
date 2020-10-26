package shekho.com.guitarShopFX.UI.Scenes;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.VBox;
import shekho.com.guitarShopFX.DAL.*;
import shekho.com.guitarShopFX.Models.*;

import java.util.HashMap;
import java.util.Map;

public class OrderListScene {

    private final TableView<Map.Entry<Article,Integer>> tvArticles = new TableView<>();
    private HashMap<Article,Integer> articles = new HashMap<>();
    private final ObservableList<Map.Entry<Article, Integer>> olArticles =
            FXCollections.observableArrayList();
    private final Scene scene;

    public Scene getScene() {
        return scene;
    }

    public OrderListScene(Database db){

        ObservableList<Order> olOrders = FXCollections.observableArrayList(db.getOrders());

        VBox layout = new VBox();
        layout.setPadding(new Insets(20));
        layout.setSpacing(40);

        Label lblOrderList = new Label("Order List");
        Label lblDetails = new Label("Details");
        lblOrderList.setId("headerLbl");
        lblDetails.setId("headerLbl");

        //table view orders
        TableView<Order> tbOrder = new TableView<>();
        tbOrder.setEditable(true);
        tbOrder.getSelectionModel().setCellSelectionEnabled(false);
        tbOrder.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        TableColumn<Order,String> orderNumberCol = new TableColumn<>("Order #");
        orderNumberCol.setMinWidth(100);
        orderNumberCol.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
        TableColumn<Order,String> dateCol = new TableColumn<>("Date");
        dateCol.setMinWidth(100);
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn<Order,String> customerNameCol = new TableColumn<>("Customer Name");
        customerNameCol.setMinWidth(100);
        customerNameCol.setCellValueFactory(o -> new SimpleStringProperty(o.getValue().getCustomer().getFullName()));
        TableColumn<Order,String> cityCol = new TableColumn<>("City");
        cityCol.setMinWidth(100);
        cityCol.setCellValueFactory(o -> new SimpleStringProperty(o.getValue().getCustomer().getCity()));
        TableColumn<Order,String> phoneNumberCol = new TableColumn<>("Phone #");
        phoneNumberCol.setMinWidth(100);
        phoneNumberCol.setCellValueFactory(o -> new SimpleStringProperty(o.getValue().getCustomer().getPhoneNumber()));
        TableColumn<Order,String> emailCol = new TableColumn<>("E-mail Address");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(o -> new SimpleStringProperty(o.getValue().getCustomer().getEmail()));
        TableColumn<Order,String> countCol = new TableColumn<>("Count");
        countCol.setMinWidth(50);
        countCol.setCellValueFactory(new PropertyValueFactory<>("count"));
        TableColumn<Order,String> totalCol = new TableColumn<>("Total");
        totalCol.setMinWidth(100);
        totalCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        tbOrder.getColumns().addAll(orderNumberCol,dateCol,customerNameCol,cityCol,phoneNumberCol,emailCol,countCol,totalCol);
        tbOrder.setItems(olOrders);


        //table view Articles
        TableView<Map.Entry<Article,Integer>> tvArticles = new TableView<>();
        tvArticles.setEditable(true);
        tvArticles.getSelectionModel().setCellSelectionEnabled(false);
        tvArticles.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        TableColumn<Map.Entry<Article,Integer>,String> idCol = new TableColumn<>("uuid");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(A -> new SimpleStringProperty(A.getValue().getKey().getId()));
        TableColumn<Map.Entry<Article,Integer>,String> brandCol = new TableColumn<>("Brand");
        brandCol.setMinWidth(100);
        brandCol.setCellValueFactory(A -> new SimpleStringProperty(A.getValue().getKey().getBrand()));
        TableColumn<Map.Entry<Article,Integer>,String> modelCol = new TableColumn<>("Model");
        modelCol.setMinWidth(100);
        modelCol.setCellValueFactory(A -> new SimpleStringProperty(A.getValue().getKey().getModel()));
        TableColumn<Map.Entry<Article,Integer>,String> acousticCol = new TableColumn<>("Acoustic");
        acousticCol.setMinWidth(100);
        acousticCol.setCellValueFactory(A -> new SimpleStringProperty(String.valueOf(A.getValue().getKey().isAcoustic())));
        TableColumn<Map.Entry<Article,Integer>,String> typeCol = new TableColumn<>("Type");
        typeCol.setMinWidth(100);
        typeCol.setCellValueFactory(A -> new SimpleStringProperty(String.valueOf(A.getValue().getKey().getType())));
        TableColumn<Map.Entry<Article,Integer>,String> priceCol = new TableColumn<>("Price");
        priceCol.setMinWidth(100);
        priceCol.setCellValueFactory(A -> new SimpleStringProperty(String.valueOf(A.getValue().getKey().getPrice())));
        TableColumn<Map.Entry<Article,Integer>,String> numberCol = new TableColumn<>("Quantity");
        numberCol.setMinWidth(100);
        numberCol.setCellValueFactory(A -> new SimpleStringProperty(String.valueOf(A.getValue().getValue())));

        tvArticles.getColumns().addAll(idCol,brandCol,modelCol,acousticCol,typeCol,priceCol,numberCol);
        tvArticles.setItems(olArticles);

        tbOrder.setRowFactory(stv ->{
            TableRow<Order> row = new TableRow<>();
            row.setOnMouseClicked(event ->{
                if(event.getClickCount() == 2 && !row.isEmpty()){
                    Order order = row.getItem();
                    articles = order.getArticles();
                    fillTableviewArticles();
                }
            });
            return row;
        });

        layout.getChildren().addAll(lblOrderList,tbOrder,lblDetails,tvArticles);
        scene = new Scene(layout);
    }
    public void fillTableviewArticles(){
        tvArticles.getItems().clear();
        olArticles.addAll(articles.entrySet());

    }
}
