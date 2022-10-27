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

public class PortalRoom extends Room {
    private Random randGen; // random number generator for location picking
    private static final String PORTAL_WARNING = "You feel a distortion in space nearby.\n";
    private static final String TELEPORT_MESSAGE = "The space distortion teleported you to another room!\n";
    private static PImage portalImage; // image of a portal to be shown in all portal rooms

    /**
     * makes a new portal room
     * 
     * @param ID          of room
     * @param description of room
     * @param image       of room
     */
    PortalRoom(int ID, String description, processing.core.PImage image) {
        super(ID, description, image);
        randGen = new Random();

    }

    /**
     * getter
     * 
     * @return String warning
     */
    public static String getPortalWarning() {
        return PORTAL_WARNING;
    }

    /**
     * getter
     * 
     * @return String message
     */
    public static String getTeleportMessage() {
        return TELEPORT_MESSAGE;
    }

    /**
     * getter of random teleport descrijption
     * 
     * @return Room to teleport to
     */
    public Room getTeleportLocation() {
        ArrayList<Room> adjacent = getAdjacentRooms();
        return adjacent.get(randGen.nextInt(adjacent.size()));
    }

    /**
     * draws portal on iamge
     */
    @Override
    public void draw() {
        super.draw();
        processing.image(portalImage, 325, 225);
    }

    /**
     * sets portal iamge
     * 
     * @param portalImagein portal image
     */
    public static void setPortalImage(processing.core.PImage portalImagein) {
        portalImage = portalImagein;

    }
}
