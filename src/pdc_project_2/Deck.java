package pdc_project_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    //Constructor
    public Deck() {
        initializeDeck();
        shuffle();
    }

    //Initializes the deck with 52 cards
    private void initializeDeck() {
        cards = new ArrayList<>();
        String[] suits = {"H", "D", "C", "S"};
        String[] ranks = {
            "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"
        };
        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    //Shuffles the deck
    public void shuffle() {
        Collections.shuffle(cards);
    }

    //Deals the top card. If deck is empty, reshuffles a new deck first!
    public Card dealCard() {
        if (cards.isEmpty()) {
            // Reinitialize and shuffle the deck if empty to prevent errors
            initializeDeck();
            shuffle();
        }
        return cards.remove(0);
    }

    //Returns the number of cards left in the deck
    public int size() {
        return cards.size();
    }
}