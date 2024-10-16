package org.example.demofinal;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Controller class for handling the logic of the BMI Calculator.
 * It includes methods to initialize the application, calculate BMI, clear fields, exit the app, and show help instructions.
 */
public class BMICalculatorController {

    @FXML
    private TextField weightField;  // TextField to input weight


    @FXML
    private TextField heightField;  // TextField to input height


    @FXML
    private ComboBox<String> unitBox;  // ComboBox to select measurement units (Metric or Imperial)

    @FXML
    private Label bmiResult;  // Label to display the calculated BMI

    @FXML
    private Label statusResult;  // Label to display the BMI status (Underweight, Normal, Overweight, Obese)

    /**
     * Initializes the controller by setting a default unit of measurement.
     * This method is called automatically after the FXML components are loaded.
     */
    @FXML
    private void initialize() {
        unitBox.setValue("Metric (kg/m)");  // Set default unit to Metric
    }

    /**
     * Method triggered when the "Calculate BMI" button is clicked.
     * It reads the user's weight, height, and unit selection, then calculates and displays the BMI and status.
     */
    @FXML
    private void calculateBMI() {
        try {
            double weight = Double.parseDouble(weightField.getText());  // Get weight input
            double height = Double.parseDouble(heightField.getText());  // Get height input
            String unit = unitBox.getValue();  // Get selected unit of measurement

            // Calculate BMI based on the selected unit
            double bmi = calculateBMI(weight, height, unit);
            bmiResult.setText(String.format("%.2f", bmi));  // Display the BMI result

            // Determine and display the BMI status (Underweight, Normal, Overweight, Obese)
            String status = determineStatus(bmi);
            statusResult.setText(status);
        } catch (Exception e) {
            // Handle invalid input (e.g., non-numeric or empty fields)
            bmiResult.setText("Invalid input");
            statusResult.setText("");
        }
    }

    /**
     * Calculates the BMI based on weight, height, and unit of measurement.
     * @param weight - The user's weight (in kg or lbs)
     * @param height - The user's height (in m or in)
     * @param unit - The unit of measurement selected ("Metric (kg/m)" or "Imperial (lbs/in)")
     * @return The calculated BMI value
     */
    private double calculateBMI(double weight, double height, String unit) {
        if (unit.equals("Metric (kg/m)")) {
            return weight / (height * height);  // Metric formula: BMI = weight (kg) / height^2 (m)
        } else {
            return (weight / (height * height)) * 703;  // Imperial formula: BMI = weight (lbs) / height^2 (in) * 703
        }
    }

    /**
     * Determines the BMI status (Underweight, Normal, Overweight, Obese) based on the calculated BMI value.
     * @param bmi - The calculated BMI value
     * @return The corresponding BMI status as a String
     */
    private String determineStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            return "Normal";
        } else if (bmi >= 25.0 && bmi <= 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    /**
     * Clears all the input fields (weight, height) and resets the unit ComboBox and result labels.
     */
    @FXML
    protected void clearFields() {
        weightField.clear();
        heightField.clear();
        unitBox.getSelectionModel().clearSelection();  // Clear the ComboBox selection
        bmiResult.setText("");  // Clear BMI result label
        statusResult.setText("");  // Clear status result label
    }

    /**
     * Exits the application when the "Exit" button is clicked.
     */
    @FXML
    protected void exitApplication() {
        Platform.exit();  // Close the application
    }

    /**
     * Displays a help dialog with instructions on how to use the BMI Calculator.
     */
    @FXML
    protected void showHelp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("How to use the BMI Calculator");
        alert.setContentText("1. Enter your weight in kilograms or pounds.\n"
                + "2. Enter your height in meters or inches.\n"
                + "3. Select the unit of measurement from the dropdown.\n"
                + "4. Click 'Calculate BMI' to see your BMI and status.\n"
                + "5. Use 'Clear' to reset the fields or 'Exit' to close the application.");
        alert.showAndWait();  // Show the help dialog
    }
}
