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

public class Room {
   private String description; // verbal description of the room
   private ArrayList<Room> adjRooms; // list of all rooms directly connect
   private final int ID; // a "unique" identifier for each room
   protected static PApplet processing; // PApplet object which the rooms will use to
   // draw stuff to the GUI
   private PImage image; // stores the image that corresponds to the background of a room

   /**
    * creates room
    * 
    * @param ID          unique id
    * @param description text descripiton
    * @param image       to show
    */
   public Room(int ID, String description, processing.core.PImage image) {
      this.ID = ID;
      this.description = description;
      this.image = image;
      adjRooms = new ArrayList<Room>();

   }

   /**
    * getter
    * 
    * @return int ID
    */
   public int getID() {
      return ID;
   }

   /**
    * getter
    * 
    * @return String description
    */
   public String getDescription() {
      return description;
   }

   /**
    * getter
    * 
    * @return ArrayList<Room> adjacent rooms
    */
   public ArrayList<Room> getAdjacentRooms() {
      return adjRooms;
   }

   /**
    * sets processing for all rooms
    * 
    * @param processingin processing
    */
   public static void setProcessing(processing.core.PApplet processingin) {
      processing = processingin;
   }

   /**
    * adds room to adj rooms
    * 
    * @param toAdd room to add
    */
   public void addToAdjacentRooms(Room toAdd) {
      adjRooms.add(toAdd);
   }

   /**
    * checks if room is adjacent
    * 
    * @param r room to check
    * @return boolean result
    */
   public boolean isAdjacent(Room r) {
      return adjRooms.contains(r);
   }

   /**
    * checks if other room id is the same
    * 
    * @param other room to check
    * @return boolean result
    */
   public boolean equals(Object other) {
      return (other instanceof Room && ((Room) other).getID() == ID);
   }

   /**
    * prints room id and descripotion
    * 
    * @return String output
    */
   private String roomString() {
      return Integer.toString(ID) + ": " + description;
   }

   /**
    * prints room and all adjacent rooms
    * 
    * @return String output
    */
   @Override
   public String toString() {
      String out = "";
      out = out + this.roomString() + "\n Adjacent Rooms:";
      for (int i = 0; i < adjRooms.size(); i++) {
         out = out + " <" + adjRooms.get(i).getID() + ">";
      }
      return out;
   }

   /**
    * draws image and text for room
    */
   public void draw() {
      processing.image(image, 0, 0);
      processing.fill(-7082);
      processing.rect(0, 500, 800, 600);
      processing.fill(0);
      processing.text(toString(), 300, 525);
   }

}
