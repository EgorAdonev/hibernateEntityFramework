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

    public int getId() {
        return accountId;
    }
    public void setId(int id) {
        accountId = id;
    }

    public double getBalance(){
        return balance;
    }
    public void setBalance(double currentBalance){
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
