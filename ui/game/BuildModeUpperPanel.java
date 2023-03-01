package ui.game;

import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JComboBox;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

import domain.model.buildingstuff.Building;





public class BuildModeUpperPanel extends JPanel {

    String buildings[]={"Student Center","CASE Building","SOS Building","SCI Building","ENG Building","SNA Building"};
    JComboBox<Object> buildingBox;
    JButton showButton;
    JButton startButton;

    public BuildModeUpperPanel() {

     buildingBox = new JComboBox<Object>(buildings);
     buildingBox.setBounds(50, 50,90,20);

     showButton =new JButton("Show");
     
     startButton = new JButton("Start");
     
     add(buildingBox);
     add(showButton);
     add(startButton);
     

 
    }

    
    public String getComboBox() {
    	
    	String building; 
    	building = buildingBox.getSelectedItem().toString();
    	
    	
    	return building;
    	
    	
    }

    public void setComboBox(Building building) {
    	
    	buildingBox.setSelectedItem(building.getName());
    	
    } 


    public void addComboBoxListener(ActionListener comboboxListener) {

        showButton.addActionListener(comboboxListener);
        
    }
    
    
    public void showErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this,errorMessage);

    	
    }
    
    public void addStartButtonListener(ActionListener ac ) {
    	startButton.addActionListener(ac);
    }




	






}