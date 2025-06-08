package pdc_project_2;


//Card class works with rank and suit, makes sure the player doesn't bust on an 
//ace and changes it to 1 instead of 11. 
public class Card {
    private final String rank; 
    private final String suit; 

    //Constructor for the deck
    public Card(String rank, String suit){
        this.rank = rank; 
        this.suit = suit; 
    }

    //Returns the value of the face and ace cards e.g face =10 ace =11
    public int getBlackJackValue (){
        switch(rank){
            case "J":
            case "Q":
            case "K":
                return 10; 
            case "A": 
                return 11; 
            default: 
                return Integer.parseInt(rank);
        }
    }

    //Is it an ace or not? that's the real question. 
    public boolean isAce (){
        return "A".equals(rank);
    }

    // returns what the card actually is 
    @Override
    public String toString(){
        return rank + "-" + suit; 
    }

    public String getImageResourcePath() {
        return "/pdc_project_2/cards/" + toString() + ".png";
    }
}