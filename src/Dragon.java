import processing.core.PApplet;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Scanner;
import java.util.Random;
import processing.core.PImage;

public class Dragon extends Character implements Moveable {

    private Random randGen; //random num generator used for moving
    private static final String DRAGON_WARNING = "You hear a fire breathing nearby!\n";
    private static final String DRAGON_ENCOUNTER = "Oh no! You ran into the fire breathing dragon!\n";

    Dragon(Room currentRoom) throws IllegalArgumentException{
        super(currentRoom, "DRAGON");
        randGen = new Random();
        if (!(currentRoom instanceof TreasureRoom)) {
            throw new IllegalArgumentException("The Dragon must start in a treasure room");
        }
    }

    public boolean changeRoom(Room destination) {
        if(canMoveTo(destination)) {
            super.setCurrentRoom(destination);
            return true;
        }
        return false;
    }

    public boolean canMoveTo(Room destination) {
            return super.getAdjacentRooms().contains(destination);
    }
    
    public Room pickRoom() {
        ArrayList<Room> adj = super.getAdjacentRooms();
        return adj.get(randGen.nextInt(adj.size()));
    }

    public static String getDragonWarning() {
        return DRAGON_WARNING;
    }

    public static String getDragonEncounter() {
        return DRAGON_ENCOUNTER;
    }
    
}
