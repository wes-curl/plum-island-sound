import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class enemySoldier here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class enemySoldier extends notPC
{
    /**
     * Act - do whatever the enemySoldier wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public static boolean active = false;
    public void killCommand(){
        
    }
    
    public void flip(){
        GreenfootImage image = new GreenfootImage(getImage());
        image.mirrorHorizontally();
        setImage(image);
    }

}
