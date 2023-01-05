package main.java.ch.uzh.player;

import main.java.ch.uzh.player.AttackStrategy.HumanPlayerAttack;
import main.java.ch.uzh.player.PlaceBoatsStrategy.HumanPlayerPlacement;
import main.java.ch.uzh.boat.Fleet;

public class HumanPlayer extends AbstractPlayer {

    public HumanPlayer(String name) {
        super(new HumanPlayerPlacement(), new HumanPlayerAttack(), new Fleet(), name);
    }
}
