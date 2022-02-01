import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class grenade here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class grenade extends notPC
{
    /**
     * Act - do whatever the grenade wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int xVelocity;
    private float yVelocity;
    private float gravity = 0.5f;
    
    public grenade(int x, int y){
        this.xVelocity = x;
        this.yVelocity = y;
    }
    
    public void act()
    {
        if(onFloor()){
            explode();
        } else {
            arc();
        }
    }
    
    private boolean onFloor(){
        return getY() >= ((MyWorld) getWorld()).floor;
    }
    
    private void explode(){
        ((MyWorld) getWorld()).removeObject(this);
    }
    
  
    
    private void arc(){
        setLocation(getX() + xVelocity, getY() + (int)yVelocity);
        yVelocity += gravity;
    }
}
