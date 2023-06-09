package app.tiktok.tables;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Table(name = "users_account")
@Entity
public class UsersAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
    private Timestamp registryTime;

    public UsersAccount() {
    }

    public UsersAccount(String login, String password, Timestamp registryTime) {
        this.login = login;
        this.password = password;
        this.registryTime = registryTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getRegistryTime() {
        return registryTime;
    }

    public void setRegistryTime(Timestamp registryTime) {
        this.registryTime = registryTime;
    }
}
