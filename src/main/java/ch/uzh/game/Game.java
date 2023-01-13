package main.java.ch.uzh.game;

import main.java.ch.uzh.player.HumanPlayer;
import main.java.ch.uzh.player.IPlayer;
import main.java.ch.uzh.board.*;

import java.util.Random;

public class Game {
    private final IPlayer[] players;
    private final int nofPlayers;

    public Game(IPlayer player1, IPlayer player2) {
        this.players = new IPlayer[] {player1, player2};
        this.nofPlayers = 2;
    }

    public void setPlayers(){

    }

    public void setup() {
        // Set-up Phase
        setPlayers();
        Grid player1grid = new Grid(GridType.OCEAN_GRID.toString());
        Grid player2grid = new Grid(GridType.OCEAN_GRID.toString());
        this.players[0].assignGrid(player1grid);
        this.players[1].assignGrid(player2grid);

        System.out.println(players[0].getName() + " has to set their ships!");
        this.players[0].placeFleet();
        System.out.println(players[1].getName() + " has to set their ships!");
        this.players[1].placeFleet();
    }

    public void play() {
        // Main Phase
        int attackerId = selectRandomStartPlayer();
        int defenderId = nextPlayerAfter(attackerId);

        while (this.players[attackerId].fleetIsAlive()) {
            System.out.println(players[attackerId].getName() + "'s has to make their turn!");
            if (this.players[attackerId] instanceof HumanPlayer) {
                System.out.println(players[defenderId].showGridContent(GridType.TARGET_GRID));
                System.out.printf("%n-----------------------%n%n");
                System.out.println(players[attackerId].showGridContent(GridType.OCEAN_GRID));
            }
            players[attackerId].shootAt(players[defenderId]);

            // Swap players
            attackerId = nextPlayerAfter(attackerId);
            defenderId = nextPlayerAfter(defenderId);
        }

        // Game finished => defender has won
        displayEndOfGame(this.players[0], this.players[1], this.players[defenderId]);
    }


    private int selectRandomStartPlayer() {
        // randomly select which user will be the first attacker
        Random rand = new Random();
        return rand.nextInt(this.nofPlayers);
    }

    private int nextPlayerAfter(int playerId) {
        return (playerId + 1) % nofPlayers;
    }

    private void displayEndOfGame(IPlayer currentUser, IPlayer opponent, IPlayer winner) {
        // Game finished => defender has won
        System.out.println(opponent.showGridContent(GridType.OCEAN_GRID));
        System.out.printf("%n-----------------------%n%n");
        System.out.println(currentUser.showGridContent(GridType.OCEAN_GRID));
        System.out.println();
        System.out.println("GAME OVER!");
        System.out.printf("%s has won!%n", winner.getName());
    }
}
