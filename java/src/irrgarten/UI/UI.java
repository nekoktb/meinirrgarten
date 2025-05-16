package irrgarten.UI;


import irrgarten.Directions;
import irrgarten.GameState;

/**
 *
 * @author nekok
 */
public interface UI {
    public Directions nextMove(); 
    public void showGame(GameState gameState);
}
