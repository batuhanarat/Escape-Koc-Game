package controller;


import domain.model.Player;
import domain.model.alien.*;
import domain.model.alien.TimeWastingAlienStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;

public class AlienController {

	private static AlienController alienControllerInstance = null;
	private AlienFactory alienFactory;
	private ArrayList<Alien> alienList;
	private Random rand = new Random();
	private Timer timerForAlienTasks;
	//public Building building;
	private static TimerTask generateAlienTask;
	private static TimerTask removeAlienTask;
	private BufferedImage shooterAlienImage = null;
	private BufferedImage blindAlienImage = null;
	private BufferedImage timeWastingAlienImage = null;
	public Boolean shot = false;
	private Player player;
	private Timer timerForRemoval;
	private Tile_Object_Handler tileObject;
	
	private AlienController(Player player, Tile_Object_Handler tile)
	{

		this.timerForAlienTasks = new Timer();
		this.timerForRemoval = new Timer();
		this.alienList = new ArrayList<Alien>();
		this.tileObject = tile;
		this.player = player;
		this.alienFactory = new AlienFactory(player,tile);
		getAlienImages();
		
		AlienController.generateAlienTask = new TimerTask() {
			@Override
			public void run()
			{
				alienList.add(generateRandomAlien());
			}
		};
		AlienController.removeAlienTask = new TimerTask() {
			@Override
			public void run() {
				resetAlienList();
				}
		};
	}
	
	public static AlienController getAlienController(Player player, Tile_Object_Handler tileObject)
	{
		if (alienControllerInstance == null)
		{
			alienControllerInstance = new AlienController(player,tileObject);
			alienControllerInstance.timerForAlienTasks.scheduleAtFixedRate(generateAlienTask, 10000, 10000);
		}
		return alienControllerInstance;
	}
	
	public Alien generateRandomAlien() {
		
		int alienType = rand.nextInt(3);
		Alien alien = alienFactory.GenerateAlien(1);
		alienList.add(alien);
		ArrayList<TimerTask> alienTimerTasks = alien.getTimerTask();
		ArrayList<Integer> actionFrequency = alien.getActionFrequency();
		if (alien.getAlienType() == 1)
		{
			timerForAlienTasks.scheduleAtFixedRate(alienTimerTasks.get(0), 0, actionFrequency.get(0));
			if (((TimeWastingAlien) alien).strategyGood)
			{
				timerForAlienTasks.scheduleAtFixedRate(alienTimerTasks.get(1), 0, actionFrequency.get(1));
			}
			else if (((TimeWastingAlien) alien).strategyConfusing)
			{
				timerForAlienTasks.schedule(alienTimerTasks.get(1), 2000);
			}
			else if (((TimeWastingAlien) alien).strategyBad)
			{
				timerForAlienTasks.schedule(alienTimerTasks.get(1), 1000);
			}
		}
		else
		{
			for (TimerTask alienTask : alienTimerTasks)
			{
				timerForAlienTasks.scheduleAtFixedRate(alienTask, 0, actionFrequency.get(alienTimerTasks.indexOf(alienTask)));
			}
		}
		return alien;
	}

	public void draw(Graphics2D g2) {
		

		if (!alienList.isEmpty())
		{
			for (Alien alien: alienList)
			{
				if (alien.getAlienType() == 0)
				{
					g2.drawImage(blindAlienImage, alien.getXLocation(), alien.getYLocation(), 48, 48, null);
				}
				else if (alien.getAlienType() == 1)
				{
					g2.drawImage(timeWastingAlienImage, alien.getXLocation(), alien.getYLocation(), 48, 48, null);
				}
				else
				{
					g2.drawImage(shooterAlienImage, alien.getXLocation(), alien.getYLocation(), 48, 48, null);
					if (!((ShooterAlien) alien).getBulletList().isEmpty())
					{
						for (AlienBullet bullet: ((ShooterAlien) alien).getBulletList())
						{
							g2.fillOval(bullet.getxLocation()+16, bullet.getyLocation()+16, 16, 16);
							//bullet.getDisappearTask().run();
							//g2.fillOval(bullet.getxLocation(), bullet.getyLocation(), 15, 15);
						}
					}
					
				}
			}
			
			
		}	
	}
	
	public ArrayList<Alien> getAlienList()
	{
		return alienList;
	}

	public void resetAlienList()
	{
		alienControllerInstance.alienList = new ArrayList<Alien>();
	}

	public void getAlienImages()
	{
		try
		{
			this.blindAlienImage = ImageIO.read(getClass().getResourceAsStream("/resources/aliens/blindalien.png"));
			this.timeWastingAlienImage = ImageIO.read(getClass().getResourceAsStream("/resources/aliens/timewastingalien.png"));
			this.shooterAlienImage = ImageIO.read(getClass().getResourceAsStream("/resources/aliens/shooteralien.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}

