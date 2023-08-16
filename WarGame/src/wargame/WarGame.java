/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package wargame;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author falzo
 *
 * @author julia
 */

/**
 *
 * @author julia
 */
public class WarGame extends Game {

public WarGame(String name) {
        super(name);
    }

    @Override
    public void play() { //need to add logic where a counter determines how mnay wins each player has and then using that in the declare winner method! 
        GroupOfCards deck = new GroupOfCards();
        deck.populateDeck();
        deck.shuffle();

        ArrayList<Player> players = super.getPlayers();
        Player user = players.get(0);
        Player computer = players.get(1);

        // Distribute the shuffled deck evenly to players
        for (int i = 0; i < 26; i++) {
            user.drawCard(deck);
            computer.drawCard(deck);
        }

        Scanner input = new Scanner(System.in);
        int turn = 1;

        while (true) {
            System.out.println("\nTurn " + turn);
            System.out.println("Press ENTER to play your card or type 'exit' to end the game.");
            String userInput = input.nextLine();

            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the game...");
                break;
            }

            Card userCard = user.playCard();
            Card computerCard = computer.playCard();

            System.out.println("You played: " + userCard);
            System.out.println("Computer played: " + computerCard);

            int userRank = getRankValue(userCard);
            int computerRank = getRankValue(computerCard);

            if (userRank > computerRank) {
                System.out.println("You win this round!");
                user.drawCard(deck);
            } else if (userRank < computerRank) {
                System.out.println("Computer wins this round!");
                computer.drawCard(deck);
            } else {
                System.out.println("It's a tie! GOING TO WAR!");

                Player roundWinner = resolveTie(user, computer, deck);

                if (roundWinner == null) {
                    System.out.println("No winner in the tie game. Exiting the game...");
                    break;
                } else {
                    roundWinner.drawCard(deck);
                }
            }

            // Check if any player has run out of cards after each round
            if (user.getDeck().getCards().isEmpty()) {
                System.out.println("You have run out of cards!");
                break;
            } else if (computer.getDeck().getCards().isEmpty()) {
                System.out.println("Computer has run out of cards!");
                break;
            }

            turn++;
        }

        declareWinner();
    }

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

    @Override
    public void declareWinner() {
        ArrayList<Player> players = super.getPlayers();
        Player user = players.get(0);
        Player computer = players.get(1);

        int userDeckSize = user.getDeck().getCards().size();
        int computerDeckSize = computer.getDeck().getCards().size();

        System.out.println("\n*** GAME OVER ***");
        if (userDeckSize > computerDeckSize) {
            System.out.println("Congratulations! You win the game!");
        } else if (userDeckSize < computerDeckSize) {
            System.out.println("Computer wins the game!");
        } else if (userDeckSize == computerDeckSize){
            System.out.println("It's a tie!");
        } else {
            System.out.println("This shouldn't work!");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

    System.out.println("Welcome to the game of War!");

    // Create players
    System.out.print("Enter your name: ");
    String userName = input.nextLine();
    Player user = new Player(userName);

    Player computer = new Player("Computer");

    // Add players to the game
    WarGame warGame = new WarGame("War Game");
    warGame.getPlayers().add(user);
    warGame.getPlayers().add(computer);

    // Start the game loop
    warGame.play();
    } 

    private Player resolveTie(Player user, Player computer, GroupOfCards deck) {
    System.out.println("Tie! Drawing cards to break the tie...");

    Card userTieCard = user.playCard();
    Card computerTieCard = computer.playCard();

    System.out.println("Your tie card: " + userTieCard);
    System.out.println("Computer's tie card: " + computerTieCard);

    int userTieRank = getRankValue(userTieCard);
    int computerTieRank = getRankValue(computerTieCard);

    if (userTieRank > computerTieRank) {
        System.out.println("You win the tie-breaker round!");
        return user;
    } else if (userTieRank < computerTieRank) {
        System.out.println("Computer wins the tie-breaker round!");
        return computer;
    } else {
        System.out.println("It's another tie! Continuing...");
        return resolveTie(user, computer, deck); // Recursive call until tie is broken
    }
    }
}

