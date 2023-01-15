package ch.uzh.player.AttackStrategy;

import ch.uzh.board.position.Position;
import java.util.HashSet;
import java.util.Set;

abstract class AbstractAttackStrategy implements IAttackStrategy {
    Set<Position> shotsTaken;

    AbstractAttackStrategy() {
        shotsTaken = new HashSet<>();
    }
}
