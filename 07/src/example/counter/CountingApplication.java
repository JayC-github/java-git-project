package example.counter;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class CountingApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        StartScreen startScreen = new StartScreen(primaryStage);
        CountScreen countScreen = new CountScreen(primaryStage);

        // Both controllers need to know about the other screen.

        startScreen.getController().setCountScreen(countScreen);
        countScreen.getController().setStartScreen(startScreen);

        startScreen.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
