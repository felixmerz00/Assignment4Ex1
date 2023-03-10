package ch.uzh.player;

import ch.uzh.board.Grid;
import ch.uzh.board.GridType;
import ch.uzh.board.position.Position;
import ch.uzh.boat.Fleet;
import ch.uzh.player.AttackStrategy.IAttackStrategy;
import ch.uzh.player.PlaceBoatsStrategy.IPlacementStrategy;


abstract class AbstractPlayer implements IPlayer {
    private final IPlacementStrategy placeStrategy;
    private final IAttackStrategy attackStrategy;
    private Grid grid;
    private final Fleet fleet;
    private final String name;

    AbstractPlayer(IPlacementStrategy placeStrategy, IAttackStrategy attackStrategy, Fleet fleet, String name) {
        this.placeStrategy = placeStrategy;
        this.attackStrategy = attackStrategy;
        this.fleet = fleet;
        this.name = name;
    }

    @Override
    public void assignGrid(Grid grid) {
        this.grid = grid;
    }

    @Override
    public void placeFleet() {
        if (this.grid == null) {
            throw new IllegalStateException("You have to assign a Grid to the Player before calling this method!");
        }
        placeStrategy.placeBoats(this.fleet, this.grid);
    }

    @Override
    public void shootAt(IPlayer opponent) {
        attackStrategy.shootAt(opponent);
    }

    @Override
    public void takeShotAt(Position position) {
        this.grid.attackAtPosition(position);
    }

    @Override
    public Boolean fleetIsAlive() {
        return fleet.stillStanding();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Position getRandomGridPosition() {
        if (this.grid == null) {
            throw new IllegalStateException("You have to assign a Grid to the Player before calling this method!");
        }
        return this.grid.getRandomPosition();
    }

    @Override
    public boolean wasAttackedAtPosition(Position position) {
        if (this.grid == null) {
            throw new IllegalStateException("You have to assign a Grid to the Player before calling this method!");
        }
        return this.grid.wasAttackedAt(position);
    }

    @Override
    public String showGridContent(GridType gridType) {
        if (this.grid == null) {
            throw new IllegalStateException("You have to assign a Grid to the Player before calling this method!");
        }
        return this.grid.showGridContent(gridType);
    }
}
