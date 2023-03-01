package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import data.DatabaseManager;
import domain.model.Player;
import domain.model.SaveFile;
import domain.model.buildingstuff.Building;
import domain.model.buildingstuff.CollisionChecker;
import domain.model.buildingstuff.Object;
import ui.game.GameBottomPanel;
import ui.game.GamePanelScreen;
import ui.game.GameUpperPanel;
import ui.game.MainFrame;

public class SaveLoadHandler {
	// call this and this calls the database class?

	private DatabaseManager db;
	private GameController game;
	
	public SaveLoadHandler() {
		this.db = DatabaseManager.getInstance();
	}

	public SaveLoadHandler(GameController game) {
		this.db = DatabaseManager.getInstance();
		this.game = game;
	}

	// UI Connection

	// Model Connection
	// Player
	// inventory
	// buildings
	// aliens

	// DB Connection
	public void save() {

		db.writeSavefile(generateSaveFile());
	}

	// Save to file
	public SaveFile generateSaveFile() {
		SaveFile save = new SaveFile();

		// Player
		Player p = game.getPlayer();
		p.save(save);

		// Buildings
		ArrayList<Building> buildings = game.getBuildings();
		save.buildingIndex = game.getBuildingIndex();
		System.out.println(game.getBuildingIndex());

		save.mapArrays = new int[buildings
				.size()][buildings.get(0).mapArray.length][buildings.get(0).mapArray[0].length];
		save.mapNames = new String[buildings.size()];
		//save.nextMapNames = new String[buildings.size()];
		save.isFinished = new Boolean[buildings.size()];


		save.numObjOfBuilding = new int[buildings.size()];

		int max = -1;
		for (int i = 0; i < buildings.size(); i++) {
			buildings.get(i).updateMapArray();
			save.mapArrays[i] = buildings.get(i).mapArray;
			save.mapNames[i] = buildings.get(i).getName();
			//save.nextMapNames[i] = buildings.get(i).nextMap;
			save.isFinished[i] = buildings.get(i).isFinished();
			save.numObjOfBuilding[i] = buildings.get(i).getArrayList().size();

			if (save.numObjOfBuilding[i] > max) {
				max = save.numObjOfBuilding[i];
			}
		}
		
		save.objX = new int[buildings.size()][max];
		save.objY = new int[buildings.size()][max];
		save.objName = new String[buildings.size()][max];
		save.objType = new int[buildings.size()][max];
		save.objKey = new Boolean[buildings.size()][max];

		for (int i = 0; i < buildings.size(); i++) {

			for (int j = 0; j < buildings.get(i).getArrayList().size(); j++) {
				ArrayList<Object> arr = buildings.get(i).getArrayList();
				Object obj = arr.get(j);

				save.objX[i][j] = obj.getXLocation();
				save.objY[i][j] = obj.getYLocation();
				save.objName[i][j] = obj.getName();
				save.objType[i][j] = obj.type;
				save.objKey[i][j] = obj.hasKey;
			}
		}

		// save.buildings = game.getBuildings();

		return save;
	}

	public void load() {
		SaveFile save = db.getSavefile();

		// Player

		// Buildings
		ArrayList<Building> buildings = new ArrayList<Building>();
		for (int i = 0; i < save.mapNames.length; i++) {
			ArrayList<Object> arr = new ArrayList<Object>();

			Building b = new Building(save.mapNames[i]);

			for (int j = 0; j < save.numObjOfBuilding[i]; j++) {
				Object o = new Object();
				o.setName(save.objName[i][j]);
				o.setXLocation(save.objX[i][j]);
				o.setYLocation(save.objY[i][j]);
				o.type = save.objType[i][j];
				o.hasKey = save.objKey[i][j];
				if(o.hasKey) {
					System.out.println("key is in " + o.getName());
				}
				o.whichRowAndColumn(o.getXLocation(), o.getYLocation());
				arr.add(o);
				b.addObject(o);
			}

			if (save.isFinished[i])
				b.setAsFinished();

			b.mapArray = save.mapArrays[i];
			//b.map = save.mapNames[i];
			//b.nextMap = save.nextMapNames[i];
			writeLoadedMaps(b);

			b.objectArrayList = (ArrayList<Object>) arr.clone();

			buildings.add(b);
		}

		// game.setBuildings(save.buildings);

		// buildingmodecontroller copied, belki burdan yurunur
		
		Building building = buildings.get(save.buildingIndex);

		CollisionChecker collisionChecker = new CollisionChecker(building);
		PowerUpHandler powerUpHandler = new PowerUpHandler(building);

		GameBottomPanel gamePanelBottomScreen = new GameBottomPanel();
		PlayerKeyHandler playerKeyHandler = new PlayerKeyHandler(powerUpHandler, gamePanelBottomScreen);
		Player player = new Player(playerKeyHandler, collisionChecker);
		player.full_time = building.objectArrayList.size() * 5;
		Tile_Object_Handler tileobject = new Tile_Object_Handler(building);
		AlienController alienController = AlienController.getAlienController(player,tileobject);
		GamePanelScreen gamePanelScreen = new GamePanelScreen(building, player, alienController, powerUpHandler,
				playerKeyHandler, gamePanelBottomScreen);
		GameUpperPanel gamePanelUpperScreen = new GameUpperPanel();
		MainFrame gameFrame = new MainFrame(gamePanelScreen, gamePanelBottomScreen, gamePanelUpperScreen, "Game");
		gameFrame.setVisible(true);
		
		
		player.load(save);

		GameController gamecontroller = new GameController(gamePanelScreen, gameFrame, player, tileobject,
				alienController, powerUpHandler, playerKeyHandler, gamePanelBottomScreen, gamePanelUpperScreen,
				buildings, save.buildingIndex);

	}

	public void writeLoadedMaps(Building building) {
		try {
			String filePath = "maps/" + building.map;
			int[][] mapObjectNumber = building.mapArray;
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filePath)));

			for (int i = 0; i < mapObjectNumber.length; i++) {
				for (int j = 0; j < mapObjectNumber[i].length; j++) {
					bw.write(mapObjectNumber[i][j] + ((j == mapObjectNumber[i].length - 1) ? " " : " "));
				}
				bw.newLine();
			}
			bw.flush();
		} catch (IOException e) {
			System.out.println("nerde");
		}
	}

}
