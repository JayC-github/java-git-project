package example.counter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CountController {

    @FXML
    private Button backButton;

    @FXML
    private Button minusButton;

    @FXML
    private Button plusButton;

    @FXML
    private TextField numberField;

    private StartScreen startScreen;

    @FXML
    public void handleBackButton(ActionEvent event) {
        startScreen.start();
    }

    public void setStartScreen(StartScreen startScreen) {
        this.startScreen = startScreen;
    }

    @FXML
    public void initialize() {
        numberField.setText("0");
    }

    @FXML
    public void handleMinus(ActionEvent event) {
        int value = Integer.parseInt(numberField.getText());
        numberField.setText(Integer.toString(value - 1));
    }

    @FXML
    public void handlePlus(ActionEvent event) {
        int value = Integer.parseInt(numberField.getText());
        numberField.setText(Integer.toString(value + 1));
    }

}

