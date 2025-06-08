package pdc_project_2;

//Subclass of Player
public class HumanPlayer extends Player {
    public HumanPlayer(String name, int chips){
     super(name, chips);   
    }
    
    public HumanPlayer(String name, int chips, int wins, int losses){
        super(name, chips, wins, losses);
    }
}