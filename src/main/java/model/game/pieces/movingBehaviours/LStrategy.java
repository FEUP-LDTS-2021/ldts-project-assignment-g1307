package model.game.pieces.movingBehaviours;

import model.game.BoardModel;
import model.game.Position;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LStrategy implements MovingBehaviour{
    @Override
    public Set<Position> getMoves(BoardModel b, Position objectPosition) {
        Set<Position> res = new HashSet<>();

        int row = objectPosition.getRow();
        int col = objectPosition.getCol();

        List<Position> possiblePositions = Arrays.asList(new Position(row -2, col -1), new Position(row -2, col + 1), new Position(row -1, col -2),
                new Position(row -1, col +2), new Position(row + 1, col -2), new Position(row + 1, col + 2), new Position(row+2, col-1),
                new Position(row + 2, col + 1));

        for (Position position : possiblePositions){
            if(b.positionInBoard(position)) res.add(position);
        }

        return res;
    }
}
