package shekho.com.guitarShopFX.UI.Scenes;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import shekho.com.guitarShopFX.DAL.Database;
import shekho.com.guitarShopFX.Models.Article;
import shekho.com.guitarShopFX.Models.Customer;

public class ManageStockScene {

    private Scene scene;
    private ObservableList<Article> olArticles;

    public Scene getScene() {
        return scene;
    }

    public ManageStockScene(Database db){

        olArticles = FXCollections.observableArrayList(db.getArticles());

        VBox layout = new VBox();
        layout.setPadding(new Insets(20,20,80,20));
        layout.setSpacing(40);

        Label lblStockMaintenance = new Label("Stock Maintenance");
        lblStockMaintenance.setId("headerLbl");

        TableView<Article> tbArticles = new TableView<>();
        tbArticles.setEditable(true);
        tbArticles.getSelectionModel().setCellSelectionEnabled(false);
        tbArticles.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        TableColumn quantityCol = new TableColumn("Quantity");
        quantityCol.setMinWidth(100);
        quantityCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("quantity"));

        TableColumn brandCol = new TableColumn("Brand");
        brandCol.setMinWidth(100);
        brandCol.setCellValueFactory(new PropertyValueFactory<Article, String>("brand"));

        TableColumn modelCol = new TableColumn("Model");
        modelCol.setMinWidth(100);
        modelCol.setCellValueFactory(new PropertyValueFactory<Article, String>("model"));

        TableColumn acousticCol = new TableColumn("Acoustic");
        acousticCol.setMinWidth(100);
        acousticCol.setCellValueFactory(new PropertyValueFactory<Article, String>("acoustic"));

        TableColumn typeCol = new TableColumn("Guitar Type");
        typeCol.setMinWidth(100);
        typeCol.setCellValueFactory(new PropertyValueFactory<Article, String>("type"));


        tbArticles.getColumns().addAll(quantityCol,brandCol,modelCol,acousticCol,typeCol);
        tbArticles.setItems(olArticles);

        HBox bottomLayout = new HBox();
        bottomLayout.setSpacing(10);

        TextField txtQuantity = new TextField();
        txtQuantity.setPromptText("Quantity");
        RadioButton rbtnNegate = new RadioButton("Negate");
        Button btnAdd = new Button("Add");
        Label lblWarning = new Label();
        lblWarning.setId("lblWarning");

        bottomLayout.getChildren().addAll(txtQuantity,rbtnNegate,btnAdd);


        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Article a = tbArticles.getSelectionModel().getSelectedItem();

                if(a != null){
                    try{
                        int newNumber = Integer.parseInt(txtQuantity.getText());

                        if(rbtnNegate.isSelected()){

                            if(a.getQuantity() - newNumber >= 0){
                                a.setQuantity(a.getQuantity() - newNumber);
                            }else{
                                lblWarning.setText("We have only "+a.getQuantity() + " in stock for "+a.getModel());
                            }
                        }else{
                            a.setQuantity(a.getQuantity() + newNumber);
                        }

                    }catch (Exception e){
                        lblWarning.setText(e.getMessage());
                    }

                }else {
                    lblWarning.setId("You did not choose any item! first choose item and then press add");
                }

                tbArticles.refresh();
            }
        });

        layout.getChildren().addAll(lblStockMaintenance,tbArticles,bottomLayout,lblWarning);
        scene = new Scene(layout);
    }
}
