package pdc_project_2;

import java.sql.*; 

/**
 *
 * @author User
 */
public class PlayerDatabase {
    private Connection conn; 
    
    
    //Connects to the derby database. 
    public PlayerDatabase() throws SQLException {
        conn = DriverManager.getConnection("jdbc:derby:Project_2;create=true");
        createTableIfNotExists();
    }
    
    //creates tables if they dont exist 
    private void createTableIfNotExists() throws SQLException{
        Statement stmt = conn.createStatement();
        try{
            stmt.executeUpdate(
            "CREATE TABLE Players (" +
            "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
            "name VARCHAR(50) UNIQUE, " +
            "chips INT, " +
            "wins INT, " + 
            "losses INT) " 
            );
        } catch (SQLException e){
            if(!"X0Y32".equals(e.getSQLState())) throw e; 
        }
    }
    
    //adds player with chip count to the game with wins and losses at zero
    public void addPlayer(String name, int chips) throws SQLException{
        PreparedStatement ps = conn.prepareStatement(
        "INSERT INTO Players (name, chips, wins, losses ) VALUES (?,?,0,0)"
        );
        ps.setString(1, name);
        ps.setInt(2, chips);
        ps.executeUpdate();
    }
    
    //updates players chips 
    public void updateChips(String name, int chips) throws SQLException{
        PreparedStatement ps = conn.prepareStatement(
        "UPDATE Players SET chips=? WHERE name=?");
        ps.setInt(1, chips);
        ps.setString(2, name);
        ps.executeUpdate();
    }
    
    public void recordGameResult(String name, boolean won) throws SQLException{
        PreparedStatement ps = conn.prepareStatement(
        "UPDATE Players SET " + (won ? "wins=wins+1" : "losses=losses+1 ") + "WHERE  name = ?");
        ps.setString(1, name);
        ps.executeUpdate();
    }
    
     public ResultSet getPlayer(String name) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
            "SELECT * FROM Players WHERE name=?");
        ps.setString(1, name);
        return ps.executeQuery();
    }

    public ResultSet getAllPlayers() throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeQuery("SELECT * FROM Players");
    }

    public ResultSet getTopWinners() throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(
            "SELECT name, wins FROM Players ORDER BY wins DESC FETCH FIRST 5 ROWS ONLY");
    }

    public void close() throws SQLException {
        conn.close();
    }
    
    
    //Code end. 
}