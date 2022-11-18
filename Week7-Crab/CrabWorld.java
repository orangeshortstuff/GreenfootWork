import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 */
public class CrabWorld extends World
{
    public static final int MAXN_WORMS = 20;
    
    protected Crab crab;
    private Lobster lobster;
    
    private Worm[] worms;
    private int wormSize = 30;
    private int remainingWorms = MAXN_WORMS;
    
    private Random generator = new Random();
    private Counter score;
    protected int points;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public CrabWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        
        crab = new Crab();
        addObject(crab, generator.nextInt(getWidth()), generator.nextInt(getHeight()));
        
        lobster = new Lobster();
        addObject(lobster, generator.nextInt(getWidth()), generator.nextInt(getHeight()));
        
        worms = new Worm[MAXN_WORMS];
        addWorms();
        
        setupScore();
    }
    
    /**
     * Add MAXN_WORMS to the world in random positions
     */
    public void addWorms()
    {
        while (remainingWorms > 0)
        {
            createWorm();
            remainingWorms--;
        }
        remainingWorms = MAXN_WORMS;
    }
    
    /**
     * This method creates & adds a single worm to a random position in
     * the world that is not too near the edge of the screen
     */
    private void createWorm()
    {
        Worm worm = new Worm();
        
        int x = generator.nextInt(getWidth());
        int y = generator.nextInt(getHeight());
        
        addObject(worm, x, y);
    }
    
    public void score()
    {
        points++;
        remainingWorms--;
        if (remainingWorms == 0)
        {
            if (points == 100)
            {
                removeObject(lobster);
                winGame();
            }
            else 
            {
                remainingWorms = MAXN_WORMS;
                addWorms();
            }
        }
        setupScore();
    }
    
    private void setupScore()
    {
        score = new Counter("Score: " + points);
        addObject (score, 60, 30);
    }
    
    public void loseGame()
    {
        showText("Game Over: You have Lost!", 400, 300);
    }
    
   public void winGame()
    {
        showText("Game Over: You have Won!", 400, 300);
    }    
}
