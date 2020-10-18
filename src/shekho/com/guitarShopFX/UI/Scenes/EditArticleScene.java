package shekho.com.guitarShopFX.UI.Scenes;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import shekho.com.guitarShopFX.DAL.Database;
import shekho.com.guitarShopFX.Models.Article;

import java.util.List;

public class EditArticleScene {

    private Scene scene;
    private List<Article> articles;
    private ObservableList<Article> olArticles;

    public Scene getScene() {
        return scene;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public EditArticleScene(Database db){

    }
}
