/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulatorforreinforcementlearning;

import javax.swing.JFrame;
import static simulatorforreinforcementlearning.Common.startX;

/**
 *
 * @author dogan.cakir
 */
public class Sarsa extends Common{
    static int n;
    static int x;
    static int y;
    static int epsilon=20;
    static double alfa=0.5;
    static double beta=0.5;
    public void initStateVal(int n)
    {
        StateVal=new double[n][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(i==startX-1&&j==startY-1) StateVal[i][j]=10000.0;
                else StateVal[i][j]=5;
            }
        }
    }
    public int selectAction(int a,int b)
    {
        int dir=0;
        double max=-1000;
        for(int i=0;i<4;i++)
        {
            if(max<Qtabl[a][b][i])
            {
                dir=i;
                max=Qtabl[a][b][i];
            }
        }
        
        if(epsilon<(int)(Math.random() * 100))return dir;
        else
        {
            while(true)
            {
                int rand=(int)(Math.random() * 3);
                if(a==0 && rand!=0) return 0;
                else if(a==n-1&&rand!=2) return 2;
                else if(b==0&& rand!=3) return 3;
                else if(b==n-1&&rand!=1)return 1;
                else return dir;
            }
        }
    }
    public Sarsa(int n) {
        startX= (int)(Math.random() * n)+1;
        startY=(int)(Math.random()*n)+1;
        endX= (int)(Math.random() * n)+1;
        endY=(int)(Math.random()*n)+1;
        Qtabl=new double[n][n][4];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                for(int k=0;k<4;k++)
                {
                    if(i==0&&k==0) Qtabl[i][j][k]=-1000;
                    else if(i==n-1&&k==2) Qtabl[i][j][k]=-1000;
                    else if(j==0&&k==3) Qtabl[i][j][k]=-1000;
                    else if(j==n-1&&k==1) Qtabl[i][j][k]=-1000;
                    else Qtabl[i][j][k]=0.25; 
                }
            }
            
        }
    }
    public void runSarsa(int n) throws InterruptedException
    {
    
    SimulatorForReinforcementLearning rects = new SimulatorForReinforcementLearning(startX,startY,n,endX,endY,startX,startY);
    JFrame frame = new JFrame("Sarsa Method");  
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    frame.add(rects);
    frame.setSize(n*25+200, n*25+200);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
        while(true)
        {
        x=startX;
        y=startY;
            initStateVal(n);

        int action=selectAction(x,y);
            int newX=x;
            int newY=y;
            if(action==0)newX=x-1;
            else if(action==1)newY=y+1;
            else if(action==2)newX=x+1;
            else if(action==3)newY=y-1;
            double reward=StateVal[newX][newY];
        while(x!=endX&&y!=endY)
        {
            rects.x=x;
            rects.y=y;
            
            int newX2=x;
            int newY2=y;
            int action2=selectAction(newX, newY);
            if(action2==0)newX2=newX-1;
            else if(action2==1)newY2=newY+1;
            else if(action2==2)newX2=newX+1;
            else if(action2==3)newY2=newY-1;
            Qtabl[x][y][action]=Qtabl[x][y][action]+alfa*(reward+beta*Qtabl[newX][newY][action2]-Qtabl[x][y][action]);
            x=newX;
            y=newY;
            rects.x=x;
            rects.y=y;
            action=action2;
            Thread.sleep(50);
            rects.repaint();  
        }
          
        }
        
    }
}
