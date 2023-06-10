package app.tiktok.tables;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Table(name = "users_videos")
@Entity
public class Videos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name_account")
    private String nameAccount;
    @Column(name = "code_video")
    private String codeVideo;
    @Column(name = "publication_time")
    private Timestamp publicationTime;
    @Column(name = "likes")
    private int likes;
    @Column(name = "name_video")
    private String nameVideo;

    public Videos() {
    }

    public Videos(String nameAccount, String codeVideo, Timestamp publicationTime, int likes, String nameVideo) {
        this.nameAccount = nameAccount;
        this.codeVideo = codeVideo;
        this.publicationTime = publicationTime;
        this.likes = likes;
        this.nameVideo = nameVideo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameAccount() {
        return nameAccount;
    }

    public void setNameAccount(String nameAccount) {
        this.nameAccount = nameAccount;
    }

    public String getCodeVideo() {
        return codeVideo;
    }

    public void setCodeVideo(String codeVideo) {
        this.codeVideo = codeVideo;
    }

    public Timestamp getPublicationTime() {
        return publicationTime;
    }

    public void setPublicationTime(Timestamp publicationTime) {
        this.publicationTime = publicationTime;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getNameVideo() {
        return nameVideo;
    }

    public void setNameVideo(String nameVideo) {
        this.nameVideo = nameVideo;
    }
}
