package model.game.pieces;

import model.game.Position;
import model.game.pieces.movingBehaviours.MovingBehaviour;

public class Pawn extends Piece{  // TODO: See what is the figure for this piece in the font
    boolean hasMoved;
    public Pawn(COLOR color, char figure, Position position, MovingBehaviour movingBehaviour) {
        super(color, figure, position, movingBehaviour); // TODO: Moving behaviour for Pawn is yet to be done
    }
}
