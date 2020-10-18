package shekho.com.guitarShopFX.UI.Scenes;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import shekho.com.guitarShopFX.DAL.Database;
import shekho.com.guitarShopFX.Models.Article;
import shekho.com.guitarShopFX.Models.Customer;
import  javafx.scene.control.*;
import shekho.com.guitarShopFX.UI.Dialogs.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CreateOrderScene {


    private TableView tvArticles = new TableView();
    private HashMap<Article,Integer> articles = new HashMap<>();
    private ObservableList<Map<String, Object>> olArticles =
    FXCollections.<Map<String, Object>>observableArrayList();

    private int amount;

    private Scene scene;
    private Customer customer;
    private int counter = 100;
    private List<Label> customersLbl;

    
    public int getOrderNumber(){
        counter++;
        return counter;
    }

    public Scene getScene() {
        return scene;
    }

    public CreateOrderScene(Database db){

        customersLbl = new ArrayList<>();
        VBox layout = new VBox();
        layout.setPadding(new Insets(20));
        layout.setSpacing(10);

        Label lblCreateOrder = new Label("Create Order #" + getOrderNumber());
        lblCreateOrder.setId("headerLbl");
        Label lblCustomer = new Label("Customer");
        lblCustomer.setId("lbl");
        Label lblArticles = new Label("Articles");
        lblArticles.setId("lbl");


        HBox search_customerFields = new HBox();
        search_customerFields.setPadding(new Insets(20,20,20,20));
        search_customerFields.setSpacing(150);

        HBox searchLayout = new HBox();
        searchLayout.setSpacing(20);
        TextField txtSearch = new TextField();
        txtSearch.setPrefWidth(150);
        Button btnSearch = new Button("Search");
        btnSearch.setPrefWidth(100);
        searchLayout.getChildren().addAll(txtSearch,btnSearch);

        GridPane gpCustomerFields = new GridPane();
        gpCustomerFields.setId("backGroundCustomer");
        gpCustomerFields.setMinWidth(300);
        gpCustomerFields.setHgap(30);
        gpCustomerFields.setVgap(10);

        Label lblFirstName = new Label("First name");
        Label lblFirstNameEmpty = new Label();
        lblFirstNameEmpty.setId("lbl");
        Label lblLastName = new Label("Last name");
        Label lblLastNameEmpty = new Label();
        lblLastNameEmpty.setId("lbl");
        Label lblStreetAddress = new Label("Street address");
        Label lblStreetAddressEmpty = new Label();
        lblStreetAddressEmpty.setId("lbl");
        Label lblCity = new Label("City");
        Label lblCityEmpty = new Label();
        lblCityEmpty.setId("lbl");
        Label lblPhoneNUmber = new Label("Phone number");
        Label lblPhoneNumberEmpty = new Label();
        lblPhoneNumberEmpty.setId("lbl");
        Label lblEmailAddress = new Label("Email address");
        Label lblEmailAddressEmpty = new Label();
        lblEmailAddressEmpty.setId("lbl");

        //first row
        gpCustomerFields.add(lblFirstName,0,0);
        gpCustomerFields.add(lblFirstNameEmpty,1,0);
        gpCustomerFields.add(lblLastName,2,0);
        gpCustomerFields.add(lblLastNameEmpty,3,0);
        //second row
        gpCustomerFields.add(lblStreetAddress,0,1);
        gpCustomerFields.add(lblStreetAddressEmpty,1,1);
        gpCustomerFields.add(lblCity,2,1);
        gpCustomerFields.add(lblCityEmpty,3,1);
        //third row
        gpCustomerFields.add(lblPhoneNUmber,0,2);
        gpCustomerFields.add(lblPhoneNumberEmpty,1,2);
        gpCustomerFields.add(lblEmailAddress,2,2);
        gpCustomerFields.add(lblEmailAddressEmpty,3,2);

        search_customerFields.getChildren().addAll(searchLayout,gpCustomerFields);

        btnSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ChooseCustomerDialog ccd = new ChooseCustomerDialog(db);
                ccd.getWindow().initModality(Modality.APPLICATION_MODAL);
                ccd.getWindow().showAndWait();

                customer = ccd.getCustomer();
                if(customer != null){

                    lblFirstNameEmpty.setText(ccd.getCustomer().getFirstName());
                    lblLastNameEmpty.setText(ccd.getCustomer().getLastName());
                    lblStreetAddressEmpty.setText(ccd.getCustomer().getStreetAddress());
                    lblCityEmpty.setText(ccd.getCustomer().getCity());
                    lblEmailAddressEmpty.setText(ccd.getCustomer().getEmail());
                    lblPhoneNumberEmpty.setText(ccd.getCustomer().getPhoneNumber());

                    customersLbl.add(lblFirstNameEmpty);
                    customersLbl.add(lblLastNameEmpty);
                    customersLbl.add(lblStreetAddressEmpty);
                    customersLbl.add(lblCityEmpty);
                    customersLbl.add(lblEmailAddressEmpty);
                    customersLbl.add(lblPhoneNumberEmpty);
                }
            }
        });


        tvArticles.setEditable(true);
        tvArticles.getSelectionModel().setCellSelectionEnabled(false);
        tvArticles.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        TableColumn<Map,String> numberCol = new TableColumn("Quantity");
        numberCol.setMinWidth(100);
        numberCol.setCellValueFactory(new MapValueFactory<>("quantity"));

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

        tvArticles.getColumns().addAll(numberCol,brandCol,modelCol,acousticCol,typeCol,priceCol);


        HBox buttonsLayout = new HBox();
        buttonsLayout.setSpacing(20);
        buttonsLayout.setAlignment(Pos.CENTER);

        Button addBtn = new Button("Add");
        Button deleteBtn = new Button("Delete");
        Button confirmBtn = new Button("Confirm");
        Button resetBtn = new Button("Reset");
        Label lblWarning = new Label();
        lblWarning.setId("lblWarning");

        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblWarning.setText("");
                AddArticlesDialog aad = new AddArticlesDialog(db);
                aad.getWindow().initModality(Modality.APPLICATION_MODAL);
                aad.getWindow().showAndWait();

                if(aad.getArticle() != null && aad.getAmount() <= aad.getArticle().getQuantity()){

                    Article article = aad.getArticle();
                    if(articles.containsKey(article)){
                        articles.remove(article);
                        articles.put(article,aad.getAmount());
                    }
                    else {
                        articles.put(article, aad.getAmount());
                    }
                    fillTableArticles();
                }
            }
        });


        deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblWarning.setText("");

                Object object = tvArticles.getSelectionModel().getSelectedItem();

                if(object != null){
                    tvArticles.getItems().removeAll(tvArticles.getSelectionModel().getSelectedItems());
                }else{
                    lblWarning.setText("You did not choose any item! choose item and then press delete");
                }

            }
        });

        resetBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                customer = null;
                for (Label l:customersLbl
                ) {
                    l.setText("");
                }
                articles  = new HashMap<>();
                fillTableArticles();
            }
        });

        confirmBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblWarning.setText("");

                if(customer != null && !articles.isEmpty()){
                    ConfirmOrderDialog cod = new ConfirmOrderDialog(db,customer,articles);
                    cod.getWindow().showAndWait();

                }else{
                    lblWarning.setText("the order is not complete! choose a customer and articles and then press confirm");
                }

                customer = null;
                for (Label l:customersLbl
                     ) {
                    l.setText("");
                }
                articles  = new HashMap<>();
                fillTableArticles();
            }
        });
        buttonsLayout.getChildren().addAll(addBtn,deleteBtn,confirmBtn,resetBtn);

        layout.getChildren().addAll(lblCreateOrder,lblCustomer,search_customerFields,lblArticles,tvArticles,buttonsLayout,lblWarning);
        scene = new Scene(layout);
    }

    public void fillTableArticles(){

        tvArticles.getItems().clear();
        olArticles.clear();
        for(Map.Entry<Article,Integer> entry : articles.entrySet()) {
            HashMap<String,Object> mapArticles = new HashMap<>();
            mapArticles.put("quantity",entry.getValue());
            mapArticles.put("brand",entry.getKey().getBrand());
            mapArticles.put("model",entry.getKey().getModel());
            mapArticles.put("acoustic",entry.getKey().isAcoustic());
            mapArticles.put("type",entry.getKey().getType());
            mapArticles.put("price",entry.getKey().getPrice());
            olArticles.add(mapArticles);
        }
        tvArticles.getItems().addAll(olArticles);
    }
}
