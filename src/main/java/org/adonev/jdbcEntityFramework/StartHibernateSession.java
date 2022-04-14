package org.adonev.jdbcEntityFramework;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class StartHibernateSession {
    public static void main(String[] args) {
        System.out.println("Connection succesfull!");
//          Random randInt = new Random();
        Transaction transactionUserCreation = null;
        Transaction transactionAccountCreation = null;
        Transaction transactionTransferCreation = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            User user1 = new User("Lil Wayne");
            User user2 = new User("Monica Belucci");
            User user3 = new User("Tony Stark");
            Account userAcc1 = new Account(29.13);
            Account userAcc2 = new Account(3429.23);
            Account userAcc3 = new Account(242934.2145);
            Transfer acc1ToYourSelf = new Transfer(false, userAcc1, userAcc1, 400.230123);
            Transfer acc1ToAcc2 = Transfer.income(700, userAcc1, userAcc2, true);
            Transfer acc2toAcc1 = Transfer.outcome(-700, userAcc2, userAcc1, false);

            // start a transaction
            transactionUserCreation = session.beginTransaction();
            // save the student objects
            session.save(user1);
            session.save(user2);
            session.save(user3);
            // commit transaction
            transactionUserCreation.commit();

            transactionAccountCreation = session.beginTransaction();
            session.save(userAcc1);
            session.save(userAcc2);
            session.save(userAcc3);
            transactionAccountCreation.commit();

            transactionTransferCreation = session.beginTransaction();
            session.save(acc1ToYourSelf);
            session.save(acc1ToAcc2);
            session.save(acc2toAcc1);
            transactionTransferCreation.commit();

            session.beginTransaction();
            List<User> users = session.createQuery("from User", User.class).list();
            users.forEach(s -> System.out.println(s.getName()));
            session.getTransaction().commit();
//            session.close();
        }
         catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
