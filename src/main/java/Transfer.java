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
    Account account;
    //0 - income(dohod), 1 - expense(trata)
    boolean type;

    public Transfer(int transferId) {
        this.transferId = transferId;
    }

    public Transfer(int transferId,Date transferTimestamp,boolean type,Account account){
        this.transferTimestamp = transferTimestamp; this.type = type ; this.account =account;
    }

    public Transfer(Date transferTimestamp,boolean type,Account account) {
        this.transferTimestamp = transferTimestamp; this.type = type; this.account = account;
    }
        double currentBalance;
        public void withdrawSelf (double amount, int accountId){
           currentBalance = account.getBalance() - amount;

    }
}
