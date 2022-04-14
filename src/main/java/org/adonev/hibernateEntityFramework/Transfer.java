package org.adonev.hibernateEntityFramework;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="Transfer",schema = "bank")
public class Transfer implements Serializable {
    private static final long serialVersionUID = 3965716188361032301L;
    @Id
    @GeneratedValue
    @Column(name = "transfer_Id")
    private int transferId;

    @Column(name = "transferTimestamp",nullable = false)
    private java.sql.Timestamp transferTimestamp;

    //join by referencedcolname - many(several tranfers could be from one Account(schet)
    @ManyToOne
    @JoinColumn(name = "accountId", referencedColumnName = "account_Id", nullable = false)
    private Account accountFrom;

    @ManyToOne
    @JoinColumn(name = "accountId", referencedColumnName = "account_Id", nullable = false)
    private Account accountTo;

    //true - income(dohod), false - expense(trata)
    @Column(name = "transferType",nullable = false)
    private boolean isIncome;

    @Column(name = "transferAmount",nullable = false)
    private double transferAmount;

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public java.sql.Timestamp getTransferTimestamp() {
        return transferTimestamp;
    }

    public void setTransferTimestamp(java.sql.Timestamp transferTimestamp) {
        this.transferTimestamp = transferTimestamp;
    }

    public Account getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Account accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Account getAccountTo() {
        return accountFrom;
    }

    public void setAccountTo(Account accountTo) {
        this.accountTo = accountTo;
    }

    public boolean isIncome(){
        return isIncome;
    }

    public void setIsIncome(boolean isIncome) {
        this.isIncome = isIncome;
    }

    public double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(double transferAmount) {
        this.transferAmount = transferAmount;
    }

    public Transfer(){
    }

    public Transfer(boolean type,Account accountSender,Account accountRecipient,double transferAmount){
        Date date = new Date();
        this.transferTimestamp = new java.sql.Timestamp(date.getTime());
        this.isIncome = type ; accountFrom = accountSender; accountTo = accountRecipient;
        this.transferAmount = transferAmount;
    }

//    public Transfer(int transferId,boolean type,Account accountSender,Account accountRecipient,double transferAmount) {
//         this.transferId = transferId; this.isIncome = type; accountFrom = accountSender; accountTo = accountRecipient;
//         this.transferAmount=transferAmount;
//    }

        double currentBalance;
        public static Transfer income(double amount, Account senderAccount, Account recipientAccount, boolean isIncome){
           Transfer incomeTransfer = null;
            // create record
           //Transfer withdrawTransfer = new Transfer( transferId,new Date(),isIncome,senderAccount,recipientAccount);
           if (amount > 0){
               // create record
               incomeTransfer = new Transfer(true,senderAccount,recipientAccount,amount);
               senderAccount.setBalance(senderAccount.getBalance()+amount);
               recipientAccount.setBalance(recipientAccount.getBalance()-amount);
           }
           else {
               System.out.println("Trying to record income with negative value!");
               try {
                   Thread.sleep(10000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
        }
           return incomeTransfer;
    }
        public static Transfer outcome(double amount, Account senderAccount, Account recipientAccount, boolean isIncome) {
            Transfer outcomeTransfer = null;
            if (amount < 0) {
                if (senderAccount.getBalance() < amount) {
                    System.out.println("Not enough money");
                }
                outcomeTransfer = new Transfer(false, senderAccount, recipientAccount, amount);
                senderAccount.setBalance(senderAccount.getBalance() - amount);
                recipientAccount.setBalance(recipientAccount.getBalance() + amount);
            } else {
                System.out.println("Trying to record outcome with positive value!");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return outcomeTransfer;
//           if(senderAccount.getUserByUserId().getId() == recipientAccount.getUserByUserId().getId()){ type  }
        }
}
