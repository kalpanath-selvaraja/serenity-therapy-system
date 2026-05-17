package com.serenity.dao;

import com.serenity.entity.Therapist;
import com.serenity.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class TherapistDAO implements DAOInterface<Therapist> {
//Without , the interface wouldn't know what type of entity the DAO works with — you'd lose all type safety and could accidentally save a Payment in the TherapistDAO

    @Override
    public void save(Therapist entity) {
        Transaction transaction = null;
        Session session = null;
        // try-with-resources automatically closes the session when done
        try{
            session = FactoryConfiguration.getInstance().getSession();

            transaction = session.beginTransaction(); // transaction changes
            session.persist(entity); // save
            transaction.commit(); // commit the transaction

        } catch(Exception e) {
            // If anything fails, undo all changes in this transaction
            if (transaction != null) transaction.rollback();  // only rolls back if the transaction has happend and then an exception arrives
            throw e;
        }finally{
            if (session != null) {
                session.close();              // session closes
            }
        }

    }



    @Override
    public void update(Therapist entity) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();

            transaction = session.beginTransaction();
            session.merge(entity); // updates
            transaction.commit();
        } catch (Exception e) {

            if(transaction != null){
                transaction.rollback();
            }

            throw e;
        }finally {
            if (session != null) {
                session.close();              // session closes
            }
        }

    }


    @Override
    public void delete(Therapist entity) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            Therapist managed = session.get(Therapist.class, entity.getTherapistId());
            if (managed != null) {
                session.remove(managed);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();              // session closes
            }
        }
    }




    @Override
    public Therapist getById(Class<Therapist> therapistClass, Serializable id) {
        // Read-only operations don't need a transaction
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.get(therapistClass, id);
        }
    }

    @Override
    public List<Therapist> getAll(Class<Therapist> therapistClass) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.createQuery("FROM Therapist", Therapist.class).list();
        }
    }


    // Custom query: find therapists by their specialization
    public List<Therapist> findBySpecialization(String specialization) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.createQuery("FROM Therapist WHERE specialization = :spec", Therapist.class)
                    .setParameter("spec", specialization)
                    .list();
        }
    }

}
