package model.game.pieces.movingBehaviours;

import model.game.board.BoardModel;
import model.game.Position;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdjacentStrategy implements MovingBehaviour{
    private final List<Position> dPos = Arrays.asList(new Position(-1,-1), new Position(-1,0), new Position(-1,1),
            new Position(0,-1), new Position(0,1), new Position(1,-1), new Position(1,1),
            new Position(1,0));

    @Override
    public Set<Position> getMoves(BoardModel b, Position objectPosition) {
        Set<Position> res = new HashSet<>();

        for (Position position : dPos){
            if(b.positionInBoard(objectPosition.add(position))) res.add(objectPosition.add(position));
        }
        return res;
    }
}
