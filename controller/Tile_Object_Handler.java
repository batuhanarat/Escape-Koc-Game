package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import domain.model.buildingstuff.Building;
import domain.model.buildingstuff.Object;
import tiles.Tile;

public class Tile_Object_Handler {
	
	
	public Tile tile[];
	ObjectHandler object_Handler;
	public Object[] objectArray;
	public Building building;
	public int to_Array[][];
	public int row;
	public int column;
	private int size = 48;
	public int time = 0;
	public List<Object> allObjects = new ArrayList<>();
	
	
	
	public Tile_Object_Handler(Building building) {
		this.building = building;
		
		tile = new Tile[10];
		objectArray =  new Object[6];
		getItemImage();
		to_Array = new int[14][24];	 
		String filePath = "/maps/"+this.building.map;
		load_map(filePath);
		 	
	}
	

	
	public void whichRowAndColumn(int x, int y) {
		 
		this.column = (x/size);	 
		this.row = (y/size);		 	 
		 
	 }
	

	
	
	
	

	
	
	 public Boolean checkIfObjectIsPresent(Building building) {
	    	
	    	 if ( building.mapArray[row][column] > 6  && building.mapArray[row][column] <13) {
	    		 
	    		 return true;
	    	 }
	    	
	    	 return false;
	    	    	
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
					to_Array[row][col] = num;
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
	/*
	public void setMap(Building building) {
		
		this.building = building;
		load_map("/maps/"+this.building.map);
		
		
	}
*/

public void decideType(Object obj) {
	
	if(obj.getName().equals("statue")) {
		
		obj.type = 7;
	}
	
	if(obj.getName().equals("tree")) {
		
		obj.type = 8;
	}
	
	if(obj.getName().equals("chest")) {
		
		obj.type = 9;
	}
	if(obj.getName().equals("shelve")) {
		
		obj.type = 10;
	}
	if(obj.getName().equals("stone")) {
		
		obj.type = 11;
	}
    if(obj.getName().equals("vehbi")) {
		
		obj.type = 12;
	 }

}
	


public void getItemImage() {
	try {
		tile[0] = new Tile();
		tile[0].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/tile1.png"));
		
		tile[1] = new Tile();
		tile[1].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/tile2.png"));
		
		tile[2] = new Tile();
		tile[2].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/tile3.png"));
		
		tile[3] = new Tile();
		tile[3].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/tile4.png"));
		
		tile[4] = new Tile();
		tile[4].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/tile5.png"));
		
		tile[5] = new Tile();
		tile[5].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/tile6.png"));
		
		
		
		
		
		objectArray[0] = new Object();
		objectArray[0].image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/statue.png"));
		
		objectArray[1] = new Object();
		objectArray[1].image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/Trees.png"));
		
		objectArray[2] = new Object();
		objectArray[2].image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/chest.png"));
		
		objectArray[3] = new Object();
		objectArray[3].image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/shelve.png"));
		
		objectArray[4] = new Object();
		objectArray[4].image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/stone.png"));
		
		objectArray[5] = new Object();
		objectArray[5].image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/vehbi.png"));
		
		
			
		
		
	} catch(IOException e) {
		e.printStackTrace();
	}
	
}

public void set_map(Graphics2D g2) {
	int column = 0;
	int row = 0;
	int x = 0;
	int y= 0;
	
	while (column < 24 && row < 14) {
		int tileNum = to_Array[row][column];
	
			if(tileNum<=5){
				
				g2.drawImage(tile[tileNum].image, x,y,48,48,null);
			}
			else if(tileNum >=7){
				g2.drawImage(objectArray[tileNum-7].image,x,y,48,48,null);
				Object instance = new Object(objectArray[tileNum-7].getName(), objectArray[tileNum-7].getImage());
				allObjects.add(instance);
				time++;
			}
			column++;
			x+= 48;
			
			if (column == 24) {
				column = 0;
				x = 0;
				row++;
				y += 48;
				
			}
		
	}
}







}
