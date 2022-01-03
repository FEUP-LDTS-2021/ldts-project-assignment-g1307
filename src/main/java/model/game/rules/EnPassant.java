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
    public Set<Move> obyRule(Piece piece) {
        Set<Move> addedMoves = piece.getMoves(gameModel.getBoardModel());
        if (piece instanceof Pawn pawn && !piece.isMoved()) { // TODO: a bit repetitive
            for (Piece p: gameModel.getPiecesInGame()) {
                if (p instanceof Pawn aPawn && aPawn.hasAdvancedTwo() && aPawn.getColor() != piece.getColor()) {
                    if (piece.getPosition().getRow() != p.getPosition().getRow()) continue;
                    int diffCol = piece.getPosition().getCol() - p.getPosition().getCol();
                    if (diffCol == - 1 || diffCol == 1) {
                        Position position = new Position(piece.getPosition().getRow() - 1, p.getPosition().getRow());
                        addedMoves.add(new CapturingMove(pawn,  new SimpleMove(pawn, position), gameModel.getPiecesInGame()));
                        break; // there will be only one possible en passant move
                    }
                }
            }
        }
        return addedMoves;
    }
}
