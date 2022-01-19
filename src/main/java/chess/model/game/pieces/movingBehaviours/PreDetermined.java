package chess.model.game.pieces.movingBehaviours;

import chess.model.game.Position;
import chess.model.game.board.BoardModel;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class PreDetermined implements MovingBehaviour{
    final protected List<Position> dPos;

    protected PreDetermined(List<Position> dPos) {
        this.dPos = dPos;
    }

    @Override
    public Set<Position> getMoves(BoardModel b, Position objectPosition) {
        Set<Position> res = new HashSet<>();

        for (Position position : dPos){
            if(b.positionInBoard(objectPosition.add(position))) res.add(objectPosition.add(position));
        }

        return res;
    }
}
