package pdc_project_2;

//imports so I can use these important functions 
import java.util.ArrayList; 
import java.util.Collections; 

/**
 *
 * @author User
 */

//Forms the deck and shuffles the deck 
public class Deck {
    private ArrayList<Card> cards; 

    //Constructing the deck with 52 cards
    public Deck(){
        cards = new ArrayList<> (); 
        String[] ranks = {"A", "2","3", "4","5", "6","7", "8","9", "10","J", "Q","K"};
        String[] suits = {"C", "D", "H", "S"};
        
        for (String suit : suits) {
            for(String rank : ranks){
                cards.add(new Card(rank, suit));
            }
        }
    }
    
    // Randomises the order of cards, essential for a fair game 
    public void shuffleDeck(){
        Collections.shuffle(cards);
    }
    
    //removes the top card off the deck
    public Card dealCard(){
        return cards.remove(cards.size()-1);
    }
    
    //
    public int size(){
        return cards.size();
    }
    
 
 }
