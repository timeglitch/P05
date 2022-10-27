//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Project 5
// Course:   CS 300 Fall 2022
//
// Author:   Frank Zhang
// Email:    fjzhang@wisc.edu
// Lecturer: Mouna Kacem
//


import processing.core.PApplet;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import processing.core.PImage;

public class Character {
    private Room currentRoom; // current room the character is in
    private String label; // a label giving a basic description of the character

    /**
     * creates new character
     * 
     * @param currentRoom room to start in
     * @param label       describe character
     */
    public Character(Room currentRoom, String label) {
        this.currentRoom = currentRoom;
        this.label = label;
    }

    /**
     * getter
     * 
     * @return Room char is in
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * getter
     * 
     * @return String of label
     */
    public String getLabel() {
        return label;
    }

    /**
     * getter
     * 
     * @return ArrayList<Room> adjacent Rooms
     */
    public ArrayList<Room> getAdjacentRooms() {
        return currentRoom.getAdjacentRooms();
    }

    /**
     * moves character, setter
     * 
     * @param newRoom to move to
     */
    public void setCurrentRoom(Room newRoom) {
        currentRoom = newRoom;
    }

}
