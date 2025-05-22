package irrgarten; //WHAT????????

import java.util.ArrayList;


public class Player extends LabyrinthCharacter {
    //Atributos privados de clase:
    private static final int MAX_WEAPONS = 2;
    private static final int MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH = 10;
    private static final int HIT2LOSE = 3;

    private static final String NAME_DEFAULT = "Player";

    //Atributos privados de instancia:
    private char number;
    private int consecutiveHits;

    private ArrayList<Weapon> weapons;
    private ArrayList<Shield> shields;

    private ShieldCardDeck shieldCardDeck;
    private WeaponCardDeck weaponCardDeck;

    //Constructor:
    public Player(char number, float intelligence, float strength) {
        super(NAME_DEFAULT + number, intelligence, strength, INITIAL_HEALTH);
        this.number = number;
        resetHits();
        this.weapons = new ArrayList<>();
        this.shields = new ArrayList<>();
        this.shieldCardDeck = new ShieldCardDeck();
        this.weaponCardDeck = new WeaponCardDeck();
        this.shieldCardDeck.addCards();
        this.weaponCardDeck.addCards();
    }
    
    public Player(Player other) {
        super(other);
        this.number = other.number;
        this.consecutiveHits = other.consecutiveHits;
        this.weapons = other.weapons;
        this.shields = other.shields;
        this.shieldCardDeck = other.shieldCardDeck;
        this.weaponCardDeck = other.weaponCardDeck;
    }

    //Resucita al jugador
    public void resurrect() {
        weapons.clear();
        shields.clear();
        setHealth(INITIAL_HEALTH);
        resetHits();
    }

    //Consultores: 
    //Devuelve el nombre (número) del jugador
    public char getNumber() {
        return number;
    }

    //Métodos de juego:

    /*
     * @brief Mueve al jugador en la dirección indicada:
     *      si la dirección dada, "direction", no es válida, se elige la primera dirección válida.
     * 
     * @param direction Dirección en la que se quiere el jugador.
     * @param validMoves ArrayList con las direcciones válidas.
     * @return La dirección en la que se moverá el jugador.
     */
    public Directions move(Directions direction, ArrayList<Directions> validMoves) {
        //P3
        int size = validMoves.size();
        boolean contained = validMoves.contains(direction);
        if ( (size > 0) &&  (!contained) ) {
            return validMoves.get(0);
        } else return direction;
    }

    //Ataca a un monstruo: calcula la suma de la fuerza del jugador 
    // y la suma de lo aportado por sus armas (sumWeapons).
    @Override
    public float attack() {
        return getStrength() + sumWeapons();
    }

    //Devuelve si el jugador se defiende o no.
    @Override
    public boolean defend(float receivedAttack) {
        return manageHit(receivedAttack);
    }


    /**
     * @brief El jugador recibe la recompensa por haber matado a un monstruo.
     *        Se le añaden armas y escudos, y se incrementa su salud.
     */
    public void receiveReward() {
        //P3

        int wReward = Dice.weaponsReward();
        int sReward = Dice.shieldsReward();
        for (int i = 0; i < wReward; i++) {
            Weapon wnew = newWeapon();          
            receiveWeapon(wnew);               
        }
        for (int i = 0; i < sReward; i++) {
            Shield snew = newShield();         
            receiveShield(snew);               
        }
        int extraHealth = Dice.healthReward();
        
        setHealth(extraHealth + getHealth());
        
    }

    
    @Override
    public String toString() {
        String cad = super.toString();
        cad += "\n\tWeapons: \n";
        for (int i = 0; i < weapons.size(); i++) {
            cad += "\t\t" + weapons.get(i).toString() + "\n";
        }
        cad += "\tShields: \n";
        for (int i = 0; i < shields.size(); i++) {
            cad += "\t\t" + shields.get(i).toString() + "\n";
        }
        return cad;
    }


    /**
     * @brief El jugador recibe un arma, "w".
     *        Descarta las armas que deben ser descartados.
     *        Si el número de armas es menor que el máximo, se añade la nueva arma, "w".
     * 
     * @param w Arma que recibe el jugador.
     */
    private void receiveWeapon(Weapon w) {
        //P3

        for (int i = 0; i < weapons.size(); i++) {
            Weapon wi = weapons.get(i);
            boolean discard = wi.discard();
            if (discard) {
                weapons.remove(i);
                i--;
            }
        }

        int size = weapons.size();

        if (size < MAX_WEAPONS) {
            weapons.add(w);
        }

    }

    /**
     * @brief El jugador recibe un escudo, "s".
     *        Descarta los escudos que deben ser descartados.
     *        Si el número de escudos es menor que el máximo, se añade el nuevo escudo.
     * 
     * @param s Escudo que recibe el jugador.
     */
    private void receiveShield(Shield s) {
        //P3

        
        
        for (int i = 0; i < shields.size(); i++) {
            Shield si = shields.get(i);
            boolean discard = si.discard();
            if (discard) {
                shields.remove(i);
                i--;
            }
        }
        

        if (shields.size() < MAX_SHIELDS) {
            shields.add(s);
        }

    }


    //Métodos privados:

    //Añade un arma al jugador
    private Weapon newWeapon() {
        Weapon weapon = this.weaponCardDeck.nextCard();
        return weapon;
    }

    //Añade un escudo al jugador
    private Shield newShield() {
        Shield shield = this.shieldCardDeck.nextCard();
        return shield;
    }
    
    // Calcula la suma del ataque de las armas del jugador
    protected float sumWeapons() {
        float sum = 0;
        for (Weapon weapon : weapons) {
            sum += weapon.attack();
        }
        return sum;
    }

    // Calcula la suma de la defensa de los escudos del jugador
    protected float sumShields() {
        float sum = 0;
        for (Shield shield : shields) {
            sum += shield.protect();
        }
        return sum;
    }

    // Calcula la energía defensiva (suma de la inteligencia con el aporte de los escudos (sumShields)) del jugador.
    protected float defensiveEnergy() {
        return getIntelligence() + sumShields();
    }


    private boolean manageHit(float receivedAttack) {
        //P3
        boolean lose;
        float defense = defensiveEnergy();
        if (defense < receivedAttack) {
            gotWounded();
            incConsecutiveHits();
        } else resetHits();
        
        if ((consecutiveHits == HIT2LOSE) || dead()){
            resetHits();
            lose = true;
        } else lose = false;
        return lose;
    }

    // Resetea el contador de golpes consecutivos
    private void resetHits() {
        consecutiveHits = 0;
    }

    // Incrementa en una unidad el contador de golpes consecutivos
    private void incConsecutiveHits() {
        consecutiveHits++;
    }

}

