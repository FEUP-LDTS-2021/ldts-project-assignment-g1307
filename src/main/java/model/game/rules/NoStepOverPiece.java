package model.game.rules;

import model.game.GameModel;
import model.game.Position;
import model.game.move.Move;
import model.game.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class NoStepOverPiece implements Rule{
    GameModel gameModel;
    public NoStepOverPiece(GameModel gameModel) {
        this.gameModel = gameModel;
    }
    @Override
    public void obyRule(Set<Move> movesToFilter, Piece p) {
        List<Position> positions = new ArrayList<>();
        for (Move move: movesToFilter) {positions.add(move.getPosition());}
        for (Position mPos : positions) {
            for (Piece piece : gameModel.getPiecesInGame()) {
                Position piecePos = piece.getPosition();
                if (mPos.equals(piecePos))
                    removeStepOverMoves(piecePos, piecePos.sub(p.getPosition()),movesToFilter);
            }
        }
    }

    private void removeStepOverMoves(Position pStepped ,Position changeInPos, Set<Move> moves) {
        Position normalPos = normalizePosition(changeInPos);
        Position pos = pStepped.add(normalPos);
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
