package pdc_project_2;

import java.util.ArrayList;
import java.util.List;

//Class constructs the hand of the player and dealer. 
public class Hand {

    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }
     public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getHandValue() {
        int total = 0;
        int aceCount = 0;
        for (Card card : cards) {
            int value = card.getBlackJackValue();
            if (card.isAce()) aceCount++;
            total += value;
        }
        while (total > 21 && aceCount > 0) {
            total -= 10;
            aceCount--;
        }
        return total;
    }
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card.toString()).append(" ");
        }
        sb.append("(Total: ").append(getHandValue()).append(")");
        return sb.toString();
    }
}