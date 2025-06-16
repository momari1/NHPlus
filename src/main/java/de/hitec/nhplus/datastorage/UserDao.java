package de.hitec.nhplus.datastorage;

import de.hitec.nhplus.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class provides access to the <code>user</code> table in the database.
 * It allows checking login credentials and creating new users.
 */
public class UserDao {

    private final Connection connection;

    /**
     * Constructor initializes a <code>UserDao</code> object with the given database connection.
     *
     * @param connection Connection object to execute SQL statements.
     */
    public UserDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * Checks whether a user exists with the given username and password.
     *
     * @param username Username to check.
     * @param password Password to check.
     * @return <code>true</code> if the user is found, <code>false</code> otherwise.
     */
    public boolean checkCredentials(String username, String password) {
        PreparedStatement preparedStatement;
        try {
            final String SQL = "SELECT * FROM user WHERE username = ? AND password = ?";
            preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet result = preparedStatement.executeQuery();
            return result.next();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    /**
     * Creates a new user in the <code>user</code> table.
     *
     * @param user The <code>User</code> object containing username and password to insert.
     * @throws SQLException If an error occurs during the database insert operation.
     */
    public void createUser(User user) throws SQLException {
        final String SQL = "INSERT INTO user (username, password) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        }
    }
}