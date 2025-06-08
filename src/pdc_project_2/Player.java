package pdc_project_2;


//Builds the player 
public class Player {
    private String name; 
    private int chips; 
    private int wins; 
    private int losses; 
    private Hand hand; 
    
    //Constructors
    public Player(String name, int chips){
        this.name = name; 
        this.chips = chips; 
        this.wins = 0; 
        this.losses = 0; 
        this.hand = new Hand();
    }
    
    public Player(String name, int chips, int wins, int losses){
        this.name = name; 
        this.chips = chips; 
        this.wins = wins; 
        this.losses = losses; 
        this.hand = new Hand();
    }
    
    public String getName(){return name;}
    public int getChips(){return chips;}
    public void setChips(int chips){this.chips = chips;}
    public int getWins(){return wins;}
    public void setWins(int wins){this.wins = wins;}
    public int getLosses(){return losses;}
    public void setLosses(int losses){this.losses = losses;}
    public Hand getHand(){return hand; }
    public void resetHand(){this.hand= new Hand();}
    
    @Override
    public String toString(){
        return name + "(Chips: " + chips + ",Wins: " +wins + ",Losses: " + losses + ")"; 
    }
}