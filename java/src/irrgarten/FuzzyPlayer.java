package irrgarten;

import java.util.ArrayList;
public class FuzzyPlayer extends Player {
   
    //Constructor:
    public FuzzyPlayer(Player other) {
        super(other);
    }

    @Override
    public Directions move(Directions direction, ArrayList<Directions> validMoves) {
        Directions dir = super.move(direction, validMoves);
        return Dice.nextStep(dir, validMoves, getIntelligence());

    }
    
    @Override
    public float attack() {
        return (sumWeapons() + Dice.intensity(getStrength()));
    }

    @Override
    protected float defensiveEnergy() {
        return (sumShields() + Dice.intensity(getIntelligence()));
        
    }

    @Override
    public String toString(){
        return ("Fuzzy"+super.toString());
    }
    
}
