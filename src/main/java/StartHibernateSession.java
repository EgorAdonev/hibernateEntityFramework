import org.hibernate.Session;
import org.hibernate.Transaction;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class StartHibernateSession {
        public static void main(String[] args) {
            try {
                Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("Connection succesfull!");
            Random randInt = new Random();
            User user1 = new User(randInt.nextInt(100)+1, "Mikhail Petrov");
            User user2 = new User(randInt.nextInt(100)+1, "Anna Ivanova");
            User user3 = new User(randInt.nextInt(100)+1, "Egor Petrov");
            Account userAcc1 = new Account(randInt.nextInt(100)+10,29.13);
            Account userAcc2 = new Account(randInt.nextInt(100)+10,3429.23);
            Account userAcc3 = new Account(randInt.nextInt(100)+10,242934.2145);
            Transfer acc1ToYourSelf = new Transfer(randInt.nextInt(100)+100,new Date(),false,userAcc1,userAcc1,400.230123);
            Transfer acc1ToAcc2 = Transfer.income(700, userAcc1, userAcc2, randInt.nextInt(100)+100, true);
            Transfer acc2toAcc1 = Transfer.outcome(-700, userAcc2, userAcc1, randInt.nextInt(100)+100, false);
            Transaction transaction = null;
            Session session = HibernateUtils.getSessionFactory().openSession();
            try {
                try  {
                    // start a transaction
                    transaction = session.beginTransaction();
                    // save the student objects
                    session.save(user1);
                    session.save(user2);
                    session.save(user3);
                    session.save(userAcc1);
                    session.save(userAcc2);
                    session.save(userAcc3);
                    session.save(acc1ToYourSelf);
                    session.save(acc1ToAcc2);
                    session.save(acc2toAcc1);
                    // commit transaction
                    transaction.commit();
                } catch (Exception e) {
                    if (transaction != null) {
                        transaction.rollback();
                    }
                    e.printStackTrace();
                }
                try  {
                    List<User> users = session.createQuery("from User", User.class).list();
                    users.forEach(s -> System.out.println(s.getName()));
                } catch (Exception e) {
                    if (transaction != null) {
                        transaction.rollback();
                    }
                    e.printStackTrace();
                }
            } finally {
                session.close();
            }
        }
    }
