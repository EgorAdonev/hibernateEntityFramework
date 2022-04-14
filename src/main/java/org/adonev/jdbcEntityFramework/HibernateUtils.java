package org.adonev.jdbcEntityFramework;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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
                // register creation
                registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
                // session creation
                sessionFactory = new MetadataSources( registry )
                        .buildMetadata()
                        .buildSessionFactory();
                //or metaData.getSessionFactoryBuilder().build();

            } catch (Exception ex) {
                ex.printStackTrace();
                // kill register if error
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
}
