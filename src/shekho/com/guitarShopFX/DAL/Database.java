package shekho.com.guitarShopFX.DAL;

import shekho.com.guitarShopFX.Models.*;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private List<User> users = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Article> articles = new ArrayList<>();

    public Database(){
        createUsers();
        createCustomers();
        createArticles();
    }


    private void createUsers(){
        users.add(new User("deldar","deldar777","Deldar",
                "Shekho","06-84077803","deldar.shekho@hotmail.com",Role.MANAGER));
        users.add(new User("ryan","ryan777","Ryan",
                "Erfmann","06-12345678","ryan.erfmann@hotmail.com",Role.SALES));
    }
    private void createCustomers(){
        customers.add(new Customer("Wim","Wiltenburg","06-87654321",
                "wim.wiltenburg@hotmail.com","Stentorstraat 90","Amsterdam"));
        customers.add(new Customer("Jack","Traven","06-87651234",
                "jack.draven@hotmail.com","Dorpsstraat 10","Arnhem"));
        customers.add(new Customer("Genny","Gamp","06-12399765",
                "genny.gamp@hotmail.com","Churchillallee 141","Den Haag"));

    }
    private void createArticles(){
        articles.add(new Article("Fender","Telecaster",false,TypeGuitar.REGULAR,1079.79,10));
        articles.add(new Article("Fender","Precision",false,TypeGuitar.BASS,1300.49,10));
        articles.add(new Article("Simon Patrick","Pro Flame Maple",true,TypeGuitar.REGULAR,1079.79,
                10));
    }


    public List<User> getUsers() {
        return users;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public User validateAuthentication(String username,String password){
        User user;

        for (User u:users
        ) {

            if (u.getUsername().equals(username)){
                user = u;
                if (user.getPassword().equals(password))
                    return user;
            }
        }
        return null;
    }
}
