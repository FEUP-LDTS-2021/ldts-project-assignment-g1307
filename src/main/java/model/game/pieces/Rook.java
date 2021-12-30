package model.game.pieces;

import model.game.Position;
import model.game.pieces.movingBehaviours.SideStrategy;

public class Rook extends Piece{// TODO: See what is the figure for this piece in the font
    boolean hasMoved;
    public Rook(COLOR color, Position position) {
        super(color, 't', position, new SideStrategy());
    }
}
