package shekho.com.guitarShopFX.UI.Scenes;



import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import shekho.com.guitarShopFX.DAL.Database;
import shekho.com.guitarShopFX.Models.*;
import  javafx.scene.control.*;
import shekho.com.guitarShopFX.UI.Dialogs.*;
import java.util.*;



public class CreateOrderScene {


    private final TableView<Map.Entry<Article,Integer>> tvArticles = new TableView<>();
    private HashMap<Article,Integer> articles = new HashMap<>();
    private final ObservableList<Map.Entry<Article,Integer>> olArticles =
            FXCollections.observableArrayList();


    private final Scene scene;
    private Customer customer;
    private final List<Label> customersLbl;
    private final int orderNumber;

    public Scene getScene() {
        return scene;
    }

    public CreateOrderScene(Database db){

        customersLbl = new ArrayList<>();
        VBox layout = new VBox();
        layout.setPadding(new Insets(20));
        layout.setSpacing(10);

        orderNumber = db.getOrderNumber();
        Label lblCreateOrder = new Label("Create Order #" + orderNumber);
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
        lblFirstName.setId("lbl");
        Label lblFirstNameEmpty = new Label();
        lblFirstNameEmpty.setId("lbl");
        Label lblLastName = new Label("Last name");
        lblLastName.setId("lbl");
        Label lblLastNameEmpty = new Label();
        lblLastNameEmpty.setId("lbl");
        Label lblStreetAddress = new Label("Street address");
        lblStreetAddress.setId("lbl");
        Label lblStreetAddressEmpty = new Label();
        lblStreetAddressEmpty.setId("lbl");
        Label lblCity = new Label("City");
        lblCity.setId("lbl");
        Label lblCityEmpty = new Label();
        lblCityEmpty.setId("lbl");
        Label lblPhoneNUmber = new Label("Phone number");
        lblPhoneNUmber.setId("lbl");
        Label lblPhoneNumberEmpty = new Label();
        lblPhoneNumberEmpty.setId("lbl");
        Label lblEmailAddress = new Label("Email address");
        lblEmailAddress.setId("lbl");
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

        btnSearch.setOnAction(actionEvent -> {
            ChooseCustomerDialog ccd = new ChooseCustomerDialog(db);
            ccd.getWindow().initModality(Modality.APPLICATION_MODAL);
            ccd.getWindow().showAndWait();

            customer = ccd.getCustomer();
            if(customer != null){

                lblFirstNameEmpty.setText(ccd.getCustomer().getFirstName());
                customersLbl.add(lblFirstNameEmpty);
                lblLastNameEmpty.setText(ccd.getCustomer().getLastName());
                customersLbl.add(lblLastNameEmpty);
                lblStreetAddressEmpty.setText(ccd.getCustomer().getStreetAddress());
                customersLbl.add(lblStreetAddressEmpty);
                lblCityEmpty.setText(ccd.getCustomer().getCity());
                customersLbl.add(lblCityEmpty);
                lblEmailAddressEmpty.setText(ccd.getCustomer().getEmail());
                customersLbl.add(lblEmailAddressEmpty);
                lblPhoneNumberEmpty.setText(ccd.getCustomer().getPhoneNumber());
                customersLbl.add(lblPhoneNumberEmpty);
            }
        });


        tvArticles.setEditable(true);
        tvArticles.getSelectionModel().setCellSelectionEnabled(false);
        tvArticles.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        TableColumn<Map.Entry<Article,Integer>,String> numberCol = new
                TableColumn<>("Quantity");
        numberCol.setMinWidth(100);
        numberCol.setCellValueFactory(A -> new SimpleStringProperty
                (String.valueOf(A.getValue().getValue())));
        TableColumn<Map.Entry<Article,Integer>,String> brandCol = new
                TableColumn<>("Brand");
        brandCol.setMinWidth(100);
        brandCol.setCellValueFactory(A -> new SimpleStringProperty
                (A.getValue().getKey().getBrand()));
        TableColumn<Map.Entry<Article,Integer>,String> modelCol = new
                TableColumn<>("Model");
        modelCol.setMinWidth(100);
        modelCol.setCellValueFactory(A -> new SimpleStringProperty
                (A.getValue().getKey().getModel()));
        TableColumn<Map.Entry<Article,Integer>,String> acousticCol = new
                TableColumn<>("Acoustic");
        acousticCol.setMinWidth(100);
        acousticCol.setCellValueFactory(A -> new SimpleStringProperty
                (String.valueOf(A.getValue().getKey().isAcoustic())));
        TableColumn<Map.Entry<Article,Integer>,String> typeCol = new
                TableColumn<>("Type");
        typeCol.setMinWidth(100);
        typeCol.setCellValueFactory(A -> new SimpleStringProperty
                (String.valueOf(A.getValue().getKey().getType())));
        TableColumn<Map.Entry<Article,Integer>,String> priceCol = new
                TableColumn<>("Price");
        priceCol.setMinWidth(100);
        priceCol.setCellValueFactory(A -> new SimpleStringProperty
                (String.valueOf(A.getValue().getKey().getPrice())));

        tvArticles.getColumns().addAll(numberCol,brandCol,modelCol,acousticCol,typeCol,priceCol);
        tvArticles.setItems(olArticles);



        HBox buttonsLayout = new HBox();
        buttonsLayout.setSpacing(20);
        buttonsLayout.setAlignment(Pos.CENTER);

        Button addBtn = new Button("Add");
        Button deleteBtn = new Button("Delete");
        Button confirmBtn = new Button("Confirm");
        Button resetBtn = new Button("Reset");
        Label lblWarning = new Label();
        lblWarning.setId("lblWarning");

        addBtn.setOnAction(actionEvent -> {
            lblWarning.setText("");
            AddArticlesDialog aad = new AddArticlesDialog(db);
            aad.getWindow().initModality(Modality.APPLICATION_MODAL);
            aad.getWindow().showAndWait();

            if(aad.getArticle() != null && aad.getAmount() <= aad.getArticle().getQuantity()){

                Article article = aad.getArticle();

                if(articles.containsKey(article)){
                    int oldValue = articles.get(article);
                    article.setQuantity(article.getQuantity() + oldValue);

                    articles.replace(article,oldValue,oldValue + aad.getAmount());

                    int ordered = articles.get(article);
                    article.setQuantity(article.getQuantity() - ordered);
                }
                else {
                    articles.put(article, aad.getAmount());

                    int ordered = articles.get(article);
                    article.setQuantity(article.getQuantity() - ordered);
                }
                fillTableArticles();
            }
        });

        deleteBtn.setOnAction(actionEvent -> {
            try{
                lblWarning.setText("");

                Article a = tvArticles.getSelectionModel().getSelectedItem().getKey();
                int orderedNumber = tvArticles.getSelectionModel().getSelectedItem().getValue();

                if(a != null){
                    a.setQuantity(a.getQuantity() + orderedNumber);
                    articles.remove(a);
                    tvArticles.getItems().removeAll(tvArticles.getSelectionModel().getSelectedItems());
                }else{
                    lblWarning.setText("You did not choose any item! choose item and then press delete");
                }
            }catch (Exception e){
                lblWarning.setText(e.getMessage());
            }
        });

        resetBtn.setOnAction(actionEvent -> {
            customer = null;
            for (Label l:customersLbl
            ) {
                l.setText("");
            }
            //if the order is reset to set articles quantity back
            setOrderedNumberBack();

            articles  = new HashMap<>();
            fillTableArticles();
        });

        confirmBtn.setOnAction(actionEvent -> {
            lblWarning.setText("");
            if(customer != null && !articles.isEmpty()){
                ConfirmOrderDialog cod = new ConfirmOrderDialog(db,customer,articles,orderNumber);
                cod.getWindow().showAndWait();

            }else{
                lblWarning.setText("the order is not complete! choose a customer and articles and then press confirm");
                //in case if the customer was not chosen to set articles quantity back
                setOrderedNumberBack();
            }
            customer = null;
            for (Label l:customersLbl
            ) {
                l.setText("");
            }
            articles  = new HashMap<>();
            fillTableArticles();
        });
        buttonsLayout.getChildren().addAll(addBtn,deleteBtn,confirmBtn,resetBtn);

        layout.getChildren().addAll(lblCreateOrder,lblCustomer,search_customerFields,lblArticles,tvArticles,buttonsLayout,lblWarning);
        scene = new Scene(layout);
    }

    public void fillTableArticles(){
        tvArticles.getItems().clear();
        olArticles.clear();
        olArticles.addAll(articles.entrySet());
    }

    //if the order is cancelled,reset or was not complete to set the articles quantity back
    public void setOrderedNumberBack(){
        for (Map.Entry<Article,Integer> entry:articles.entrySet()
        ) {
            int orderedNumber  = entry.getValue();
            entry.getKey().setQuantity(entry.getKey().getQuantity() + orderedNumber);
        }
    }
}
