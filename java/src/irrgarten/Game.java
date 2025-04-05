
package irrgarten;

import java.util.ArrayList;

public class Game {

    private static final int MAX_ROUNDS = 10;

    private int currentPlayerIndex;
    private String log;

    private ArrayList<Player> players;
    private ArrayList<Monster> monsters;
    private Player currentPlayer;
    private Labyrinth labyrinth;

    
    /**
     * @brief Constructor de la clase Game.
     * 
     * Inicializa el juego con un número dado de jugadores, creando una lista de
     * jugadores y un laberinto. Configura el laberinto y determina quién comienza
     * el juego.
     * 
     * @param nPlayers Número de jugadores en el juego.
     */
    public Game(int nPlayers){
        this.currentPlayerIndex = Dice.whoStarts(nPlayers);
        this.log = "";
        this.players = new ArrayList<>();
        this.monsters = new ArrayList<>();
        //La inicialización de labyrinth debería hacerse en configureLabyrinth() para meter literales

        //Lo de abajo debe hacerlo el configureLabyrinth?????
        Dice d = new Dice();
        for (int i = 0; i < nPlayers; i++) {
            players.add(new Player( (char)i , Dice.randomIntelligence() , Dice.randomStrength() )); 
        }

        this.currentPlayer = players.get(currentPlayerIndex);

        configureLabyrinth();
        
    }


    /**
     * @brief Comprueba si el juego ha terminado. Verifica si hay un ganador en el laberinto.
     * @return true si el juego ha terminado, false en caso contrario.
     */
    public boolean finished() {
        return (labyrinth.haveAWinner());
    }


    public boolean nextStep(Directions preferredDirection){
        //P3



        return true;
    }


    /**
     * @brief Genera una instancia de GameState integrando toda la información del estado del juego.
     * 
     * Proporciona una representación en cadena del estado del juego, incluyendo la
     * configuración del laberinto, los jugadores y los monstruos.
     * 
     * @return El estado actual del juego como un objeto GameState.
     */
    public GameState getGameState(){
        //string con los jugadores
        String players_cad = "";
        for(int i = 0; i < players.size()-1; i++){
            players_cad+= players.get(i).toString();
            players_cad+= ", ";
        }
        players_cad+= players.get(players.size()-1).toString();

        //string con los monstruos
        String monster_cad = "";
        for(int i = 0; i < monsters.size()-1; i++){
            monster_cad+= monsters.get(i).toString();
            monster_cad+= ", ";
        }
        monster_cad+= monsters.get(monsters.size()-1).toString();
        

        GameState gameState= new GameState(labyrinth.toString() , players_cad, monster_cad, currentPlayerIndex, finished() , log  );


        return gameState;
    }



    /**
     * @brief Configura el laberinto.
     * 
     * Inicializa el laberinto y coloca a los jugadores y monstruos en él. También
     * establece la posición inicial de los jugadores.
     */
    private void configureLabyrinth() {
        int nRows = 6;
        int nCols = 7;
        int exitRow = 5;
        int exitCol = 6;

        labyrinth = new Labyrinth(nRows, nCols, exitRow, exitCol);
    
        char[][] base = {
            {'-', 'X', '-', 'M', 'X', 'X', 'X'},
            {'-', 'X', 'M', '-', '-', '-', '-'},
            {'-', 'X', 'X', 'X', '-', 'X', '-'},
            {'M', '-', '-', '-', 'M', '-', '-'},
            {'X', 'X', 'X', 'X', '-', 'X', 'M'},
            {'X', '-', 'M', '-', '-', 'X', 'E'}
        };
        
        // Inicializa el laberinto
        for (int row = 0; row < nRows; row++) {
            for (int col = 0; col < nCols; col++) {
                char cell = base[row][col];
                switch (cell) {
                    case 'X': // Muro
                        labyrinth.addBlock(Orientation.HORIZONTAL, row, col, 1);
                        break;
                    case 'M': // Monstruo
                        Monster m = new Monster("Bombardiro Crocodilo", Dice.randomIntelligence() , Dice.randomStrength());
                        labyrinth.addMonster(row, col, m);
                        monsters.add(m);
                        break;
                    // Salida ya está puesta en el constructor de Labyrinth (ultimos dos argumentos)
                    default:
                        break;
                }
            }
        }
    
        // Nota: esto solo debe ejecutarse si los jugadores ya fueron creados
        if (!players.isEmpty()) {
            labyrinth.spreadPlayers(players.toArray(new Player[0]));
        }
    }
    

    
    /**
     * @brief Cambia al siguiente jugador.
     * 
     * Actualiza el índice del jugador actual para pasar al siguiente jugador en la
     * lista de jugadores. Si se llega al final de la lista, vuelve al primer jugador.
     * 
     */
    private void nextPlayer(){
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        currentPlayer = players.get(currentPlayerIndex);
    }

    
    private Directions actualDirections(Directions preferredDirection){
        //P3
        return preferredDirection;
    }

    private GameCharacter combat(Monster monster){
        //P3
        return null;
    }
    
    private void manageReward(GameCharacter winner){
        //P3
    }

    private void manageResurrection(){
        //P3
    }

    /*  Añade al final del atributo log (concatena cadena al final) el mensaje
     *  indicando que el jugador ha ganado el combate. También añade el indicador de nueva línea al final.
     */
    private void logPlayerWon(){
        String mensaje = "Player#" + currentPlayer.getNumber() + " ha ganado el combate" + "\n";
        log += mensaje;
    }

    /*
     * Añade al final del atributo log (concatena cadena al final) el mensaje
     * indicando que el monstruo ha ganado el combate. 
     * También añade el indicador de nueva línea al final.
     */
    private void logMonsterWon(){
        String mensaje = "El Monstruo ha ganado el combate contra Player#" + currentPlayer.getNumber() + "\n";
        log += mensaje;
    }

    /*
     * Añade al final del atributo log (concatena cadena al final) el mensaje
     * indicando que el jugador ha resucitado. 
     * También añade el indicador de nueva línea al final.
     */
    private void logResurrected(){
        String mensaje = "Player#" + currentPlayer.getNumber() + " ha resucitado" + "\n";   
        log += mensaje;
    }

    /*
     * Añade al final del atributo log (concatena cadena al final) el mensaje
     * indicando que el jugador ha perdido el turno por estar muerto
     * También añade el indicador de nueva línea al final.
     */
    private void logPlayerSkipTurn(){
        String mensaje = "Player#" + currentPlayer.getNumber() + " ha perdido el turno por estar muerto" + "\n";
        log += mensaje;
    }
    
    /*
     * Añade al final del atributo log (concatena cadena al final) el mensaje
     * indicando que el jugador no ha seguido las instrucciones del jugador humano (no fue posible).
     * También añade el indicador de nueva línea al final.
     */
    private void logPlayerNoOrders(){
        String mensaje = "Player#" + currentPlayer.getNumber() + " no ha seguido las instrucciones dadas (no fue posible)" + "\n";
        log += mensaje; 
    }

    /*
     * Añade al final del atributo log (concatena cadena al final) el mensaje
     * indicando que el jugador se ha movido a una celda vacía o no le ha sido posible moverse. 
     * También añade el indicador de nueva línea al final.
     */
    private void logNoMonster(){
        String mensaje = "Player#" + currentPlayer.getNumber() + " se ha movido a una celda vacía o no le ha sido posible moverse" + "\n";
        log += mensaje;
    }

    /*
     * Añade al final del atributo log (concatena cadena al final) el mensaje
     * indicando que se han producido "rounds" de "max" rondas de combate
     * También añade el indicador de nueva línea al final.
     */
    private void logRounds(int rounds, int max){
        String mensaje = "Se han producido " + rounds + " de " + max + " rondas de combate" + "\n";
        log += mensaje;
    }



}
