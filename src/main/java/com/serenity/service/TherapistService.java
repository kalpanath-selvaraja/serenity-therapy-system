package com.serenity.service;

import com.serenity.dao.DAOFactory;
import com.serenity.dao.TherapistDAO;
import com.serenity.entity.Therapist;

import java.util.List;

public class TherapistService {
    // Obtain the DAO through the factory, not by creating it directly
    private final TherapistDAO therapistDAO = DAOFactory.getTherapistDAO();

    public void addTherapist(Therapist therapist) {
        therapistDAO.save(therapist);
    }

    public void updateTherapist(Therapist therapist) {
        therapistDAO.update(therapist);
    }

    public void deleteTherapist(Therapist therapist) {
        therapistDAO.delete(therapist);
    }

    public Therapist getTherapistById(Long id) {
        return therapistDAO.getById(Therapist.class, id);
    }

    public List<Therapist> getAllTherapists() {
        return therapistDAO.getAll(Therapist.class);
    }

    public List<Therapist> findBySpecialization(String specialization) {
        return therapistDAO.findBySpecialization(specialization);
    }
}