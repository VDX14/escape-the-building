package game.core;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.IOException;

import game.characters.Player;

/**
 * Handles saving the current game state to a local file.
 */
public class SaveGame {

    /**
     * Serializes the Player object to the file path provided by the user.
     * 
     * @param player The player object to save
     * @param path The file path where the save file will be stored
     */
    public static void save(Player player, String path) {

        try (
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
        ) {

            out.writeObject(player);

            System.out.println("Game saved successfully at: " + path);

            //Print file size (assignment requirement)
            File file = new File(path);
            System.out.println("Save file: " + file.getName());
            System.out.println("File size: " + file.length() + " bytes");

        } 
        catch (IOException e) {
            System.out.println("Error saving game.");
            e.printStackTrace();
        }
    }
}