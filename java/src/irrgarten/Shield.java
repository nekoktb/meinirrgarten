/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 * @brief Representa un escudo en el laberinto.
 * 
 * La clase `Shield` modela un escudo que puede ser utilizado por los jugadores
 * para protegerse de ataques. Cada escudo tiene un nivel de protección y un
 * número limitado de usos.
 */
public class Shield {
    private float protection; ///< Nivel de protección que ofrece el escudo.
    private int uses; ///< Número de usos restantes del escudo.

    /**
     * @brief Constructor de la clase `Shield`.
     * @param protection Nivel de protección que ofrece el escudo.
     * @param uses Número inicial de usos del escudo.
     * 
     * Inicializa un escudo con el nivel de protección y los usos especificados.
     */
    public Shield(float protection, int uses) {
        this.protection = protection;
        this.uses = uses;
    }

    /**
     * @brief Proporciona protección al jugador y reduce los usos del escudo.
     * @return El nivel de protección del escudo si tiene usos restantes, o 0 si no tiene usos.
     * 
     * Este método decrementa el número de usos del escudo cada vez que se utiliza.
     */
    public float protect() {
        if (uses > 0) {
            uses--; // Reduce el número de usos restantes.
            return protection; // Devuelve el nivel de protección.
        } else {
            return 0; // Si no tiene usos, no ofrece protección.
        }  
    }

    /**
     * @brief Representa el escudo como una cadena de texto.
     * @return Una cadena que contiene el nivel de protección y los usos restantes del escudo.
     * 
     * El formato de la cadena es: `S[protection, uses]`.
     */
    @Override
    public String toString() {
        return ("S[" + protection + ", " + uses + "]"); 
    }

    /**
     * @brief Determina si el escudo debe ser descartado.
     * @return `true` si el escudo debe ser descartado, `false` en caso contrario.
     * 
     * Este método utiliza el método `discardElement` de la clase `Dice` para
     * determinar si el escudo debe ser descartado en función de los usos restantes.
     */
    public boolean discard() {
        return (Dice.discardElement(uses));
    }
}
