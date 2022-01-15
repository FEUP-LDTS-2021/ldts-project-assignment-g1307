package model.endGame;

import jdk.jshell.spi.ExecutionControl;
import model.Model;
import model.game.player.Player;


public class EndGameModel implements Model {
    private Player winner;
    private boolean checkmate;

    public EndGameModel(Player winner, boolean checkmate){
        this.winner = winner;
        this.checkmate = checkmate;
    }

    public boolean tied() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("N implementado");
    }


    public boolean wonByCheckmate() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("N implementado");
    }

    public Player getWinner() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("N implementado");
    }
}
