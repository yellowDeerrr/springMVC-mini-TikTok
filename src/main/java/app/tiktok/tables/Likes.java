package app.tiktok.tables;

import jakarta.persistence.*;

@Entity
@Table(name = "users_likes")
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name_account_likes_video")
    private String nameAccountLikesVideo;
    @Column(name = "id_video")
    private String idVideo;
    @Column(name = "account_id")
    private String accountId;
    @Column(name = "name_video")
    private String nameVideo;
    public Likes() {
    }

    public Likes(String nameAccountLikesVideo, String idVideo, String accountId, String nameVideo) {
        this.nameAccountLikesVideo = nameAccountLikesVideo;
        this.idVideo = idVideo;
        this.accountId = accountId;
        this.nameVideo = nameVideo;
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

    public String getNameVideo() {
        return nameVideo;
    }

    public void setNameVideo(String nameVideo) {
        this.nameVideo = nameVideo;
    }
}
