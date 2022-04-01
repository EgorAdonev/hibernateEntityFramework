import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Transfer",schema = "bank")
public class Transfer {
    @Id
    @Column(name = "id")
    int transferId;
    @Column(name = "transferTimestamp",nullable = false)
    Date transferTimestamp;
    //join by referencedcolname - many(several tranfers could be from one Account(schet)
    @ManyToOne
    @JoinColumn(name = "accountId", referencedColumnName = "id", nullable = false)
    Account accountFrom;
    @ManyToOne
    @JoinColumn(name = "accountId", referencedColumnName = "id", nullable = false)
    Account accountTo;
    //true - income(dohod), false - expense(trata)
    @Column(name = "transferType",nullable = false)
    boolean isIncome;
    @Column(name = "transferAmount",nullable = false)
    private double transferAmount;

    public Transfer(int transferId) {
        this.transferId = transferId;
    }

    public Transfer(int transferId,Date transferTimestamp,boolean type,Account accountSender,Account accountRecipient,double transferAmount){
        this.transferId = transferId;
        this.transferTimestamp = new Date(); this.isIncome = type ; accountFrom = accountSender; accountTo = accountRecipient;
        this.transferAmount = transferAmount;
    }

    public Transfer(int transferId,boolean type,Account accountSender,Account accountRecipient,double transferAmount) {
         this.transferId = transferId; this.isIncome = type; accountFrom = accountSender; accountTo = accountRecipient;
         this.transferAmount=transferAmount;
    }

        double currentBalance;
        public void income(double amount, Account senderAccount, Account recipientAccount, int transferId, boolean isIncome){
            // create record
           //Transfer withdrawTransfer = new Transfer( transferId,new Date(),isIncome,senderAccount,recipientAccount);
           if (amount > 0){
               // create record
               Transfer incomeTransfer = new Transfer( transferId,new Date(), true,senderAccount,recipientAccount,amount);
               senderAccount.setBalance(senderAccount.getBalance()+amount);
               recipientAccount.setBalance(recipientAccount.getBalance()-amount);
           }
           else {
               System.out.println("Trying to record outcome with negative value!");
               try {
                   Thread.sleep(10000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
        }
    }
        public void outcome(double amount, Account senderAccount, Account recipientAccount, int transferId, boolean isIncome){
            if (amount < 0) {
                if (senderAccount.getBalance() < amount) {
                    System.out.println("Not enough money");
                }
                Transfer outcomeTransfer = new Transfer(transferId, new Date(), false, senderAccount, recipientAccount, amount);
                senderAccount.setBalance(senderAccount.getBalance()-amount);
                recipientAccount.setBalance(recipientAccount.getBalance()+amount);
            }
            else {
                System.out.println("Trying to record outcome with positive value!");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//           if(senderAccount.getUserByUserId().getId() == recipientAccount.getUserByUserId().getId()){ type  }
        }
}
