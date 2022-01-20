package chess.model.game.player;

import chess.model.game.GameSubscriber;
import chess.model.game.clock.Clock;
import chess.model.game.pieces.Piece;

public class Player implements GameSubscriber {
    Clock clock;
    final Piece.COLOR color;

    public Player(Clock clock, Piece.COLOR color) {
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
        if (playerTurn)
            clock.resume();
        else
            clock.pause();
    }
}
