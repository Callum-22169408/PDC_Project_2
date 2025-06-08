    package pdc_project_2;

import javax.swing.SwingUtilities;

    //Main class where the program is run from. 
    //Enter Username and the game will boot up.
public class PDC_Project_2 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameController());
    }
    
}