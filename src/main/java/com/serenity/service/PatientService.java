package com.serenity.service;

import com.serenity.dao.DAOFactory;
import com.serenity.dao.PatientDAO;
import com.serenity.entity.Patient;

import java.util.List;

public class PatientService {
    private final PatientDAO patientDAO = DAOFactory.getPatientDAO();

    public void registerPatient(Patient patient) {
        patientDAO.save(patient);
    }

    public void updatePatient(Patient patient) {
        patientDAO.update(patient);
    }

    public void deletePatient(Patient patient) {
        patientDAO.delete(patient);
    }

    public Patient getPatientById(Long id) {
        return patientDAO.getById(Patient.class, id);
    }

    public List<Patient> getAllPatients() {
        return patientDAO.getAll(Patient.class);
    }

    // Delegates to the LIKE-based HQL query in PatientDAO
    public List<Patient> searchByName(String name) {
        return patientDAO.findByName(name);
    }

    public Patient findByEmail(String email) {
        return patientDAO.findByEmail(email);
    }
}