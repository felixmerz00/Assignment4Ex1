package ch.uzh;


import ch.uzh.game.Game;
import ch.uzh.player.HumanPlayer;

public class Main {

    public static void main(String[] args) {

        //create new game and play the game
        Game game = new Game(new HumanPlayer(HumanPlayer.setName("first")), new HumanPlayer(HumanPlayer.setName("second")));
        game.setup();
        game.play();
    }
}
