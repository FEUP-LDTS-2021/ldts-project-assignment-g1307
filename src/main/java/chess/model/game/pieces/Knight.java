package chess.model.game.pieces;

import chess.model.game.Position;
import chess.model.game.pieces.movingBehaviours.LStrategy;

public class Knight extends Piece{
    public Knight(COLOR color, Position position) {
        super(color, 'j', position, new LStrategy());
    }
}
