/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 * @brief Representa un arma en el laberinto.
 * 
 * La clase `Weapon` modela un arma que puede ser utilizada por los jugadores
 * para atacar. Cada arma tiene un nivel de potencia y un número limitado de usos.
 */
public class Weapon {
    private float power; ///< Nivel de potencia del arma.
    private int uses; ///< Número de usos restantes del arma.

    /**
     * @brief Constructor de la clase `Weapon`.
     * @param power Nivel de potencia del arma.
     * @param uses Número inicial de usos del arma.
     * 
     * Inicializa un arma con el nivel de potencia y los usos especificados.
     */
    public Weapon(float power, int uses) {
        this.power = power;
        this.uses = uses;
    }

    /**
     * @brief Realiza un ataque con el arma.
     * @return El nivel de potencia del arma si tiene usos restantes, o 0 si no tiene usos.
     * 
     * Este método decrementa el número de usos del arma cada vez que se utiliza.
     */
    public float attack() {
        if (uses > 0) {
            uses--; // Reduce el número de usos restantes.
            return power; // Devuelve el nivel de potencia del arma.
        } else {
            return 0; // Si no tiene usos, no realiza ataque.
        }
    }

    /**
     * @brief Representa el arma como una cadena de texto.
     * @return Una cadena que contiene el nivel de potencia y los usos restantes del arma.
     * 
     * El formato de la cadena es: `W[power, uses]`.
     */
    @Override 
    public String toString() {
        return ("W[" + power + ", " + uses + "]"); 
    }

    /**
     * @brief Determina si el arma debe ser descartada.
     * @return `true` si el arma debe ser descartada, `false` en caso contrario.
     * 
     * Este método utiliza el método `discardElement` de la clase `Dice` para
     * determinar si el arma debe ser descartada en función de los usos restantes.
     */
    public boolean discard() {
        return (Dice.discardElement(uses));
    }
}
