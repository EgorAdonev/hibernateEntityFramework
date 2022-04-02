import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.mapping.MetadataSource;

public class HibernateUtils {
        private static StandardServiceRegistry registry;
//        private static SessionFactory ourSessionFactory = null;

        private static SessionFactory sessionFactory = null;

        static {
            Configuration cfg = new Configuration().configure();
        }

        public static SessionFactory getSessionFactory() {
            if(sessionFactory == null) {
            try {
                // создаем регистр
                registry = new StandardServiceRegistryBuilder().configure().build();
                // создаем источники метаданных
                MetadataSources sources = new MetadataSources(registry);
                // метаданные
                Metadata metaData = sources.getMetadataBuilder().build();
                // созданик сессии
                sessionFactory = metaData.getSessionFactoryBuilder().build();

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
