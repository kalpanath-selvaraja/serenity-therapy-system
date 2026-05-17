package com.serenity.dao;

import com.serenity.entity.Payment;
import com.serenity.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class PaymentDAO implements DAOInterface<Payment> {

    @Override
    public void save(Payment entity) {
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
    public void update(Payment entity) {
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
    public void delete(Payment entity) {
        Transaction transaction = null;
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            transaction = session.beginTransaction();
            Payment managed = session.get(Payment.class, entity.getPaymentId());
            if (managed != null) session.remove(managed);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    @Override
    public Payment getById(Class<Payment> clazz, Serializable id) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.get(clazz, id);
        }
    }

    @Override
    public List<Payment> getAll(Class<Payment> clazz) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.createQuery("FROM Payment", Payment.class).list();
        }
    }

    // Find all payments made by a specific patient
    public List<Payment> findByPatient(Long patientId) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.createQuery("FROM Payment WHERE patient.patientId = :pid", Payment.class)
                    .setParameter("pid", patientId)
                    .list();
        }
    }
}