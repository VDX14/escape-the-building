package game.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * This class handles all database actions for the game. 
 * Connects the derby database and stored player's attack power.
 */
public class DatabaseManager {
	
	private static boolean tableExistsMessagePrinted = false;
	
	//Database URL and creates the database if non-existent. 
		private static final String URL = "jdbc:derby:AdventureDB;create=true";

		/**
		 * Method creates table called player_stats.
		 * Table will store player's ID and attack power.
		 */
		public static void createTable() {

	        try (Connection conn = DriverManager.getConnection(URL);
	             Statement stmt = conn.createStatement()) {

	            //SQL command to create table.
	            String sql = "CREATE TABLE player_stats (" +
	                         "id INT PRIMARY KEY, " +
	                         "attack_power INT)";

	            //Run the SQL command.
	            stmt.executeUpdate(sql);
	            
	            //Print message to console if table is already created. 
	            System.out.println("Table is created successfully.");

	        } catch (SQLException e) {
	        	
	        	 // Print "Table already exists" only the first time
	        	// X0Y32 = table exists
	            if ("X0Y32".equals(e.getSQLState())) { 
	            	
	                // Print this message only once
	                if (!tableExistsMessagePrinted) {
	                    System.out.println("Table already exists.");
	                    tableExistsMessagePrinted = true;
	                }
	                
	            } else {
	            	
	                // For other errors, print normally
	                e.printStackTrace();
	            }
	        }
		}
	        
		/**
		 * This method adds a new player to player_stats table.
		 * Saves player's ID and attack power into database.
		 * 
		 * @param id the unique ID number of the player.
		 * @param attackPower the player's attack power value. 
		 */
		public static void insertPlayer(int id, int attackPower) {
			
			//SQL statement to insert a new player into the table. 
			String sql = "INSERT INTO player_stats VALUES (?,?)";
			
			try (Connection conn = DriverManager.getConnection(URL);
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {

		            //Set ID as first placeholder and attackPower as second placeholder.
		            pstmt.setInt(1, id);
		            pstmt.setInt(2, attackPower);

		            //Execute SQL insert.
		            pstmt.executeUpdate();
		            
		            //Print message to console if player is added successfully.
		            System.out.println("Player is inserted into database.");

		        } catch (SQLException e) {
		        	
		            //If player already exists, print this message to console. 
		            System.out.println("Player already exists in database.");
		        }
		}
		
		/**
	     * Checks if a player with a given ID exists in the database.
	     *
	     * @param id the player's ID
	     * @return true if player exists, false otherwise
	     */
	    public static boolean playerExists(int id) {
	    	
	    	//SQL statement to check if a player with this ID exist.
	        String sql = "SELECT 1 FROM player_stats WHERE id = ?";
	        
	        try (Connection conn = DriverManager.getConnection(URL);
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        	//Set the player's ID in the SQL query.
	            pstmt.setInt(1, id);
	            
	            //Execute query and get the result.
	            ResultSet rs = pstmt.executeQuery();
	            
	            //If there is at least one row, player exist. 
	            return rs.next();

	        } catch (SQLException e) {
	        	
	        	//Print error details if something goes wrong. 
	            e.printStackTrace();
	        }
	        
	        //If an error happens, assume player doesn't exist. 
	        return false; 
	    }
		
		/**
		 * This method gets the attack power of a player from the database. 
		 * 
		 * @param id the unique ID of the player. 
		 * @return the player's attack power if found.
		 */
		public static int getAttackPower(int id) {

			//SQL statement to get attack_power from the table. 
		        String sql = "SELECT attack_power FROM player_stats WHERE id = ?";

		        try (Connection conn = DriverManager.getConnection(URL);
		             PreparedStatement pstmt = conn.prepareStatement(sql)) {

		        	//Put the ID value into the placeholder. 
		            pstmt.setInt(1, id);
		            
		          //Execute query and get the result.. 
		            ResultSet rs = pstmt.executeQuery();

		            //If player with that ID already exist. 
		            if (rs.next()) {
		            	
		            	//Return the attack_power value from the table. 
		                return rs.getInt("attack_power");
		            }

		        } catch (SQLException e) {
		        	
		        	//Print error details if something goes wrong. 
		            e.printStackTrace();
		        }

		        //If player not found or error, return default attack power of 10. 
		        return 10;
		    }
		
		/**
		 * Updates the player's attack power in the database. 
		 * 
		 * @param id the player's ID.
		 * @param newPower the new attack power value.
		 */
		public static void updateAttackPower(int id, int newPower) {
			
			//SQL statement to update attack_power for a specific player. 
			String sql = "UPDATE player_stats SET attack_power = ? WHERE id = ?";

	        try (Connection conn = DriverManager.getConnection(URL);
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        	//Set first placeholder to the new attack power and second to player's ID. 
	            pstmt.setInt(1, newPower);
	            pstmt.setInt(2, id);

	            //Execute the SQL update command.
	            pstmt.executeUpdate();
	            
	            //Print message to console when update is successful.
	            System.out.println("Attack power is updated in database.");

	        } catch (SQLException e) {
	        	
	        	//Print error details if something goes wrong. 
	            e.printStackTrace();
	        }
		}
	}
