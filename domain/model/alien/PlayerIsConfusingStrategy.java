package domain.model.alien;

import controller.ObjectHandler;
import controller.Tile_Object_Handler;
import domain.model.Player;

public class PlayerIsConfusingStrategy implements TimeWastingAlienStrategy {
	Player player;
	Tile_Object_Handler objectHandler;
	public PlayerIsConfusingStrategy(Player player, Tile_Object_Handler objectHandler) {
		this.player= player;
		this.objectHandler = objectHandler;
	}
    public void wasteTime()
    {
    	System.out.println("Confusing Strategy is done\n");
    }
    
}