package de.hitec.nhplus.datastorage;

import de.hitec.nhplus.model.Caregiver;
import de.hitec.nhplus.utils.DateConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Data access object (DAO) for {@link Caregiver}.
 * <p>
 * This class extends {@code DaoImp} and implements all necessary SQL operations
 * for managing caregiver data in the database.
 */
public class CaregiverDao extends DaoImp<Caregiver> {

    /**
     * The constructor initiates an object of <code>CaregiverDao</code> and passes the connection to its super class.
     *
     * @param connection Object of <code>Connection</code> to execute the SQL-statements.
     */
    public CaregiverDao(Connection connection) {
        super(connection);
    }

    /**
     * Generates a {@code PreparedStatement} to insert a new caregiver into the database.
     *
     * @param caregiver Caregiver to be inserted.
     * @return Prepared statement to insert the caregiver.
     */
    @Override
    protected PreparedStatement getCreateStatement(Caregiver caregiver) {
        PreparedStatement preparedStatement = null;

        try {
            final String SQL = "INSERT INTO caregiver (firstname, surname, phoneNumber, active, inactiveSince ) " +
                    "VALUES (?, ?, ?, ?, ?)";

            preparedStatement = this.connection.prepareStatement(SQL);

            preparedStatement.setString(1, caregiver.getFirstName());
            preparedStatement.setString(2, caregiver.getSurname());
            preparedStatement.setString(3, caregiver.getPhoneNumber());
            preparedStatement.setBoolean(4, caregiver.isActive());
            preparedStatement.setString(5, caregiver.getInactiveSince());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    /**
     * Generates a <code>PreparedStatement</code> to query a caregiver by a given caregiver id (cid).
     *
     * @param cid Caregiver id to query.
     * @return <code>PreparedStatement</code> to query the caregiver.
     */
    @Override
    protected PreparedStatement getReadByIDStatement(long cid) {
        PreparedStatement preparedStatement = null;
        try {
            final String SQL = "SELECT * FROM caregiver WHERE cid = ?";
            preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setLong(1, cid);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    /**
     * Maps a <code>ResultSet</code> of one caregiver to an object of <code>Caregiver</code>.
     *
     * @param result ResultSet with a single row. Columns will be mapped to an object of class <code>Caregiver</code>.
     * @return Object of class <code>Caregiver</code> with the data from the resultSet.
     */
    @Override
    protected Caregiver getInstanceFromResultSet(ResultSet result) throws SQLException {
        return new Caregiver(
                result.getLong(1),
                result.getString(2),
                result.getString(3),
                result.getString(4),
                result.getBoolean(5),
                DateConverter.convertStringToLocalDate(result.getString(6)));
    }

    /**
     * Generates a <code>PreparedStatement</code> to query all Caregivers.
     *
     * @return <code>PreparedStatement</code> to query all Caregivers.
     */
    @Override
    protected PreparedStatement getReadAllStatement() {
        PreparedStatement statement = null;
        try {
            final String SQL = "SELECT * FROM caregiver";
            statement = this.connection.prepareStatement(SQL);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return statement;
    }

    /**
     * Maps a <code>ResultSet</code> of all caregivers to an <code>ArrayList</code> of <code>Caregiver</code> objects.
     *
     * @param result ResultSet with all rows. The Columns will be mapped to objects of class <code>Caregiver</code>.
     * @return <code>ArrayList</code> with objects of class <code>Caregiver</code> of all rows in the
     * <code>ResultSet</code>.
     */
    @Override
    protected ArrayList<Caregiver> getListFromResultSet(ResultSet result) throws SQLException {
        ArrayList<Caregiver> list = new ArrayList<>();
        while (result.next()) {
            LocalDate inactiveSince = DateConverter.convertStringToLocalDate(result.getString(6));
            Caregiver caregiver = new Caregiver(
                    result.getLong(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getBoolean(5),
                    inactiveSince);
            list.add(caregiver);
        }
        return list;
    }

    /**
     * Generates a <code>PreparedStatement</code> to update the given caregiver, identified
     * by the id of the caregiver (cid).
     *
     * @param caregiver Caregiver object to update.
     * @return <code>PreparedStatement</code> to update the given caregiver.
     */
    @Override
    protected PreparedStatement getUpdateStatement(Caregiver caregiver) {
        PreparedStatement preparedStatement = null;

        try {
            final String SQL =
                    "UPDATE caregiver SET " +
                            "firstname = ?, " +
                            "surname = ?, " +
                            "phoneNumber = ?, " +
                            "active = ?, " +
                            "inactiveSince = ? " +
                            "WHERE cid = ?";
            preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setString(1, caregiver.getFirstName());
            preparedStatement.setString(2, caregiver.getSurname());
            preparedStatement.setString(3, caregiver.getPhoneNumber());
            preparedStatement.setBoolean(4, caregiver.isActive());
            preparedStatement.setString(5, caregiver.getInactiveSince());
            //preparedStatement.setDate(5, caregiver.getInactiveSince());
            preparedStatement.setLong(6, caregiver.getCid());

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }

    /**
     * Generates a <code>PreparedStatement</code> to delete a caregiver with the given id.
     *
     * @param cid Id of the caregiver to delete.
     * @return <code>PreparedStatement</code> to delete caregiver with the given id.
     */
    @Override
    protected PreparedStatement getDeleteStatement(long cid) {
        PreparedStatement preparedStatement = null;
        try {
            final String SQL = "DELETE FROM caregiver WHERE cid = ?";
            preparedStatement = this.connection.prepareStatement(SQL);
            preparedStatement.setLong(1, cid);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return preparedStatement;
    }
}