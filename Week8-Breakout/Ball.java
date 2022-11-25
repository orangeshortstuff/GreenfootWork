import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Move the ball around the screen using the 
 * inbuilt turtle graphics.  Ball is a rectangle
 * drawn as an image.
 * 
 * @author Derek Peacock 
 * @version 0
 */
public class Ball extends ShapeSprite
{
    private GameWorld game;
    
    // Current velocity change in x and y
    
    private int dx;
    private int dy;
    
    public Ball(int width, int height)
    {
        super(Shapes.Oval, width, height);
        
        dx = speed; dy = speed;
        turn(45);
    }
    
    /**
     * if the ball has hit the edge of the world, or
     * has hit the paddle or a brick then reverse direction
     * of the ball and remove any brick that has been hit
     */
    public void act()
    {
        game = (GameWorld)getWorld();
        
        int x = getX(); int y = getY();
        
        checkCollisions();
        
        if (game.bricksLeft == 0) 
        {
            game.rowsNextRound++;
            game.endGame(true);
            setLocation(100 - dx, y + dy);
        }
        else
            setLocation(x + dx, y + dy);
    }
    
    private void checkCollisions()
    {
        if(getOneIntersectingObject(Brick.class) != null)
        {
            removeTouching(Brick.class);
            game.increaseScore();
            dx = -dx;
        }
        
        if(getOneIntersectingObject(Paddle.class) != null)
        {
            dx = -dx;
        }
        
        if (this.getX() + dx < 0)
        {
            game.endGame(false);
        }
            
        if (this.getY() + dy > game.getHeight() || this.getY() + dy < 0) 
        {
            dy = -dy;
        }
            
        if (this.getX() + dx > game.getWidth()) 
        {
            dx = -dx;
        }
    }
}
