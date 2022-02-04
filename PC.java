import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Enumeration;

/**
 * Write a description of class PC here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PC extends Actor
{
    /**
     * Act - do whatever the PC wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    float xVelocity = 0;
    float yVelocity = 0;
    int timesJumped = 0;
    int dashes = 2;
    public void act()
    {
        int groundHeight = ((MyWorld) getWorld()).floor - getImage().getHeight() / 2;
        // handles dissmissing popups
        if(getKeyUp("enter")){
            ((MyWorld) getWorld()).makeNextPopup();
        } 
        // handles player movement left/right
        //get user input
        if(((MyWorld) getWorld()).active){
            float speed = 1.5f;
            float gravity = 1.2f;
            if(Greenfoot.isKeyDown("left")){
                xVelocity -= speed; 
                if(xVelocity < -10){
                    xVelocity = -10;
                }
            } else if(Greenfoot.isKeyDown("right")){
                xVelocity += speed;
                if(xVelocity > 10){
                    xVelocity = 10;
                }
            } else {
                if(xVelocity > speed){
                    xVelocity -= speed;
                } else if(xVelocity < -speed){
                    xVelocity += speed;
                } else {
                    xVelocity = 0;
                }
            }
            
            //apply gravity
            if(getY() < groundHeight){
                yVelocity += gravity;
            } else {
                timesJumped = 0;
                dashes = 2;
                yVelocity = 0;
            }
            
            float jumpPower = 22f;
            int maxJumps = 2;
            if(getKeyDown("space")){
                if(timesJumped < maxJumps){
                    yVelocity = -jumpPower;
                    timesJumped++;
                }
            }
        
        //if touching anything...
        java.util.List<Actor> touching = getIntersectingObjects(null);
        for(Actor a : touching){
            //is it a hurtbox of alignment 1? (enemy?)
            if((a instanceof hurtBox) && ((hurtBox) a).alignment != 1){
                //THEN PERISH
                System.out.println("ouch!");
            }   
            if(a instanceof Barrier){
               if (Greenfoot.isKeyDown("right"))
               {
                   //System.out.println("barrier -> going right");
                   setLocation(getX() - 5, getY());
               }
               else if (Greenfoot.isKeyDown("left"))
               {
                   setLocation(getX() + 5, getY());
               }
            } 
        }
    }
}
    
    //returns true if a key has been released this frame
    Hashtable<String, Boolean> keyDownTable = new Hashtable<String, Boolean>();
    private boolean getKeyUp(String key){
        return ((keyDownTable.get(key) != null) && (keyDownTable.get(key))) && !Greenfoot.isKeyDown(key);
    }
    
    //returns true if a key has been pressed this frame
    private boolean getKeyDown(String key){
        return ((keyDownTable.get(key) != null) && !(keyDownTable.get(key))) && Greenfoot.isKeyDown(key);
    }
    
    // gets if a key has been pressed more than delay frames ago
    Hashtable<String, Integer> delayTable = new Hashtable<String, Integer>();
    Integer delay = 10;
    private boolean getDelayKey(String key){
        if(getKeyUp(key)){
            if(delayTable.get(key) == null){
                delayTable.put(key, delay);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    
    //updates the delaytable and they keyup table
    private void updateTable(){
        Enumeration<String> keys = delayTable.keys();
        while(keys.hasMoreElements()){
            String key = keys.nextElement();
            Integer value = delayTable.get(key);
            if(value > 1){
                delayTable.put(key, value - 1);
            } else {
                delayTable.remove(key);
            }
        }
        
        String[] observedKeys = {"space", "enter"};
        //reset the keyup table
        keyDownTable = new Hashtable<String, Boolean>();
        //set every key that is down to true
        for(int i = 0; i< observedKeys.length; i++){
            keyDownTable.put(observedKeys[i], Greenfoot.isKeyDown(observedKeys[i]));
        }
    }
}
