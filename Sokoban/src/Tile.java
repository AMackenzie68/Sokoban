import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;


public abstract class Tile implements UserInterface{

    protected int xAxis;
    protected int yAxis;
    private String type;
    private Rectangle tileShape;

    public void setxAxis(int xAxis) {
        this.xAxis = xAxis;
    }

    public void setyAxis(int yAxis) {
        this.yAxis = yAxis;
    }

    public int getxAxis() {
        return xAxis;
    }

    public int getyAxis() {
        return yAxis;
    }

    public Tile(int x,int y, String tempType, Image sprite) {
        //Sets the coordinates the tile will display at
        xAxis = x;
        yAxis = y;
        //Sets the type of tile, the height and width, as well as the display image
        this.type = tempType;

        ImagePattern spriteTile = new ImagePattern(sprite); //Have to use ImagePattern instead of image, as it can handle the image being too small or large for the shape
        tileShape = new Rectangle();
        tileShape.setHeight(32);
        tileShape.setWidth(32);
        tileShape.setFill(spriteTile);
        setNodeType(tempType);
        GridPane.setConstraints(tileShape,xAxis,yAxis);
        //removes the gaps between rows in the gridpane, to make the map seem intact even after adding new elements
        //also adds the tile's rectangle to the grid
        mapPane.setHgap(0);
        mapPane.setVgap(0);
        mapPane.getChildren().add(tileShape);
    }

    public Tile(){
        //This is an example of Constructor Overloading
        xAxis = 0;
        yAxis = 0;
        type = "Ground";
        setNodeType(type);
        ImagePattern spriteTile = new ImagePattern(new Image("SokobanImages/Floor.png"));
        tileShape = new Rectangle();
        tileShape.setHeight(32);
        tileShape.setWidth(32);
        tileShape.setFill(spriteTile);
        GridPane.setConstraints(tileShape,xAxis,yAxis);
        mapPane.setHgap(0);
        mapPane.setVgap(0);
        mapPane.getChildren().add(tileShape);
    }

    public Rectangle getTileShape() {
        return tileShape;
    }

    public void setNodeType(String type){
        this.getTileShape().setId(type);
        //setId method found on oracle DOCS for Nodes.
        //Used as identifier for the different tile types
        // https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Node.html#setId-java.lang.String-
    }
}
