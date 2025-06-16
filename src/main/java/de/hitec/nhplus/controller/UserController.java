package de.hitec.nhplus.controller;

import de.hitec.nhplus.Main;
import de.hitec.nhplus.datastorage.ConnectionBuilder;
import de.hitec.nhplus.datastorage.DaoFactory;
import de.hitec.nhplus.datastorage.UserDao;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The <code>UserController</code> contains the login logic for the application.
 * It handles user input, credential verification and navigation to the main view.
 */
public class UserController {

    @FXML
    private Button buttonLogin;

    @FXML
    private TextField textFieldUserName;

    @FXML
    private TextField textFieldPassword;

    private UserDao userDao;

    /**
     * Initializes the controller after all @FXML fields have been injected.
     * Sets up listeners for form validation and prepares the DAO.
     */
    public void initialize() {
        this.userDao = DaoFactory.getDaoFactory().createUserDAO();

        this.buttonLogin.setDisable(true);

        ChangeListener<String> inputListener = (observable, oldValue, newText) ->
                UserController.this.buttonLogin.setDisable(!UserController.this.areInputDataValid());

        this.textFieldUserName.textProperty().addListener(inputListener);
        this.textFieldPassword.textProperty().addListener(inputListener);
    }

    /**
     * Called when the login button is pressed.
     * Verifies credentials and opens the main application window if successful.
     * Otherwise, shows an error message.
     */
    @FXML
    public void handleLogin() {
        String username = textFieldUserName.getText();
        String password = textFieldPassword.getText();

        try {
            if (userDao.checkCredentials(username, password)) {
                openMainView();
            } else {
                showLoginError();
            }
        } catch (Exception e) {
            e.printStackTrace();
            showLoginError();
        }
    }

    /**
     * Opens the main application window after a successful login.
     * Loads the FXML file, sets the new scene, and configures the close behavior.
     */
    private void openMainView() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/de/hitec/nhplus/MainWindowView.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) buttonLogin.getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setTitle("NHPlus - Startseite");
            stage.setScene(scene);
            stage.setResizable(true);
            stage.show();

            stage.setOnCloseRequest(event -> {
                ConnectionBuilder.closeConnection();
                Platform.exit();
                System.exit(0);
            });
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }


    /**
     * Displays an error message when the login fails.
     */
    private void showLoginError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login fehlgeschlagen");
        alert.setHeaderText(null);
        alert.setContentText("Benutzername oder Passwort ist falsch.");
        alert.showAndWait();
    }

    /**
     * Validates that both username and password fields are filled in.
     *
     * @return <code>true</code> if input is valid, <code>false</code> otherwise.
     */
    private boolean areInputDataValid() {
        return !textFieldUserName.getText().isBlank() && !textFieldPassword.getText().isBlank();
    }
}
