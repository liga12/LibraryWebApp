package model;

/**
 * Created by LIGA on 27.10.2016.
 */
public class User {
    private final int id;
    private final String login;
    private final String email;

    public User(int id, String login, String email) {
        this.id = id;
        this.login = login;
        this.email = email;


    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }
}
