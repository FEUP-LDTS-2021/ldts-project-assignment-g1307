package model.game.pieces;

import model.game.Position;
import model.game.pieces.movingBehaviours.SideStrategy;

public class Rook extends Piece{
    public Rook(COLOR color, Position position) {
        super(color, 't', position, new SideStrategy());
    }
}
