package model.game.pieces;

import model.game.Position;
import model.game.pieces.movingBehaviours.DiagonalStrategy;

import java.util.Set;

public class Bishop extends Piece{// TODO: See what is the figure for this piece in the font
    public Bishop(COLOR color, char figure, Position position) {
        super(color, 'n', position, new DiagonalStrategy());
    }
}
