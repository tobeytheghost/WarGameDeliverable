/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wargame;

/**
 *
 * @author falzo
 */


/**
 *
 * @author julia
 */
/**
 * A class to be used as the base Card class for the project. Must be general enough to be instantiated for any Card
 * game. Students wishing to add to the code should remember to add themselves as a modifier.
 *
 * @author dancye
 */
public class Card {
    
    private final String rank; 
    private final String suit; 
    
    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit; 
    }
    @Override
    public String toString() {
        return rank + " of " + suit; 
    }
}