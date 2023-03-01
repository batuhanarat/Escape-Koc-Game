package domain.model.alien;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;
import domain.model.Player;

public class AlienBullet {

    private TimerTask bulletMovementUp;
    private TimerTask bulletMovementDown;
    private TimerTask bulletMovementRight;
    private TimerTask bulletMovementLeft;

    private TimerTask disappearTask;

    private int xLocation;
    private int yLocation;

    private Player player;
    

    public AlienBullet (int xLocation, int yLocation, Player player, ArrayList<AlienBullet> bulletList)
    {
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.player = player;

        this.bulletMovementUp = new TimerTask(){
            @Override
            public void run()
            {
                moveUp();
            };
        };
        this.bulletMovementDown = new TimerTask(){
            @Override
            public void run()
            {
                moveDown();
            };
        };
        this.bulletMovementRight = new TimerTask(){
            @Override
            public void run()
            {
                moveRight();
            };
        };
        this.bulletMovementLeft = new TimerTask(){
            @Override
            public void run()
            {
                moveLeft();
            };
        };
    }
  
    private void moveUp()
    {
        this.yLocation -= 20;
    }

    private void moveDown()
    {
        this.yLocation += 20;
    }

    private void moveRight()
    {
        this.xLocation += 20;
    }

    private void moveLeft()
    {
        this.xLocation -= 20;
    }



    private void disappear(ArrayList<AlienBullet> bulletList)
    {
        bulletList.remove(0);
    }

    public TimerTask getBulletMovementUp() {
        return bulletMovementUp;
    }


    public TimerTask getBulletMovementDown() {
        return bulletMovementDown;
    }


    public TimerTask getBulletMovementRight() {
        return bulletMovementRight;
    }


    public TimerTask getBulletMovementLeft() {
        return bulletMovementLeft;
    }

    public TimerTask getDisappearTask()
    {
        return disappearTask;
    }


    public int getxLocation() {
        return xLocation;
    }


    public int getyLocation() {
        return yLocation;
    }
}
