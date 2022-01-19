package chess.model.game.pieces.movingBehaviours;

import chess.model.game.board.BoardModel;
import chess.model.game.Position;

import java.util.HashSet;
import java.util.Set;

public class DiagonalStrategy implements MovingBehaviour{
    @Override
    public Set<Position> getMoves(BoardModel b, Position objectPosition) {
        Set<Position> possibleMoves = new HashSet<>();

        addPossibleMovesInDir(true,true,possibleMoves,objectPosition,b);
        addPossibleMovesInDir(true,false,possibleMoves,objectPosition,b);
        addPossibleMovesInDir(false,true,possibleMoves,objectPosition,b);
        addPossibleMovesInDir(false,false,possibleMoves,objectPosition,b);

        return possibleMoves;
    }

    public void addPossibleMovesInDir(boolean upRow, boolean upCol, Set<Position> pMs, Position pos, BoardModel b) {
        int c = upRow ? 1:-1;
        int r = upCol ? 1:-1;
        int row = pos.getRow();
        int col = pos.getCol();
        for (int i = 1; b.positionInBoard(new Position(row +i*r,col + i*c)); i++) {
            pMs.add(new Position(row+i*r, col+i*c));
        }
    }
}