import javafx.scene.image.Image;

public class Diamond extends Tile {
//Diamond Class IS A Tile - Inheritance
    public Diamond(int x, int y){
        super(x,y,"Diamond",new Image("SokobanImages/Diamond.png"));

    }
}
