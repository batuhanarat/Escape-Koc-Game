package domain.model.alien;

import controller.ObjectHandler;
import controller.Tile_Object_Handler;
import domain.model.Player;

public class AlienFactory {

    public Player player;
    public Tile_Object_Handler objectHandler;

    public AlienFactory(Player player, Tile_Object_Handler objectHandler)
    {
        this.player = player;
        this.objectHandler = objectHandler;
        
    }

    public Alien GenerateAlien(int alienType)
    {
        if (alienType == 0)
        {
            return new BlindAlien(player);
        }
        else if (alienType == 1)
        {
            return new TimeWastingAlien(player,objectHandler);
        }
        else 
        {
            return new ShooterAlien(player);
        }
    }
    
}