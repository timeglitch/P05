import processing.core.PApplet;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import processing.core.PImage;

public class TreasureRoom extends Room {
    private static final String TREASURE_WARNING = "You sense that there is treasure nearby.\n";
    private static PImage treasureBackground; // the image ALWAYS used for treasure rooms

    /**
     * creates a tresure room
     * set treasure image iwth static method
     * 
     * @param ID of room
     */
    public TreasureRoom(int ID) {
        super(ID, "In the back of this room, you spot a treasure chest. It is locked...", treasureBackground);

    }

    /**
     * getter
     * 
     * @return String warning
     */
    public static String getTreasureWarning() {
        return TREASURE_WARNING;
    }

    /**
     * check if player has key and is in room
     * 
     * @param p player to check
     * @return boolean result
     */
    public boolean playerCanGrabTreasure(Player p) {
        return p.hasKey() && p.getCurrentRoom().equals(this);
    }

    /**
     * setter
     * 
     * @param treasureBackgroundin image
     */
    public static void setTreasureBackground(processing.core.PImage treasureBackgroundin) {
        treasureBackground = treasureBackgroundin;
    }

}
