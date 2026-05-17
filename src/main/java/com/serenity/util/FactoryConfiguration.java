package com.serenity.util;

import com.serenity.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(User.class);
        cfg.addAnnotatedClass(TherapySession.class);
        cfg.addAnnotatedClass(TherapyProgram.class);
        cfg.addAnnotatedClass(Therapist.class);
        cfg.addAnnotatedClass(SessionStatus.class);
        cfg.addAnnotatedClass(PaymentStatus.class);
        cfg.addAnnotatedClass(Payment.class);
        cfg.addAnnotatedClass(Patient.class);
        sessionFactory = cfg.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        return factoryConfiguration == null
                ? factoryConfiguration = new FactoryConfiguration()
                : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}