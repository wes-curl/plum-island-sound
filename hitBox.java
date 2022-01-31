import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class hitBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class hitBox extends Actor
{
    /**
     * Act - do whatever the hitBox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public hitBox(){
        
    }
    
    private int alignment;
    private enemySoldier parent;
    
    public void act()
    {
        //if touching anything...
        java.util.List<Actor> touching = getIntersectingObjects(null);
        for(Actor a : touching){
            //is it a hurtbox of opposite alignment?
            /*if((a instanceof hurtBox) && a.alignment != alignment){
                //THEN PERISH
                parent.killCommand();
            }   */
        }
    }
}
