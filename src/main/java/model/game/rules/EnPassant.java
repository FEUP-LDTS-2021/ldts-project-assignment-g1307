package model.game.rules;

import model.game.GameModel;
import model.game.Position;
import model.game.pieces.Pawn;
import model.game.pieces.Piece;

import java.util.Set;

public class EnPassant implements Rule {
    GameModel gameModel;
    EnPassant(GameModel gameModel) {
        this.gameModel = gameModel;
    }
    @Override
    public Set<Position> obyRule(Piece piece) {
        Set<Position> addedMoves = piece.getMoves(gameModel.getBoardModel());
        if (piece instanceof Pawn pawn && !piece.isMoved()) { // TODO: a bit repetitive
            for (Piece p: gameModel.getPiecesInGame()) {
                if (p instanceof Pawn aPawn && aPawn.hasAdvancedTwo() && aPawn.getColor() != piece.getColor()) {
                    if (piece.getPosition().getRow() != p.getPosition().getRow()) continue;
                    int diffCol = piece.getPosition().getCol() - p.getPosition().getCol();
                    if (diffCol == - 1 || diffCol == 1) {
                        addedMoves.add(new Position(piece.getPosition().getRow() - 1, p.getPosition().getRow()));
                        //gameModel.removePiece(p); -- it only should remove if move is made ... TODO: same as Castle
                        break; // there will be only one possible en passant move
                    }
                }
            }
        }
        return addedMoves;
    }
}
