package chess.model.game.pieces;

import chess.model.game.Position;
import chess.model.game.pieces.movingBehaviours.DiagonalStrategy;
import chess.model.game.pieces.movingBehaviours.MovingBehaviourGroup;
import chess.model.game.pieces.movingBehaviours.SideStrategy;

public class Queen extends Piece{
    public Queen(COLOR color, Position position) {
        super(color, 'w', position, new MovingBehaviourGroup().add(new DiagonalStrategy()).add(new SideStrategy()));
    }
}
