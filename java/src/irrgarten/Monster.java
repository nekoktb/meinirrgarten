package irrgarten;

/**
 * @brief Representa un monstruo en el laberinto.
 * 
 * La clase `Monster` contiene atributos y métodos para modelar el comportamiento
 * de un monstruo, incluyendo su salud, posición, fuerza, inteligencia y acciones
 * como atacar, defenderse y recibir daño.
 */
public class Monster extends LabyrinthCharacter {
    private static final int INITIAL_HEALTH = 5; ///< Salud inicial del monstruo.


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
        super(name, intelligence+10, strength+10, INITIAL_HEALTH);
    }


    /**
     * @brief Realiza un ataque.
     * @return Un valor flotante que representa la intensidad del ataque, calculado
     *         en función de la fuerza del monstruo.
     */
    public float attack() {
        return (Dice.intensity(getStrength()));
    }

    /**
     * @brief Defiende al monstruo de un ataque recibido.
     * @param receivedAttack Intensidad del ataque recibido.
     * @return `true` si el monstruo está muerto tras recibir el ataque, `false` si logra sobrevivir.
     */
    public boolean defend(float receivedAttack) {
        boolean isDead = dead();
        
        if (!isDead) {
            float defensiveEnergy = Dice.intensity(getIntelligence());
            
            if (defensiveEnergy < receivedAttack) {
                gotWounded();
                isDead = dead();
            }
        }
        return isDead;
    }
}
