package shekho.com.guitarShopFX.UI.Scenes;

import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import shekho.com.guitarShopFX.DAL.Database;
import shekho.com.guitarShopFX.Models.*;
import shekho.com.guitarShopFX.UI.Dialogs.AddUserDialog;

import java.util.List;

public class EditUserScene {

    private Scene scene;
    private List<User> users;
    private ObservableList<User> olUsers;

    public Scene getScene() {
        return scene;
    }

    public EditUserScene(Database db){

        olUsers = FXCollections.observableArrayList(db.getUsers());
        users = db.getUsers();

        VBox layout = new VBox();
        layout.setPadding(new Insets(20));
        layout.setSpacing(40);

        Label lblUser = new Label("Users");
        lblUser.setId("headerLbl");

        TableView<User> tvUser = new TableView<>();
        tvUser.setEditable(true);
        tvUser.getSelectionModel().setCellSelectionEnabled(false);
        tvUser.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


        TableColumn<User,String> userNameCol = new TableColumn<>("Username");
        userNameCol.setMinWidth(100);
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        TableColumn<User,String> passwordCol = new TableColumn<>("Password");
        passwordCol.setMinWidth(100);
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        TableColumn<User,String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn<User,String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn<User,String> phoneNumberCol = new TableColumn<>("Phone #");
        phoneNumberCol.setMinWidth(100);
        phoneNumberCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        TableColumn<User,String> emailCol = new TableColumn<>("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn<User,String> roleCol = new TableColumn<>("Role");
        roleCol.setMinWidth(200);
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));


        tvUser.getColumns().addAll(userNameCol,passwordCol,firstNameCol,lastNameCol,phoneNumberCol,emailCol,roleCol);
        tvUser.setItems(olUsers);

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
            User user = tvUser.getSelectionModel().getSelectedItem();
            if(user != null){

                db.getUsers().remove(user);
                users = db.getUsers();
                olUsers = FXCollections.observableArrayList(users);
                tvUser.setItems(olUsers);
            }else{
                lblWarning.setText("You did not choose any user! Choose a user and then press delete");
            }

        });

        addBtn.setOnAction(actionEvent -> {
            AddUserDialog aud = new AddUserDialog(db);
            aud.getWindow().initModality(Modality.APPLICATION_MODAL);
            aud.getWindow().showAndWait();

            users = db.getUsers();
            olUsers = FXCollections.observableArrayList(users);
            tvUser.setItems(olUsers);
        });
        layout.getChildren().addAll(lblUser,tvUser,buttonsLayout,lblWarning);
        scene = new Scene(layout);
    }
}
