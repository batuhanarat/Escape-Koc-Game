package ui.game;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JPanel;

import domain.model.buildingstuff.Object;
public class HintRectangle extends JPanel {
    private int x;
    private int y;
    public HintRectangle(Object object){
        this.x = object.XLocation;
        this.y = object.YLocation;
        this.setVisible(true);
    }

    public void draw(Graphics2D g2){
        g2.setColor(Color.RED); 
        int thickness = 2;
        Stroke oldStroke = g2.getStroke();
        g2.setStroke(new BasicStroke(thickness));
        g2.drawRect(x-50,y-50,200,200);
    
    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);  
         g.drawRect(x,y,120,120); 
         g.fillRect(x,y,120,120); 
         g.setColor(Color.RED);  
           
        }
}
