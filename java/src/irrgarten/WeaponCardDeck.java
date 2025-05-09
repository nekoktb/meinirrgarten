package irrgarten;

public class WeaponCardDeck extends CardDeck<Weapon> {

    @Override
    protected void addCards() {

        //final int MAX_CARDS = 10; //Número máximo de cartas en la baraja

        /** 
        for (int i = 0; i < MAX_CARDS; i++) {
            this.addCard(new Weapon(Dice.randomStrength(), Dice.usesLeft()));
        }
        */

        //Rellenar como queramos para probar primero
        this.addCard(new Weapon(1, 1));


    }
    
}
