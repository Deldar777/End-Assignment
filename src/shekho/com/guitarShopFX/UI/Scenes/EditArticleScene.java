package shekho.com.guitarShopFX.UI.Scenes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import shekho.com.guitarShopFX.DAL.Database;
import shekho.com.guitarShopFX.Models.Article;
import shekho.com.guitarShopFX.Models.Customer;
import shekho.com.guitarShopFX.UI.Dialogs.CreateArticlesDialog;

import java.util.List;

public class EditArticleScene {

    private Scene scene;
    private List<Article> articles;
    private ObservableList<Article> olArticles;

    public Scene getScene() {
        return scene;
    }

    public EditArticleScene(Database db){

        articles = db.getArticles();
        olArticles = FXCollections.observableArrayList(articles);

        VBox layout = new VBox();
        layout.setPadding(new Insets(20));
        layout.setSpacing(40);

        Label lblArticle = new Label("Articles");
        lblArticle.setId("headerLbl");

        TableView<Article> tvArticles = new TableView<>();
        tvArticles.setEditable(true);
        tvArticles.getSelectionModel().setCellSelectionEnabled(false);
        tvArticles.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        TableColumn brandCol = new TableColumn("Brand");
        brandCol.setMinWidth(100);
        brandCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("brand"));

        TableColumn modelCol = new TableColumn("Model");
        modelCol.setMinWidth(100);
        modelCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("model"));

        TableColumn acousticCol = new TableColumn("Acoustic");
        acousticCol.setMinWidth(100);
        acousticCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("acoustic"));

        TableColumn typeCol = new TableColumn("Type");
        typeCol.setMinWidth(100);
        typeCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("type"));

        TableColumn priceCol = new TableColumn("Price");
        priceCol.setMinWidth(100);
        priceCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("price"));

        tvArticles.getColumns().addAll(brandCol,modelCol,acousticCol,typeCol,priceCol);
        tvArticles.setItems(olArticles);

        HBox buttonsLayout = new HBox();
        buttonsLayout.setSpacing(20);
        buttonsLayout.setAlignment(Pos.CENTER);

        Button addBtn = new Button("Add");
        addBtn.setPrefWidth(100);
        Button deleteBtn = new Button("Delete");
        deleteBtn.setPrefWidth(100);
        Label lblWarning = new Label();
        lblWarning.setId("lblWarning");
        buttonsLayout.getChildren().addAll(addBtn,deleteBtn);

        deleteBtn.setOnAction(actionEvent -> {
            Article article = tvArticles.getSelectionModel().getSelectedItem();

            if(article != null){
                articles.remove(article);
                olArticles = FXCollections.observableArrayList(articles);
                tvArticles.setItems(olArticles);
            }else{
                lblWarning.setText("You did not choose any article! Choose an article and then press delete");
            }

        });

        addBtn.setOnAction(actionEvent -> {
            CreateArticlesDialog ca = new CreateArticlesDialog(db);
            ca.getWindow().initModality(Modality.APPLICATION_MODAL);
            ca.getWindow().showAndWait();

            articles = db.getArticles();
            olArticles = FXCollections.observableArrayList(articles);
            tvArticles.setItems(olArticles);
        });



        layout.getChildren().addAll(lblArticle,tvArticles,buttonsLayout,lblWarning);
        scene = new Scene(layout);
    }
}
