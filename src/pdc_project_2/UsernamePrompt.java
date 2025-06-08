package pdc_project_2;

import javax.swing.*;
import java.sql.*;

//Class deals with the implementation of a start screen to enter the username 
public class UsernamePrompt {

    public static HumanPlayer promptForPlayer(PlayerDatabase db) {
        while (true) {
            String username = JOptionPane.showInputDialog(
                    null,
                    "Enter your username:",
                    "BlackJack Login",
                    JOptionPane.QUESTION_MESSAGE
            );
            if (username == null) {
                // User cancelled
                System.exit(0);
            }
            username = username.trim();
            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Username cannot be empty.");
                continue;
            }
            try {
                ResultSet rs = db.getPlayer(username);
                if (rs.next()) {
                    int chips = rs.getInt("chips");
                    int wins = rs.getInt("wins");
                    int losses = rs.getInt("losses");
                    JOptionPane.showMessageDialog(
                        null,
                        String.format("Welcome back, %s!\nChips: %d\nWins: %d\nLosses: %d", username, chips, wins, losses)
                    );
                    return new HumanPlayer(username, chips, wins, losses);
                } else {
                    db.addPlayer(username, 100);
                    JOptionPane.showMessageDialog(
                        null,
                        String.format("Welcome, %s! New account created with 100 chips.", username)
                    );
                    return new HumanPlayer(username, 100, 0, 0);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}