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
    private boolean dead = false;
    private boolean notDamaged = true;
    //how close the player has to be to take damage.
    private int damaging = 0;
    
    public grenade(int x, int y){
        this.xVelocity = x;
        this.yVelocity = y;
    }
    
    public void act()
    {
        //set up trueposition
        if(truePositionX == null){
            truePositionX = getX();
        }
        if(onFloor() && !dead){
            explode();
            dead = true;
        } else if (!dead){
            arc();
        } else {
            MyWorld world = ((MyWorld) getWorld());
            setLocation(truePositionX - world.playerX, getY());
            if(!getObjectsInRange(damaging, PC.class).isEmpty() && notDamaged){
                notDamaged = false;
                System.out.println("oof!");
            }
        }
        
        //hide if on edge
        if(getX() <= 0){
            getImage().setTransparency(0);
        } else if(getX() >= 999){
            getImage().setTransparency(0);
        } else {
            getImage().setTransparency(255);
        }
    }
    
    private boolean onFloor(){
        return (getY() >= ((MyWorld) getWorld()).floor);
    }
    
    private void explode(){
        Thread newThread = new Thread(() -> {
            animate();
        });
        newThread.start();
    }
    
    private void animate(){
        MyWorld world = ((MyWorld) getWorld());
        String[] images = new String[]{"explosion-1.png","explosion-2.png","explosion-3.png","explosion-4.png","explosion-5.png"};
        int[] radii = new int[]{12, 20, 28, 35, 38};
        for(int i = 0; i < images.length; i++){
            setImage(new GreenfootImage(images[i]));
            damaging = radii[i] + 30;
            try{
                Thread.sleep(50);                    
            } catch(InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
        world.removeObject(this);
    }
  
    
    private void arc(){
        MyWorld world = ((MyWorld) getWorld());
        yVelocity += gravity;
        truePositionX += xVelocity;
        setLocation(truePositionX - world.playerX, getY() + (int)yVelocity);
    }
}
