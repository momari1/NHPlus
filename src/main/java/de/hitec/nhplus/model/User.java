package de.hitec.nhplus.model;

import javafx.beans.property.SimpleStringProperty;

/**
 * Represents a system user who can log into the NHPlus application.
 * Each user has a username and a password.
 */
public class User {
    private final SimpleStringProperty username;
    private final SimpleStringProperty password;

    /**
     * Constructor to create a new User object with the given username and password.
     * This object can be used to authenticate access to the application.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public User(String username, String password) {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
    }

    /**
     * Returns the username of the user.
     *
     * @return The username as a String.
     */
    public String getUsername() {
        return username.get();
    }

    /**
     * Returns the username property (JavaFX binding).
     *
     * @return The username property.
     */
    public SimpleStringProperty usernameProperty() {
        return username;
    }

    /**
     * Sets a new username.
     *
     * @param username New username to be set.
     */
    public void setUsername(String username) {
        this.username.set(username);
    }

    /**
     * Returns the password of the user.
     *
     * @return The password as a String.
     */
    public String getPassword() {
        return password.get();
    }

    /**
     * Returns the password property (JavaFX binding).
     *
     * @return The password property.
     */
    public SimpleStringProperty passwordProperty() {
        return password;
    }

    /**
     * Sets a new password.
     *
     * @param password New password to be set.
     */
    public void setPassword(String password) {
        this.password.set(password);
    }

}
