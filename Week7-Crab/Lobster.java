import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.util.List;

/**
 * Write a description of class Lobster here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Lobster extends Animal
{
    private int speed;
    private Random generator = new Random();
    
    private CrabWorld world;
    
    private int rotateTimer;
    
    /**
     * This method looks to see if the crab is within a certain
     * distances and if so turns in that direction.  If not within
     * range the lobster makes a move in a random direction.
     * If the lobster touches the crab, the game ends.
     */
    public void act()
    {
        world = (CrabWorld)getWorld();
        speed = 2 + (int)(world.points / 80);
        if(getNeighbours(200 + world.points,true,Crab.class).size() > 0)
        {
            turnTowards(world.crab.getX(),world.crab.getY());
            if (isTouching(Crab.class))
            {
                removeTouching(Crab.class);
                world.loseGame();
            }
        }
        else
        {
            if (rotateTimer == 0)
            {
                turn(-60);
                turn(generator.nextInt(120));
                rotateTimer = 40;
            }
            else 
            {
                rotateTimer--;
            }
        }
        move(speed);
    }
}
