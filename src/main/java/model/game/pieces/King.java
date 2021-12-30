package model.game.pieces;

import model.game.Position;
import model.game.pieces.movingBehaviours.AdjacentStrategy;

public class King extends Piece{
    boolean hasMoved;
    public King(COLOR color, Position position) {
        super(color, 'l', position, new AdjacentStrategy());
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