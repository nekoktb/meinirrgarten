package irrgarten;

abstract class LabyrinthCharacter {

    private static final int NULL_POS = -1;

    // Nombre
    private String name;
    
    // Inteligencia
    private int intelligence;

    // Fuerza
    private int strength;

    // Salud
    private int health;

    // Posición
    private int row;
    private int col;



    public LabyrinthCharacter(String name, int intelligence, int strength, int health) {
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = health;
        this.row = NULL_POS;
        this.col = NULL_POS;
    }

    public LabyrinthCharacter(LabyrinthCharacter other) {
        this.name = other.name;
        this.intelligence = other.intelligence;
        this.strength = other.strength;
        this.health = other.health;
        this.row = other.row;
        this.col = other.col;
    }

    public boolean dead() {
        return health <= 0;
    }    

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }

    protected float getIntelligence() {
        return intelligence;
    }

    protected float getStrength() {
        return strength;
    }

    protected float getHealth() {
        return health;
    }

    protected void setHealth(int health) {
        this.health = health;
    }

    public void setPos(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    @Override
    public String toString(){
        String cad = "[" + name + ": (HP: " + health + "; SP: " + strength + "; IP: " + intelligence + "); POS:{" + row + "," + col + "}]";
        return cad;
    }

    protected void gotWounded() {
        health--;
    }

    public abstract float attack();

    public abstract boolean defend(float attack);
}
    
    

