package model.gameover;

import model.Model;
import model.game.player.Player;


public class GameOverModel implements Model {
    private final Player winner;
    private final boolean checkmate;

    public GameOverModel(Player winner, boolean checkmate){
        this.winner = winner;
        this.checkmate = checkmate;
    }

    public boolean tied() {
        return winner == null;
    }

    public boolean wonByCheckmate() {
        return checkmate;
    }

    public String gameOverMessage() {
        if (winner != null){
            return winner.getColor().name() + " won by " + (checkmate? "checkmate":"time");
        }
        return "It's a draw";
    }
}
