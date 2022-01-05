package model.game.pieces;

import model.game.Position;
import model.game.pieces.movingBehaviours.LStrategy;

public class Knight extends Piece{
    public Knight(COLOR color, Position position) {
        super(color, 'j', position, new LStrategy());
    }
}
