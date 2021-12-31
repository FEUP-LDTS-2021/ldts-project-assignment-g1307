package model.game.pieces;

import model.game.Position;
import model.game.pieces.movingBehaviours.AdjacentStrategy;

public class King extends Piece{
    public King(COLOR color, Position position) {
        super(color, 'l', position, new AdjacentStrategy());
    }
}