package wargame;

/**
 *
 * @author Toufiq
 */
/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */

import java.util.ArrayList;

/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */

/**
 * The class that models your game. You should create a more specific child of this class and instantiate the methods
 * given.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */

public abstract class Game {

    private final String name; // The title of the game
    private ArrayList<Player> players; // The players of the game to be stored in an ArrayList

    // Constructor to set name and add player to players ArrayList
    public Game(String name) {
        this.name = name;
        players = new ArrayList();
    }

     // Return the name
    public String getName() {
        return name;
    }

    // Return the players of this game
    public ArrayList<Player> getPlayers() {
        return players;
    }

    // Set players
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    // Play the game
    public abstract void play();

    // Display the winning player when game is over
    public abstract void declareWinner();

} // End class
