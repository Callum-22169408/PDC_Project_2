package pdc_project_2;

import javax.swing.*;
import java.awt.*;
import java.util.List;

//GUI Window for the BlackJack game
public class BlackJackGUI extends JFrame {
    private JLabel playerScoreLabel;
    private JLabel dealerScoreLabel;
    private JTextArea rulesTextArea;
    private CardPanel dealerPanel, playerPanel;
    private JLabel statusLabel, userInfoLabel;
    private JButton hitButton, standButton, newGameButton, exitButton; // Added exitButton
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

        playerScoreLabel = new JLabel("Your Score: 0");
        playerScoreLabel.setForeground(Color.WHITE);
        dealerScoreLabel = new JLabel("Dealer Score: 0");
        dealerScoreLabel.setForeground(Color.WHITE);

        JPanel dealerScorePanel = new JPanel(new BorderLayout());
        dealerScorePanel.setBackground(new Color(0, 102, 0));
        dealerScorePanel.add(dealerScoreLabel, BorderLayout.CENTER);

        JPanel playerScorePanel = new JPanel(new BorderLayout());
        playerScorePanel.setBackground(new Color(0, 102, 0));
        playerScorePanel.add(playerScoreLabel, BorderLayout.CENTER);

        JPanel dealerSection = new JPanel(new BorderLayout());
        dealerSection.setBackground(new Color(0, 102, 0));
        dealerSection.add(dealerScorePanel, BorderLayout.NORTH);
        dealerSection.add(dealerPanel, BorderLayout.CENTER);

        JPanel playerSection = new JPanel(new BorderLayout());
        playerSection.setBackground(new Color(0, 102, 0));
        playerSection.add(playerScorePanel, BorderLayout.NORTH);
        playerSection.add(playerPanel, BorderLayout.CENTER);

        JPanel handsPanel = new JPanel(new GridLayout(2, 1));
        handsPanel.setBackground(new Color(0, 102, 0));
        handsPanel.add(wrapWithLabel("Dealer:", dealerSection));
        handsPanel.add(wrapWithLabel("You:", playerSection));
        add(handsPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(new Color(0, 102, 0));
        hitButton = new JButton("Hit");
        standButton = new JButton("Stand");
        newGameButton = new JButton("New Game");
        exitButton = new JButton("Exit"); // Added exit button
        statusLabel = new JLabel("Welcome!");
        statusLabel.setForeground(Color.WHITE);

        //Add control buttons and status 
        controlPanel.add(hitButton);
        controlPanel.add(standButton);
        controlPanel.add(newGameButton);
        controlPanel.add(exitButton); // Add the exit button to the panel
        controlPanel.add(statusLabel);
        add(controlPanel, BorderLayout.SOUTH);

        hitButton.addActionListener(e -> controller.playerHit());
        standButton.addActionListener(e -> controller.playerStand());
        newGameButton.addActionListener(e -> controller.startNewRound());
        exitButton.addActionListener(e -> System.exit(0)); // Exit action listener

        rulesTextArea = new JTextArea();
        rulesTextArea.setText(
            "Blackjack Rules:\n"
          + "- Try to get as close to 21 as possible without going over.\n"
          + "- Number cards are worth their value; face cards are 10; Ace is 1 or 11.\n"
          + "- Dealer must hit on 16 or less and stand on 17 or more.\n"
          + "- If you go over 21, you bust and lose."
        );
        rulesTextArea.setEditable(false);
        rulesTextArea.setLineWrap(true);
        rulesTextArea.setWrapStyleWord(true);
        rulesTextArea.setBackground(new Color(230, 230, 230));
        rulesTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        rulesTextArea.setMargin(new Insets(10, 10, 10, 10));
        rulesTextArea.setPreferredSize(new Dimension(260, 0));
        add(rulesTextArea, BorderLayout.EAST);

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

    public void updateHands(Hand dealerHandObj, Hand playerHandObj, boolean showDealerFullHand) {
        if (!showDealerFullHand && dealerHandObj.getCards().size() > 0) {
            java.util.List<Card> partial = new java.util.ArrayList<>();
            partial.add(dealerHandObj.getCards().get(0));
            dealerPanel.setHand(partial);
        } else {
            dealerPanel.setHand(dealerHandObj.getCards());
        }
        playerPanel.setHand(playerHandObj.getCards());

        int dealerValue = showDealerFullHand
                ? dealerHandObj.getHandValue()
                : (dealerHandObj.getCards().size() > 0
                    ? new Hand(java.util.Collections.singletonList(dealerHandObj.getCards().get(0))).getHandValue()
                    : 0);
        int playerValue = playerHandObj.getHandValue();
        updateScores(dealerValue, playerValue);
    }

    // Update score labels
    public void updateScores(int dealerScore, int playerScore) {
        dealerScoreLabel.setText("Dealer Score: " + dealerScore);
        playerScoreLabel.setText("Your Score: " + playerScore);
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