package org.adonev.hibernateEntityFramework;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "account",schema = "public")
public class Account implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_Id", nullable = false)
    @OneToMany(mappedBy = "transferId")
    private int accountId;
    @Basic
    @Column(name = "balance", nullable=false)
    private double balance;
    @ManyToOne
    @JoinColumn(name = "user_Id", referencedColumnName = "id", nullable = false)
    private User userByUserId;

    public int getaccountId() {
        return accountId;
    }
    public void setaccountId(int id) {
        accountId = id;
    }

    public double getbalance(){
        return balance;
    }
    public void setbalance(double currentBalance){
        balance = currentBalance;
    }

    public User getuserByUserId() {
        return userByUserId;
    }
    public void setuserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    public Account( double balance) {
        this.balance = balance;
    }
    public Account(){
    }
}
