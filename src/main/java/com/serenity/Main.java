package com.serenity;

import com.serenity.util.FactoryConfiguration;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Hibernate...");
        Session session = FactoryConfiguration.getInstance().getSession();
        System.out.println("Hibernate started successfully!");
        System.out.println("Tables created. Check your MySQL database.");
        session.close();
    }
}