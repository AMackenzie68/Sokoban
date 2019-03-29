import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public abstract class ElementMoveable extends Tile {
    private ArrayList<int[]> coveredDiamondCoords = new ArrayList<>();

    public ElementMoveable(int x, int y, String type, Image sprite) {
        super(x, y, type, sprite);
    }

    private Rectangle getMapObject(int x, int y) {
        //Code sourced from user Shreyas Dave on Stack Overflow
        // With minor changes (casting to rectangle, making variables more appropriate, etc.)
        // no other way of searching gridpane was clear
        for (Node node : mapPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node) == y) {
                return (Rectangle) node;
            }
        }
        return null;

    }

    private boolean checkWalls(int x, int y, CardinalDirection cardinalDirection) {
        //Checks if the object is a wall, and if it is, returns that a wall is there (true)
        //Otherwise returns false
        switch (cardinalDirection) {
            case NORTH:
                y--;
                break;
            case EAST:
                x++;
                break;
            case SOUTH:
                y++;
                break;
            case WEST:
                x--;
                break;
        }
        if (getMapObject(x,y) != null) {
            if (getMapObject(x, y).getId().equals("Wall")) {
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }

    private boolean checkForCrate(int x, int y, CardinalDirection cardinalDirection) {
        //Similar function to checkWalls, but checks for crates specifically
        switch (cardinalDirection) {
            case NORTH:
                y--;
                break;
            case EAST:
                x++;
                break;
            case SOUTH:
                y++;
                break;
            case WEST:
                x--;
                break;
        }
        if(getMapObject(x,y) != null) {
            System.out.println("The object at " + x + " , " + y + " is a: " + getMapObject(x,y).getId());
            if (getMapObject(x, y).getId().equals("Crate")) {
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }

    private boolean checkCrateDirection(int x, int y, CardinalDirection cardinalDirection) {
        //Checks if a crate in the direction supplied from the coordinates can move in that direction
        int crateMoveX = x;
        int crateMoveY = y;
        switch (cardinalDirection) {
            case NORTH:
                crateMoveY = y - 1;
                break;
            case EAST:
                crateMoveX = x + 1;
                break;
            case SOUTH:
                crateMoveY = y + 1;
                break;
            case WEST:
                crateMoveX = x - 1;
                break;
            default:
                crateMoveX = x;
                crateMoveY = y;
        }

        String newSpaceId = getMapObject(crateMoveX, crateMoveY).getId();
        //System.out.println(newSpaceId);
        //Checks if the space holds a crate or a wall which would hold back the crate
        if (newSpaceId.equals("Crate")) {
            return false;
        } else if (newSpaceId.equals("Wall")) {
            return false;
        } else {
            return true;
        }

    }

    private boolean checkCoveredDiamonds(int x, int y) {
        //Checks the list of diamonds removed from the map to see if the parameter coordinates match
        for (int[] diamondCoords : coveredDiamondCoords) {
            if (((diamondCoords[0] == x) && (diamondCoords[1] == y))) {
                coveredDiamondCoords.remove(diamondCoords);
                return true;
            }
        }
        return false;
    }

    private void moveCrate(int x, int y, CardinalDirection cardinalDirection){
        //Moves a crate found one square away from the coordinates(x,y)given, in the direction given.
        Rectangle crateToMove;

        int newCrateX = x;
        int newCrateY = y;

        switch(cardinalDirection){
            case NORTH: newCrateY--;break;
            case EAST: newCrateX++;break;
            case SOUTH: newCrateY++;break;
            case WEST: newCrateX--;break;
        }

        crateToMove = getMapObject(x,y);
        crateToMove.toFront();


        Rectangle tileInNewSpace = getMapObject(newCrateX,newCrateY);
        assert tileInNewSpace != null;
        if(tileInNewSpace.getId().equals("Diamond")){
            int[] newDiamondCoords = new int[2];
            newDiamondCoords[0] = newCrateX;
            newDiamondCoords[1] = newCrateY;
            coveredDiamondCoords.add(newDiamondCoords);
        }

        //Removes old tile, adds new tile, and ensures that the tile displays on top of the frame
        System.out.println("The tile about to be removed is a " + tileInNewSpace.getId());
        mapPane.getChildren().remove(tileInNewSpace);
        if(checkCoveredDiamonds(x,y)){
            GridPane.setConstraints(new Diamond(x,y).getTileShape(),x,y);
        }else{
            GridPane.setConstraints(new Ground(x,y).getTileShape(),x,y);
        }
        GridPane.setConstraints(crateToMove,newCrateX,newCrateY);
        //mapPane.getChildren().add(crateToMove);
        crateToMove.toFront();
        //GridPane.setConstraints(new Ground(newCrateX,newCrateY).getTileShape(),newCrateX,newCrateY);

    }


    public boolean movementChecker(int x, int y, CardinalDirection cardinalDirection){
    //Checks if there is any tile that would block a WarehouseKeeper or Crate from moving
       //Also handles the collision between the player and a movable crate by calling the moveCrate method
        //Returns true if movement is possible, otherwise returns false.
        System.out.println(getMapObject(x,y).getId());
        if(checkWalls(x,y,cardinalDirection)){
            System.out.println("Player cannot move in direction " + cardinalDirection);
            return false;
        }

        if(checkForCrate(x,y,cardinalDirection)){
            int crateX=x;
            int crateY=y;

            switch(cardinalDirection){
                case NORTH: crateY--;break;
                case EAST: crateX++;break;
                case SOUTH: crateY++;break;
                case WEST: crateX--;break;
                default:crateX = x; crateY = y;
            }

            if(checkCrateDirection(crateX,crateY,cardinalDirection)){
                System.out.println("Crate can move");
                moveCrate(crateX,crateY,cardinalDirection);
                getMapObject(crateX,crateY).toFront();
                return true;
            }else{
                System.out.println("Crate blocked.");
                System.out.println("direction: " + cardinalDirection);
                return false;
            }
        }
       return true;
    }
}