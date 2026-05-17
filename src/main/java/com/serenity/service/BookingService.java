package com.serenity.service;

import com.serenity.dao.DAOFactory;
import com.serenity.dao.TherapistDAO;
import com.serenity.dao.TherapySessionDAO;
import com.serenity.entity.Patient;
import com.serenity.entity.Therapist;
import com.serenity.entity.TherapyProgram;
import com.serenity.entity.TherapySession;

import java.util.List;

public class BookingService {
    // This service uses  TWO DAOs and  the Facade pattern in action
    private final TherapySessionDAO sessionDAO = DAOFactory.getTherapySessionDAO();
    private final TherapistDAO therapistDAO = DAOFactory.getTherapistDAO();

    // setting up all relationships before saving
    public void bookSession(Patient patient, Therapist therapist, TherapyProgram program, TherapySession session) {
        session.setPatient(patient);
        session.setTherapist(therapist);
        session.setProgram(program);
        sessionDAO.save(session);
    }

    public void updateSession(TherapySession session) {
        sessionDAO.update(session);
    }

    public void cancelSession(TherapySession session) {
        sessionDAO.delete(session);
    }

    public List<TherapySession> getAllSessions() {
        return sessionDAO.getAll(TherapySession.class);
    }

    // Uses the JOIN FETCH query to load therapist and program details in one GO
    public List<TherapySession> getSessionsForPatient(Long patientId) {
        return sessionDAO.findByPatient(patientId);
    }
}