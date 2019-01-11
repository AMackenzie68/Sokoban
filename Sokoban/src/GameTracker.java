import java.util.ArrayList;
import java.util.Arrays;

public class GameTracker {

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

    public void checkCovered(){
        Boolean[] coveredDiamonds = new Boolean[diamonds.size()];
        int counter = 0;
        for (Tile tempDiamond : diamonds){

            int diamondX = tempDiamond.getxAxis();
            int diamondY = tempDiamond.getyAxis();
            for (Tile tempCrate : crates){
                int crateX = tempCrate.getxAxis();
                int crateY = tempCrate.getyAxis();
                if (crateX == diamondX && crateY == diamondY){
                    coveredDiamonds[counter] = true;
                }
            }

            if(Arrays.asList(coveredDiamonds).contains(false)){
                setGameResult(true);
            }else{
                setGameResult(false);
            }

        }

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
        checkCovered();
        return gameResult;
    }

}
