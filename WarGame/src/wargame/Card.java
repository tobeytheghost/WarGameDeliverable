/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wargame;

/**
 * A class to be used as the base Card class for the project. Must be general enough to be instantiated for any Card
 * game. Students wishing to add to the code should remember to add themselves as a modifier.
 *
 * @author dancye
 */
public class Card {
    
    private final String rank;  //rank constant 
    private final String suit;  //suit constant
    
    //Card Object with parameters needed to define the card
    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit; 
    }
    
   //Create getters for the two variables but not setters as the variables are final and will be defined
    public String getRank(){
        return rank;
    }
    
    public String getSuit(){
        return suit;
    }
    //toString method used to display the rank and suit of the Card when displayed in the game
    @Override
    public String toString() {
        return rank + " of " + suit; 
    }
}