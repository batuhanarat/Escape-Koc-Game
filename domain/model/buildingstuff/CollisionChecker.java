package domain.model.buildingstuff;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JPanel;

import domain.model.Player;
import ui.game.GamePanelScreen;


public class CollisionChecker {
	
	Building building;
	JPanel panel;
	
//	ObjectHandler obj_handler = new ObjectHandler(panel,building);
	
	public int map_Array[][];
	
	

	
	public CollisionChecker(Building building) {
		
		this.building = building;
		map_Array = new int[14][24];
		String filePath = "/maps/"+this.building.map; 
		load_map(filePath);
	}
	
	
	
	public void checkTile(Player player) {
		
		int entLeftX = player.x - player.solidArea.x;
		int entRightX = player.x + player.solidArea.x + player.solidArea.width;;	
		int entUpY = player.y + player.solidArea.y;
		int entDownY = player.y + player.solidArea.y + player.solidArea.height;		
		int entLeftCol = entLeftX / 48;
		int entRightCol = entRightX / 48;
		int entTopRow = entUpY/ 48;
		int entDownRow = entDownY / 48;
		
		
		int tileNum, tileNum2;
		
		if (player.direction.equals("up")) {
			entTopRow = (entUpY - player.speed) / 48;
			tileNum = map_Array[entTopRow][entLeftCol];
			tileNum2 =  map_Array[entTopRow][entRightCol];
			if ((tileNum >= 7) || (tileNum2 >= 7)) {
				/*
				if (obj_handler.object[tileNum-7].collision == true || obj_handler.object[tileNum2-7].collision == true) {
					return 1;
					
				}
				*/
				player.collision = true;
			}
			
		
		}
		
		 if (player.direction.equals("down")) {
			entDownRow = (entDownY + player.speed) / 48;
			tileNum = map_Array[entDownRow][entLeftCol];
			tileNum2 =  map_Array[entDownRow][entRightCol];
			
			
			if ((tileNum >= 7) || (tileNum2 >= 7)) {
				/*
				if (obj_handler.object[tileNum-7].collision == true || obj_handler.object[tileNum2-7].collision == true) {
					return 1;
				}
				*/
				player.collision = true;
			}
		}
		
		
		if (player.direction.equals("left")) {
			entLeftCol = (entLeftX - player.speed) / 48;
			tileNum = map_Array[entTopRow][entLeftCol];
			tileNum2 =  map_Array[entDownRow][entLeftCol];
			

			if ((tileNum >= 7) || (tileNum2 >= 7)) {
				/*
				if (obj_handler.object[tileNum-7].collision == true || obj_handler.object[tileNum2-7].collision == true) {
					return 1;
				}
				*/
				
				player.collision = true;
			}
			
		}
		
		 if (player.direction.equals("right")) {
			entRightCol = (entRightX + player.speed) / 48;
			tileNum = map_Array[entTopRow][entRightCol];
			tileNum2 =  map_Array[entDownRow][entRightCol];

			if ((tileNum >= 7) || (tileNum2 >= 7)) {
				/*
				if (obj_handler.object[tileNum-7].collision == true || obj_handler.object[tileNum2-7].collision == true) {
					return 1;
				}
				*/
				
				player.collision = true;
			}
		}

	
		
		
		
		
	}
	
	

	public void load_map(String filePath){
		
		try {

			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));

			
			int col = 0;
			int row = 0;
			
			while (col < 24 && row < 14) {
				String line = reader.readLine();

				
				while (col < 24) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					map_Array[row][col] = num;
					col++;
				}
				
				if (col == 24) {
					col = 0;
					row++;
				}
			}

			
			reader.close();
			
			
		} catch(Exception e) {
			e.printStackTrace();
			
		}
		


	}

}
