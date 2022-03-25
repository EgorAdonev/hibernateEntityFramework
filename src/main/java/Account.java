import javax.persistence.*;


@Entity
@Table(name = "account",schema = "bank")
public class Account {
    @Id
    @GeneratedValue
    @Column(name = "accountId", nullable = false)
    @OneToMany(mappedBy = "transferId")
    private int accountId;
    @Basic
    @Column(name = "balance", nullable=false)
    private double balance;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
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

    public Account(int accountId, double balance) {
        this.balance = balance;
        this.accountId = accountId;
    }
    public Account(){
    }
}
