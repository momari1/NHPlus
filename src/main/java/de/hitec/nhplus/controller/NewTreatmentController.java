package de.hitec.nhplus.controller;

import de.hitec.nhplus.datastorage.DaoFactory;
import de.hitec.nhplus.datastorage.TreatmentDao;
import de.hitec.nhplus.model.Caregiver;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import de.hitec.nhplus.model.Patient;
import de.hitec.nhplus.model.Treatment;
import de.hitec.nhplus.utils.DateConverter;
import javafx.util.StringConverter;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class NewTreatmentController {

    @FXML
    private Label labelPatientFirstName;

    @FXML
    private Label labelPatientSurname;

    @FXML
    private Label labelCaregiverName;

    @FXML
    private Label labelCaregiverPhoneNumber;

    @FXML
    private TextField textFieldBegin;

    @FXML
    private TextField textFieldEnd;

    @FXML
    private TextField textFieldDescription;

    @FXML
    private TextArea textAreaRemarks;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonCancel;


    private AllTreatmentController controller;
    private Patient patient;
    private Caregiver caregiver;

    private Stage stage;

    public void initialize(AllTreatmentController controller, Stage stage, Patient patient, Caregiver caregiver) {
        this.controller = controller;
        this.patient = patient;
        this.caregiver = caregiver;
        this.stage = stage;

        this.buttonAdd.setDisable(true);
        this.buttonCancel.setDisable(false);
        ChangeListener<String> inputNewPatientListener = (observableValue, oldText, newText) ->
                NewTreatmentController.this.buttonAdd.setDisable(NewTreatmentController.this.areInputDataInvalid());
        this.textFieldBegin.textProperty().addListener(inputNewPatientListener);
        this.textFieldEnd.textProperty().addListener(inputNewPatientListener);
//        this.textFieldCaregiverPhoneNumber.textProperty().addListener(inputNewPatientListener);
        this.textFieldDescription.textProperty().addListener(inputNewPatientListener);
        this.textAreaRemarks.textProperty().addListener(inputNewPatientListener);
        this.datePicker.valueProperty().addListener((observableValue, localDate, t1) -> NewTreatmentController.this.buttonAdd.setDisable(NewTreatmentController.this.areInputDataInvalid()));
        this.datePicker.setConverter(new StringConverter<>() {
            @Override
            public String toString(LocalDate localDate) {
                return (localDate == null) ? "" : DateConverter.convertLocalDateToString(localDate);
            }

            @Override
            public LocalDate fromString(String localDate) {
                return DateConverter.convertStringToLocalDate(localDate);
            }
        });
        this.showPatientData();
        this.showCaregiverData();
    }

    private void showPatientData() {
        this.labelPatientFirstName.setText(patient.getFirstName());
        this.labelPatientSurname.setText(patient.getSurname());
    }

    private void showCaregiverData() {
        this.labelCaregiverName.setText(caregiver.getSurname() + ", " + caregiver.getFirstName());
        this.labelCaregiverPhoneNumber.setText(caregiver.getPhoneNumber());
    }

    @FXML
    public void handleAdd() {
        LocalDate date = this.datePicker.getValue();
        LocalTime begin = DateConverter.convertStringToLocalTime(textFieldBegin.getText());
        LocalTime end = DateConverter.convertStringToLocalTime(textFieldEnd.getText());
        String caregiverPhoneNumber = this.labelCaregiverPhoneNumber.getText();
        String description = textFieldDescription.getText();
        String remarks = textAreaRemarks.getText();
        Treatment treatment = new Treatment(patient.getPid(), date, begin, end, caregiverPhoneNumber, description, remarks, caregiver.getCid());
        createTreatment(treatment);
        controller.readAllAndShowInTableView();
        stage.close();
    }

    private void createTreatment(Treatment treatment) {
        TreatmentDao dao = DaoFactory.getDaoFactory().createTreatmentDao();
        try {
            dao.create(treatment);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    public void handleCancel() {
        stage.close();
    }

    private boolean areInputDataInvalid() {
        if (this.textFieldBegin.getText() == null || this.textFieldEnd.getText() == null) {
            return true;
        }
        try {
            LocalTime begin = DateConverter.convertStringToLocalTime(this.textFieldBegin.getText());
            LocalTime end = DateConverter.convertStringToLocalTime(this.textFieldEnd.getText());
            if (!end.isAfter(begin)) {
                return true;
            }
        } catch (Exception exception) {
            return true;
        }
        return this.textFieldDescription.getText().isBlank() || this.datePicker.getValue() == null;
    }
}