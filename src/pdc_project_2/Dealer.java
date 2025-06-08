package pdc_project_2;

//Subclass of Player for the dealer
public class Dealer extends Player {
    public Dealer(){
        super("Dealer", 0);
    }
    
    public void playTurn(Deck deck){
        while(getHand().getHandValue()<17){
            getHand().addCard(deck.dealCard());
        }
    }
}