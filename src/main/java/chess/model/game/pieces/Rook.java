package chess.model.game.pieces;

import chess.model.game.Position;
import chess.model.game.pieces.movingBehaviours.SideStrategy;

public class Rook extends Piece{
    public Rook(COLOR color, Position position) {
        super(color, 't', position, new SideStrategy());
    }
}
