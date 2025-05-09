package irrgarten;

import java.util.ArrayList;
import java.util.Collections;

abstract class CardDeck <T extends CombatElement> {

    private ArrayList<T> cardeck; ///< Baraja de elementos de combate.

    /**
     * Constructor de la clase abstracta CardDeck.
     * @param cardeck ArrayList de elementos de combate.
     */
    public CardDeck(){
        cardeck = new ArrayList<T>();
    }

    protected abstract void addCards(); //ABSTRACTO: implementar en las subclases

    protected void addCard(T card) {
        this.cardeck.add(card);
    }

    public T nextCard(){
        if (this.cardeck.isEmpty()) {
            this.addCards();
            Collections.shuffle(this.cardeck);
        }
        return this.cardeck.remove(0);
    }
    
}
