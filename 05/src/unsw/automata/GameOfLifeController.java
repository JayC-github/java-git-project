package unsw.automata;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * A JavaFX controller for the Conway's Game of Live Application.
 *
 * @author Robert Clifton-Everest
 *
 */
public class GameOfLifeController {
    @FXML
    private GridPane cells;

    /*
     * @FXML private Button Tick;
     */

    @FXML
    private Button playbtm;

    // below is like the Emphasise emphasise
    private GameOfLife gol;
    // A timeline u create 
    private Timeline timeline;
    // check if ur playing the game
    private boolean playing;

    public GameOfLifeController() {
        // intialize the model of controller
        gol = new GameOfLife();
        // create a timeline
        timeline = new Timeline();
        // add a keyframe to the timelin that calls tick() method every 0.5s
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                gol.tick();
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    @FXML
    public void handlePlay(ActionEvent event) {
        // if the game is stop, start playing the game
        if (!playing) {
            playing = true;
            playbtm.setText("Stop");
            timeline.play();
        // if u stop the game, set the playing false
        } else {
            playing = false;
            playbtm.setText("Play");
            timeline.stop();
        }
    }

    @FXML
    public void handleTick(ActionEvent event) {
        // triggers a single tick of the game
        //System.out.println("tick!!!!!!!!!!!!!");
        gol.tick();
    }

    @FXML
    public void initialize() {
        // bidirectional bindings between the cells and each corresponding checkbox.
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                CheckBox cb = new CheckBox();
                gol.cellProperty(i,j).bindBidirectional(cb.selectedProperty());
                cells.add(cb, i, j);
            }
        }
    }
}

