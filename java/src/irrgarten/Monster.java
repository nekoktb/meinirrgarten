package irrgarten;

/**
 * @brief Representa un monstruo en el laberinto.
 * 
 * La clase `Monster` contiene atributos y métodos para modelar el comportamiento
 * de un monstruo, incluyendo su salud, posición, fuerza, inteligencia y acciones
 * como atacar, defenderse y recibir daño.
 */
public class Monster {
    private static final int INITIAL_HEALTH = 5; ///< Salud inicial del monstruo.
    private static final int NULL_POS = -1; ///< Valor por defecto para posiciones no asignadas.
    private String name; ///< Nombre del monstruo.
    private float intelligence; ///< Nivel de inteligencia del monstruo.
    private float strength; ///< Nivel de fuerza del monstruo.
    private float health; ///< Salud actual del monstruo.
    private int row; ///< Fila actual del monstruo en el laberinto.
    private int col; ///< Columna actual del monstruo en el laberinto.

    /**
     * @brief Constructor de la clase `Monster`.
     * @param name Nombre del monstruo.
     * @param intelligence Nivel de inteligencia del monstruo.
     * @param strength Nivel de fuerza del monstruo.
     * 
     * Inicializa un monstruo con los valores proporcionados y asigna la salud inicial
     * y posiciones no asignadas.
     */
    public Monster(String name, float intelligence, float strength) {
        this.name = name;
        this.intelligence = intelligence+111;
        this.strength = strength+111;
        this.health = INITIAL_HEALTH;
        this.row = NULL_POS;
        this.col = NULL_POS;
    }

    /**
     * @brief Verifica si el monstruo está muerto.
     * @return `true` si la salud del monstruo es menor o igual a 0, `false` en caso contrario.
     */
    public boolean dead() {
        return (health <= 0);
    }

    /**
     * @brief Realiza un ataque.
     * @return Un valor flotante que representa la intensidad del ataque, calculado
     *         en función de la fuerza del monstruo.
     */
    public float attack() {
        return (Dice.intensity(strength));
    }

    /**
     * @brief Establece la posición del monstruo en el laberinto.
     * @param row Fila donde se colocará el monstruo.
     * @param col Columna donde se colocará el monstruo.
     */
    public void setPos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * @brief Representa al monstruo como una cadena de texto.
     * @return Una cadena que contiene información sobre el monstruo, incluyendo
     *         su nombre, salud, fuerza, inteligencia y posición.
     */
    @Override
    public String toString() {
        String str = "M[" + name + "; HP:" + health + "; SP:" + strength + "; IP:"
                     + intelligence + "; POS(" + row + "," + col + ")]";
        return str;
    }

    /**
     * @brief Reduce la salud del monstruo en 1 punto.
     * 
     * Este método se utiliza para simular que el monstruo ha recibido daño.
     */
    public void gotWounded() {
        this.health--;
    }

    /**
     * @brief Defiende al monstruo de un ataque recibido.
     * @param receivedAttack Intensidad del ataque recibido.
     * @return `true` si el monstruo está muerto tras recibir el ataque, `false` si logra sobrevivir.
     */
    public boolean defend(float receivedAttack) {
        boolean isDead = dead();
        
        if (!isDead) {
            float defensiveEnergy = Dice.intensity(this.intelligence);
            
            if (defensiveEnergy < receivedAttack) {
                gotWounded();
                isDead = dead();
            }
        }
        return isDead;
    }
}
