/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 * @brief Representa el estado actual del juego.
 * 
 * La clase `GameState` almacena información sobre el estado del laberinto,
 * los jugadores, los monstruos, el jugador actual, si hay un ganador, y un registro
 * de eventos del juego.
 */
public class GameState {
    private String labyrinth; ///< Representación del laberinto como una cadena.
    private String players; ///< Información sobre los jugadores como una cadena.
    private String monsters; ///< Información sobre los monstruos como una cadena.
    private int currentPlayer; ///< Índice del jugador que tiene el turno actual.
    private boolean winner; ///< Indica si hay un ganador en el juego.
    private String log; ///< Registro de eventos del juego.

    /**
     * @brief Constructor de la clase `GameState`.
     * @param labyrinth Representación del laberinto como una cadena.
     * @param players Información sobre los jugadores como una cadena.
     * @param monsters Información sobre los monstruos como una cadena.
     * @param currentPlayer Índice del jugador que tiene el turno actual.
     * @param winner Indica si hay un ganador en el juego.
     * @param log Registro de eventos del juego.
     * 
     * Inicializa un estado del juego con los valores proporcionados.
     */
    public GameState(String labyrinth, String players, String monsters, int currentPlayer,
                     boolean winner, String log)
    {
        this.labyrinth = labyrinth;
        this.players = players;
        this.monsters = monsters;
        this.currentPlayer = currentPlayer;
        this.winner = winner;
        this.log = log;
    }

    /**
     * @brief Obtiene la representación del laberinto.
     * @return Una cadena que representa el laberinto.
     */
    public String getLabyrinth() {
        return (labyrinth);
    }

    /**
     * @brief Obtiene la información de los jugadores.
     * @return Una cadena que contiene la información de los jugadores.
     */
    public String getPlayers() {
        return (players);
    }

    /**
     * @brief Obtiene la información de los monstruos.
     * @return Una cadena que contiene la información de los monstruos.
     */
    public String getMonsters() {
        return (monsters);
    }

    /**
     * @brief Obtiene el índice del jugador actual.
     * @return Un entero que representa el índice del jugador que tiene el turno actual.
     */
    public int getCurrentPlayer() {
        return (currentPlayer);
    }

    /**
     * @brief Verifica si hay un ganador en el juego.
     * @return `true` si hay un ganador, `false` en caso contrario.
     */
    public boolean getWinner() {
        return (winner);
    }

    /**
     * @brief Obtiene el registro de eventos del juego.
     * @return Una cadena que contiene el registro de eventos del juego.
     */
    public String getLog() {
        return (log);
    }
}
