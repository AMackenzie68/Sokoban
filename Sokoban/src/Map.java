import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Map implements UserInterface{

    private int level;
    private ArrayList<String> FileRows = new ArrayList<>();
    private ArrayList<Tile> MapRows = new ArrayList<>();
    private WarehouseKeeper warehouseKeeper;

    public Map(int tempMapNo){
        ReadMap(tempMapNo);
    }

    public int getLevel() {return level;}

    public WarehouseKeeper getWarehouseKeeper() {return warehouseKeeper;}

    private void ReadMap(int tempLevel){
        //Reads the required map file and stores the result in an String Arraylist.
        String file;
        level = tempLevel;

        switch(level){
            //Sets the filepath for the map based on level number
            case 1: file = "src/SokobanLevels/level1.txt"; break;
            case 2: file = "src/SokobanLevels/level2.txt"; break;
            case 3: file = "src/SokobanLevels/level3.txt"; break;
            case 4: file = "src/SokobanLevels/level4.txt"; break;
            case 5: file = "src/SokobanLevels/level5.txt"; break;
            case 6: file = "src/SokobanLevels/FileNotFoundTest.txt"; break;
            default: file = "src/SokobanLevels/level1.txt";
        }

        try{
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedFileReader = new BufferedReader(fileReader);

            String FileRow = bufferedFileReader.readLine();

            while(FileRow != null){
                FileRows.add(FileRow);
                FileRow = bufferedFileReader.readLine();
            }
            bufferedFileReader.close();

        }catch (FileNotFoundException e){
            System.out.println("File not found at: " + file);
            System.out.println("Check File System");
            e.printStackTrace();
            AlertBox.display("Level File Not Found","The level files could not be found, please re-download the program and try again" );
            System.exit(1);
        } catch (IOException e) {
            System.out.println("IO Exception");
            System.out.println("Check Files");
            e.printStackTrace();
            AlertBox.display("Level File Not Found","The level files could not be accessed, please re-download the program and try again" );
            System.exit(2);
        }
        createMap();
    }

    private void createMap(){
        //Creates a Tile Arraylist based on the characters found in the FileRows String Arraylist
        int mapX;
        int mapY = 0;

        for(int i = 0; i < FileRows.size(); i++){
            //Cycles through the y-axis (rows) of the map/file
            mapX = 0;
            String row = FileRows.get(i);
                    for (int j = 0; j < FileRows.get(i).length();j++){
                        //Cycles through the x axis (columns/characters) of the map
                        String fileCharacter = FileRows.get(i).substring(j,j+1);
                        //Switch to check the character at each point in the file, and create the appropriate objects in the map Arraylist
                        switch(fileCharacter){
                            case "@":   warehouseKeeper = new WarehouseKeeper(mapX,mapY);
                                        MapRows.add(warehouseKeeper);
                                        break;
                            case "*":   Crate newCrate = new Crate(mapX,mapY);
                                        gameTrack.addCrates(newCrate);
                                        MapRows.add(newCrate);
                                        break;

                            case " ":   Ground newFloor = new Ground(mapX,mapY);
                                        MapRows.add(newFloor);
                                        break;

                            case "X":   Wall newWall = new Wall(mapX,mapY);
                                        MapRows.add(newWall);
                                        break;

                            case ".":   Diamond newDiamond = new Diamond(mapX,mapY);
                                        gameTrack.addDiamonds(newDiamond);
                                        MapRows.add(newDiamond);
                                        break;

                            default: MapRows.add(new Ground (mapX,mapY));
                        }
                        mapX++;
                    }
         mapY++;
        }
    }

    public int getMapHorizontal(){
        //Width of the map found using the length of one line from the map file
        //Multiplied to account for the size of the tile image
        return ((FileRows.get(1).length()) * 33);
    }

    public int getMapVertical(){
        //Height of the map found using number of lines in the map file
        //Multiplied to account for the size of the tile image
        return ((FileRows.size() +1) * 32);
    }

}
