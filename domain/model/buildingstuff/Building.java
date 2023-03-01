package domain.model.buildingstuff;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import domain.model.Loadable;
import domain.model.Saveable;

public class Building{
	
	
	//Contraints

	// save
	public static final int studentCenterObjectLimit = 1; //5
	public static final int CASEObjectLimit = 1; //7
	public static final int SOSObjectLimit = 1; //10
	public static final int SCIObjectLimit = 1; //14
	public static final int ENGObjectLimit = 1; //19
	public static final int SNAObjectLimit = 1; //25
	private Object ob1 = null;
	
	public int tileIndex;
	
    public ArrayList<Object> objectArrayList = new ArrayList<Object>();
    
    public ArrayList<Object> stackObjectArrayList= new ArrayList<Object>();
    
    

 
    
    private String name;
    private int constrain;
    private Boolean finished = false;
    public String map;
    public String nextMap;
    String filePath;
    
    
    public int[][] mapArray;
    
    
    public Building(String name) {
    	
        this.mapArray = new int[14][24];
    	this.name= name;
    	
    	
    	if(name.equals("Student Center")) {
    		this.map = "studentcenter.txt";
    		this.nextMap = "CASE Building";
    		this.filePath = "/maps/studentcenter.txt";
    		this.tileIndex = 0;
    		//loadMapArray(mapArray,filePath);
    	
    	
    	}

    	else if(name.equals("CASE Building")) {
    		this.map = "case.txt"; 
    		this.nextMap = "SOS Building";
    		this.filePath = "/maps/case.txt";
    		this.tileIndex = 1;

    		//loadMapArray(mapArray,filePath);
    	}
    	

    	else if(name.equals("SOS Building")) {
			this.map = "sos.txt";
    		this.nextMap = "SCI Building";
    		 this.filePath = "/maps/sos.txt";
     		this.tileIndex = 2;

    		//loadMapArray(mapArray,filePath);
		}
		
    	else if(name.equals("SCI Building")) {	
			this.map = "sci.txt";
    		this.nextMap = "ENG Building";
    		 filePath = "/maps/sci.txt";
     		this.tileIndex = 3;

    		//loadMapArray(mapArray,filePath);
		}
		
    	else if(name.equals("ENG Building")) {
			this.map = "eng.txt";
    		this.nextMap = "SNA Building";
    		 filePath = "/maps/eng.txt";
     		this.tileIndex = 4;

    		//loadMapArray(mapArray,filePath);
		}
		
    	else if(name.equals("SNA Building")) {	
			this.map = "sna.txt";
    		this.nextMap = null;
    		 filePath = "/maps/sna.txt";
     		this.tileIndex = 5;

    		//loadMapArray(mapArray,filePath);
		}
		
		
		
		
    	
    }
    
    
    
    
    
   
    
    
    
    
    public void loadMapArray(int[][] mapArray,String filePath) {
    	
    	
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
    				mapArray[row][col] = num;
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
    	    System.out.println("problem in load map array in building class");


    	}
    	
    	
    }
    
    public Object keyReturner(){
    	
    	for(int i = 0; i<objectArrayList.size();i++){
    		if(objectArrayList.get(i).hasKey){
    			return objectArrayList.get(i);
    		}
    		
    	}
    	return ob1;
       } 
    
    
    public void updateMapArray() {
    	
    	loadMapArray(mapArray,filePath);
    	
    	
    }
    
    
    public void printMapArray() {
    	System.out.println();
		System.out.println(this.filePath);

    	updateMapArray();
    	System.out.println();
    	 for (int i = 0; i < mapArray.length; i++) { //this equals to the row in our matrix.
             for (int j = 0; j < mapArray[i].length; j++) { //this equals to the column in each row.
                System.out.print(mapArray[i][j] + " ");
             }
             System.out.println(); //change line on console as row comes to end in the matrix.
          }
    }
    
    
    public Boolean isFinished() {
    	return finished;
    }
    
    
    
    public void setAsFinished() {
	  
	  this.finished = true;
	  
	  
	  
  }
    
    
    public int getConstrain() {
    	
    	return constrain;
    	
    	
    }
    
    public String getName() {
    	return name;
    }
    
    
    
    public void addObject(Object obj) {
    	
    	objectArrayList.add(obj);
    	
    	
    }
    
    
   
    
    
    
    public ArrayList<Object> getArrayList() {
    	
    	return objectArrayList;
    	
    	
    	
    }
    
  
    
    public Boolean isValid() {
    	

    	
    	if(name.equals("Student Center")) {
    		
    		
    		constrain = studentCenterObjectLimit;
    		
    		if(objectArrayList.size() >= studentCenterObjectLimit) {
    			return true;
    			
    		} 
    		
    	}
    	
    	
    	if(name.equals("CASE Building")) {
    		
    		map = "case.txt";

    		
    		constrain =CASEObjectLimit;
    		
    		if(objectArrayList.size() >= CASEObjectLimit) {
    			return true;
    			
    		}
    		
    	}
    	
    	if(name.equals("SOS Building")) {
    		
    		map = "sos.txt";

    		
    		constrain = SOSObjectLimit;
		
		if(objectArrayList.size() >= SOSObjectLimit) {
			return true;
			
			}
		
    	}
    	
    	if(name.equals("SCI Building")) {
    		constrain = SCIObjectLimit;

		
		if(objectArrayList.size() >= SCIObjectLimit) {
			return true;
			
			}
		
    	}
    	
    	if(name.equals("ENG Building")) {
    		constrain = ENGObjectLimit;

		
		if(objectArrayList.size() >= ENGObjectLimit) {
			return true;
			
			}
		
    	}
    	
    	if(name.equals("SNA Building")) {
    		
    		constrain =SNAObjectLimit ;
    		
    		if(objectArrayList.size() >= SNAObjectLimit) {
    			return true;
    		
    		}
    		
        }
    	

    	
    	return false;
    	
    }
    
    
    
    
    
    
	

	
	
	

}
