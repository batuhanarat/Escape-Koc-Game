package domain.model.alien;
import java.util.Random;
import controller.ObjectHandler;
import controller.Tile_Object_Handler;
import domain.model.Player;

public class PlayerIsBadStrategy implements TimeWastingAlienStrategy {
	Player player;
	Tile_Object_Handler objectHandler;
	public PlayerIsBadStrategy(Player player,Tile_Object_Handler objectHandler) {
		this.player = player;
		this.objectHandler = objectHandler;
	}
    public void wasteTime()
    {
    	int previous_Key;
        for (int i = 0; i < objectHandler.building.objectArrayList.size(); i++) {
        	if (objectHandler.building.objectArrayList.get(i).hasKey) {
        		previous_Key = i;
        		objectHandler.building.objectArrayList.get(i).hasKey = false;
        		break;
        	}
        }
        Random rand = new Random();
        int random = rand.nextInt(objectHandler.building.objectArrayList.size());
        objectHandler.building.objectArrayList.get(random).hasKey = true;
        System.out.println("BAD Strategy is done\n");
        
    }
    
}