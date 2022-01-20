package chess.model.game.rules;

import chess.model.game.Position;
import chess.model.game.board.BoardModel;
import chess.model.game.move.Move;
import chess.model.game.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class NoStepOverPiece implements Rule{
    Set<Piece> pieceSet;
    BoardModel boardModel;
    public NoStepOverPiece(BoardModel boardModel, Set<Piece> pieceSet) {
        this.pieceSet = pieceSet;
        this.boardModel = boardModel;
    }
    @Override
    public void obyRule(Set<Move> movesToFilter, Piece p) {
        List<Position> positions = new ArrayList<>();
        for (Move move: movesToFilter) {positions.add(move.getPosition());}
        for (Position mPos : positions) {
            for (Piece piece : pieceSet) {
                Position piecePos = piece.getPosition();
                if (mPos.equals(piecePos))
                    removeStepOverMoves(piecePos, piecePos.sub(p.getPosition()),movesToFilter);
            }
        }
    }

    private void removeStepOverMoves(Position pStepped ,Position changeInPos, Set<Move> moves) {
        Position normalPos = normalizePositionChange(changeInPos);

        Position pos = pStepped.add(normalPos);
        for (; boardModel.positionInBoard(pos); pos = pos.add(normalPos)) {
            Position finalPos = pos;
            moves.removeIf(move -> move.getPosition().equals(finalPos));
        }
    }

    private Position normalizePositionChange(Position position) {
        int row = position.getRow();
        int col = position.getCol();
        int rowLen = row != 0 ? Math.abs(row) : 1;
        int colLen = col != 0 ? Math.abs(col) : 1;
        return new Position(row/rowLen, col/colLen);
    }
}
