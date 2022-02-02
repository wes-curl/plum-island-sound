import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class DialogBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DialogBox extends Actor
{
    /**
     * Act - do whatever the DialogBox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    TextHolder TH;
    public void setUpDialogBox(String text){
        TH = new TextHolder();
        GreenfootImage textImage = new GreenfootImage(text, 24, null, null);
        TH.setImage(textImage);
        MyWorld world = (MyWorld)getWorld();
        world.addObject(TH, getX(), getY());
    }
    
    public void act()
    {
        // Add your action code here.
    }
    
    public void destroy(){
        MyWorld world = (MyWorld)getWorld();
        world.removeObject(TH);
        world.removeObject(this);
    }
}
