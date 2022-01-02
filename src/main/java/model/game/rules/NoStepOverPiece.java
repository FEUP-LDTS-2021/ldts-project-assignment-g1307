package model.game.rules;

import model.game.GameModel;
import model.game.Position;
import model.game.move.Move;
import model.game.pieces.Piece;

import java.util.Set;

public class NoStepOverPiece implements Rule{
    GameModel gameModel;
    NoStepOverPiece(GameModel gameModel) {
        this.gameModel = gameModel;
    }
    @Override
    public Set<Move> obyRule(Piece p) {
        Set<Move> filterMoves = p.getMoves(gameModel.getBoardModel());
        for (Move move : filterMoves) {
            for (Piece piece : gameModel.getPiecesInGame()) {
                if (move.getPosition().equals(piece.getPosition()))
                    removeStepOverMoves(piece, piece.getPosition().sub(p.getPosition()),filterMoves);
            }
        }
        return filterMoves;
    }

    private void removeStepOverMoves(Piece pStepped ,Position changeInPos, Set<Move> moves) {
        Position normalPos = normalizePosition(changeInPos);
        Position pos = pStepped.getPosition().add(normalPos);
        for (; gameModel.getBoardModel().positionInBoard(pos); pos = pos.add(normalPos)) {
            Position finalPos = pos;
            moves.removeIf(move -> move.getPosition().equals(finalPos));
        }
    }

    private Position normalizePosition(Position position) {
        double posLength = Math.sqrt(Math.pow(position.getCol(),2) + Math.pow(position.getRow(), 2));
        double inverseLength = 1.0d / (int)posLength;

        return new Position((int)(position.getRow() * inverseLength), (int)(position.getCol() * inverseLength));
    }
}
