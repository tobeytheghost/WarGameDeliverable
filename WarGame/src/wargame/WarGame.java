/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package wargame;

/**
 * This class represents a card game called "War" where a user competes against the computer.
 * The game keeps track of wins for both players and determines the ultimate winner.
 * The class extends the abstract class "Game" and implements the specific game logic.
 */
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author julian menegn
 */
public class WarGame extends Game {

    // Variables to track the number of wins for the user and the computer
    private int userWins;
    private int computerWins;

     /**
     * Constructor to initialize the WarGame with a given name.
     * Initializes win counters for both players.
     * @param name The name of the game.
     */
    public WarGame(String name) {
        super(name);
        this.userWins = 0;
        this.computerWins = 0;
    }
    /**
     * The main game loop where the actual game logic is implemented.
     * Initializes and shuffles the deck, distributes cards to players, and manages the rounds.
     */
    @Override
    public void play() {
        // Create and shuffle the deck of cards
        GroupOfCards deck = new GroupOfCards();
        deck.populateDeck();
        deck.shuffle();
        // Get the list of players (user and computer)
        ArrayList<Player> players = super.getPlayers();
        Player user = players.get(0);
        Player computer = players.get(1);
        
        // Distribute cards to both players
        for (int i = 0; i < 26; i++) {
            user.drawCard(deck);
            computer.drawCard(deck);
        }
        
        // Set up user input scanner and initialize turn counter
        Scanner input = new Scanner(System.in);
        int turn = 1;

        // Main game loop
        while (true) {
            System.out.println("\nTurn " + turn);
            System.out.println("Press ENTER to play your card or type 'exit' to end the game.");
            String userInput = input.nextLine();

            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the game...");
                break;
            }
            
            // Draw cards and determine round winner
            Card userCard = user.playCard();
            Card computerCard = computer.playCard();

            System.out.println("You played: " + userCard);
            System.out.println("Computer played: " + computerCard);

            int userRank = getRankValue(userCard);
            int computerRank = getRankValue(computerCard);

            if (userRank > computerRank) {
                System.out.println("You win this round!");
                user.drawCard(deck);
                userWins++;
            } else if (userRank < computerRank) {
                System.out.println("Computer wins this round!");
                computer.drawCard(deck);
                computerWins++;
            } else {
                // Tie: Handle tie-breaker round
                System.out.println("It's a tie! GOING TO WAR!");

                Player roundWinner = resolveTie(user, computer, deck);

                if (roundWinner == null) {
                    System.out.println("No winner in the tie game. Exiting the game...");
                    break;
                } else {
                    roundWinner.drawCard(deck);
                }
            }
            
            // Check for empty decks and increment turn
            if (user.getDeck().getCards().isEmpty()) {
                System.out.println("You have run out of cards!");
                break;
            } else if (computer.getDeck().getCards().isEmpty()) {
                System.out.println("Computer has run out of cards!");
                break;
            }

            turn++;
        }
        
        // Declare the final winner
        declareWinner();
    }
    
    //Returns numeric value of card's rank
    private int getRankValue(Card card) {
        String rank = card.toString().split(" ")[0];

        switch (rank) {
            case "Ace":
                return 14;
            case "King":
                return 13;
            case "Queen":
                return 12;
            case "Jack":
                return 11;
            default:
                return Integer.parseInt(rank);
        }
    }

    //Display the final result of the game, delcaring the final winner
    //Compare the number of wins to both players and displays the corresponding message
    @Override
    public void declareWinner() {
        ArrayList<Player> players = super.getPlayers();
        Player user = players.get(0);
        Player computer = players.get(1);

        //Get the size of boht player's decks 
        int userDeckSize = user.getDeck().getCards().size();
        int computerDeckSize = computer.getDeck().getCards().size();

        System.out.println("\n*** GAME OVER ***");
        if (userWins > computerWins) {
            System.out.println("Congratulations! You win the game with " + userWins + " wins!");
        } else if (userWins < computerWins) {
            System.out.println("Computer wins the game with " + computerWins + " wins!");
        } else {
            System.out.println("It's a tie! Both players have " + userWins + " wins.");
        }
    }

    //The main entry point of the game, it collects the user names, creates players, and then begins the game loop
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the game of War!");

        System.out.print("Enter your name: ");
        String userName = input.nextLine();
        Player user = new Player(userName);

        Player computer = new Player("Computer");

        //Create a new instance of hte game and add players
        WarGame warGame = new WarGame("War Game");
        warGame.getPlayers().add(user);
        warGame.getPlayers().add(computer);

        //Start the game loop
        warGame.play();
    }

    /*
    * Resolves a tie breaker during the game. 
    * Draws tie cards, compares their ranks, and determines the round winner
    */
    private Player resolveTie(Player user, Player computer, GroupOfCards deck) {
        System.out.println("Tie! Drawing cards to break the tie...");

        //Draw tie cards from player's decks 
        Card userTieCard = user.playCard();
        Card computerTieCard = computer.playCard();

        System.out.println("Your tie card: " + userTieCard);
        System.out.println("Computer's tie card: " + computerTieCard);

        //Get ranks of tie cards 
        int userTieRank = getRankValue(userTieCard);
        int computerTieRank = getRankValue(computerTieCard);

        //Compare ranks to determine the winner of the tie-breaker round 
        if (userTieRank > computerTieRank) {
            System.out.println("You win the tie-breaker round!");
            return user;
        } else if (userTieRank < computerTieRank) {
            System.out.println("Computer wins the tie-breaker round!");
            return computer;
        } else {
            System.out.println("It's another tie! Continuing...");
            return resolveTie(user, computer, deck); //Recrusive call until tie is broken
        }
    }
}