package com.serenity;

import com.serenity.dao.DAOFactory;
import com.serenity.dao.TherapistDAO;
import com.serenity.entity.Therapist;
import com.serenity.util.FactoryConfiguration;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Get the DAO from the factory
        TherapistDAO therapistDAO = DAOFactory.getTherapistDAO();

        // CREATE: save a new therapist
        Therapist therapist = new Therapist();
        therapist.setName("Dr. Silva");
        therapist.setSpecialization("CBT");
        therapist.setPhone("0771234567");
        therapist.setEmail("silva@serenity.lk");
        therapist.setAvailabilityStatus(true);
        therapistDAO.save(therapist);
        System.out.println("Saved therapist with ID: " + therapist.getTherapistId());

        // READ: retrieve all therapists
        List<Therapist> allTherapists = therapistDAO.getAll(Therapist.class);
        System.out.println("Total therapists: " + allTherapists.size());

        // UPDATE: change the therapist's specialization
        therapist.setSpecialization("Cognitive Behavioral Therapy");
        therapistDAO.update(therapist);
        System.out.println("Updated specialization.");

        // READ by ID: verify the update
        Therapist fetched = therapistDAO.getById(Therapist.class, therapist.getTherapistId());
        System.out.println("Fetched specialization: " + fetched.getSpecialization());

        // DELETE: remove the test record
//        therapistDAO.delete(therapist);
//        System.out.println("Deleted therapist.");

        // Verify deletion
//        Therapist deleted = therapistDAO.getById(Therapist.class, therapist.getTherapistId());
//        System.out.println("After delete, fetch result: " + deleted);

        // Clean up Hibernate resources

        System.out.println("DAO layer test complete!");
    }
}