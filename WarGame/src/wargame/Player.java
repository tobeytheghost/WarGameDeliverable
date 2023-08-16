/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wargame;

/**
 *
 * @author falzo
 */

import java.util.ArrayList;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */
public class Player {

    private final String name; //the unique name for this player
    private final GroupOfCards deck; 

    /**
     * A constructor that allows you to set the player's unique ID
     *
     * @param name the unique ID to assign to this player.
     */
    public Player(String name) {
        this.name = name; 
        deck = new GroupOfCards(); 
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

    public void drawCard(GroupOfCards source) {
        // Check if the source deck is empty
        if (source.getCards().isEmpty()) {
            return; // If the source deck is empty, do nothing and exit the method
        }

    // Draw a card from the source deck and add it to the player's deck
        ArrayList<Card> playerDeck = deck.getCards();
        playerDeck.add(source.getCards().remove(0)); 
    }
    
    public Card playCard() {
        return deck.getCards().remove(0); 
    }

}
