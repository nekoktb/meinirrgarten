/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import irrgarten.Controller.Controller;
import irrgarten.UI.GraphicUI;

/**
 *
 * @author nekok
 */
public class TestP1 {
    public static void main(String[] args) {
        Game game = new Game(3);
        GraphicUI ui = new GraphicUI();
        Controller controller = new Controller(game, ui);
        
        controller.play();
            
    }
}
