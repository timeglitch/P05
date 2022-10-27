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

    
    /** changes rooms
     * @param destination to go to
     * @return boolean is successful,
     */
    public boolean changeRoom(Room destination) {
        if(canMoveTo(destination)) {
            super.setCurrentRoom(destination);
            return true;
        }
        return false;
    }

    
    /** 
     * checks if dest is adjacent
     * @param destination to go to
     * @return boolean result
     */
    public boolean canMoveTo(Room destination) {
            return super.getAdjacentRooms().contains(destination);
    }
    
    
    /** 
     * picks random room
     * @return Room to move to
     */
    public Room pickRoom() {
        ArrayList<Room> adj = super.getAdjacentRooms();
        return adj.get(randGen.nextInt(adj.size()));
    }

    
    /** 
     * getter
     * @return String dragon warning
     */
    public static String getDragonWarning() {
        return DRAGON_WARNING;
    }

    
    /** 
     * getter
     * @return String to print out
     */
    public static String getDragonEncounter() {
        return DRAGON_ENCOUNTER;
    }
    
}
