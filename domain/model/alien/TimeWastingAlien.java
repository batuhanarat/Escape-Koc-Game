package domain.model.alien;

import java.util.TimerTask;

import controller.ObjectHandler;
import controller.Tile_Object_Handler;
import domain.model.Player;

import java.util.ArrayList;

public class TimeWastingAlien extends Alien {
	static TimeWastingAlienStrategy timeWastingAlienStrategy ;
	TimeWastingAlienStrategy timeWastingAlienStrategy2;
	TimeWastingAlienStrategy timeWastingAlienStrategy3;

	public boolean strategyGood;
	public boolean strategyConfusing;
	public boolean strategyBad;
	
	
	Player player;
	Tile_Object_Handler objectHandler;
	
	public TimeWastingAlien(Player player, Tile_Object_Handler objectHandler) {
		
		super();
		this.player = player;
		this.objectHandler = objectHandler;
		this.alienType = 1;
		this.actionFrequency = new ArrayList<Integer>();
        this.actionFrequency.add(1000);
		this.actionFrequency.add(3000);
        this.alienTimerTask = new ArrayList<TimerTask>();
		TimerTask strategyCheckTask = new TimerTask(){
			@Override
			public void run()
			{
				setStrategy(player, objectHandler);

			};
		};
		TimerTask wasteTimeTask = new TimerTask()
		{
			@Override
			public void run()
			{
				executeStrategy();
			};
		};
		alienTimerTask.add(strategyCheckTask);
		alienTimerTask.add(wasteTimeTask);
	}
	
	public void setStrategy(Player player, Tile_Object_Handler objectHandler)
	{
		
		if (player.full_time - player.playerTime > player.full_time * 0.7) {
			timeWastingAlienStrategy = new PlayerIsGoodStrategy(player,objectHandler);
			System.out.println("hello thıs ıs goodth");
			strategyGood = true;
			strategyBad = false;
			strategyConfusing = false;
		}
		
		else if ((player.full_time - player.playerTime <  player.full_time * 0.7) && (player.full_time - player.playerTime > player.full_time * 0.3)) {
			timeWastingAlienStrategy = new PlayerIsConfusingStrategy(player,objectHandler);
			System.out.println("hello thıs ıs confusingth");
			strategyGood = false;
			strategyBad = false;
			strategyConfusing = true;
		}
		
		else {
			timeWastingAlienStrategy = new PlayerIsBadStrategy(player,objectHandler);
			System.out.println("hello thıs ıs badth");
			strategyGood = false;
			strategyBad = true;
			strategyConfusing = false;
		}

		
		
	}

	public void executeStrategy( ) {
		
		timeWastingAlienStrategy.wasteTime();
	}
	
	
	
	
	
	
	


}
	
	
	
	
	
	
	
	



