package com.serenity.dao;

// Factory pattern: centralizes DAO creation so the rest of the app
// never uses "new TherapistDAO()" directly
public class DAOFactory {
    public static TherapistDAO getTherapistDAO() { return new TherapistDAO(); }
    public static PatientDAO getPatientDAO() { return new PatientDAO(); }
    public static TherapyProgramDAO getTherapyProgramDAO() { return new TherapyProgramDAO(); }
    public static TherapySessionDAO getTherapySessionDAO() { return new TherapySessionDAO(); }
    public static PaymentDAO getPaymentDAO() { return new PaymentDAO(); }
    public static UserDAO getUserDAO() { return new UserDAO(); }
}