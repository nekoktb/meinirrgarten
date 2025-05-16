package irrgarten;

import java.util.ArrayList;
import java.util.Random;

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
        
        for (int i = 0; i < nPlayers; i++) {
            players.add(new Player( (char)(i+'0') , Dice.randomIntelligence() , Dice.randomStrength() )); 
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
        this.log = "";
        boolean dead = currentPlayer.dead();
        if (!dead) {
            Directions direction = actualDirection(preferredDirection);
            
            if (direction != preferredDirection) 
                logPlayerNoOrders();
            
            Monster monster = labyrinth.putPlayer(direction, currentPlayer);

            if (monster == null)
                logNoMonster();
            else {
                GameCharacter winner = combat(monster);
                manageReward(winner);
            }
        }
        else 
            manageResurrection();

        boolean endGame = finished();

        if (!endGame)
            nextPlayer();


        return endGame;
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
            players_cad+= "\n";
        }
        players_cad+= players.get(players.size()-1).toString();

        //string con los monstruos
        String monster_cad = "";
        for(int i = 0; i < monsters.size()-1; i++){
            monster_cad+= monsters.get(i).toString();
            monster_cad+= "\n";
        }
        monster_cad+= monsters.get(monsters.size()-1).toString();
        

        GameState gameState= new GameState(labyrinth.toString() , players_cad, monster_cad, currentPlayerIndex, finished() , log );


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
        String monstruos[] = {"Bombardino Crocodilo", "Bombardini Guzzini", "Tun Tun Tun Sahur", "Tralalero Tralala"};
        Random random = new Random();
        for (int row = 0; row < nRows; row++) {
            for (int col = 0; col < nCols; col++) {
                char cell = base[row][col];
                switch (cell) {
                    case 'X': // Muro
                        labyrinth.addBlock(Orientation.HORIZONTAL, row, col, 1);
                        break;
                    case 'M': // Monstruo
                        Monster m = new Monster(monstruos[random.nextInt(4)], Dice.randomIntelligence() , Dice.randomStrength());
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
            labyrinth.spreadPlayers(players);
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

    /**
    * @brief Obtiene la dirección real en la que se moverá el jugador.
    *
    * Este método consulta el laberinto para obtener las direcciones válidas según
    * la posición actual del jugador y, a partir de la dirección preferida, determina
    * la dirección real de movimiento utilizando la lógica interna del jugador.
    *
    * @param preferredDirection La dirección preferida por el jugador.
    * @return La dirección final en la que se moverá el jugador.
    */
    private Directions actualDirection(Directions preferredDirection){
        int currentRow = currentPlayer.getRow();
        int currentCol = currentPlayer.getCol();
        ArrayList<Directions> validMoves = labyrinth.validMoves(currentRow, currentCol);
        Directions output = currentPlayer.move(preferredDirection, validMoves);
        return output;
    }

    private GameCharacter combat(Monster monster){
        int rounds = 0;
        
        GameCharacter winner = GameCharacter.PLAYER;
        float playerAttack = currentPlayer.attack();
        boolean lose = monster.defend(playerAttack);

        while (!lose && rounds < MAX_ROUNDS) {
            winner = GameCharacter.MONSTER;
            rounds++;
            float monsterAttack = monster.attack();
            lose = currentPlayer.defend(monsterAttack);

            if(!lose) {
                playerAttack = currentPlayer.attack();
                winner = GameCharacter.PLAYER;
                lose = monster.defend(playerAttack);
            }
        }
        logRounds(rounds, MAX_ROUNDS);
        return winner;
    }
    
    private void manageReward(GameCharacter winner){
        //P3
        if (winner == GameCharacter.PLAYER) {
            currentPlayer.receiveReward();
            logPlayerWon();
        }
        else 
            logMonsterWon();
    }

    private void manageResurrection(){
        //P3
        boolean resurrect = Dice.resurrectPlayer();
        if (resurrect) {
            FuzzyPlayer fuzzyPlayer = new FuzzyPlayer(currentPlayer);
            fuzzyPlayer.resurrect();
            currentPlayer = fuzzyPlayer;

            labyrinth.setPlayerPos(currentPlayer.getRow(), currentPlayer.getCol(), fuzzyPlayer);

            logResurrected();
        } else {
            logPlayerSkipTurn();
        }
    }

    /*  Añade al final del atributo log (concatena cadena al final) el mensaje
     *  indicando que el jugador ha ganado el combate. También añade el indicador de nueva línea al final.
     */
    private void logPlayerWon(){
        String mensaje = "Player" + currentPlayer.getNumber() + " ha ganado el combate" + "\n";
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
        String mensaje = "Player" + currentPlayer.getNumber() + " ha resucitado" + "\n";   
        log += mensaje;
    }

    /*
     * Añade al final del atributo log (concatena cadena al final) el mensaje
     * indicando que el jugador ha perdido el turno por estar muerto
     * También añade el indicador de nueva línea al final.
     */
    private void logPlayerSkipTurn(){
        String mensaje = "Player" + currentPlayer.getNumber() + " ha perdido el turno por estar muerto" + "\n";
        log += mensaje;
    }
    
    /*
     * Añade al final del atributo log (concatena cadena al final) el mensaje
     * indicando que el jugador no ha seguido las instrucciones del jugador humano (no fue posible).
     * También añade el indicador de nueva línea al final.
     */
    private void logPlayerNoOrders(){
        String mensaje = "Player" + currentPlayer.getNumber() + " no ha seguido las instrucciones dadas (no fue posible)" + "\n";
        log += mensaje; 
    }

    /*
     * Añade al final del atributo log (concatena cadena al final) el mensaje
     * indicando que el jugador se ha movido a una celda vacía o no le ha sido posible moverse. 
     * También añade el indicador de nueva línea al final.
     */
    private void logNoMonster(){
        String mensaje = "Player" + currentPlayer.getNumber() + " se ha movido a una celda vacía (sin monstruo) o no le ha sido posible moverse" + "\n";
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
