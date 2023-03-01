package domain.model.alien;

import domain.model.Player;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class BlindAlien extends Alien {
	
	
	private Player player;
	
	public BlindAlien(Player player) {
		
		super();
		this.player = player;
		this.alienType = 0;
		this.actionFrequency = new ArrayList<Integer>();
		this.actionFrequency.add(2000);
		this.actionFrequency.add(500);
		this.alienTimerTask = new ArrayList<TimerTask>();
		TimerTask movementTask = new TimerTask(){
			@Override
			public void run()
			{
				move();
			};
		};
		TimerTask killPlayerTask = new TimerTask(){
			@Override
			public void run()
			{
				checkCollision(player);
			};
		};
		alienTimerTask.add(movementTask);
		alienTimerTask.add(killPlayerTask);
	}
	
	private void move()
	{
		int moveDirection = rand.nextInt(4);
		if (moveDirection == 0)
		{
			moveUp();
		}
		else if (moveDirection == 1)
		{
			moveDown();
		}
		else if (moveDirection == 2)
		{
			moveRight();
		}
		else 
		{
			moveLeft();
		}
	}

	private boolean checkCollision(Player player)
	{
		int xDifference = Math.abs(player.getx() - this.getXLocation());
		int yDifference = Math.abs(player.gety() - this.getYLocation());

		if (xDifference <= 48 && yDifference <= 48)
		{
			killPlayer(player);
			return true;
			
		}
		else
		{
			return false;
		}
	}

	private void killPlayer(Player player)
	{
		
			player.totalLives -= 1;
		
	}
	
	
	
	public void moveRight() {
		if (this.xLocation + 48 > 1100) {
			this.xLocation += 48;
		}
	
	}
	
	public void moveLeft() {
		if (this.xLocation - 48 > 0) {
			this.xLocation -= 48;
		}
	
	}
	
	public void moveUp() {
		if (this.yLocation - 48 > 0 ) {
			this.yLocation -= 48;
		}
	
	}
	
	public void moveDown() {
		if (this.yLocation + 48 < 600) {
			this.yLocation += 48;
		}
		
	}
	


}
