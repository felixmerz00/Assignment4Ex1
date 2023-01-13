package main.java.ch.uzh;

import main.java.ch.uzh.player.ComputerPlayer;
import main.java.ch.uzh.player.HumanPlayer;
import main.java.ch.uzh.game.Game;

public class Main {

    public static void main(String[] args) {

        //create new game and play the game
        Game game = new Game(new HumanPlayer("Student"), new HumanPlayer("NAME"));
        game.setup();
        game.play();
    }
}
