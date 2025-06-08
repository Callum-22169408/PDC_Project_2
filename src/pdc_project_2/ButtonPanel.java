package pdc_project_2;

import javax.swing.*;
import java.awt.*;

   //Button panel 
public class ButtonPanel extends JPanel {
    //Buttons for hitting, standing, and quitting
    public JButton hitButton = new JButton("Hit");
    public JButton standButton = new JButton("Stand");
    public JButton quitButton = new JButton("Quit");

    
    public ButtonPanel() {
        setLayout(new FlowLayout());
        //Adding the buttons
        add(hitButton);
        add(standButton);
        add(quitButton);
    }
}