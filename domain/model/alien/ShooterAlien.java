package domain.model.alien;

import domain.model.alien.AlienBullet;
import domain.model.Player;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;

public class ShooterAlien extends Alien {

    private Player player;
    private ArrayList<AlienBullet> bulletList;
    private static Random rand = new Random();
    private static Timer timerForShooting = new Timer(); 

    public ShooterAlien(Player player)
    {
        super();
        this.player = player;
        this.alienType = 2;
        this.bulletList = new ArrayList<AlienBullet>();
        this.actionFrequency = new ArrayList<Integer>();
        this.actionFrequency.add(1000);
        this.alienTimerTask = new ArrayList<TimerTask>();
        TimerTask shootTask = new TimerTask(){
            @Override
            public void run()
            {
                shoot(player, bulletList);
                checkCollision(player);
            };
        };
        alienTimerTask.add(shootTask);
    }

    private void shoot(Player player, ArrayList<AlienBullet> bulletList)
    {
        AlienBullet bullet = new AlienBullet(this.xLocation, this.yLocation, player, bulletList);
        bulletList.add(bullet);
        int shootDirection = rand.nextInt(4);
        if (shootDirection == 0)
        {
            timerForShooting.scheduleAtFixedRate(bullet.getBulletMovementUp(), 0, 100);
        }
        else if (shootDirection == 1)
        {
            timerForShooting.scheduleAtFixedRate(bullet.getBulletMovementDown(), 0, 100);
        }
        else if (shootDirection == 2)
        {
            timerForShooting.scheduleAtFixedRate(bullet.getBulletMovementRight(), 0, 100);
        }
        else
        {
            timerForShooting.scheduleAtFixedRate(bullet.getBulletMovementLeft(), 0, 100);
        }
    }

    private void checkCollision(Player player)
    {
        int xDifference = Math.abs(player.getx() - this.getXLocation());
		int yDifference = Math.abs(player.gety() - this.getYLocation());

		if (xDifference <= 48*4 && yDifference <= 48*4)
		{
			if (!player.protectionVest)
            {
                player.totalLives -= 1;
            }
		}
    }

    public ArrayList<AlienBullet> getBulletList()
    {
        return this.bulletList;
    }
   


    
}


/* private boolean checkCollision(Player player)
	{
		int xDifference = Math.abs(player.getx() - this.getXLocation());
		int yDifference = Math.abs(player.gety() - this.getYLocation());

		if (xDifference <= 48*4 && yDifference <= 48*4)
		{
			if(player.protectionVest == false) {
			killPlayer(player);
			}
			return true;
			
		}
		else
		{
			return false;
		}
	}	private void killPlayer(Player player)
	{
		
		player.totalLives -= 1;
	
} */