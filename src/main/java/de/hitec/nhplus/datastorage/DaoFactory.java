package de.hitec.nhplus.datastorage;

public class DaoFactory {

    private static DaoFactory instance;

    private DaoFactory() {
    }

    public static DaoFactory getDaoFactory() {
        if (DaoFactory.instance == null) {
            DaoFactory.instance = new DaoFactory();
        }
        return DaoFactory.instance;
    }

    public TreatmentDao createTreatmentDao() {
        return new TreatmentDao(ConnectionBuilder.getConnection());
    }

    public PatientDao createPatientDAO() {
        return new PatientDao(ConnectionBuilder.getConnection());
    }

    /**
     * Creates a new CaregiverDao using the shared database connection.
     *
     * @return a new CaregiverDao object.
     */
    public CaregiverDao createCaregiverDao(){
        return new CaregiverDao(ConnectionBuilder.getConnection());
    }

    public UserDao createUserDAO() {
        return new UserDao(ConnectionBuilder.getConnection());
    }
}
