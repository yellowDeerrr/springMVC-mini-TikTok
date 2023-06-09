package app.tiktok.tables;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Table(name = "users_videos")
@Entity
public class Videos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameAccount;
    private String codeVideo;
    private Timestamp publicationTime;

    // Конструктори, гетери і сетери

    public Videos() {
    }

    public Videos(String nameAccount, String codeVideo, Timestamp publicationTime) {
        this.nameAccount = nameAccount;
        this.codeVideo = codeVideo;
        this.publicationTime = publicationTime;
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
}
