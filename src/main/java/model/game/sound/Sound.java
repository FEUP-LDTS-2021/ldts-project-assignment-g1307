package model.game.sound;

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

    public void selectSound(Move move) {
        if (gameModel.gameEnded())
            clip = loadSound("src/main/resources/gameover.wav");
        else if (move instanceof CapturingMove)
            clip = loadSound("src/main/resources/capture.wav");
        else
            clip = loadSound("src/main/resources/move.wav");
    }

    public Clip loadSound(String filePath) throws NullPointerException{
        try {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(filePath));
            Clip soundClip = AudioSystem.getClip();
            soundClip.open(audioInput);
            FloatControl gainControl = (FloatControl) soundClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-25.0f);
            return soundClip;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void playSound(Move move){
        selectSound(move);
        clip.setMicrosecondPosition(0);
        clip.start();
    }

}