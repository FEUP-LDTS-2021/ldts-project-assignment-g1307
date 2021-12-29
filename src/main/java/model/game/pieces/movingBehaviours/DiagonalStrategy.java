package model.game.pieces.movingBehaviours;

import model.game.BoardModel;
import model.game.Position;

import java.util.HashSet;
import java.util.Set;

public class DiagonalStrategy implements MovingBehaviour{
    @Override
    public Set<Position> getMoves(BoardModel b, Position objectPosition) {
        return new HashSet<>();
    }
}