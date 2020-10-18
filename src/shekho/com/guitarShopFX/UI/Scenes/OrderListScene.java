package shekho.com.guitarShopFX.UI.Scenes;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import shekho.com.guitarShopFX.DAL.Database;
import shekho.com.guitarShopFX.Models.Article;
import shekho.com.guitarShopFX.Models.Customer;
import shekho.com.guitarShopFX.Models.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderListScene {

    private ObservableList<Order> olOrders;

    private TableView tvArticles = new TableView();
    private HashMap<Article,Integer> articles;
    private ObservableList<Map<String, Object>> olArticles =
            FXCollections.<Map<String, Object>>observableArrayList();
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
        lblOrderList.setId("headerLbl");
        lblDetails.setId("headerLbl");

        //table view orders
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

        TableColumn<Order,String> customerNameCol = new TableColumn("Customer Name");
        customerNameCol.setMinWidth(100);
        customerNameCol.setCellValueFactory(o -> new SimpleStringProperty(o.getValue().getCustomer().getFullName()));

        TableColumn<Order,String> cityCol = new TableColumn("City");
        cityCol.setMinWidth(100);
        cityCol.setCellValueFactory(o -> new SimpleStringProperty(o.getValue().getCustomer().getCity()));

        TableColumn<Order,String> phoneNumberCol = new TableColumn("Phone #");
        phoneNumberCol.setMinWidth(100);
        phoneNumberCol.setCellValueFactory(o -> new SimpleStringProperty(o.getValue().getCustomer().getPhoneNumber()));

        TableColumn<Order,String> emailCol = new TableColumn("E-mail Address");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(o -> new SimpleStringProperty(o.getValue().getCustomer().getEmail()));

        TableColumn countCol = new TableColumn("Count");
        countCol.setMinWidth(50);
        countCol.setCellValueFactory(new PropertyValueFactory<Order,String>("count"));

        TableColumn totalCol = new TableColumn("Total");
        totalCol.setMinWidth(100);
        totalCol.setCellValueFactory(new PropertyValueFactory<Order,String>("totalPrice"));

        tbOrder.getColumns().addAll(orderNumberCol,dateCol,customerNameCol,cityCol,phoneNumberCol,emailCol,countCol,totalCol);
        tbOrder.setItems(olOrders);


        //table view Articles
        tvArticles.setEditable(true);
        tvArticles.getSelectionModel().setCellSelectionEnabled(false);
        tvArticles.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        TableColumn<Map,String> idCol = new TableColumn("uuid");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(new MapValueFactory<>("id"));

        TableColumn<Map,String> brandCol = new TableColumn("Brand");
        brandCol.setMinWidth(100);
        brandCol.setCellValueFactory(new MapValueFactory<>("brand"));

        TableColumn<Map,String> modelCol = new TableColumn("Model");
        modelCol.setMinWidth(100);
        modelCol.setCellValueFactory(new MapValueFactory<>("model"));

        TableColumn<Map,String> acousticCol = new TableColumn("Acoustic");
        acousticCol.setMinWidth(100);
        acousticCol.setCellValueFactory(new MapValueFactory<>("acoustic"));

        TableColumn<Map,String> typeCol = new TableColumn("Type");
        typeCol.setMinWidth(100);
        typeCol.setCellValueFactory(new MapValueFactory<>("type"));

        TableColumn<Map,String> priceCol = new TableColumn("Price");
        priceCol.setMinWidth(100);
        priceCol.setCellValueFactory(new MapValueFactory<>("price"));

        TableColumn<Map,String> quantityCol = new TableColumn("Quantity");
        quantityCol.setMinWidth(100);
        quantityCol.setCellValueFactory(new MapValueFactory<>("quantity"));

        tvArticles.getColumns().addAll(idCol,brandCol,modelCol,acousticCol,typeCol,priceCol,quantityCol);

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

        for (Map.Entry<Article,Integer> entry:articles.entrySet()
             ) {
            HashMap<String,Object> hashMap = new HashMap<>();
            hashMap.put("id",entry.getKey().getId());
            hashMap.put("brand",entry.getKey().getBrand());
            hashMap.put("model",entry.getKey().getModel());
            hashMap.put("acoustic",entry.getKey().isAcoustic());
            hashMap.put("type",entry.getKey().getType());
            hashMap.put("price",entry.getKey().getPrice());
            hashMap.put("quantity",entry.getValue());
            olArticles.add(hashMap);
        }
        tvArticles.setItems(olArticles);
    }
}
