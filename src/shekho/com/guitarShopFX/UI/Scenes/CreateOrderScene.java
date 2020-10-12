package shekho.com.guitarShopFX.UI.Scenes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import shekho.com.guitarShopFX.DAL.Database;
import shekho.com.guitarShopFX.Models.Order;
import  javafx.scene.control.*;
import shekho.com.guitarShopFX.UI.Dialogs.ChooseCustomerDialog;


public class CreateOrderScene {

    private Order order;
    private Scene scene;
    private int orderNumber;
    private int counter = 1000000;

    public int createOrderNumber(){
        counter++;
        return counter;
    }

    public Scene getScene() {
        return scene;
    }

    public CreateOrderScene(Database db){

        VBox layout = new VBox();
        layout.setPadding(new Insets(20));
        layout.setSpacing(10);

        Label lblCreateOrder = new Label("Create Order #" + createOrderNumber());
        lblCreateOrder.setId("lblWelcome");
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
        gpCustomerFields.setHgap(30);
        gpCustomerFields.setVgap(10);

        Label lblFirstName = new Label("First name");
        Label lblFirstNameEmpty = new Label();
        Label lblLastName = new Label("Last name");
        Label lblLastNameEmpty = new Label();
        Label lblStreetAddress = new Label("Street address");
        Label lblStreetAddressEmpty = new Label();
        Label lblCity = new Label("City");
        Label lblCityEmpty = new Label();
        Label lblPhoneNUmber = new Label("Phone number");
        Label lblPhoneNumberEmpty = new Label();
        Label lblEmailAddress = new Label("Email address");
        Label lblEmailAddressEmpty = new Label();

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
                ChooseCustomerDialog customerDialog = new ChooseCustomerDialog(db);
                customerDialog.getWindow().showAndWait();

                if(customerDialog.getCustomer() != null){

                    lblFirstNameEmpty.setText(customerDialog.getCustomer().getFirstName());
                    lblLastNameEmpty.setText(customerDialog.getCustomer().getLastName());
                    lblStreetAddressEmpty.setText(customerDialog.getCustomer().getStreetAddress());
                    lblCityEmpty.setText(customerDialog.getCustomer().getCity());
                    lblEmailAddressEmpty.setText(customerDialog.getCustomer().getEmail());
                    lblPhoneNumberEmpty.setText(customerDialog.getCustomer().getPhoneNumber());
                }
            }
        });

        TableView tArticles = new TableView();

        TableColumn colQuantity = new TableColumn("Quantity");
        TableColumn colBrand = new TableColumn("Brand");
        TableColumn colModel = new TableColumn("Model");
        TableColumn colAcoustic = new TableColumn("Acoustic");
        TableColumn colType = new TableColumn("Type");
        TableColumn colPrice = new TableColumn("Price");

        tArticles.getColumns().addAll(colQuantity,colBrand,colModel,colAcoustic,colType,colPrice);

        HBox buttonsLayout = new HBox();
        buttonsLayout.setSpacing(20);
        buttonsLayout.setAlignment(Pos.CENTER);

        Button addBtn = new Button("Add");
        Button deleteBtn = new Button("Delete");
        Button confirmBtn = new Button("Confirm");
        Button resetBtn = new Button("Reset");

        buttonsLayout.getChildren().addAll(addBtn,deleteBtn,confirmBtn,resetBtn);

        layout.getChildren().addAll(lblCreateOrder,lblCustomer,search_customerFields,lblArticles,tArticles,buttonsLayout);
        scene = new Scene(layout);
    }
}
