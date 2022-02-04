import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class hurtBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class hurtBox extends Actor
{
    /**
     * Act - do whatever the hurtBox wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public int alignment;
    
    public hurtBox(int x, int y, int align){
        GreenfootImage img = new GreenfootImage(x,y);
        img.setColor(Color.RED);
        img.fill();
        setImage(img);
        this.alignment = align;
    }
    
    public hurtBox(int r, int align){
        GreenfootImage img = new GreenfootImage(1,1);
        img.setColor(Color.RED);
        img.fill();
        setImage(img);
        this.alignment = align;
    }
    
    public void act()
    {
    }
}
