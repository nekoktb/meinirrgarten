
package irrgarten.UI;

import irrgarten.Directions;
import irrgarten.GameState;
import java.util.Scanner;


public class TextUI {
    
    private static Scanner in = new Scanner(System.in);
    
    private char readChar() {
        String s = in.nextLine();
        return s.charAt(0);
    }
    

    public Directions nextMove() {
        System.out.print("Where? ");
        
        Directions direction = Directions.DOWN;
        boolean gotInput = false;
        
        while (!gotInput) {
            char c = readChar();
            switch(c) {
                case 'w':
                    System.out.print(" UP\n");
                    direction = Directions.UP;
                    gotInput = true;
                    break;
                case 's':
                    System.out.print(" DOWN\n");
                    direction = Directions.DOWN;
                    gotInput = true;
                    break;
                case 'd':
                    System.out.print("RIGHT\n");
                    direction = Directions.RIGHT;
                    gotInput = true;
                    break;
                case 'a':
                    System.out.print(" LEFT\n");
                    direction = Directions.LEFT;
                    gotInput = true;    
                    break;
            }
        }    
        return direction;
    }
    
    public void showGame(GameState gameState) {  

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("========== IRRGARTEN ==========");
        System.out.println("¿Ganador?: " + gameState.getWinner());
        System.out.println("Jugador actual: " + gameState.getCurrentPlayer());
        System.out.println();
        System.out.println("------ Laberinto ------");
        System.out.println(gameState.getLabyrinth().toString());
        System.out.println();
        System.out.println("------ Jugadores ------");
        System.out.println(gameState.getPlayers().toString());
        System.out.println();
        System.out.println("------ Monstruos ------");
        System.out.println(gameState.getMonsters().toString());
        System.out.println();
        System.out.println("------ Registro ------");
        System.out.println(gameState.getLog());
        System.out.println("===============================");
    }
    
}
