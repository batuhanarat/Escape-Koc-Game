package controller;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import domain.model.buildingstuff.Building;
import domain.model.buildingstuff.Object;
import tiles.Tile;
import tiles.TileHandler;

public class ObjectHandler {
	
	
	JPanel panel;
	public  Object[] object;
	public int mapObjectNumber[][];
	
	Building building;
	
	
	
public ObjectHandler(JPanel panel,Building building) {
	
		
	
		this.panel = panel;
		this.building=building;
		object = new Object[6];
		mapObjectNumber = new int[14][24];
		getObjectImage();	
		load_map("/maps/"+this.building.map);
		
	
		 
	}


public void setMap(Building building) {
	
	this.building = building;
	load_map("/maps/"+this.building.map);
	
	
}





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
	


public void getObjectImage() {
	try {
		object[0] = new Object();
		object[0].image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/statue.png"));
		
		
		object[1] = new Object();
		object[1].image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/Trees.png"));
		
		object[2] = new Object();
		object[2].image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/chest.png"));
		
		object[3] = new Object();
		object[3].image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/shelve.png"));
		
		object[4] = new Object();
		object[4].image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/stone.png"));
		
		object[5] = new Object();
		object[5].image = ImageIO.read(getClass().getResourceAsStream("/resources/objects/vehbi.png"));
		
		
		
		
		
		
		
	} catch(IOException e) {
		e.printStackTrace();
	}
}




public  int[][] load_map(String filePath) {
	
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
				mapObjectNumber[row][col] = num;
				col++;
			}
			
			if (col == 24) {
				col = 0;
				row++;
			}
		}
		
		reader.close();
		
		
		
		
	} catch(Exception e) {
		
	    System.out.println(e);

	}
	return mapObjectNumber;
	
}

public void addObjectsToMap(int row, int col, int type) {
	
	 	 
	 this.mapObjectNumber[row][col] = type;
	 
	
	 
	 for (int i = 0; i < mapObjectNumber.length; i++) {
		    for (int j = 0; j < mapObjectNumber[i].length; j++) {
		        System.out.print(mapObjectNumber[i][j] + " ");
		    }
		    System.out.println();
		}
		
	
	
}

public void saveAddedObjectsToMap(Building building) throws IOException {
	
	    try {
	    	String filePath = "maps/"+building.map;

	        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filePath)));

	        for (int i = 0; i < mapObjectNumber.length; i++) {
	            for (int j = 0; j < mapObjectNumber[i].length; j++) {
	                bw.write(mapObjectNumber[i][j] + ((j == mapObjectNumber[i].length-1) ? " " : " "));
	            }
	            bw.newLine();
	        }
	        bw.flush();
	    } catch (IOException e) {
	    	System.out.println("nerde");
	    }
	
	
	
	
}





}
