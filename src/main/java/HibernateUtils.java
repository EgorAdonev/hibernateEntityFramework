import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.mapping.MetadataSource;

public class HibernateUtils {
        private static StandardServiceRegistry registry = null;
        private static SessionFactory sessionFactory = null;

        static {
            Configuration cfg = new Configuration();
            cfg.addAnnotatedClass(User.class);
            cfg.addAnnotatedClass(Account.class);
            cfg.addAnnotatedClass(Transfer.class);
            cfg.configure();
        }

        public static synchronized SessionFactory getSessionFactory() {
            if(sessionFactory == null) {
            try {
                // создаем регистр
                registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
                // создание сессии
                sessionFactory = new MetadataSources( registry )
                        .buildMetadata()
                        .buildSessionFactory();
                //or metaData.getSessionFactoryBuilder().build();

            } catch (Exception ex) {
                ex.printStackTrace();
                // убиваем регистр при ошибке иннициализации
                if (registry != null) StandardServiceRegistryBuilder.destroy(registry);
            }
        }
            return sessionFactory;
        }

        public static void shutdown() {
        // Close caches and connection pools
            if (registry != null) {
                StandardServiceRegistryBuilder.destroy(registry);
            }
            getSessionFactory().close();
        }

//        public static SessionFactory getSessionFactory() {
//            if(ourSessionFactory == null) {
//            try {
//                // создаем регистр
//                registry = new StandardServiceRegistryBuilder().configure().build();
//                // создаем источники метаданных
//                MetadataSources sources = new MetadataSources(registry);
//                // метаданные
//                Metadata metaData = sources.getMetadataBuilder().build();
//                // созданик сессии
//                ourSessionFactory = metaData.getSessionFactoryBuilder().build();
//
//            } catch (Exception ex) {
//                ex.printStackTrace();
//                // убиваем регистр при ошибке иннициализации
//                if (registry != null) StandardServiceRegistryBuilder.destroy(registry);
//            }
//        }
//            return ourSessionFactory;
//        }
//        public static void shutdown() {
//            if (registry != null) {
//                StandardServiceRegistryBuilder.destroy(registry);
//             }
//        }
}
