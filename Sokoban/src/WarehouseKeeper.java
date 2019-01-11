import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;


public class WarehouseKeeper extends ElementMoveable {

    public WarehouseKeeper(int x, int y){
        super(x,y,"WarehouseKeeper",new Image("SokobanImages/WarehouseKeeper.png"));
        new Ground(x,y);
        getTileShape().toFront();
        //Brings Warehouse keeper to front of whatever tile they are made in
        getTileShape().setFocusTraversable(true);
        //Assigns keyboard focus to the warehouse keeper so input can be read

    }

    public void warehouseKeeperMovement(KeyEvent keypress){
      //Handles the Key inputs for the WarehouseKeeper/Player and moves the tile accordingly

        //get the current position of the keeper
        int warehouseKeeperX = getxAxis();
        int warehouseKeeperY = getyAxis();

        if(keypress.getCode() == KeyCode.UP){
            if(movementChecker(warehouseKeeperX,warehouseKeeperY,1)){
                warehouseKeeperY--;
            }
        }

        if(keypress.getCode() == KeyCode.RIGHT){
            if(movementChecker(warehouseKeeperX,warehouseKeeperY,2)){
                warehouseKeeperX++;
            }
        }

        if(keypress.getCode() == KeyCode.DOWN){
            if(movementChecker(warehouseKeeperX,warehouseKeeperY,3)){
                warehouseKeeperY++;
            }
        }

        if(keypress.getCode() == KeyCode.LEFT){
            if(movementChecker(warehouseKeeperX,warehouseKeeperY,4)){
                warehouseKeeperX--;
            }
        }

        //Set the warehousekeeper's new coords
        setxAxis(warehouseKeeperX);
        setyAxis(warehouseKeeperY);

        //Add the shape to the new  and bring it forward
        GridPane.setConstraints(getTileShape(),getxAxis(),getyAxis());
        getTileShape().toFront();
    }

}
