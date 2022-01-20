package model.game.sound;

import jdk.jshell.spi.ExecutionControl;
import model.game.GameModel;
import model.game.move.CapturingMove;
import model.game.move.Move;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;


public class Sound {
    private final GameModel gameModel;
    private Clip clip;

    public Sound(GameModel gameModel){
        this.gameModel = gameModel;
        this.clip = null;
    }

    public void selectSound(Move move) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("N implementado");
    }

    public Clip loadSound(String filePath) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("N implementado");
    }

    public void playSound(Move move) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("N implementado");
    }

}