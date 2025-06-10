package de.hitec.nhplus.model;

import javafx.beans.property.SimpleStringProperty;


/**
 * Abstract base class representing a person in the system.
 * <p>
 * Provides basic personal information such as first name and surname.
 * Other classes like {@code Patient} or {@code Caregiver} extend this class.
 */
public abstract class Person {
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty surname;

    /**
     * Constructs a {@code Person} with the given first name and surname.
     *
     * @param firstName First name of the person.
     * @param surname   Last name of the person.
     */
    public Person(String firstName, String surname) {
        this.firstName = new SimpleStringProperty(firstName);
        this.surname = new SimpleStringProperty(surname);
    }

    // ---------------------- Getter & Setter ----------------------

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }
}