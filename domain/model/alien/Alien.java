package domain.model.alien;

import java.awt.image.BufferedImageOp;
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;
import java.util.ArrayList;



public abstract class Alien {

	protected int alienType;
	protected ArrayList<TimerTask> alienTimerTask;
	protected ArrayList<Integer> actionFrequency;
	protected int xLocation;
	protected int yLocation;
	protected static Random rand = new Random();


	public Alien()
	{
		this.xLocation = rand.nextInt(1000);
		this.yLocation = rand.nextInt(600);
	}

	public ArrayList<TimerTask> getTimerTask()
	{
		return this.alienTimerTask;
	}

	public ArrayList<Integer> getActionFrequency()
	{
		return this.actionFrequency;
	}

    public int getAlienType()
	{
        return this.alienType;
    }

	public int getXLocation()
	{
		return this.xLocation;
	}

	public int getYLocation()
	{
		return this.yLocation;
	}
	
}

	
	
	
	
	