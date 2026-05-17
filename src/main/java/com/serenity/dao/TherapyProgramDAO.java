package com.serenity.dao;

import com.serenity.entity.TherapyProgram;
import com.serenity.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class TherapyProgramDAO implements DAOInterface<TherapyProgram>{

    @Override
    public void save(TherapyProgram entity) {
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
    public void update(TherapyProgram entity) {
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
    public void delete(TherapyProgram entity) {
        Transaction transaction = null;
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            transaction = session.beginTransaction();
            TherapyProgram managed = session.get(TherapyProgram.class, entity.getProgram_id());
            if (managed != null) session.remove(managed);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    @Override
    public TherapyProgram getById(Class<TherapyProgram> clazz, Serializable id) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.get(clazz, id);
        }
    }

    @Override
    public List<TherapyProgram> getAll(Class<TherapyProgram> clazz) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.createQuery("FROM TherapyProgram", TherapyProgram.class).list();
        }
    }

//    private List<TherapyProgram> findByfeeRange

}
