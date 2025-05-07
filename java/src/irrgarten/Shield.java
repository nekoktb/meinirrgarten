
package irrgarten;

/**
 * @brief Representa un escudo en el laberinto a partir de la clase `CombatElement`.
 */
public class Shield extends CombatElement {
   
    /**
     * @brief Constructor de la clase `Shield`.
     * @param protection Nivel de protección que ofrece el escudo.
     * @param uses Número inicial de usos del escudo.
     */
    public Shield(float protection, int uses) {
        super(protection, uses); // Llama al constructor de la clase CombatElement.
    }

    /**
     * @brief Proporciona protección al jugador y reduce los usos del escudo.
     * @return El nivel de protección del escudo si tiene usos restantes, o 0 si no tiene usos.
     */
    public float protect() {
        return super.produceEffect();
    }

    /**
     * @brief Representa el escudo como una cadena de texto.
     * @return Una cadena que contiene el nivel de protección y los usos restantes del escudo.
     */
    @Override
    public String toString() {
        return ("S"+super.toString()); 
    }

    /**
     * @brief Determina si el escudo debe ser descartado.
     * @return `true` si el escudo debe ser descartado, `false` en caso contrario.
     */
    public boolean discard() {
        return super.discard();
    }
}
