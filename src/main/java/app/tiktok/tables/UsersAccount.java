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
    @Column(name = "user_name")
    private String userName;
    @Column(name = "registry_time")
    private Timestamp registryTime;
    @Column(name = "photo_id")
    private String photoId;
    @Column(name = "close_or_open_account_videos")
    private boolean closeOrOpenAccountVideo;
    @Column(name = "close_or_open_account_likes")
    private boolean closeOrOpenAccountLikes;

    public UsersAccount() {
    }

    public UsersAccount(String login, String password, String userName, Timestamp registryTime, String photoId, boolean closeOrOpenAccountVideo, boolean closeOrOpenAccountLikes) {
        this.login = login;
        this.password = password;
        this.userName = userName;
        this.registryTime = registryTime;
        this.photoId = photoId;
        this.closeOrOpenAccountVideo = closeOrOpenAccountVideo;
        this.closeOrOpenAccountLikes = closeOrOpenAccountLikes;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public boolean isCloseOrOpenAccountVideo() {
        return closeOrOpenAccountVideo;
    }

    public void setCloseOrOpenAccountVideo(boolean closeOrOpenAccountVideo) {
        this.closeOrOpenAccountVideo = closeOrOpenAccountVideo;
    }

    public boolean isCloseOrOpenAccountLikes() {
        return closeOrOpenAccountLikes;
    }

    public void setCloseOrOpenAccountLikes(boolean closeOrOpenAccountLikes) {
        this.closeOrOpenAccountLikes = closeOrOpenAccountLikes;
    }
}
