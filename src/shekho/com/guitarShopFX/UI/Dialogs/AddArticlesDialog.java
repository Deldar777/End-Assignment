package shekho.com.guitarShopFX.UI.Dialogs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import shekho.com.guitarShopFX.DAL.Database;
import shekho.com.guitarShopFX.Models.Article;
import shekho.com.guitarShopFX.Models.Customer;

import java.util.ArrayList;
import java.util.List;

public class AddArticlesDialog {



    private ObservableList<Article> olArticles;
    private Article article;
    private Stage window;
    private Scene scene;

    public Stage getWindow() {
        return window;
    }
    public Article getArticle() {
        return article;
    }


    public AddArticlesDialog(Database db){

        olArticles = FXCollections.observableArrayList(db.getArticles());
        window = new Stage();
        window.setTitle("GuitarShop FX - Add Article");
        Image image = new Image("resources/css/images/guitarImage.png");
        window.getIcons().add(image);

        VBox layout = new VBox();
        layout.setPadding(new Insets(40));
        layout.setSpacing(40);

        TableView<Article> articlesTable = new TableView<>();
        articlesTable.setEditable(true);
        articlesTable.getSelectionModel().setCellSelectionEnabled(false);
        articlesTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


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

        articlesTable.getColumns().addAll(brandCol,modelCol,acousticCol,typeCol,priceCol);
        articlesTable.setItems(olArticles);


        HBox bottom = new HBox();
        bottom.setAlignment(Pos.CENTER);
        bottom.setPadding(new Insets(20));
        bottom.setSpacing(10);

        TextField txtNumbers = new TextField();
        txtNumbers.setPromptText("Numbers");
        txtNumbers.setPrefWidth(100);
        Button btnAdd = new Button("Add");
        Button btnCancel = new Button("Cancel");
        Label lblWarning = new Label();
        lblWarning.setId("lblWarning");

        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                try{

                    article = articlesTable.getSelectionModel().getSelectedItem();
                    article.setNumber(Integer.parseInt(txtNumbers.getText()));

                    if(article != null && article.getNumber() <= article.getQuantity()){
                        window.close();
                    }else{
                        lblWarning.setText("Not enough for "+ article.getBrand()+" "+article.getModel()+", only "
                                +article.getQuantity()+ " remaining");
                    }

                }catch (Exception e){
                    lblWarning.setText(e.getMessage());
                }



            }
        });

        bottom.getChildren().addAll(txtNumbers,btnAdd,btnCancel);
        layout.getChildren().addAll(articlesTable,bottom,lblWarning);
        scene = new Scene(layout);
        scene.getStylesheets().add("resources/css/style.css");
        window.setScene(scene);
    }
}
