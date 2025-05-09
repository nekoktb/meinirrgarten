/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.ArrayList;
import java.util.Random;

/**
 * @brief Clase utilitaria para generar valores aleatorios.
 * 
 * La clase `Dice` proporciona métodos estáticos para generar valores aleatorios
 * utilizados en el juego, como posiciones, atributos de jugadores y monstruos,
 * recompensas, y probabilidades de eventos.
 */
public class Dice {
    private static final int MAX_USES = 5; ///< Número máximo de usos de armas y escudos.
    private static final float MAX_INTELLIGENCE = 10.0f; ///< Valor máximo para la inteligencia.
    private static final float MAX_STRENGTH = 10.0f; ///< Valor máximo para la fuerza.
    private static final float RESURRECT_PROB = 0.3f; ///< Probabilidad de resurrección de un jugador.
    private static final int WEAPONS_REWARD = 2; ///< Máximo número de armas recibidas como recompensa.
    private static final int SHIELDS_REWARD = 3; ///< Máximo número de escudos recibidos como recompensa.
    private static final int HEALTH_REWARD = 5; ///< Máximo número de unidades de salud recibidas como recompensa.
    private static final int MAX_ATTACK = 3; ///< Máxima potencia de las armas.
    private static final int MAX_SHIELD = 2; ///< Máxima potencia de los escudos.

    private static final Random generator = new Random(); ///< Generador de números aleatorios.

    /**
     * @brief Genera una posición aleatoria dentro de un rango.
     * @param max Valor máximo (exclusivo) para la posición.
     * @return Un entero aleatorio entre 0 (inclusive) y max (exclusivo).
     */
    public static int randomPos(int max) {
        return (generator.nextInt(max));
    }

    /**
     * @brief Determina qué jugador comienza el juego.
     * @param nplayers Número de jugadores.
     * @return Un entero aleatorio entre 0 y nplayers-1.
     */
    public static int whoStarts(int nplayers) {
        return (generator.nextInt(nplayers));
    }

    /**
     * @brief Genera un valor aleatorio para la inteligencia.
     * @return Un valor flotante entre 0 y MAX_INTELLIGENCE.
     */
    public static float randomIntelligence() {
        return (generator.nextFloat(MAX_INTELLIGENCE));
    }

    /**
     * @brief Genera un valor aleatorio para la fuerza.
     * @return Un valor flotante entre 0 y MAX_STRENGTH.
     */
    public static float randomStrength() {
        return (generator.nextFloat(MAX_STRENGTH));
    }

    /**
     * @brief Determina si un jugador es resucitado.
     * @return `true` si el jugador es resucitado, `false` en caso contrario.
     */
    public static boolean resurrectPlayer() {
        return generator.nextFloat() < RESURRECT_PROB;
    }

    /**
     * @brief Genera una recompensa en armas.
     * @return Un entero entre 0 y WEAPONS_REWARD.
     */
    public static int weaponsReward() {
        return (generator.nextInt(WEAPONS_REWARD + 1));
    }

    /**
     * @brief Genera una recompensa en escudos.
     * @return Un entero entre 0 y SHIELDS_REWARD.
     */
    public static int shieldsReward() {
        return (generator.nextInt(SHIELDS_REWARD + 1));
    }

    /**
     * @brief Genera una recompensa en salud.
     * @return Un entero entre 0 y HEALTH_REWARD.
     */
    public static int healthReward() {
        return (generator.nextInt(HEALTH_REWARD + 1));
    }

    /**
     * @brief Genera la potencia de un arma.
     * @return Un valor flotante entre 0 y MAX_ATTACK.
     */
    public static float weaponPower() {
        return (generator.nextFloat(MAX_ATTACK));
    }

    /**
     * @brief Genera la potencia de un escudo.
     * @return Un valor flotante entre 0 y MAX_SHIELD.
     */
    public static float shieldPower() {
        return (generator.nextFloat(MAX_SHIELD));
    }

    /**
     * @brief Genera un número aleatorio de usos restantes para un objeto.
     * @return Un entero entre 0 y MAX_USES.
     */
    public static int usesLeft() {
        return (generator.nextInt(MAX_USES + 1));
    }

    /**
     * @brief Calcula la intensidad de una acción basada en la competencia.
     * @param competence Nivel de competencia.
     * @return Un valor flotante entre 0 y competence.
     */
    public static float intensity(float competence) {
        return (generator.nextFloat(competence));
    }

    /**
     * @brief Determina si un elemento debe ser descartado.
     * @param usesLeft Número de usos restantes del elemento.
     * @return `true` si el elemento debe ser descartado, `false` en caso contrario.
     */
    public static boolean discardElement(int usesLeft) {
        return generator.nextFloat() >= ((float) usesLeft / MAX_USES);
    }

    /**
     * @brief Genera un paso aleatorio basado en la inteligencia y las preferencias.
     * @param preference Preferencia de dirección.
     * @param validMoves Lista de movimientos válidos.
     * @param intelligence Nivel de inteligencia del jugador.
     * @return La dirección del siguiente paso.
     */
    public static Directions nextStep(Directions preference, ArrayList<Directions> validMoves, float intelligence){
        if ( randomIntelligence() < intelligence){
            return preference;
        }
        else{
            int randomIndex = generator.nextInt(validMoves.size());
            return validMoves.get(randomIndex);
        }
    }
}
