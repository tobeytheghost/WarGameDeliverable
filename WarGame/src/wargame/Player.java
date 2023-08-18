/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wargame;


import java.util.ArrayList;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 *
 * @author dancye
 * @author Yassine Assim August 17th 2023
 */
public class Player {

    private final String name; //the unique name for this player
    private final GroupOfCards deck;  //the deck of cards held by player


    /**
     * A constructor that allows you to set the player's unique ID
     *
     * @param name the unique ID to assign to this player.
     */
    public Player(String name) {
        this.name = name; 
        deck = new GroupOfCards();  //Initialize the player's deck
    }

    /**
     * @return the player name
     */
    public String getName() {
        return name;
    }
    
    public GroupOfCards getDeck() {
        return deck; 
    }
    
    /**
     * Draws a card from source deck and adds it to the player's deck.
     * @param source the source deck from which to draw the card
     */

    public void drawCard(GroupOfCards source) {
        // Check if the source deck is empty
        if (source.getCards().isEmpty()) {
            return; // If the source deck is empty, do nothing and exit the method
        }

    // Draw a card from the source deck and add it to the player's deck
        ArrayList<Card> playerDeck = deck.getCards();
        playerDeck.add(source.getCards().remove(0)); 
    }
    
    /**
     * Plays a card from the player's deck
     * 
     * @return 
     */
    public Card playCard() {
        return deck.getCards().remove(0); //Remove and return the top card from player's deck
    }

}
