import java.util.ArrayList;


package irrgarten;


public class Player {
    //Atributos privados de clase:
    private static final int MAX_WEAPONS = 2;
    private static final int MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH = 10;
    private static final int HIT2LOSE = 3;

    private static final String NAME_DEFAULT = "Player#";
    private static final int NULL_POS = -1;

    //Atributos privados de instancia:
    private String name;
    private char number;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    private int consecutiveHits;

    private ArrayList<Weapon> weapons;
    private ArrayList<Shield> shields;

    //Constructor:
    public Player(char number, float intelligence, float strength) {
        this.number = number;
        this.name = NAME_DEFAULT + number;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = INITIAL_HEALTH;
        this.row = NULL_POS;
        this.col = NULL_POS;
        //this.consecutiveHits = 0;  //¿Esto cuenta como número mágico? 
        resetHits() //en vez de hacerlo a mano como arriba?
        this.weapons = new ArrayList<>();
        this.shields = new ArrayList<>();
    }

    //Resucita al jugador
    public void resurrect() {
        weapons.clear();
        shields.clear();
        health = INITIAL_HEALTH;
        resetHits();
    }

    //Consultores: 
    //Devuelve la fila en la que se encuentra el jugador
    public int getRow() {
        return row;
    }

    //Devuelve la columna en la que se encuentra el jugador
    public int getCol() {
        return col;
    }

    //Devuelve el nombre (número) del jugador
    public char getNumber() {
        return number;
    }

    //Modificadores:
    //Establece la posición del jugador
    public void setPos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    //Devuelve true si el jugador está muerto
    public boolean isDead() {
        return health <= 0;
    }

    //Métodos de juego:
    //Mueve al jugador en la dirección indicada
    public void move(String direction, ArrayList<String> validMoves) {
        //se dará informacion en la pŕactica 3 ////////////////////////////////////////////////
        throw new UnsupportedOperationException();

    }

    //Ataca a un monstruo: calcula la suma de la fuerza del jugador 
    // y la suma de lo aportado por sus armas (sumWeapons).
    public float attack() {
        return strength + sumWeapons();
    }

    //El jugador se defiende: 
    public void defend(float receivedAttack) {   //acabar//////////////////////////////////////////////////////////////
        
    }


    public void receiveReward() {
        //se dará informacion en la pŕactica 3 ////////////////////////////////////////////////
        throw new UnsupportedOperationException();
    }

    
    @Override
    public String toString() {
        return "P[" + name + " (HP: " + health + "; SP: " + strength + "; IP: " + intelligence + "); POS:{" + row + "," + col + "}]";
    }

    private void receiveWeapon(Weapon w) {
        //se dará informacion en la pŕactica 3 ////////////////////////////////////////////////
        throw new UnsupportedOperationException();
    }

    private void receiveShield(Shield s) {
        //se dará informacion en la pŕactica 3 ////////////////////////////////////////////////
        throw new UnsupportedOperationException();
    }


    //Métodos privados:

    //Añade un arma al jugador
    private Weapon newWeapon() {
        if (weapons.size() < MAX_WEAPONS) {
            Weapon weapon = new Weapon(Dice.weaponPower(), Dice.randomUses());
            weapons.add(weapon);
            return weapon;
        } else {
            return new Weapon();
        }
        
    }

    //Añade un escudo al jugador
    private Shield newShield() {
        if (shields.size() < MAX_SHIELDS) {
            Shield shield = new Shield(Dice.shieldPower(), Dice.randomUses());
            shields.add(shield);
            return shield;
        } else {
            return new Shield();
        }
    }
    
    // Calcula la suma del ataque de las armas del jugador
    private float sumWeapons() {
        float sum = 0;
        for (Weapon weapon : weapons) {
            sum += weapon.attack();
        }
        return sum;
    }

    // Calcula la suma de la defensa de los escudos del jugador
    private float sumShields() {
        float sum = 0;
        for (Shield shield : shields) {
            sum += shield.protect();
        }
        return sum;
    }

    // Calcula la energía defensiva (suma de la inteligencia con el aporte de los escudos (sumShields)) del jugador.
    private float defensiveEnergy() {
        return intelligence + sumShields();
    }

    private void manageHit(float receivedAttack) {
        //se dará informacion en la pŕactica 3 ////////////////////////////////////////////////
        throw new UnsupportedOperationException();
    }

    // Resetea el contador de golpes consecutivos
    private void resetHits() {
        consecutiveHits = 0;
    }

    // Decrementa en una unidad la salud del jugador.
    private void gotWounded() {
        health--;
    }

    // Incrementa en una unidad el contador de golpes consecutivos
    private void incConsecutiveHits() {
        consecutiveHits++;
    }

}

