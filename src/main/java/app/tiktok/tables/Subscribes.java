package app.tiktok.tables;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "users_subscribes")
public class Subscribes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "account_name_to_subscribe")
    private String accountNametoSubscribe;
    @Column(name = "account_id")
    private String accountId;
    @Column(name = "wait_or_subscribe")
    private String waitOrSubscribe;
    @Column(name = "time")
    private Timestamp time;

    public Subscribes() {
    }

    public Subscribes(String accountNametoSubscribe, String accountId, String waitOrSubscribe, Timestamp time) {
        this.accountNametoSubscribe = accountNametoSubscribe;
        this.accountId = accountId;
        this.waitOrSubscribe = waitOrSubscribe;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNametoSubscribe() {
        return accountNametoSubscribe;
    }

    public void setAccountNametoSubscribe(String accountNametoSubscribe) {
        this.accountNametoSubscribe = accountNametoSubscribe;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getWaitOrSubscribe() {
        return waitOrSubscribe;
    }

    public void setWaitOrSubscribe(String waitOrSubscribe) {
        this.waitOrSubscribe = waitOrSubscribe;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
