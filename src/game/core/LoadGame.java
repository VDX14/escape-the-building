package game.core;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

import game.characters.Player;

/**
 * Handles loading a saved player from a file.
 */
public class LoadGame {

    /**
     * Deserializes the player object from the file path provided.
     * 
     * @param path The file path of the save file.
     * @return The loaded Player object or null if loading fails.
     */
    public static Player load(String path) {

        try (
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
        ) {

            Player player = (Player) in.readObject();

            System.out.println("Game loaded successfully.");

            return player;

        } 
        catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading game.");
            e.printStackTrace();
            return null;
        }
    }
}