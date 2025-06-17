package de.hitec.nhplus.controller;

import de.hitec.nhplus.Main;
import de.hitec.nhplus.datastorage.CaregiverDao;
import de.hitec.nhplus.datastorage.DaoFactory;
import de.hitec.nhplus.datastorage.PatientDao;
import de.hitec.nhplus.datastorage.TreatmentDao;
import de.hitec.nhplus.model.Caregiver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import de.hitec.nhplus.model.Patient;
import de.hitec.nhplus.model.Treatment;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AllTreatmentController {

    @FXML
    private TableView<Treatment> tableView;

    @FXML
    private TableColumn<Treatment, Integer> columnId;

    @FXML
    private TableColumn<Treatment, Integer> columnPid;

    @FXML
    private TableColumn<Treatment, String> columnDate;

    @FXML
    private TableColumn<Treatment, String> columnBegin;

    @FXML
    private TableColumn<Treatment, String> columnEnd;

    @FXML
    private TableColumn<Treatment, String> columnCaregiverPhoneNumber;

    @FXML
    private TableColumn<Treatment, String> columnDescription;

    @FXML
    private ComboBox<String> comboBoxPatientSelection;

    @FXML
    private ComboBox<String> comboBoxCaregiverSelection;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonNewTreatment;

    private TreatmentDao dao;
    private final ObservableList<String> patientSelection = FXCollections.observableArrayList();
    private final ObservableList<String> caregiverSelection = FXCollections.observableArrayList();

    private final ObservableList<Treatment> treatments = FXCollections.observableArrayList();

    private ArrayList<Patient> patientList;
    private ArrayList<Caregiver> caregiverList;


    public void initialize() {
        readAllAndShowInTableView();
        comboBoxPatientSelection.setItems(patientSelection);
        comboBoxPatientSelection.getSelectionModel().select(0);
        comboBoxCaregiverSelection.setItems(caregiverSelection);
        comboBoxCaregiverSelection.getSelectionModel().select(0);

        this.columnId.setCellValueFactory(new PropertyValueFactory<>("tid"));
        this.columnPid.setCellValueFactory(new PropertyValueFactory<>("pid"));
        this.columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        this.columnBegin.setCellValueFactory(new PropertyValueFactory<>("begin"));
        this.columnEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        this.columnCaregiverPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("caregiverPhoneNumber"));
        this.columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        this.tableView.setItems(this.treatments);


        // Disabling the button to delete treatments as long, as no treatment was selected.
        this.buttonDelete.setDisable(true);

        this.buttonNewTreatment.setDisable(false);

        this.tableView.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldTreatment, newTreatment) ->
                        AllTreatmentController.this.buttonDelete.setDisable(newTreatment == null));

        this.createComboBoxPatientData();
        this.createComboBoxCaregiverData();
    }

    public void readAllAndShowInTableView() {
        this.treatments.clear();
        comboBoxPatientSelection.getSelectionModel().select(0);
        comboBoxCaregiverSelection.getSelectionModel().select(0);

        this.dao = DaoFactory.getDaoFactory().createTreatmentDao();
        try {
            this.treatments.clear();
            this.treatments.addAll(dao.readAll());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }


    private void createComboBoxPatientData() {
        patientSelection.clear();
        patientSelection.add("alle Patienten/innen");

        PatientDao dao = DaoFactory.getDaoFactory().createPatientDAO();
        try {
            patientList = (ArrayList<Patient>) dao.readAll();
            for (Patient patient : patientList) {
                this.patientSelection.add(formatPatientDisplayName(patient));
            }
            comboBoxPatientSelection.setItems(patientSelection);
            comboBoxPatientSelection.getSelectionModel().selectFirst(); // "alle Patienten/innen" wird vorausgewählt
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }


    /**
     * Loads all caregivers from the database and fills the caregiver ComboBox.
     * Adds a default "all caregivers" option and selects it by default.
     */
    private void createComboBoxCaregiverData() {
        caregiverSelection.clear();
        caregiverSelection.add("alle Pfleger/innen");

        CaregiverDao dao = DaoFactory.getDaoFactory().createCaregiverDao();
        try {
            caregiverList = (ArrayList<Caregiver>) dao.readAll();
            for (Caregiver caregiver : caregiverList) {
                this.caregiverSelection.add(formatCaregiverDisplayName(caregiver));
            }
            comboBoxCaregiverSelection.setItems(caregiverSelection);
            comboBoxCaregiverSelection.getSelectionModel().selectFirst(); // "alle Pfleger/innen" wird vorausgewählt
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private String formatPatientDisplayName(Patient patient) {
        return String.format("%s, %s", patient.getSurname(), patient.getFirstName());
    }


    /**
     * Formats the caregiver's name as "Surname, Firstname" for display in the ComboBox.
     *
     * @param caregiver the caregiver whose name should be formatted
     * @return formatted display name of the caregiver
     */
    private String formatCaregiverDisplayName(Caregiver caregiver) {
        return String.format("%s, %s", caregiver.getSurname(), caregiver.getFirstName());
    }

    @FXML
    public void handleComboBox() {
        String selectedPatient = this.comboBoxPatientSelection.getSelectionModel().getSelectedItem();

        this.treatments.clear();
        this.dao = DaoFactory.getDaoFactory().createTreatmentDao();

        if (selectedPatient == null || selectedPatient.equals("alle Patienten/innen")) {
            try {
                this.treatments.addAll(this.dao.readAll());
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        } else {
            Patient patient = getPatientFromDisplayName(selectedPatient);
            if (patient != null) {
                try {
                    this.treatments.addAll(this.dao.readTreatmentsByPid(patient.getPid()));
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    private Patient getPatientFromDisplayName(String displayName) {
        for (Patient patient : patientList) {
            if (displayName.equals(formatPatientDisplayName(patient))) {
                return patient;
            }
        }
        return null;
    }


    /**
     * Finds the caregiver object that matches the given display name.
     *
     * @param displayName the formatted name ("Surname, Firstname") selected from the ComboBox
     * @return the matching Caregiver object, or null if not found
     */
    private Caregiver getCaregiverFromDisplayName(String displayName) {
        for (Caregiver caregiver : caregiverList) {
            if (displayName.equals(formatCaregiverDisplayName(caregiver))) {
                return caregiver;
            }
        }
        return null;
    }


    @FXML
    public void handleDelete() {
        int index = this.tableView.getSelectionModel().getSelectedIndex();
        Treatment t = this.treatments.remove(index);
        TreatmentDao dao = DaoFactory.getDaoFactory().createTreatmentDao();
        try {
            dao.deleteById(t.getTid());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    public void handleNewTreatment() {
        try {
            String selectedPatient = this.comboBoxPatientSelection.getSelectionModel().getSelectedItem();
            String selectedCaregiver = this.comboBoxCaregiverSelection.getSelectionModel().getSelectedItem();

            Patient patient = getPatientFromDisplayName(selectedPatient);
            Caregiver caregiver = getCaregiverFromDisplayName(selectedCaregiver);
            newTreatmentWindow(patient, caregiver);
        } catch (NullPointerException exception) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Patient oder Pfleger für die Behandlung fehlt!");
            alert.setContentText("Wählen Sie über die Combobox einen Patienten und einen Pfleger aus!");
            alert.showAndWait();
        }
    }

    @FXML
    public void handleMouseClick() {
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && (tableView.getSelectionModel().getSelectedItem() != null)) {
                int index = this.tableView.getSelectionModel().getSelectedIndex();
                Treatment treatment = this.treatments.get(index);
                treatmentWindow(treatment);
            }
        });
    }

    public void newTreatmentWindow(Patient patient, Caregiver caregiver) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/de/hitec/nhplus/NewTreatmentView.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);

            // the primary stage should stay in the background
            Stage stage = new Stage();

            NewTreatmentController controller = loader.getController();
            controller.initialize(this, stage, patient, caregiver);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void treatmentWindow(Treatment treatment) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/de/hitec/nhplus/TreatmentView.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);

            // the primary stage should stay in the background
            Stage stage = new Stage();
            TreatmentController controller = loader.getController();
            controller.initializeController(this, stage, treatment);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}