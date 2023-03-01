package domain.model;

import java.io.Serializable;
import java.util.ArrayList;

import domain.model.buildingstuff.Building;

public class SaveFile implements Serializable{
	private static final long serialVersionUID = -6641529653857746630L;
	
	public String username;
	public Double fulltime;
	public int totalLives;
	public int posX;
	public int posY;
	
	public String[] mapNames;
	public String[] nextMapNames;
	public Boolean[] isFinished;
	public int[][][] mapArrays;
	public int buildingIndex;
	public int[][] objX;
	public int[][] objY;
	public String[][] objName;
	public int[][] objType;
	public Boolean[][] objKey;
	public int[] numObjOfBuilding;
	
	
}
