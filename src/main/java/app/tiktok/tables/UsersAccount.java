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
    @Column(name = "registry_time")
    private Timestamp registryTime;
    @Column(name = "photo_id")
    private String photoId;
    @Column(name = "close_or_open_account")
    private boolean closeOrOpenAccount;

    public UsersAccount() {
    }

    public UsersAccount(String login, String password, Timestamp registryTime, String photoId, boolean closeOrOpenAccount) {
        this.login = login;
        this.password = password;
        this.registryTime = registryTime;
        this.photoId = photoId;
        this.closeOrOpenAccount = closeOrOpenAccount;
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

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public boolean isCloseOrOpenAccount() {
        return closeOrOpenAccount;
    }

    public void setCloseOrOpenAccount(boolean closeOrOpenAccount) {
        this.closeOrOpenAccount = closeOrOpenAccount;
    }
}
