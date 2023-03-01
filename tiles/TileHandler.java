package tiles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import domain.model.buildingstuff.Building;

import java.awt.Graphics2D;

//import main.GamePanel;

public class TileHandler {
	JPanel panel;
	public Tile[] tile;
	public int mapTileNumber[][];
	public Building building;
	public static int initializer = 0; 	
	
	
	
	
	public TileHandler(JPanel panel,Building building) {
	
		final int originalTileSize = 16;
		final int scale = 3; 
		
		final int tileSize = originalTileSize * scale; // 48x48
		final int maxScreenCol = 24;
		final int maxScreenRow = 14;
		
		final int screenWidth = tileSize * maxScreenCol;
		final int screenHeight = tileSize * maxScreenRow;
		
		this.building=building;
		this.panel = panel;
		
		
		this.tile = new Tile[6];
		this.mapTileNumber = new int[14][24];
		getTileImage();
		
		load_map("/maps/"+this.building.map);
		for (int i = 0; i < mapTileNumber.length; i++) { //this equals to the row in our matrix.
            for (int j = 0; j < mapTileNumber[i].length; j++) { //this equals to the column in each row.
               System.out.print(mapTileNumber[i][j] + " ");
            }
            System.out.println(); //change line on console as row comes to end in the matrix.
         }
		
		
	}
	
	public void setMap(Building building) {
		
		this.building=building;
		//load_map("maps/"+building.map);

		
	}

	
	
	public void getTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/tile1.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/tile2.png"));
			//tile[1].collision = true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/tile3.png"));
			//tile[2].collision = true;
			
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/tile4.png"));
			
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/tile5.png"));
			
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/resources/tiles/tile6.png"));
			
			
			
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public void load_map(String filePath) {

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
					mapTileNumber[row][col] = num;
					col++;
				}
				
				if (col == 24) {
					col = 0;
					row++;
				}
			}

			
			reader.close();
			
			
		} catch(Exception e) {

			
		}
	}
	
	public void draw(Graphics2D g2) {
		int column = 0;
		int row = 0;
		int x = 0;
		int y= 0;
		
		while (column < 24 && row < 14) {
			int tileNum = mapTileNumber[row][column];
			
				g2.drawImage(tile[tileNum].image, x,y,48,48,null);
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
