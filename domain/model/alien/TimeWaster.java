package domain.model.alien;

import controller.ObjectHandler;
import controller.Tile_Object_Handler;
import domain.model.Player;

public class TimeWaster {
	private TimeWastingAlienStrategy strategy;
	
	public TimeWaster(TimeWastingAlienStrategy strategy) {
		this.strategy = strategy;
	}
	
	public void executeStrategy(Player player, Tile_Object_Handler objectHandler ) {
		
		strategy.wasteTime();
	}
}