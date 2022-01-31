import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class sceneryObject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class sceneryObject extends notPC
{
    /**
     * Act - do whatever the sceneryObject wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public void act()
    {
        //set up trueposition
        if(truePositionX == null){
            truePositionX = getX();
        }
        //place in the world
        MyWorld world = (MyWorld)getWorld();
        setLocation(truePositionX - world.playerX, getY());
        //hide if on edge
        if(getX() <= 0){
            getImage().setTransparency(0);
        } else if(getX() >= 999){
            getImage().setTransparency(0);
        } else {
            getImage().setTransparency(255);
        }
    }
}
