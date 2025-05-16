package irrgarten;

import java.util.ArrayList;
public class FuzzyPlayer extends Player {
   
    //Constructor:
    public FuzzyPlayer(Player other) {
        super(other); //falta el constructor de copia de player 
    }

    public Directions move(Directions direction, ArrayList<Directions> validMoves) {
        Directions dir = super.move(direction, validMoves);
        return Dice.nextStep(dir, validMoves, getIntelligence());

    }

    public void attack() {
        
    }

    protected float defensiveEnergy() {
        
    }

    @Override
    String toString(){

    }
    
}
