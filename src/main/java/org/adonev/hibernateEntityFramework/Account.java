package org.adonev.hibernateEntityFramework;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "account",schema = "bank")
public class Account implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
    @GeneratedValue
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

    public User getUserByUserId() {
        return userByUserId;
    }
    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    public Account( double balance) {
        this.balance = balance;
    }
    public Account(){
    }
}
