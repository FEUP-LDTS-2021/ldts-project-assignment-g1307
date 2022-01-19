package chess.model.game.pieces;

import chess.model.game.Position;
import chess.model.game.pieces.movingBehaviours.AdjacentStrategy;

public class King extends Piece{
    boolean inCheck;

    public King(COLOR color, Position position) {
        super(color, 'l', position, new AdjacentStrategy());
        inCheck = false;
    }

    public boolean inCheck() {
        return inCheck;
    }

    public void setInCheck(boolean inCheck){
        this.inCheck = inCheck;
    }
}