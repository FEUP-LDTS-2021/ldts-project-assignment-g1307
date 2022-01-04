package model.game.player;

import model.game.GameSubscriber;
import model.game.pieces.Piece;

import java.time.Clock;

public class Player implements GameSubscriber {
    Clock clock;
    final Piece.COLOR color;

    Player(Clock clock, Piece.COLOR color) {
        this.clock = clock;
        this.color = color;
    }

    public Clock getClock() {
        return clock;
    }

    public Piece.COLOR getColor() {
        return color;
    }

    @Override
    public void setTurn(boolean playerTurn) {

    }
}
