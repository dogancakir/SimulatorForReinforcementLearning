/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulatorforreinforcementlearning;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author dogan.cakir
 */
public class Common {
    static int startX;
    static int startY;
    static int endX;
    static int endY;
    public static HashMap<StateActionPair, List> Qtable = new HashMap<StateActionPair, List>();
    public static List<StateActionPair> Policy = new ArrayList<StateActionPair>();
    public static List<StateActionPair> StateValue = new ArrayList<StateActionPair>();
    public static double Qtabl[][][];
    public static double StateVal[][];
    
    public static HashMap<StateActionPair, ArrayList<Double>> ReturnsList = new HashMap<StateActionPair, ArrayList<Double>>();
    
    public static int findStateValueMaxRewarded(StateActionPair main,int bef)
    {
        double max=-1000000.0;
        int dir=0;
        List <Integer> directions=new ArrayList<Integer>();
        HashMap <Integer, StateActionPair> States = new HashMap<Integer, StateActionPair>();
    
            for(int j=0;j<StateValue.size();j++)
            {
                if(StateValue.get(j).x==main.x&&StateValue.get(j).y==main.y-1&&bef!=2) States.put(0,StateValue.get(j) );
                else if(StateValue.get(j).x==main.x+1&&StateValue.get(j).y==main.y&&bef!=3) States.put(1,StateValue.get(j));
                else if(StateValue.get(j).x==main.x&&StateValue.get(j).y==main.y+1&&bef!=0) States.put(2,StateValue.get(j));
                else if(StateValue.get(j).x==main.x-1&&StateValue.get(j).y==main.y&&bef!=1) States.put(3,StateValue.get(j));
            }
            for(int i=0;i<4;i++)
            {
                if(States.containsKey(i)) if(max<States.get(i).reward) 
                {
                    dir=i;
                    max=States.get(i).reward;
                }
                
                
            }
            return dir;
    }

    
}
