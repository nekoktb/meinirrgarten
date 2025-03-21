package irrgarten;

public class Labyrinth {
    private static final char BLOCK_CHAR = 'X';
    private static final char EMPTY_CHAR = '-';
    private static final char MONSTER_CHAR = 'M';
    private static final char COMBAT = 'C';
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

    // Constructor para inicializar el laberinto
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

    public void spreadPlayers(Player[] players) {
       // P3
        }

    public boolean haveAWinner(){
        if (players[exitRow][exitCol] != null) {
            return true;
        } else {
            return false;
        }
    }

    public String toString(){
        return "";
    }

    public void addMonster(int row, int col, Monster monster){
        if(row < 0 || row >= nRows || col < 0 || col >= nCols){
            throw new IllegalArgumentException("Invalid position");
        }

        if(labyrinth[row][col] != EMPTY_CHAR){
            throw new IllegalArgumentException("Position already occupied");
        }

        // Comprobar que son referencias
        monster.setPos(row, col);
        monsters[row][col] = monster;
        labyrinth[row][col] = MONSTER_CHAR;
    }

    public Monster putPlayer(Directions direction, Player player){
        return null; // P3
    }
    
    public void addBlock(Orientation orientation, int startRow, int startCol, int length){
        // P3

    }

    public Directions[] validMoves(int row, int col){
        // P3
        return null;
    }

}


