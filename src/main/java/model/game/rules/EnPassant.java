package model.game.rules;

import model.game.GameModel;
import model.game.Position;
import model.game.move.CapturingMove;
import model.game.move.Move;
import model.game.move.MoveDecorator;
import model.game.move.SimpleMove;
import model.game.pieces.Pawn;
import model.game.pieces.Piece;
import model.game.pieces.Rook;

import java.lang.ref.Cleaner;
import java.util.Set;

public class EnPassant implements Rule {
    GameModel gameModel;
    public EnPassant(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void obyRule(Set<Move> movesToFilter, Piece piece)   {
        if (piece instanceof Pawn pawn && !piece.isMoved()) {
            for (Piece p: gameModel.getPiecesInGame()) {
                Position pPiece = piece.getPosition();
                Position pP = p.getPosition();
                if (p instanceof Pawn aPawn && aPawn.hasAdvancedTwo() && aPawn.getColor() != piece.getColor()) {
                    if (pPiece.getRow() != pP.getRow()) continue;
                    int diffCol = pPiece.getCol() - pP.getCol();
                    if (diffCol == - 1 || diffCol == 1) {
                        Position position = new Position(pPiece.getRow() - 1, pP.getRow());
                        movesToFilter.add(new CapturingMove(pawn,  new SimpleMove(pawn, position), gameModel.getPiecesInGame()));
                        break; // there will be only one possible en passant move
                    }
                }
            }
        }
    }
}
