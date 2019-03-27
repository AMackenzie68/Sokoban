import javafx.scene.Node;

import java.util.ArrayList;

public class GameTracker implements UserInterface{

    private ArrayList<Tile> diamonds = new ArrayList<>();
    private ArrayList<Tile> crates = new ArrayList<>();
    private boolean gameResult;

    public GameTracker(){
        wipeLists();
        gameResult = false;
    }

    public void wipeLists() {
        diamonds.clear();
        crates.clear();
    }

    public boolean checkWinConditions(){

        for(Node Tile : mapPane.getChildren()){
            if (Tile.getId().equals("Diamond")){
                return false;
            }
        }
        return true;

    }

    public void addCrates(Crate crate){
        crates.add(crate);
    }

    public void addDiamonds(Diamond diamond){
        diamonds.add(diamond);
    }

    public void setGameResult(boolean result){
        gameResult = result;
    }

    public boolean getGameResult(){
        gameResult = checkWinConditions();
        return gameResult;
    }

}
