package model.game.pieces;

import model.game.Position;
import model.game.pieces.movingBehaviours.DiagonalStrategy;
import model.game.pieces.movingBehaviours.MovingBehaviourGroup;
import model.game.pieces.movingBehaviours.SideStrategy;

public class Queen extends Piece{
    public Queen(COLOR color, Position position) {
        super(color, 'w', position, new MovingBehaviourGroup().add(new DiagonalStrategy()).add(new SideStrategy()));
    }
}
