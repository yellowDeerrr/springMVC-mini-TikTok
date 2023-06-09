package app.tiktok.tables;

import jakarta.persistence.*;

@Entity
@Table(name = "users_likes")
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameAccountLikesVideo;
    private String idVideo;
    private String accountId;

    public Likes() {
    }

    public Likes(String nameAccountLikesVideo, String idVideo, String accountId) {
        this.nameAccountLikesVideo = nameAccountLikesVideo;
        this.idVideo = idVideo;
        this.accountId = accountId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameAccountLikesVideo() {
        return nameAccountLikesVideo;
    }

    public void setNameAccountLikesVideo(String nameAccountLikesVideo) {
        this.nameAccountLikesVideo = nameAccountLikesVideo;
    }

    public String getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(String idVideo) {
        this.idVideo = idVideo;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
