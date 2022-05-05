package org.adonev.hibernateEntityFramework;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "account",schema = "public")
public class Account implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
    @Column(name = "account_Id", nullable = false)
    private int accountId;
    @Basic
    @Column(name = "balance", nullable=false)
    private double balance;
    @ManyToOne
    @JoinColumn(name = "user_Id", referencedColumnName = "userBy", nullable = false)
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
