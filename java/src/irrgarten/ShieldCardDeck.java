package irrgarten;

public class ShieldCardDeck extends CardDeck<Shield> {

    @Override
    protected void addCards() {

        //final int MAX_CARDS = 10; //Número máximo de cartas en la baraja

        /** 
        for (int i = 0; i < MAX_CARDS; i++) {
            this.addCard(new Shield(Dice.randomStrength(), Dice.usesLeft()));
        }
        */


        //Rellenar como queramos para probar primero
        this.addCard(new Shield(1, 5));
        this.addCard(new Shield(2, 5));
        this.addCard(new Shield(3, 5));
        this.addCard(new Shield(4, 5));
     
    }
    
}
