package org.adonev.hibernateEntityFramework;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user",schema = "bank")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    @OneToMany(mappedBy = "userByUserId")
    private int userId;
    @Basic
    @Column(name = "name", nullable = false, length = 30)
    private String userName;

    public int getuserId() {
        return userId;
    }

    public void setuserId(int id) {
        userId = id;
    }

    public String getuserName() {
        return userName;
    }

    public void setuserName(String name) { userName = name; }

    public User(String userName) {
        this.userName = userName;
    }

    public User() {
    }
}