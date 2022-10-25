import processing.core.PApplet;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import processing.core.PImage;

public class Room {
    private String description; //verbal description of the room
    private ArrayList<Room> adjRooms; //list of all rooms directly connect
    private final int ID; // a "unique" identifier for each room
    protected static PApplet processing; //PApplet object which the rooms will use to
    //draw stuff to the GUI
    private PImage image; //stores the image that corresponds to the background of a room

    public Room(int ID,  String description,  processing.core.PImage image) {
        this.ID = ID;
        this.description = description;
        this.image = image;
        adjRooms = new ArrayList<Room>();

    }

     public int getId() {
        return ID;
     }

     public String getDescription() {
        return description;
     }

     public ArrayList<Room> getAdjacentRooms() {
        return adjRooms;
     }
    
     public static void setProcessing(processing.core.PApplet processingin) {
        processing = processingin;
     }

     public void addToAdjacentRooms(Room toAdd) {
        adjRooms.add(toAdd);
     }

     public boolean isAdjacent(Room r) {
        return adjRooms.contains(r);
     }

     public boolean equals(Object other) {
        return (other instanceof Room && ((Room) other).getId() == ID);
     }

     public String roomString() {
        return Integer.toString(ID) + ": " + description;
     }

     @Override
     public String toString() {
        String out = "";
        out = out + this.roomString() + "\n Adjacent Rooms:";
        for (int i = 0; i < adjRooms.size(); i++) {
            out = out + " <" + adjRooms.get(i).getId() + ">";
        }
        return out;
     }

     public void draw() {
        processing.image(image, 0, 0);
        processing.fill(-7082);
        processing.rect(0, 500, 800, 600);
        processing.fill(0);
        processing.text(toString(), 300, 525);
     }
     

}
