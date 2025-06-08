package pdc_project_2;

import javax.swing.*;
import java.awt.*;
import java.util.List;

//GUI Window for the BlackJack game
public class BlackJackGUI extends JFrame {
    private CardPanel dealerPanel, playerPanel;
    private JLabel statusLabel, userInfoLabel;
    private JButton hitButton, standButton, newGameButton;
    private GameController controller;

    public BlackJackGUI(GameController controller) {
        this.controller = controller;
        setTitle("BlackJack Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 550);
        getContentPane().setBackground(new Color(0, 102, 0));
        setLayout(new BorderLayout());

        userInfoLabel = new JLabel();
        updatePlayerInfo(controller.getPlayer());
        userInfoLabel.setOpaque(true);
        userInfoLabel.setBackground(new Color(0, 102, 0));
        userInfoLabel.setForeground(Color.WHITE); 
        add(userInfoLabel, BorderLayout.NORTH);

        dealerPanel = new CardPanel();
        playerPanel = new CardPanel();

        //Set the backround colour to green 
        dealerPanel.setBackground(new Color(0, 102, 0));
        playerPanel.setBackground(new Color(0, 102, 0));

        JPanel handsPanel = new JPanel(new GridLayout(2, 1));
        handsPanel.setBackground(new Color(0, 102, 0));
        handsPanel.add(wrapWithLabel("Dealer:", dealerPanel));
        handsPanel.add(wrapWithLabel("You:", playerPanel));
        add(handsPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(new Color(0, 102, 0));
        hitButton = new JButton("Hit");
        standButton = new JButton("Stand");
        newGameButton = new JButton("New Game");
        statusLabel = new JLabel("Welcome!");
        statusLabel.setForeground(Color.WHITE);

        //Add control buttons and status 
        controlPanel.add(hitButton);
        controlPanel.add(standButton);
        controlPanel.add(newGameButton);
        controlPanel.add(statusLabel);
        add(controlPanel, BorderLayout.SOUTH);

        hitButton.addActionListener(e -> controller.playerHit());
        standButton.addActionListener(e -> controller.playerStand());
        newGameButton.addActionListener(e -> controller.startNewRound());

        setVisible(true);
    }

    //method to wrap the panel with the label 
    private JPanel wrapWithLabel(String title, JComponent comp) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(0, 102, 0));
        JLabel label = new JLabel(title);
        label.setForeground(Color.WHITE);
        panel.add(label, BorderLayout.NORTH);
        panel.add(comp, BorderLayout.CENTER);
        return panel;
    }

    public void updateHands(List<Card> dealerHand, List<Card> playerHand, boolean showDealerFullHand) {
        if (!showDealerFullHand && dealerHand.size() > 0) {
            java.util.List<Card> partial = new java.util.ArrayList<>();
            partial.add(dealerHand.get(0));
            dealerPanel.setHand(partial);
        } else {
            dealerPanel.setHand(dealerHand);
        }
        playerPanel.setHand(playerHand);
    }

    public void setStatus(String status) {
        statusLabel.setText(status);
    }

    public void setGameActive(boolean active) {
        hitButton.setEnabled(active);
        standButton.setEnabled(active);
    }

    //Update the bar at the top of the screen 
    public void updatePlayerInfo(HumanPlayer player) {
        userInfoLabel.setText("Player: " + player.getName() +
                " | Chips: " + player.getChips() +
                " | Wins: " + player.getWins() +
                " | Losses: " + player.getLosses());
    }
}