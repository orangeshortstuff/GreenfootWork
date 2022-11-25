import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class sets up the world for Breakout
 * with a paddle, a ball and some bricks
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameWorld extends World
{
    private static final int SCREEN_WIDTH = 600;
    private static final int SCREEN_HEIGHT = 400;
    
    private Paddle paddle;
    private Ball ball;
    
    private Counter score;
    
    
    protected int rowsNextRound = 1;
    protected int bricksLeft = rowsNextRound * 5;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public GameWorld()
    {    
        super(SCREEN_WIDTH, SCREEN_HEIGHT, 1); 
        
        paddle = new Paddle(10, 80);
        ball = new Ball(20,20);
        
        addObject(paddle, 10, 200);
        addObject(ball, 100, 200);
        
        score = new Counter("Score: ");
        addObject(score, 60, 20);
        
        setupBricks();
    }
    
    public void endGame(boolean winGame)
    {
        if(winGame)
        {
            if (rowsNextRound > 3)
            {
                showText("Game Over: You Won!",300, 200);
            }
            else
                setupBricks();
        }
        else
            showText("Game Over: You Lost!",300, 200);
    }
    
    private void setupBricks()
    {
        for(int i = 0; i < rowsNextRound; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                Brick brick = new Brick(20,60);
                addObject(brick, 560-(40*i), 40+(80*j));
            }
        }
        bricksLeft = rowsNextRound * 5;
    }
    
    public void increaseScore()
    {
        score.add(10);
        bricksLeft--;
    }
}
