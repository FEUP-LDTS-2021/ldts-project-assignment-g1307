package model.game.pieces;

import model.game.Position;
import model.game.pieces.movingBehaviours.SideStrategy;

public class Rook extends Piece{
    boolean hasMoved;
    public Rook(COLOR color, Position position) {
        super(color, 't', position, new SideStrategy());
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
