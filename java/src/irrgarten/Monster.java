/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author carlos
 */
public class Monster {
    private static final int INITIAL_HEALTH = 5;
    private static final int NULL_POS = -1;
    private String name;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    
    public Monster(String name, float intelligence, float strength) {
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = INITIAL_HEALTH;
        this.row = NULL_POS;
        this.col = NULL_POS;
    }
    
    public boolean dead() {
        return (health <= 0);
    }
    
    public float attack() {
        return (Dice.intensity(strength));
    }
    
    public void setPos(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    
    @Override
    public String toString() {
        String str = "M[ " + name +"; HP:" + health   + "; SP:" + strength + "; IP:"
                     + intelligence + "; POS(" + row + "," + col + ")]";
        return str;
    }
    
    public void gotWounded() {
        this.health--;
    } 
    
    public boolean defend(float receivedAttack) {
        throw new UnsupportedOperationException();
    }
}
