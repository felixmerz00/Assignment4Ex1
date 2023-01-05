package main.java.ch.uzh.player;

import main.java.ch.uzh.player.AttackStrategy.ComputerPlayerAttack;
import main.java.ch.uzh.player.PlaceBoatsStrategy.ComputerPlayerPlacement;
import main.java.ch.uzh.boat.Fleet;

// this shall be the default strategy for the computer player (as described by the rules of the assignment).
public class ComputerPlayer extends AbstractPlayer {

    public ComputerPlayer() {
        super(new ComputerPlayerPlacement(), new ComputerPlayerAttack(), new Fleet(), "Computer");
    }
}
