package shekho.com.guitarShopFX.UI.Dialogs;

import javafx.collections.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import shekho.com.guitarShopFX.DAL.Database;
import shekho.com.guitarShopFX.Models.*;

public class CreateArticlesDialog {

    private Stage window;
    public Stage getWindow() {
        return window;
    }

    public CreateArticlesDialog(Database db){

        window = new Stage();
        window.setTitle("GuitarShop FX - Add Article");
        Image image = new Image("resources/css/images/guitarImage.png");
        window.getIcons().add(image);

        VBox layout = new VBox();
        layout.setPadding(new Insets(40));
        layout.setSpacing(40);

        Label lblAddArticle = new Label("Add Article");
        lblAddArticle.setId("headerLbl");
        Label lblWaring = new Label();
        lblWaring.setId("lblWarning");

        GridPane articleLayout = new GridPane();
        articleLayout.setVgap(10);
        articleLayout.setHgap(10);

        Label lblBrand = new Label("Brand");
        TextField txtBrand = new TextField();
        txtBrand.setPromptText("Brand");

        Label lblModel = new Label("Model");
        TextField txtModel = new TextField();
        txtModel.setPromptText("Model");

        Label lblAcoustic = new Label("Acoustic");
        ChoiceBox<Boolean> cbAcoustic = new ChoiceBox<>();
        cbAcoustic.getItems().addAll(true,false);
        cbAcoustic.setValue(true);

        Label lblType = new Label("Type");
        ChoiceBox<TypeGuitar> cbType = new ChoiceBox<>();
        cbType.getItems().addAll(TypeGuitar.BASS,TypeGuitar.REGULAR );
        cbType.setValue(TypeGuitar.REGULAR);

        Label lblPrice = new Label("Price");
        TextField txtPrice = new TextField();
        txtPrice.setPromptText("Price");

        Label lblQuantity = new Label("Quantity");
        TextField txtQuantity = new TextField();
        txtQuantity.setPromptText("Quantity");

        Label lblId = new Label("Id");
        TextField txtId = new TextField();
        txtId.setPromptText("Id");

        HBox buttonLayout = new HBox();
        Button btnAdd = new Button("Add");
        btnAdd.setPrefWidth(150);
        buttonLayout.setAlignment(Pos.CENTER);

        articleLayout.add(lblBrand,0,0);
        articleLayout.add(txtBrand,1,0);
        articleLayout.add(lblModel,0,1);
        articleLayout.add(txtModel,1,1);
        articleLayout.add(lblAcoustic,0,2);
        articleLayout.add(cbAcoustic,1,2);
        articleLayout.add(lblType,0,3);
        articleLayout.add(cbType,1,3);
        articleLayout.add(lblPrice,0,4);
        articleLayout.add(txtPrice,1,4);
        articleLayout.add(lblQuantity,0,5);
        articleLayout.add(txtQuantity,1,5);
        articleLayout.add(lblId,0,6);
        articleLayout.add(txtId,1,6);
        articleLayout.add(btnAdd,1,7);

        btnAdd.setOnAction(actionEvent -> {
            lblWaring.setText("");

            try{
                String brand = txtBrand.getText();
                String model = txtModel.getText();
                boolean acoustic = cbAcoustic.getValue();
                TypeGuitar type = cbType.getValue();
                double price  = Double.parseDouble(txtPrice.getText());
                int quantity = Integer.parseInt(txtQuantity.getText());
                String id = txtId.getId();

                if(!brand.isEmpty() && !model.isEmpty() && price != 0 && quantity != 0){

                    Article article = new Article(brand,model,acoustic,type,price,quantity,id);
                    db.getArticles().add(article);
                    window.close();

                }else{
                    lblWaring.setText("Not all fields are filled!");
                }
            }catch (Exception e){
                lblWaring.setText(e.getMessage());
            }
        });

        layout.getChildren().addAll(lblAddArticle,articleLayout,lblWaring);

        Scene scene = new Scene(layout);
        scene.getStylesheets().add("resources/css/style.css");
        window.setScene(scene);
    }
}
