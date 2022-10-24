import processing.core.PApplet;

import processing.core.PApplet;
public class DragonTreasureGame extends PApplet {
    public static void main(String[] args) {
        
        PApplet.main("DragonTreasureGame");
    }

    @Override
    public void settings() {
        size(800, 600);
    }

    @Override
    public void setup() {
        this.getSurface().setTitle("Dragon Treasure Adventure"); // sets the title of the window
        this.imageMode(PApplet.CORNER); //Images are drawn using the x,y-coordinate
        //as the top-left corner
        this.rectMode(PApplet.CORNERS); //When drawing rectangles interprets args
        //as top-left corner and bottom-right corner respectively
        this.focused = true; // window will be active upon running program
        this.textAlign(CENTER); // sets the text alignment to center
        this.textSize(20); //sets the font size for the text
    }
}
