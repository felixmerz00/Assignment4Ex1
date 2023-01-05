package main.java.ch.uzh.player.AttackStrategy;

import main.java.ch.uzh.player.IPlayer;
import main.java.ch.uzh.board.Position;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayerAttack extends AbstractAttackStrategy{
    public HumanPlayerAttack() {}

    @Override
    public void shootAt(IPlayer opponent) {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object

        while(true){
            try{
                System.out.println("Please enter the position to shoot at.");
                String input = scanner.nextLine();  // Read user input
                verifyInput(input);

                Position position = Position.parse(input);

                if (opponent.wasAttackedAtPosition(position)) {
                    System.out.println("A shot has already been fired at " + position);
                }
                else {
                    opponent.takeShotAt(position);
                    break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred, please try again: " + e.getMessage());
            }
        }
    }

    private void verifyInput(String s){
        //verify against basic heuristics
        if(s.length() != 2)
            throw new InputMismatchException("Please enter valid input");


        //check if coordinate is valid
        if(Position.parse(s) == null)
            throw new InputMismatchException("Please enter valid location");
    }
}
