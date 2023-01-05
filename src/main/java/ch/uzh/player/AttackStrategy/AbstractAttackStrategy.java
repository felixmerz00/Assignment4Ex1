package main.java.ch.uzh.player.AttackStrategy;

import main.java.ch.uzh.board.Position;

import java.util.HashSet;
import java.util.Set;

abstract class AbstractAttackStrategy implements IAttackStrategy {
    Set<Position> shotsTaken;

    AbstractAttackStrategy() {
        shotsTaken = new HashSet<>();
    }
}
