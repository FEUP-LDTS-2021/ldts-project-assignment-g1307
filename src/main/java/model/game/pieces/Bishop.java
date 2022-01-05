package model.game.pieces;

import model.game.Position;
import model.game.pieces.movingBehaviours.DiagonalStrategy;

import java.util.Set;

public class Bishop extends Piece{
    public Bishop(COLOR color, Position position) {
        super(color, 'n', position, new DiagonalStrategy());
    }
}
