package model.game.pieces;

import model.game.Position;
import model.game.pieces.movingBehaviours.AdjacentStrategy;

public class King extends Piece{// TODO: See what is the figure for this piece in the font
    boolean hasMoved;
    public King(COLOR color, char figure, Position position) {
        super(color, 'l', position, new AdjacentStrategy());
    }
}