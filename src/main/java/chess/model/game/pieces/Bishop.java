package chess.model.game.pieces;

import chess.model.game.Position;
import chess.model.game.pieces.movingBehaviours.DiagonalStrategy;

public class Bishop extends Piece{
    public Bishop(COLOR color, Position position) {
        super(color, 'n', position, new DiagonalStrategy());
    }
}
