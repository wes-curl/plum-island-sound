import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Blue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Blue extends enemySoldier
{
    private int speed = 2;
    private String state;
    private int range = 500;
    GreenfootImage baseImage = null;
    private boolean moving = true;
    private hitBox HB = null;
    
    public void act()
    {
        GreenfootImage image = getImage(); 
        MyWorld world = (MyWorld)getWorld();
        
        //set up trueposition
        if(truePositionX == null){
            truePositionX = getX();
        }
        
        //handle hitbox
        if(HB == null){
            HB = new hitBox(1, this);
            HB.getImage().setTransparency(100);
            world.addObject(HB, getX(), getY());
        } else {
            HB.setLocation(getX(), getY());
        }
        
        if(((MyWorld)getWorld()).active){
            if(state == "still"){
                
            } else if (state == "attacking"){
                int animateAttackSpeed = 100;
                moving = false;
                state = "attack in progress";
                Thread newThread = new Thread(() -> {
                    animate(new String[]
                    {"blue-attack-1.png","blue-attack-1.png","blue-attack-2.png","blue-attack-2.png","blue-attack-2.png"}, 
                    animateAttackSpeed, "none");
                    shootBullet();
                    animate(new String[] {"blue-reload-t.png","blue-reload-1.png","blue-reload-2.png","blue-reload-3.png","blue-reload-2.png",
                        "blue-reload-1.png","blue-reload-2.png","blue-reload-3.png","blue-reload-2.png",
                        "blue-reload-1.png","blue-reload-2.png","blue-reload-3.png","blue-reload-2.png"}, 
                    animateAttackSpeed, "start-moving");
                });
                newThread.start();
            } else if (state == "start-moving"){
                if(truePositionX != null){
                    //which direction is the player in?
                    if(Math.abs((truePositionX - world.playerX) - 500) < range){
                        state = "attacking";
                    } else {
                        animateWalking();
                        moving = true;
                    }
                }
            } else if(state == "animating"){
                
            } else {
                state = "start-moving";
            }
            if(moving){
                int direction = (int) Math.signum((truePositionX - world.playerX) - 500);
                truePositionX -= speed * direction;
            }
            
            
            setDirection();
        }
        
        //place in the world
        setLocation(truePositionX - world.playerX, world.floor - (image.getHeight() / 2));
        //hide if on edge
        if(getX() <= 0){
            getImage().setTransparency(0);
        } else if(getX() >= 999){
            getImage().setTransparency(0);
        } else {
            getImage().setTransparency(255);
        }
    }
    
    private void setDirection(){
        MyWorld world = (MyWorld)getWorld();
        int direction = (int) Math.signum((truePositionX - world.playerX) - 500);
        GreenfootImage image = null;
        
        if(baseImage == null){
            image = new GreenfootImage(getImage());
            baseImage = image;
        } else {
            image = new GreenfootImage(baseImage);  
        }
        
        //flip the sprite if it is facing in the wrong direction
        if(direction > 0){
            image.mirrorHorizontally();
            setImage(image);
        } else if(direction < 0){
            setImage(image);        
        }
    }
    
    private void setImageStable(String name){
        GreenfootImage image = new GreenfootImage(name);
        setImage(image);
        baseImage = image;
    }
    
    private void animateWalking(){
        int animateMoveSpeed = 200;
        Thread newThread = new Thread(() -> {
            animate(new String[]{"blue-walk-1.png","blue-walk-2.png","blue-walk-1.png","blue-walk-3.png"}, animateMoveSpeed, "start-moving");
        });
        newThread.start();
    }
    
    private void animate(String[] images, int animateSpeed, String nextState){
        state = "animating";
        for(int i = 0; i < images.length; i++){
            setImageStable(images[i]);
            try{
                Thread.sleep(animateSpeed);                    
            } catch(InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
        state = nextState;
    }
    
    private void shootBullet(){
        MyWorld world = (MyWorld)getWorld();
        int direction = (int) Math.signum((truePositionX - world.playerX) - 500);
        Bullet B = new Bullet(direction * -8, 1);
        world.addObject(B, getX() + (direction * -60), getY() - 70);
        B.truePositionX = truePositionX + (direction * -60);
    }
}
