/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulatorforreinforcementlearning;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author dogan.cakir
 */
public class MonteCarlo extends Common{

    public MonteCarlo(int n) {
        startX= (int)(Math.random() * n)+1;
        startY=(int)(Math.random()*n)+1;
        endX= (int)(Math.random() * n)+1;
        endY=(int)(Math.random()*n)+1;
        for(int i=1;i<=n;i++)
        {
            for(int j=1;j<=n;j++)
            {
               StateActionPair temp=new StateActionPair(i, j);
               temp.reward=0.5;
               if(i==endX&&j==endY) 
                   temp.reward=10;
               
               StateValue.add(temp);
               for(int k=0;k<4;k++)
               {
                temp= new StateActionPair(i, j,k);
                if((i==0&&k==1)||(j==0&&k==4)||(i==n-1&&k==2)||(j==n-1&&k==3)) temp.reward=-10000.0;
                else temp.reward=0.25;
                Policy.add(temp);
               }
               
            }
        }

        
    }    
    public static void runMC(int n) throws InterruptedException, FileNotFoundException, IOException{
        int epsilon=20;
    SimulatorForReinforcementLearning rects = new SimulatorForReinforcementLearning(startX,startY,n,endX,endY,startX,startY);
    JFrame frame = new JFrame("Monte Carlo Method");  
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    frame.add(rects);
    frame.setSize(n*25+200, n*25+200);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    while(true)
    {
        int i=0;
        rects.x=startX;
        rects.y=startY;
        ReturnsList.clear();
        int bef=-1;
        do        
        {
        StateActionPair temp=new StateActionPair(rects.x, rects.y);
        int rand=findStateValueMaxRewarded(temp,bef);
        int eps=(int)(Math.random() * 100);
        if(eps<epsilon) rand=(int)(Math.random()*4);
        if(rand ==0 && rects.y>1) {
            rects.y=rects.y-1;
            bef=0;
        }
        else if(rand==1 && rects.x<n){ 
            rects.x=rects.x+1;
            bef=1;
        }
        else if(rand==2 && rects.y<n) {
            rects.y=rects.y+1;
            bef=2;
        }
        else if(rand==3 && rects.x>1) {
            rects.x=rects.x-1;
            bef=3;
        }
        Thread.sleep(10);
        rects.repaint();

        double R=0.0;
            StateActionPair state=new StateActionPair(rects.x, rects.y);
            if(ReturnsList.containsKey(state))
            {
                
                R=ReturnsList.get(state).get(0);
                ReturnsList.get(state).add(R);
            }
            else{
                ArrayList <Double> tmp=new ArrayList<>();
                if(rects.x==endX && rects.y==endY)
                tmp.add(1.0);
                else
                tmp.add(-0.3);
                ReturnsList.put(state,tmp );
            }
            
            double a=0.0;
            int j;
            for(j=0;j<ReturnsList.get(state).size();j++)
                a+=ReturnsList.get(state).get(j);
            int ind=0;
            for(int u=0;u<StateValue.size();u++)
            {
                if(StateValue.get(u).direction==state.direction&&StateValue.get(u).x==state.x&&StateValue.get(u).y==state.y)
                {
                    ind =u;
                }
            }
            ReturnsList.get(state).add(temp.reward);
            state.reward=a/(double)j;
            StateValue.set(ind, state);
            i++;
        }
        while(rects.x!=endX||rects.y!=endY);
            
        System.out.println(i);
        
        
        /*
        int rand=(int)(Math.random() * 4);
        int step=(int)(Math.random() * 4);
        if(rand ==0 && rects.y-step>0) rects.y=rects.y-step;
        else if(rand==1 && rects.x+step<n) rects.x=rects.x+step;
        else if(rand==2 && rects.y+step<n) rects.y=rects.y+step;
        else if(rand==3 && rects.x-step>0) rects.x=rects.x-step;
        Thread.sleep(50);
        rects.repaint();
*/
       
    }
    }
}
