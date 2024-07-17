package org.example.dictionaryee.factory;

import org.example.dictionaryee.entity.Task;
import org.example.dictionaryee.entity.Word;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateSessionFactoryUtil {

    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {

    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            ServiceRegistry standardRegistry =
                    new StandardServiceRegistryBuilder()
                            .configure()
                            .build();
            sessionFactory = new MetadataSources(standardRegistry)
                    .addAnnotatedClass(Word.class)
                    .addAnnotatedClass(Task.class)
                    .buildMetadata()
                    .buildSessionFactory();

        }
        return sessionFactory;
    }
}
