package irrgarten;

public class Labyrinth {
    private static final char BLOCK_CHAR = 'X';
    private static final char EMPTY_CHAR = '-';
    private static final char MONSTER_CHAR = 'M';
    private static final char COMBAT_CHAR = 'C';
    private static final char EXIT = 'E';
    private static final int ROW = 0;
    private static final int COL = 1;

    private int nRows;
    private int nCols;
    private int exitRow;
    private int exitCol;

    private char[][] labyrinth;
    private Monster[][] monsters;
    private Player[][] players;

    /**
     * @brief Constructor para inicializar el laberinto.
     * @param nRows Número de filas del laberinto.
     * @param nCols Número de columnas del laberinto.
     * @param exitRow Fila donde se encuentra la salida.
     * @param exitCol Columna donde se encuentra la salida.
     * 
     * Inicializa las matrices del laberinto, monstruos y jugadores, y llena
     * el laberinto con celdas vacías.
     */
    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.exitRow = exitRow;
        this.exitCol = exitCol;

        // Inicializar las matrices con las dimensiones dadas
        labyrinth = new char[nRows][nCols];
        monsters = new Monster[nRows][nCols];
        players = new Player[nRows][nCols];

        // Inicializar el laberinto con celdas vacías
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                labyrinth[i][j] = EMPTY_CHAR;
            }
        }
    }

    /**
     * @brief Distribuye jugadores en el laberinto.
     * @param players Array de jugadores a distribuir.
     * 
     * Este método está pendiente de implementación (P3).
     */
    public void spreadPlayers(Player[] players) {
        // P3
    }

    /**
     * @brief Verifica si hay un ganador.
     * @return true si un jugador está en la posición de salida, false en caso contrario.
     */
    public boolean haveAWinner() {
        if (players[exitRow][exitCol] != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @brief Representa el laberinto como una cadena.
     * @return Una representación en texto del laberinto.
     * 
     */
    public String toString() {
        String str = "";
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                str += labyrinth[i][j];  
                str += " ";
            }
            str += "\n";
        }
        return str;
    }

    /**
     * @brief Añade un monstruo al laberinto.
     * @param row Fila donde se colocará el monstruo.
     * @param col Columna donde se colocará el monstruo.
     * @param monster Objeto Monster a añadir.
     * 
     * Lanza una excepción si la posición es inválida o ya está ocupada.
     */
    public void addMonster(int row, int col, Monster monster) {
        if (!posOK(row, col)) {
            throw new IllegalArgumentException("Invalid position");
        }

        if (!emptyPos(row, col)) {
            throw new IllegalArgumentException("Position already occupied");
        }

        // Comprobar que son referencias
        monster.setPos(row, col);
        monsters[row][col] = monster;
        labyrinth[row][col] = MONSTER_CHAR;
    }

    /**
     * @brief Mueve un jugador en una dirección específica.
     * @param direction Dirección en la que se moverá el jugador.
     * @param player Jugador que se moverá.
     * @return Un objeto Monster si el jugador encuentra un monstruo, null en caso contrario.
     * 
     * Este método está pendiente de implementación (P3).
     */
    public Monster putPlayer(Directions direction, Player player) {
        return null; // P3
    }

    /**
     * @brief Añade un bloque al laberinto.
     * @param orientation Orientación del bloque (horizontal o vertical).
     * @param startRow Fila inicial del bloque.
     * @param startCol Columna inicial del bloque.
     * @param length Longitud del bloque.
     * 
     * Este método está pendiente de implementación (P3).
     */
    public void addBlock(Orientation orientation, int startRow, int startCol, int length) {
        // P3
    }

    /**
     * @brief Obtiene los movimientos válidos desde una posición.
     * @param row Fila actual.
     * @param col Columna actual.
     * @return Un array de direcciones válidas.
     * 
     * Este método está pendiente de implementación (P3).
     */
    public Directions[] validMoves(int row, int col) {
        // P3
        return null;
    }

    /**
     * @brief Verifica si una posición está dentro de los límites del laberinto.
     * @param row Fila a verificar.
     * @param col Columna a verificar.
     * @return true si la posición es válida, false en caso contrario.
     */
    private boolean posOK(int row, int col) {
        return row >= 0 && row < nRows && col >= 0 && col < nCols;
    }

    /**
     * @brief Verifica si una posición está vacía.
     * @param row Fila a verificar.
     * @param col Columna a verificar.
     * @return true si la posición está vacía, false en caso contrario.
     */
    private boolean emptyPos(int row, int col) {
        return labyrinth[row][col] == EMPTY_CHAR;
    }

    /**
     * @brief Verifica si una posición contiene un monstruo.
     * @param row Fila a verificar.
     * @param col Columna a verificar.
     * @return true si la posición contiene un monstruo, false en caso contrario.
     */
    private boolean monsterPos(int row, int col) {
        return labyrinth[row][col] == MONSTER_CHAR;
    }

    /**
     * @brief Verifica si una posición es la salida.
     * @param row Fila a verificar.
     * @param col Columna a verificar.
     * @return true si la posición es la salida, false en caso contrario.
     */
    private boolean exitPos(int row, int col) {
        return labyrinth[row][col] == EXIT;
    }

    /**
     * @brief Verifica si una posición es de combate.
     * @param row Fila a verificar.
     * @param col Columna a verificar.
     * @return true si la posición es de combate, false en caso contrario.
     */
    private boolean combatPos(int row, int col) {
        return labyrinth[row][col] == COMBAT_CHAR;
    }

    /**
     * @brief Actualiza el estado de una posición antigua en el laberinto.
     * @param row Fila de la posición a actualizar.
     * @param col Columna de la posición a actualizar.
     * 
     * Si la posición es válida y contiene un combate, la marca como una posición
     * de monstruo. De lo contrario, la marca como una posición vacía.
     */
    private void updateOldPos(int row, int col) {
        if (posOK(row, col)) { 
            if (combatPos(row, col)) {
                labyrinth[row][col] = MONSTER_CHAR;
            } else {

                labyrinth[row][col] = EMPTY_CHAR;
            }
        }
    }

    /**
     * @brief Verifica si se puede pisar una posición.
     * @param row Fila a verificar.
     * @param col Columna a verificar.
     * @return true si se puede pisar, false en caso contrario.
     */
    private boolean canStepOn(int row, int col) {
        return posOK(row, col) && (emptyPos(row, col) || exitPos(row, col) || combatPos(row, col));
    }

    /**
     * @brief Convierte una dirección en una nueva posición.
     * @param row Fila actual.
     * @param col Columna actual.
     * @param direction Dirección a la que se desea mover.
     * @return Un array con la nueva posición [fila, columna].
     */
    private int[] dir2Pos(int row, int col, Directions direction) {
        int[] pos = new int[2];
        pos[ROW] = row;
        pos[COL] = col;

        switch (direction) {
            case UP:
                pos[ROW]--;
                break;
            case DOWN:
                pos[ROW]++;
                break;
            case LEFT:
                pos[COL]--;
                break;
            case RIGHT:
                pos[COL]++;
                break;
        }

        return pos;
    }

    /**
     * @brief Encuentra una posición vacía aleatoria en el laberinto.
     * @return Un array con la posición vacía [fila, columna].
     */
    private int[] randomEmptyPos() {
        int[] pos = new int[2];
        do {
            pos[ROW] = Dice.randomPos(nRows);
            pos[COL] = Dice.randomPos(nCols);
        } while (!emptyPos(pos[ROW], pos[COL]));

        return pos;
    }

    /**
     * @brief Mueve un jugador de una posición a otra en el laberinto.
     * @param oldRow Fila actual del jugador.
     * @param oldCol Columna actual del jugador.
     * @param row Nueva fila del jugador.
     * @param col Nueva columna del jugador.
     * @param player Jugador a mover.
     * @return Un objeto Monster si el jugador encuentra un monstruo, null en caso contrario.
     * 
     * Este método está pendiente de implementación (P3).
     */
    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player) {
        return null; // P3
    }
}
