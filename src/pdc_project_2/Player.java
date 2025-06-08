package pdc_project_2;

/**
 *
 * @author User
 */

public class Player {
    
    //Variables
    private String name; 
    private int chips; 
    private int wins; 
    private int losses; 
    private Hand hand; 
    
    //Constructor 
    public Player(String name, int Chips){
        this.name = name; 
        this.chips = chips; 
        this.wins = 0; 
        this.losses = 0; 
        this.hand = new Hand();
    }
    
    //Constructor to load player in 
    public Player(String name, int chips, int wins, int losses){
        this.name = name; 
        this.chips = chips; 
        this.wins = wins; 
        this.losses = losses; 
        this.hand = new Hand();
    }
    
    //starting to link the player class to sql functionality 
    public static Player fromResultSet(java.sql.ResultSet rs) throws java.sql.SQLException{
        String name = rs.getString("name");
        int chips = rs.getInt("chips");
        int wins = rs.getInt("wins");
        int losses = rs.getInt("losses");
        return new Player(name, chips, wins, losses);
    }
    
    //Getter and setters
    public String getName(){return name;}
    public int getChips(){return chips;}
    public void setChips(int chips){this.chips = chips;}
    public int getWins(){return wins;}
    public void setWins(int wins){this.wins = wins;}
    public int getLosses(){return losses;}
    public void setLosses(int losses){this.losses = losses;}
    
    //new hand
    public void resetHand(){
        this.hand = hand; 
    }
    
    @Override
    public String toString(){
        return name + "(Chips: " + chips + ",Wins: " +wins + ",Losses: " + losses + ")"; 
    }

//End of code! 
}
