package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import domain.model.SaveFile;

public class DatabaseManager {

	private static DatabaseManager dbManager;
	private BufferedWriter writer;
	private BufferedReader reader;
	private final String userpath = "resources/data/users.txt";
	private final String savepath = "resources/data/save.to";
	
	private DatabaseManager() {
		
	}

	public static DatabaseManager getInstance() {
		if (dbManager == null) {
			dbManager = new DatabaseManager();
		}
		return dbManager;
	}

	// database format is {username, password, savefile = 0}
	// will be fixed after savefile is implemented
	public Boolean writeUser(String username, String password) {
		writer = this.getWriter(this.userpath);

		if (checkIfUserExists(username))
			return false;

		try {
			writer.append(username + "/" + password + "\n");
			System.out.println("wrote " + username + " " + password);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	private Boolean checkIfUserExists(String username) {
		reader = getReader(this.userpath);
		String line;
		try {
			line = reader.readLine();

			while (line != null) {
				if (line.split("/")[0].equals(username)) {
					reader.close();
					System.out.println("Username taken.");
					return true;
					}
				line = reader.readLine();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void deleteUser(String username) {

	}

	public boolean checkUser(String username, String password) {
		reader = getReader(this.userpath);
		try {
			String line = reader.readLine();
			while (line != null) {
				String[] temp = line.split("/");

				if (temp[0].equals(username) && temp[1].equals(password)) {
					reader.close();
					return true;
				}

				line = reader.readLine();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Username or password is incorrect.");
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void writeSavefile(SaveFile save) {
		try {
			FileOutputStream fos = new FileOutputStream(new File(savepath));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(save);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public SaveFile getSavefile() {
		try {
			FileInputStream fis = new FileInputStream(new File(savepath));
			ObjectInputStream ois = new ObjectInputStream(fis);
			SaveFile save = (SaveFile) ois.readObject();
			System.out.println(save);
			return save;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}

	public void updateSavefile() {

	}

	private BufferedWriter getWriter(String path) {
		File f = new File(path);

		FileWriter fw = null;

		try {
			fw = new FileWriter(f, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new BufferedWriter(fw);
	}

	private BufferedReader getReader(String path) {
		InputStream is = getClass().getResourceAsStream("/" + path);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		return reader;
	}

}
