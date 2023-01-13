package main.java.ch.uzh.player;

import main.java.ch.uzh.player.AttackStrategy.HumanPlayerAttack;
import main.java.ch.uzh.player.PlaceBoatsStrategy.HumanPlayerPlacement;
import main.java.ch.uzh.boat.Fleet;

import java.util.Scanner;

public class HumanPlayer extends AbstractPlayer {

    public HumanPlayer(String name) {
        super(new HumanPlayerPlacement(), new HumanPlayerAttack(), new Fleet(), name);
    }

    public static String setName(String numberOfPlayer) {
        Scanner Input = new Scanner(System.in);
        System.out.println("Enter the name for the " + numberOfPlayer + " player: ");
        String playerName = null;
        boolean validInput = false;
        while (!validInput) {
            playerName = Input.nextLine();
            if (playerName.length() > 0){
                validInput = true;
            }
            else {
                System.out.println("Name must be at least on character long, try again: ");
            }
        }
        return playerName;
    }
}
