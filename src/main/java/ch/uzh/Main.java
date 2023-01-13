package main.java.ch.uzh;


import main.java.ch.uzh.player.HumanPlayer;
import main.java.ch.uzh.game.Game;

public class Main {

    public static void main(String[] args) {

        //create new game and play the game
        Game game = new Game(new HumanPlayer(HumanPlayer.setName("first")), new HumanPlayer(HumanPlayer.setName("second")));
        game.setup();
        game.play();
    }
}
