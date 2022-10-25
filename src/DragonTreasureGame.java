import processing.core.PApplet;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import processing.core.PImage;
public class DragonTreasureGame extends PApplet {
    private ArrayList<Room>roomList;
    public static void main(String[] args) {
        
        PApplet.main("DragonTreasureGame");
        
    }

    @Override
    public void settings() {
        size(800, 600);
    }

    @Override
    public void setup() {
        roomList = new ArrayList<Room>();
        this.getSurface().setTitle("Dragon Treasure Adventure"); // sets the title of the window
        this.imageMode(PApplet.CORNER); //Images are drawn using the x,y-coordinate
        //as the top-left corner
        this.rectMode(PApplet.CORNERS); //When drawing rectangles interprets args
        //as top-left corner and bottom-right corner respectively
        this.focused = true; // window will be active upon running program
        this.textAlign(CENTER); // sets the text alignment to center
        this.textSize(20); //sets the font size for the text
        Room.setProcessing(this);
        PImage testimage = this.loadImage("src\\images\\2.jpg");
        roomList.add(new Room(1, "test text", testimage));

    }

    public void draw() {
        roomList.get(0).draw();
    }
}
