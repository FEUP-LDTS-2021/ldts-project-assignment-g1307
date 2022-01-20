package model.game.sound;

import jdk.jshell.spi.ExecutionControl;
import model.game.GameModel;
import model.game.move.Move;

public class Sound {
    private final GameModel gameModel;

    public Sound(GameModel gameModel){
        this.gameModel = gameModel;
    }

    public void selectSound(Move move) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("N implementado");
    }

    public void playSound(String filePath) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("N implementado");
    }
}