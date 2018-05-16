/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulatorforreinforcementlearning;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author dogan.cakir
 */
public class SimulatorForReinforcementLearning extends JPanel{
    public static int x;
    public static int y;
    public static int n;
    public static int endX;
    public static int endY;
    public static int startX;
    public static int startY;
    
    public SimulatorForReinforcementLearning(int x, int y,int n,int endX,int endY,int startX,int startY) {
        this.x=x;
        this.y=y;
        this.n=n;
        this.endX=endX;
        this.endY=endY;
        this.startX=startX;
        this.startY=startY;
    }
    
    @Override
    public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    g2d.setColor(Color.BLACK);
    for(int i=0;i<n*25;i+=25)
        for(int j=0;j<n*25;j+=25)
            g2d.drawRect(i, j, 25, 25);

    g2d.setColor(Color.BLUE);
    g2d.fillRect((startX-1)*25+1, (startY-1)*25+1, 24, 24);    
    g2d.setColor(Color.RED);
    g2d.fillRect((x-1)*25+10, (y-1)*25+10, 10, 10);
    g2d.setColor(Color.GREEN);
    g2d.fillRect((endX-1)*25+1, (endY-1)*25+1, 24, 24);
    

    

  }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        
    n=10;
    /*MonteCarlo mc=new MonteCarlo(n);
    mc.runMC(n);
    */ 
    Sarsa sarsa = new Sarsa(n);
    sarsa.runSarsa(n);
    
  }
    }
    

