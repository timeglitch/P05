import processing.core.PImage;

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Project 5
// Course:   CS 300 Fall 2022
//
// Author:   Frank Zhang
// Email:    fjzhang@wisc.edu
// Lecturer: Mouna Kacem
//


public class StartRoom extends Room{

    /**
     * makes new starting room
     * @param ID unique id
     * @param image of room
     */
    public StartRoom(int ID, PImage image) {
        super(ID, "You find yourself in the entrance to a cave holding treasure.", image);

    }
}
