import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public interface UserInterface {
    //Couldn't see any other way to do this
    //Works as an interface, making the GUI and Game Tracker available to the classes that need it
    //without getting in the way of the inheritance of the classes


    Stage mainStage = new Stage();


    //Main Grid for the game to function with
    GridPane mapPane = new GridPane();


    //Tracker to check if win conditions are satisfied
    GameTracker gameTrack = new GameTracker();
}
