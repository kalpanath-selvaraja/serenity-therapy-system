package com.serenity.dao;

import com.serenity.entity.User;
import com.serenity.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class UserDAO implements DAOInterface<User> {

    @Override
    public void save(User entity) {
        Transaction transaction = null;
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    @Override
    public void update(User entity) {
        Transaction transaction = null;
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    @Override
    public void delete(User entity) {
        Transaction transaction = null;
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            transaction = session.beginTransaction();
            User managed = session.get(User.class, entity.getId());
            if (managed != null) session.remove(managed);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    @Override
    public User getById(Class<User> clazz, Serializable id) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.get(clazz, id);
        }
    }

    @Override
    public List<User> getAll(Class<User> clazz) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.createQuery("FROM User", User.class).list();
        }
    }

    // Find a user by their unique username (used for login)
    public User findByUsername(String username) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.createQuery("FROM User WHERE username = :username", User.class)
                    .setParameter("username", username)
                    .uniqueResult();
        }
    }
}