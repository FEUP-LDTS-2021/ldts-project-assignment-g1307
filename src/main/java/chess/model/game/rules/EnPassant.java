package chess.model.game.rules;

import chess.model.game.Position;
import chess.model.game.move.CapturingMove;
import chess.model.game.move.Move;
import chess.model.game.move.SimpleMove;
import chess.model.game.pieces.Pawn;
import chess.model.game.pieces.Piece;
import chess.model.game.pieces.movingBehaviours.TwoAndOneStrategy;

import java.util.Set;

public class EnPassant implements Rule {
    Set<Piece> pieceSet;
    public EnPassant(Set<Piece> pieceSet) {
        this.pieceSet = pieceSet;
    }

    @Override
    public void obyRule(Set<Move> movesToFilter, Piece piece)   {
        if (piece instanceof Pawn pawn) {
            for (Piece p: pieceSet) {
                Position pPiece = piece.getPosition();
                Position pP = p.getPosition();
                if (p instanceof Pawn aPawn && aPawn.hasAdvancedTwo() && aPawn.getColor() != piece.getColor()) {
                    if (pPiece.getRow() != pP.getRow()) continue;
                    int diffCol = pPiece.getCol() - pP.getCol();
                    TwoAndOneStrategy.Direction direction = ((TwoAndOneStrategy) pawn.getMovingBehaviour()).getDirection();
                    if (diffCol == - 1 || diffCol == 1) {
                        Position position = new Position(pPiece.getRow() + direction.change, pP.getCol());
                        movesToFilter.add(new CapturingMove(p,  new SimpleMove(pawn, position), pieceSet));
                    }
                }
            }
        }
    }
}
