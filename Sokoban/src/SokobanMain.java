import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SokobanMain extends Application implements UserInterface {

    public static void main(String[] args) {
        launch();
    }

    public void start(Stage newStage){
        //start the game
        new SokobanGame();


        //configure the stage
        //Stage mainStage = new Stage();
        mainStage.setTitle("Sokoban - WarehouseKeeper");
        mainStage.getIcons().add(new Image("SokobanImages/WarehouseKeeper.png"));
        mainStage.setScene(new Scene(UserInterface.mapPane));
        mainStage.show();
    }

}
