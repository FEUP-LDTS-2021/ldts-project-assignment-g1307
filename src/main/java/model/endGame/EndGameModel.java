package model.endGame;

import jdk.jshell.spi.ExecutionControl;
import model.Model;
import model.game.player.Player;


public class EndGameModel implements Model {
    private Player[] winners;
    private boolean checkmate;

    public EndGameModel(Player[] winners, boolean checkmate){
        this.winners = winners;
        this.checkmate = checkmate;
    }

    public boolean tied() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("N implementado");
    }

    public boolean wonByCheckmate(Player[] winners) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("N implementado");
    }
}
