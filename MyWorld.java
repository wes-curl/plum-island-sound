import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.LinkedList;
import java.util.Queue;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    GreenfootImage textArea;
    public int playerX = 0;
    public int floor = 512;
    public MyWorld()
    {    
        // Create a new world with 1000x600 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1); 
        prepare();
        
        //makePopups("Text can be really long. Overflow text may be a serious \nissue the program needs to be able to handle.", "This is something else");
    }
    
    int boxX = 200;
    int boxY = 30;
    
    int textX = 210;
    int textY = 35;
    
    Queue<String> popupQueue = new LinkedList<>();
    
    //displays some number of popups
    public void makePopups(String... text){
        for (String message : text) {
              popupQueue.add(message);
        }  
        makeNextPopup();
    }
    
    // shows a popup text box
    public void makeNextPopup()
    {
        String text = popupQueue.remove();
        dismiss();
        //make the text into an image
        GreenfootImage textImage = new GreenfootImage(text, 28, null, null);
        //make the text background be a thing
        GreenfootImage textBackground = new GreenfootImage("text-background.png");
        
        //copy the background to be restored after
        textArea = new GreenfootImage(textBackground);
        textArea.drawImage(getBackground(), -boxX, -boxY);
        //draw the text on the background
        getBackground().drawImage(textBackground, boxX, boxY);
        getBackground().drawImage(textImage, textX, textY);
    }
 
    // method to remove text image
    public void dismiss()
    {
        if (textArea != null){
            //puts the beackground back on top
            getBackground().drawImage(textArea, boxX, boxY);
            //if there is something else to display, display it
            if(popupQueue.peek() != null){
                makeNextPopup();
            }
        }
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        PC pC = new PC();
        addObject(pC,280,344);
        pC.setLocation(500,512);
        sceneryObject sceneryObject = new sceneryObject();
        addObject(sceneryObject,922,499);
        sceneryObject sceneryObject2 = new sceneryObject();
        addObject(sceneryObject2,238,507);
        Yellow yellow = new Yellow();
        addObject(yellow,351,406);
        pC.setLocation(505,478);
        removeObject(yellow);
        Blue blue = new Blue();
        addObject(blue,127,406);
        Yellow yellow2 = new Yellow();
        addObject(yellow2,761,410);
        Red red = new Red();
        addObject(red,267,415);
    }
}
