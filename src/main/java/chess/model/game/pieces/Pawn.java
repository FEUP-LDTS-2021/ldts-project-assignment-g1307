package chess.model.game.pieces;

import chess.model.game.Position;
import chess.model.game.pieces.movingBehaviours.TwoAndOneStrategy;

public class Pawn extends Piece{
    boolean advancedTwo = false;
    public Pawn(COLOR color, Position position, TwoAndOneStrategy movingBehaviour) {
        super(color, 'o', position, movingBehaviour);
    }

    @Override
    public void moveToPosition(Position position) {
        advancedTwo = Math.abs(this.position.getRow() - position.getRow()) >= 2;
        this.position = position;
        hasMoved = true;
    }

    public boolean hasAdvancedTwo() {
        return advancedTwo;
    }
}
