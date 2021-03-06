package shekho.com.guitarShopFX.UI.Dialogs;

import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import shekho.com.guitarShopFX.DAL.Database;
import shekho.com.guitarShopFX.Models.*;



public class AddArticlesDialog {

    private Article article;
    private int amount;
    private final Stage window;


    public Stage getWindow() {
        return window;
    }
    public Article getArticle() {
        return article;
    }
    public int getAmount() { return amount; }

    public AddArticlesDialog(Database db){

        ObservableList<Article> olArticles = FXCollections.observableArrayList(db.getArticles());
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


        TableColumn<Article,String> brandCol = new TableColumn<>("Brand");
        brandCol.setMinWidth(100);
        brandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));
        TableColumn<Article,String> modelCol = new TableColumn<>("Model");
        modelCol.setMinWidth(100);
        modelCol.setCellValueFactory(new PropertyValueFactory<>("Model"));
        TableColumn<Article,String> acousticCol = new TableColumn<>("Acoustic");
        acousticCol.setMinWidth(100);
        acousticCol.setCellValueFactory(new PropertyValueFactory<>("acoustic"));
        TableColumn<Article,String> typeCol = new TableColumn<>("Type");
        typeCol.setMinWidth(100);
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        TableColumn<Article,String> priceCol = new TableColumn<>("Price");
        priceCol.setMinWidth(100);
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

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

        btnAdd.setOnAction(actionEvent -> {

            try{

                article = articlesTable.getSelectionModel().getSelectedItem();
                amount = Integer.parseInt(txtNumbers.getText());

                if(article != null){

                    if(amount <= article.getQuantity()){
                        window.close();

                    }else{
                        lblWarning.setText("Not enough for "+ article.getBrand()+" "+article.getModel()+", only "
                                +article.getQuantity()+ " remaining");
                    }
                }else {
                    lblWarning.setText("you did not choose any item! choose item and then press add");
                }
            }catch (Exception e){
                lblWarning.setText(e.getMessage());
            }
        });

        btnCancel.setOnAction(actionEvent -> window.close());

        bottom.getChildren().addAll(txtNumbers,btnAdd,btnCancel);
        layout.getChildren().addAll(articlesTable,bottom,lblWarning);
        Scene scene = new Scene(layout);
        scene.getStylesheets().add("resources/css/style.css");
        window.setScene(scene);
    }
}
