package com.serenity.dao;

import com.serenity.entity.Patient;
import com.serenity.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.io.Serializable;
import java.util.List;

public class PatientDAO implements DAOInterface<Patient> {

    @Override
    public void save(Patient entity) {
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
    public void update(Patient entity) {
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
    public void delete(Patient entity) {
        Transaction transaction = null;
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            transaction = session.beginTransaction();
            Patient managed = session.get(Patient.class, entity.getPatientId());
            if (managed != null) session.remove(managed);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    @Override
    public Patient getById(Class<Patient> clazz, Serializable id) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.get(clazz, id);
        }
    }

    @Override
    public List<Patient> getAll(Class<Patient> clazz) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.createQuery("FROM Patient", Patient.class).list();
        }
    }

    // Search patients by partial name match using LIKE
    public List<Patient> findByName(String name) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.createQuery("FROM Patient WHERE name LIKE :name", Patient.class)
                    .setParameter("name", "%" + name + "%")
                    .list();
        }
    }

    // Find a single patient by their unique email
    public Patient findByEmail(String email) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.createQuery("FROM Patient WHERE email = :email", Patient.class)
                    .setParameter("email", email)
                    .uniqueResult();
        }
    }
}