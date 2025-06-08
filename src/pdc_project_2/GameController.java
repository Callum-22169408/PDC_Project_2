package pdc_project_2;

import java.sql.*;
import javax.swing.*;


//Game controller class to control the GUI and scoremanager
public class GameController {
    private BlackJackGame game;
    private BlackJackGUI gui;
    private PlayerDatabase db;
    private ScoreManager scoreManager;
    private HumanPlayer player;

    public GameController() {
        try {
            db = new PlayerDatabase();
            scoreManager = new ScoreManager(db);

            // Username prompts pops up for the user 
            player = UsernamePrompt.promptForPlayer(db);
            game = new BlackJackGame(player);
            gui = new BlackJackGUI(this);
            startNewRound();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error initializing database: " + e.getMessage());
            System.exit(1);
        }
    }
    //getter
    public HumanPlayer getPlayer() {
        return player;
    }

    //Hit or stand for the user
    public void startNewRound() {
        game.startNewRound();
        gui.setStatus("Your turn. Hit or Stand?");
        gui.setGameActive(true);
        gui.updatePlayerInfo(player);
        updateGUI(false);
    }

    //in the hit case
    public void playerHit() {
        game.getPlayer().getHand().addCard(game.getDeck().dealCard());
        updateGUI(false);

        if (game.getPlayer().getHand().getHandValue() > 21) {
            gui.setStatus("You bust! Dealer wins.");
            gui.setGameActive(false);
            endRound(false);
        }
    }

    //in the stand case
    public void playerStand() {
        gui.setGameActive(false);
        // Dealer's turn
        game.getDealer().playTurn(game.getDeck());
        updateGUI(true);

        int playerValue = game.getPlayer().getHand().getHandValue();
        int dealerValue = game.getDealer().getHand().getHandValue();

        String result;
        boolean playerWon;
        if (dealerValue > 21 || playerValue > dealerValue) {
            result = "You win!";
            playerWon = true;
        } else if (playerValue < dealerValue) {
            result = "Dealer wins!";
            playerWon = false;
        } else {
            result = "Push!";
            playerWon = false;
        }
        gui.setStatus(result);
        endRound(playerWon);
    }

    private void endRound(boolean playerWon) {
        try {
            // Update the wins and the losses in the database
            if (playerWon) {
                scoreManager.recordWin(player.getName());
                player.setWins(player.getWins() + 1);
                player.setChips(player.getChips() + 10); 
            } else {
                scoreManager.recordLoss(player.getName());
                player.setLosses(player.getLosses() + 1);
                player.setChips(Math.max(player.getChips() - 10, 0)); 
            }
            db.updateChips(player.getName(), player.getChips());
            gui.updatePlayerInfo(player);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to update player stats: " + e.getMessage());
        }
        updateGUI(true);
    }

    private void updateGUI(boolean showDealerFullHand) {
        gui.updateHands(
            game.getDealer().getHand().getCards(),
            game.getPlayer().getHand().getCards(),
            showDealerFullHand
        );
    }
}