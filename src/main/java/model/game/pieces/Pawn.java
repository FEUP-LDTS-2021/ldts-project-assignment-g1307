package model.game.pieces;

import model.game.Position;
import model.game.pieces.movingBehaviours.MovingBehaviour;
import model.game.pieces.movingBehaviours.TwoAndOneStrategy;

public class Pawn extends Piece{  // TODO: See what is the figure for this piece in the font
    boolean advancedTwo = false;
    public Pawn(COLOR color, Position position, TwoAndOneStrategy movingBehaviour) {
        super(color, 'o', position, movingBehaviour);
    }

    @Override
    public void moveToPosition(Position position) {
        advancedTwo = Math.abs(this.position.getRow() - position.getRow()) == 2;
        this.position = position;
        hasMoved = true;
    }

    public boolean hasAdvancedTwo() {
        return advancedTwo;
    }
}
