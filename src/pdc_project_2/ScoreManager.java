package pdc_project_2;

import java.sql.SQLException;

  //Manages wins and losses in the database. 
public class ScoreManager {
    private PlayerDatabase db;

    public ScoreManager(PlayerDatabase db) {
        this.db = db;
    }

    public void recordWin(String playerName) {
        try {
            db.recordGameResult(playerName, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void recordLoss(String playerName) {
        try {
            db.recordGameResult(playerName, false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}