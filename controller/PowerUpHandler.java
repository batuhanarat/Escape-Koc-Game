package controller;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import domain.model.alien.Alien;
import domain.model.buildingstuff.Building;
import domain.model.buildingstuff.Object;
import domain.model.powerup.ExtraLifePowerUp;
import domain.model.powerup.ExtraTimePowerUp;
import domain.model.powerup.HintPowerUp;
import domain.model.powerup.PlasticBottlePowerUp;
import domain.model.powerup.Power_Up;
import domain.model.powerup.ProtectionVestPowerUp;

public class PowerUpHandler {

	public  Power_Up[] powerUps;
	public int mapPowerUpNumber[][];
	public Power_Up powerUp;
	public Building building;
	
	private Timer timerForPowerUpTasks;
	private Timer secondTimerForPowerUpTasks;
	private static TimerTask generatePowerUpCreationTask;
	private static TimerTask generatePowerUpRemovalTask;

public int row;
public int col;
	
	
	
public PowerUpHandler(Building building) {
	
		
	
		this.building=building;
		powerUps = new Power_Up[5];
		mapPowerUpNumber = new int[14][24];
		getInitialPowerUps();	
		load_map("/maps/"+this.building.map);
		this.timerForPowerUpTasks = new Timer();
		this.secondTimerForPowerUpTasks = new Timer();
		
		generatePowerUpCreationTask = new TimerTask() {
			
			@Override
			public void run() 
			{
			  
				try {
					createPowerUp();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		};
		
		generatePowerUpRemovalTask = new TimerTask() {
			@Override
			public void run() 
			{
			  
				
				removePowerUp();
				
			}
		};	
		
				 
	}




public void startPowerUp() {
	
	
	timerForPowerUpTasks.scheduleAtFixedRate(generatePowerUpCreationTask, 12000, 12000);
	secondTimerForPowerUpTasks.scheduleAtFixedRate(generatePowerUpRemovalTask, 18000, 12000);

	
	
}




public void createPowerUp() throws IOException {
	 
	 handlePowerUpGeneration();
	 
	 while(!ifPositionsAreValidAddPowerUpToMap() ) {
		 handlePowerUpGeneration();					 
		 
	 }
	 
	 saveAddedObjectsToMap(building); 
	 
	 
}




public void handlePowerUpGeneration()  {
	
	 Random rand = new Random();
	 int powerUpType = rand.nextInt(5);
	 powerUp =generatePowerUp(2); 
	 decideMapIndex(powerUp);
	 generateNewPowerUp(powerUpType);
	 
	
	
}

public Power_Up generatePowerUp(int powerUpType) {


	switch (powerUpType) {
	case 0:
		// extraLife.
		return powerUps[0];

	case 1:
		// extraTime
		return powerUps[1];

	case 2:
		// hint
		return powerUps[2];

	case 3:
		// plasticBottle
		return powerUps[3];

	case 4:
		// protectionVest
		return powerUps[4];

	}

	return powerUps[0];

}


public void decideMapIndex(Power_Up pu) {
	
	if(pu.type == 0) {
		
		pu.mapIndex = 13;
	}
	
	if(pu.type == 1) {
		
		pu.mapIndex = 14;
	}
	
	if(pu.type == 2) {
		
		pu.mapIndex = 15;
	}
	
	if(pu.type == 3) {
		
		pu.mapIndex = 16;
	}
	if(pu.type == 4) {
		
		pu.mapIndex = 17;
	}
    

}



public void generateNewPowerUp(int powerUpType) {
	
	
	if(powerUpType == 0) {
		powerUps[0] = new ExtraLifePowerUp();

		
	}
	else if(powerUpType == 1) {
		powerUps[1] = new ExtraTimePowerUp();

		
	}
	else if(powerUpType == 2) {
		powerUps[2] = new HintPowerUp();

		
	}
	else if(powerUpType == 3) {
		powerUps[3] = new PlasticBottlePowerUp();

		
	}
	else if(powerUpType == 4) {
		powerUps[4] = new ProtectionVestPowerUp();

		
	}
	
	
	
	
}







public Boolean ifPositionsAreValidAddPowerUpToMap() {
	
	
	int x = powerUp.XLocation;
	int y = powerUp.YLocation;
	
	
	whichRowAndColumn(x,y);
	
	int index = mapPowerUpNumber[powerUp.row][powerUp.column];
	
	if(index >= 7) {	
		return false;
	}
	
	
	
	addPowerUpToMap(powerUp.row,powerUp.column,powerUp.mapIndex);
	//building.addPowerUpToItsArray(powerUp);
	
	return true;
	
	
	
}


public void addPowerUpToMap(int row, int col, int index) {
	
 
	 this.mapPowerUpNumber[row][col] = index;	 

	
}





public void removePowerUp() {



				if(powerUp !=  null) {
					mapPowerUpNumber[powerUp.row][powerUp.column] = building.tileIndex;  
					  powerUp= null;
					  	 
						
				}
			
	 
}


public void getInitialPowerUps() {
	
	powerUps[0] = new ExtraLifePowerUp();
	powerUps[1] = new ExtraTimePowerUp();
	powerUps[2] = new HintPowerUp();
	powerUps[3] = new PlasticBottlePowerUp();
	powerUps[4] = new ProtectionVestPowerUp();
	
}





public void whichRowAndColumn(int x, int y) {
	 //Double powerupsize = 48.0;
	
	 
	// powerUp.column = roundOffAlgorithm(x,powerupsize);
	 //powerUp.row = roundOffAlgorithm(y,powerupsize);
	
	
		
			 
			powerUp.column = (x/48);	 
			powerUp.row = (y/48);		
			
			
			System.out.println(	powerUp.row);
			 
			System.out.println(powerUp.column );

	 
 }
public void whichRowAndColumn2(int x, int y) {
	 //Double powerupsize = 48.0;
	
	 
	// powerUp.column = roundOffAlgorithm(x,powerupsize);
	 //powerUp.row = roundOffAlgorithm(y,powerupsize);
	
	
		
			 
			this.col = (x/48);	 
			this.row = (y/48);		
			
			
			//System.out.println(	powerUp.row);
			 
			//System.out.println(powerUp.column );

	 
}





public Boolean checkIfPowerUpIsPresent(Building building) {
	
	//load_map("/maps/"+this.building.map);
	
	
	
    System.out.println("bu mapte ikinci tıkladığımda görmemem lazım");

	
	 for (int i = 0; i < mapPowerUpNumber.length; i++) {
		    for (int j = 0; j < mapPowerUpNumber[i].length; j++) {
		        System.out.print(mapPowerUpNumber[i][j] + " ");
		    }
		    System.out.println();
		}
	

	   // System.out.println(mapPowerUpNumber[powerUp.row][powerUp.column]);

	
	 if ( mapPowerUpNumber[powerUp.row][powerUp.column] > 12 && mapPowerUpNumber[powerUp.row][powerUp.column] <18) {
		 
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
				mapPowerUpNumber[row][col] = num;
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





public void saveAddedObjectsToMap(Building building) throws IOException {
	
	    try {
	    	String filePath = "maps/"+building.map;

	        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filePath)));

	        for (int i = 0; i < mapPowerUpNumber.length; i++) {
	            for (int j = 0; j < mapPowerUpNumber[i].length; j++) {
	                bw.write(mapPowerUpNumber[i][j] + ((j == mapPowerUpNumber[i].length-1) ? " " : " "));
	            }
	            bw.newLine();
	        }
	        bw.flush();
	    } catch (IOException e) {
	    	System.out.println("savepowerup");
	    }
	
	
	
	
}
public void draw(Graphics2D g2) {
	
	//bunu bir dene,çalışırsa hepsini kendi drawına çevir
	
	
	// ArrayList<Power_Up> powerUpList = building.powerUpArrayList;

	if (powerUp !=null)
	{
		
			if (powerUp.getPowerUpType() == 0)
			{
				g2.drawImage(powerUps[0].image, powerUp.XLocation, powerUp.YLocation, 48, 48, null);
			}
			else if (powerUp.getPowerUpType() == 1)
			{
				g2.drawImage(powerUps[1].image, powerUp.XLocation, powerUp.YLocation, 48, 48, null);
			}
			else if (powerUp.getPowerUpType() == 2)
			{
				g2.drawImage(powerUps[2].image, powerUp.XLocation, powerUp.YLocation, 48, 48, null);
			}
			else if (powerUp.getPowerUpType() == 3)
			{
				
				
				g2.drawImage(powerUps[3].image, powerUp.XLocation, powerUp.YLocation, 48, 48, null);
			
				
				}
			else	if (powerUp.getPowerUpType() == 4)
			{
				g2.drawImage(powerUps[4].image, powerUp.XLocation, powerUp.YLocation, 48, 48, null);
			}
		
			if(powerUps[3].thrown) {
				
				if(powerUps[3].direction == 2) {
	        		g2.drawImage(powerUps[3].image,502,83,48,48,null);
	        		System.out.println("NORTHA CIZDI");
	        		
	        		//powerUps[3].thrown = false;
				}
	        	if(powerUps[3].direction == 1) {
	        		g2.drawImage(powerUps[3].image,502,578,48,48,null);
	        		System.out.println("SOUTH CIZDI");
	        		
	        		//powerUps[3].thrown = false;
	        	}
	        		
	        	if(powerUps[3].direction == 3) {
	        		g2.drawImage(powerUps[3].image,955,289,48,48,null);
	        		System.out.println("EAST CIZDI");
	        		
	        		//powerUps[3].thrown = false;
	        		
	        	}
	        	if(powerUps[3].direction == 4) {
	        		g2.drawImage(powerUps[3].image,101,289,48,48,null);
	        		System.out.println("WEST CIZDI");
	        		
	        		//powerUps[3].thrown = false;
	        		
	        	}
	        		
	        		
	        	}
			}
			   
		
	








	
}


}



