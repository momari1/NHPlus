package de.hitec.nhplus.model;

import de.hitec.nhplus.utils.DateConverter;
import javafx.beans.property.SimpleStringProperty;
import java.time.LocalDate;

/**
 * Represents a caregiver in the system.
 * <p>
 * Extends the {@link Person} class with additional details like phone number,
 * active status, and the date the caregiver became inactive.
 */
public class Caregiver extends Person {

    private long cid;
    private String phoneNumber;
    private boolean active;
    private final SimpleStringProperty inactiveSince;

    /**
     * Constructs a new Caregiver without an ID.
     * <p>
     * This constructor is typically used to create caregiver objects before they are saved in the database.
     *
     * @param firstname     Caregiver's first name.
     * @param surname       Caregiver's last name.
     * @param phoneNumber   Caregiver's phone number.
     * @param active        Active status.
     * @param inactiveSince Date since the caregiver became inactive.
     */
    public Caregiver(String firstname, String surname, String phoneNumber, boolean active, LocalDate inactiveSince) {
        super(surname, firstname);
        this.phoneNumber = phoneNumber;
        this.active = active;
        this.inactiveSince = new SimpleStringProperty(DateConverter.convertLocalDateToString(inactiveSince));
    }

    /**
     * Constructor to initiate an object of class <code>Caregiver</code> with the given parameter. Use this constructor
     * to initiate objects, which are already persisted and have a patient id (cid).
     *
     * @param cid           Caregiver ID.
     * @param firstname     First name.
     * @param surname       Last name.
     * @param phoneNumber   Phone number.
     * @param active        Active status.
     * @param inactiveSince Date since the caregiver became inactive.
     */
    public Caregiver(Long cid, String firstname, String surname, String phoneNumber, boolean active, LocalDate inactiveSince) {
        super(surname, firstname);
        this.cid = cid;
        this.phoneNumber = phoneNumber;
        this.active = active;
        this.inactiveSince = new SimpleStringProperty(DateConverter.convertLocalDateToString(inactiveSince));
    }


    // ---------------------- Getter & Setter ----------------------


    /**
     * @return the ID of the Caregiver object.
     */
    public long getCid() {
        return cid;
    }

    /**
     * @return the phone number of the Caregiver.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber as string
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns whether the caregiver is currently active.
     *
     * @return true if the caregiver is active, false otherwise.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the caregiver's active status.
     *
     * @param active true if the caregiver should be marked as active, false otherwise.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Returns the date since the caregiver has been inactive.
     *
     * @return the date the caregiver became inactive, or null if still active.
     */
    public String getInactiveSince() {
        return inactiveSince.get();
    }

    /**
     * Stores the given string as new <code>birthOfDate</code>.
     *
     * @param inactiveSince as string in the following format: YYYY-MM-DD.
     */
    public void setInactiveSince(String inactiveSince) {
        this.inactiveSince.set(inactiveSince);
    }

    /**
     * @return string-representation of the Caregiver
     */
    public String toString() {
        return "Caregiver" + "\nCID: " + this.cid +
                "\nFirstname: " + this.getFirstName() +
                "\nSurname: " + this.getSurname() +
                "\nTelephone: " + this.phoneNumber +
                "\n active: " + this.active +
                "\n inactiveSince: " + this.inactiveSince +
                "\n";
    }
}