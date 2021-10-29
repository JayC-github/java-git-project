package example.counter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartController {

    @FXML
    private Button startButton;

    private CountScreen countScreen;

    @FXML
    void handleStartButton(ActionEvent event) {
        countScreen.start();
    }

    public void setCountScreen(CountScreen countScreen) {
        this.countScreen = countScreen;
    }

}

