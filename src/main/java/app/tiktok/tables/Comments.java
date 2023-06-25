package app.tiktok.tables;

import jakarta.persistence.*;

@Entity
@Table(name = "users_comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "accountName")
    private String accountName;
    @Column(name = "idVideo")
    private String idVideo;
    @Column(name = "name_account_comment_video")
    private String nameAccountCommentVideo;
    private String text;

    public Comments(){}

    public Comments(String accountName, String idVideo, String nameAccountCommentVideo, String text){
        this.accountName = accountName;
        this.idVideo = idVideo;
        this.nameAccountCommentVideo = nameAccountCommentVideo;
        this.text = text;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getAccountName(){
        return accountName;
    }
    public void setAccountName(String accountName){
        this.accountName = accountName;
    }

    public String getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(String idVideo) {
        this.idVideo = idVideo;
    }

    public String getNameAccountCommentVideo(){
        return nameAccountCommentVideo;
    }
    public void setNameAccountCommentVideo(String nameAccountCommentVideo){
        this.nameAccountCommentVideo = nameAccountCommentVideo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
