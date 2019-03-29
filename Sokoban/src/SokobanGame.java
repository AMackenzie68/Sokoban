import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class SokobanGame  implements UserInterface{

    private Map currentMap;
    private ArrayList<File> mapFiles = new ArrayList<>();




    public SokobanGame(){


        File levelFiles = new File("src/SokobanLevels");
        File[] fileList = levelFiles.listFiles();

        assert fileList != null;
        mapFiles.addAll(Arrays.asList(fileList));

        mainMenu();
    }

    public void mainMenu(){
        setStage();
        clearScene();

        Label menuLabel = new Label("Welcome To Sokoban");
        GridPane.setConstraints(menuLabel,0,0);
        mapPane.getChildren().add(menuLabel);

        for(int i = 1; i<7; i++) {
            Button levelButton = new Button("Level " + i);

           int levelNumber = i;

            levelButton.setOnMouseClicked(e -> {
                newLevel((int) levelNumber);
            });

            mapPane.setVgap(20);
            GridPane.setConstraints(levelButton, 0, i+1);
            mapPane.getChildren().add(levelButton);
        }
    }

    public void clearScene(){
        gameTrack.wipeLists();
        mapPane.getChildren().clear();
    }


    public int getWidth(){
       Map map = getCurrentMap();
        if (map == null){
            return 800;
        }else {
            return map.getMapHorizontal();
        }
    }

    public int getHeight(){
        Map map = getCurrentMap();
        if (map == null){
            return 480;
        }else{
            return map.getMapVertical();
        }
    }

    public Map getCurrentMap() {
        return currentMap;
    }

    public void newLevel(int levelNumber){
        clearScene();
        currentMap = new Map(levelNumber);
        setStage();
        WarehouseKeeper playerKeeper = currentMap.getWarehouseKeeper();
        playerKeeper.getTileShape().setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                playerKeeper.warehouseKeeperMovement(event);

                if(gameTrack.getGameResult()){
                    endgame();
                }
            }
        });

    }
    public void setStage(){
        mainStage.setWidth(getWidth());
        mainStage.setHeight(getHeight());
    }

    public void endgame(){
        if(!gameComplete()){

            System.exit(0);

        }
        clearScene();
        mainMenu();
    }

    public boolean gameComplete(){
        System.out.println("game complete");
        return (ConfirmBox.display("Congratulations!","You beat level " + currentMap.getLevel() + ".\nPlay again?"));
    }
}
