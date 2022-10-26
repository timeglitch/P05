import processing.core.PApplet;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import processing.core.PImage;

public class Character {
    private Room currentRoom; //current room the character is in
    private String label; //a label giving a basic description of the character

    public Character(Room currentRoom, String label) {
        this.currentRoom = currentRoom;
        this.label = label;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public String getLabel() {
        return label;
    }

    public ArrayList<Room> getAdjacentRooms() {
        return currentRoom.getAdjacentRooms();
    }

    public void setCurrentRoom(Room newRoom) {
        currentRoom = newRoom;
    }



    
}
