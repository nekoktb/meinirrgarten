
package irrgarten;

/**
 * @brief Representa un arma en el laberinto a partir de la clase CombatElement
 */
public class Weapon extends CombatElement {
    
    /**
     * @brief Constructor de la clase `Weapon`.
     * @param power Nivel de potencia del arma.
     * @param uses Número inicial de usos del arma.
     */
    public Weapon(float power, int uses) {
        super(power, uses); 
    }

    /**
     * @brief Realiza un ataque con el arma.
     * @return El nivel de potencia del arma si tiene usos restantes, o 0 si no tiene usos.
     */
    public float attack() {
        return super.produceEffect();
    }

    /**
     * @brief Representa el arma como una cadena de texto.
     * @return Una cadena que contiene el nivel de potencia y los usos restantes del arma.
     */
    @Override 
    public String toString() {
        return ("W"+super.toString());
    }

    /**
     * @brief Determina si el arma debe ser descartada.
     * @return `true` si el arma debe ser descartada, `false` en caso contrario.
     */
    public boolean discard() {
        return super.discard();
    }
}
