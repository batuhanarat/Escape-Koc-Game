package domain.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MapResetter {
	
	public MapResetter() {
		
	}
	
	
	public void mapResetter(){
		
		try {
			FileWriter file_writer;
			file_writer = new FileWriter("maps/studentcenter.txt");
			for (int i = 0; i < 14; i++) {
				for (int j = 0; j < 24; j++) {
					file_writer.write("0 ");
				}
				file_writer.write("\n");
			}
			
			file_writer.close();
			
			file_writer = new FileWriter("maps/case.txt");
			for (int i = 0; i < 14; i++) {
				for (int j = 0; j < 24; j++) {
					file_writer.write("1 ");
				}
				file_writer.write("\n");
			}
			
			file_writer.close();
			
			file_writer = new FileWriter("maps/sos.txt");
			for (int i = 0; i < 14; i++) {
				for (int j = 0; j < 24; j++) {
					file_writer.write("2 ");
				}
				file_writer.write("\n");
			}
			
			file_writer.close();
			
			file_writer = new FileWriter("maps/sci.txt");
			for (int i = 0; i < 14; i++) {
				for (int j = 0; j < 24; j++) {
					file_writer.write("3 ");
				}
				file_writer.write("\n");
			}
			
			file_writer.close();
			
			file_writer = new FileWriter("maps/eng.txt");
			for (int i = 0; i < 14; i++) {
				for (int j = 0; j < 24; j++) {
					file_writer.write("4 ");
				}
				file_writer.write("\n");
			}
			
			file_writer.close();
			
			file_writer = new FileWriter("maps/sna.txt");
			for (int i = 0; i < 14; i++) {
				for (int j = 0; j < 24; j++) {
					file_writer.write("5 ");
				}
				file_writer.write("\n");
			}
			
			file_writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
	}

}
