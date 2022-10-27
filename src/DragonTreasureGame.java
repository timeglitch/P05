import processing.core.PApplet;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.sound.sampled.Port;
import java.util.Random;
import processing.core.PImage;

public class DragonTreasureGame extends PApplet {
    private ArrayList<Room>roomList;
    private File roomInfo;
    private File mapInfo;
    private ArrayList<Character>characters;
    private boolean isDragonTurn;
    private int gameState;
    public static void main(String[] args) {
        
        PApplet.main("DragonTreasureGame");
        
    }

    @Override
    public void settings() {
        size(800, 600);
    }

    @Override
    public void setup() {
        //init variables
        roomList = new ArrayList<Room>();
        roomInfo = new File("src\\roominfo.txt");
        mapInfo = new File("src\\map.txt");
        characters = new ArrayList<Character>();
        isDragonTurn = false;
        gameState = 0;

        //init pApplet
        this.getSurface().setTitle("Dragon Treasure Adventure"); // sets the title of the window
        this.imageMode(PApplet.CORNER); //Images are drawn using the x,y-coordinate
        //as the top-left corner
        this.rectMode(PApplet.CORNERS); //When drawing rectangles interprets args
        //as top-left corner and bottom-right corner respectively
        this.focused = true; // window will be active upon running program
        this.textAlign(CENTER); // sets the text alignment to center
        this.textSize(20); //sets the font size for the text
        Room.setProcessing(this);
        //image loading
        PortalRoom.setPortalImage(this.loadImage("src\\images\\portal.png"));
        TreasureRoom.setTreasureBackground(this.loadImage("src\\images\\treasure.jpg"));
        loadRoomInfo();
        loadMap();
        loadCharacters();

        /*
        PImage testimage = this.loadImage("src\\images\\2.jpg");
        TreasureRoom.setTreasureBackground(testimage);
        roomList.add(new PortalRoom(4, "this is the portalroom", testimage));
        roomList.add(new TreasureRoom(3));
        roomList.add(new StartRoom(2, testimage));
        roomList.add(new Room(1, "test text", testimage));*/
    }

    public void draw() {
        //characters.get(2).getCurrentRoom().draw();
        Player p;
        Dragon d;
        Character k;
        int a = 0;
        int b = 0;
        int c = 0;
        for(int i = 0; i < characters.size(); i++) {
          if(characters.get(i) instanceof Player) {
             a = i;
          }
          if(characters.get(i) instanceof Dragon){
            b = i;
          }
          c = i;
        }
        p = (Player)characters.get(a);
        d = (Dragon)characters.get(b);
        k = characters.get(c);



        Room current = p.getCurrentRoom();
        ArrayList<Room> adj = current.getAdjacentRooms();
        current.draw();
        //check for nearby warnings
        if (p.isPortalNearby()) {
          System.out.println(PortalRoom.getPortalWarning());
        }
        if (p.isTreasureNearby()) {
          System.out.println(TreasureRoom.getTreasureWarning());
        }
        if (p.isDragonNearby(d)) {
          System.out.println(Dragon.getDragonWarning());
        }
        if(p.getCurrentRoom().equals(k.getCurrentRoom()) && p.hasKey()) {
          p.obtainKey();
          System.out.println("KEY OBTAINED");
        }
        if(p.teleport()) {
          p.setCurrentRoom(((PortalRoom)p.getCurrentRoom()).getTeleportLocation());
        }

        if(isDragonTurn) {
          Room picked = d.pickRoom();
          if(d.canMoveTo(picked)) {
            d.changeRoom(picked);
            isDragonTurn = false;
          }
        }

        if(p.getCurrentRoom() instanceof TreasureRoom && p.hasKey()) {
          gameState = 1;
          System.out.println("You Win");
        }
        if(p.getCurrentRoom().equals(d.getCurrentRoom())) {
          gameState = 2;
          System.out.println(Dragon.getDragonWarning());
          System.out.println("You Lose");

        }
    }

    @Override
    public void keyPressed() {
      Player p;
      Dragon d;
      Character k;
      int a = 0;
      int b = 0;
      int c = 0;
      for(int i = 0; i < characters.size(); i++) {
        if(characters.get(i) instanceof Player) {
            a = i;
        }
        if(characters.get(i) instanceof Dragon){
          b = i;
        }
        c = i;
      }
      p = (Player)characters.get(a);
      d = (Dragon)characters.get(b);
      k = characters.get(c);

      if (gameState != 0) { 
        return;
      }
      Room matchRoom = new Room(key, null, null);
      int movetoindex = roomList.indexOf(matchRoom);
      if(movetoindex < 0) {
        System.out.println("Pick a valid room");;
      }

      Room destination = roomList.get(movetoindex);
      
      if(p.canMoveTo(destination)) {
        p.setCurrentRoom(destination);
        isDragonTurn = true;

      }

    }

     /** Loads in room info using the file stored in roomInfo
   *  @author Michelle 
   */
  private void loadRoomInfo() {
    System.out.println("Loading rooms...");
    Scanner fileReader = null;
    try {
      
      //scanner to read from file
      fileReader= new Scanner(roomInfo);
      
      //read line by line until none left
      while(fileReader.hasNext()) {
        String nextLine = fileReader.nextLine();
        
        //parse info and create new room 
        String[] parts = nextLine.split(" \\| ");
        int ID = Integer.parseInt(parts[1].trim()); //get the room id
        String imageName = null;
        String description = null;
        PImage image = null;
        Room newRoom = null;
        
        if(parts.length >= 3) {
          imageName = parts[2].trim();
          image = this.loadImage("images" + File.separator + imageName);
         
        }
        
        if(parts.length == 4) {
          description = parts[3].trim(); //get the room description
        }
   
        switch(parts[0].trim()) {
          case "S":
            newRoom = new StartRoom(ID, image);
            break;
          case "R":
            newRoom = new Room(ID, description, image);
            break;
          case "P":
            newRoom = new PortalRoom(ID, description, image);
            break;
          case "T":
            newRoom = new TreasureRoom(ID);
            break;
          default:
            break;
        }  
        
        if(newRoom != null) {
          roomList.add(newRoom);
        }
      }
    }catch(IOException e) { //handle checked exception
      e.printStackTrace();
    }finally {
      if(fileReader != null)
        fileReader.close(); //close scanner regardless of what happened for security reasons :)
    }
  }
  
  /** Loads in room connections using the file stored in mapInfo
   *  @author Michelle 
   */
  private void loadMap() {
    System.out.println("Loading map...");
    Scanner fileReader = null;
    try {
          //scanner to read from file
          fileReader= new Scanner(mapInfo);
          
        //read line by line until none left
          while(fileReader.hasNext()) {
            
            //parse info
            String nextLine = fileReader.nextLine();
            String parts[] = nextLine.split(" ");
            int id = Integer.parseInt(parts[0]);
            
            Room toEdit = getRoomByID(id); //get the room we need to update info for adjacent rooms
            
            //add all the rooms to the adj room list of toEdit
            for(int i=1; i<parts.length; i++) {
              Room toAdjAdd = getRoomByID(Integer.parseInt(parts[i]));
              toEdit.addToAdjacentRooms(toAdjAdd);
            }
          }
        }catch(IOException e) { //handle checked exception
          e.printStackTrace();
        }finally {    //close scanner regardless of what happened for security reasons :)
          if(fileReader != null)
            fileReader.close();
        }
  }
  
  /**
   * Get the room objected associated with the given ID.
   * @param id the ID of the room to retrieve
   * @return the Room that corresponds to that id
   * @author Michelle
   */
  private Room getRoomByID(int id){
    int indexToEdit = roomList.indexOf(new Room(id,"dummy", null));
    Room toEdit = roomList.get(indexToEdit); 
    return toEdit;
  }

  private void loadCharacters() {
    System.out.println("Adding characters...");
    characters.add(new Character(getRoomByID(5),"KEYHOLDER"));
    characters.add(new Player(getRoomByID(1)));
    characters.add(new Dragon(getRoomByID(9)));
  }
}
