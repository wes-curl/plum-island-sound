import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Barrier here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Barrier extends notPC

{
    /**
     * Act - do whatever the Barrier wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    //public Barrier(){
        //GreenfootImage image = getImage();  
        //  image.scale(image.getWidth(), image.getHeight() + 250);
        //setImage(image);
        
    //}
    public void act()
    {
        
        
        //set up trueposition
        if(truePositionX == null){
            truePositionX = getX();
        }
        //place in the world
        MyWorld world = (MyWorld)getWorld();
        //place in the world
        GreenfootImage image = getImage(); 
        
        
        //hide if on edge
        if (Greenfoot.mouseClicked(this))
        {   
            getWorld().removeObject(this);
        }
        else if(getX() <= 0){
            getImage().setTransparency(0);
        } else if(getX() >= 999){
            getImage().setTransparency(0);
        } else {
            getImage().setTransparency(255);
        }
        
        
    }
}
