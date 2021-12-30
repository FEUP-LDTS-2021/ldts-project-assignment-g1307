package model.game.pieces;

import model.game.Position;
import model.game.pieces.movingBehaviours.MovingBehaviour;
import model.game.pieces.movingBehaviours.TwoAndOneStrategy;

public class Pawn extends Piece{  // TODO: See what is the figure for this piece in the font
    boolean hasMoved;
    public Pawn(COLOR color, Position position, TwoAndOneStrategy movingBehaviour) {
        super(color, 'o', position, movingBehaviour);
        hasMoved = false;
    }

    public boolean isMoved() {
        return hasMoved;
    }

    @Override
    public void moveToPosition(Position position) {
        super.moveToPosition(position);
        hasMoved = true;
    }
}
