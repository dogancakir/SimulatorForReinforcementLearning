/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulatorforreinforcementlearning;

/**
 *
 * @author dogan.cakir
 */
public class StateActionPair {
    public int x;
    public int y;
    public int direction;
    public double reward;

    public StateActionPair(int x,int y,int dir) {
        this.x=x;
        this.y=y;
        this.direction=dir;
    }
        public StateActionPair(int x,int y) {
        this.x=x;
        this.y=y;
    }
        
        public boolean equals(StateActionPair other)
        {
            if(this.x==other.x && this.y==other.y && this.direction==other.direction) return true;
            return false;
        }
}
