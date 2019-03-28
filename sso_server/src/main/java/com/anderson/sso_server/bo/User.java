package com.anderson.sso_server.bo;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="serveruser")
public class User {
    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    @Column(name="username")
    private String userName;
    @Column(name="password")
    private String passWord;
    @Column(name="cert")
    private String cert;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getCert() {
        return cert;
    }

    public void setCert(String cert) {
        this.cert = cert;
    }
}
