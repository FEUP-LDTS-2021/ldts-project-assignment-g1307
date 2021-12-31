package model.game.pieces.movingBehaviours;

import model.game.Position;

import java.util.Arrays;

public class AdjacentStrategy extends PreDetermined{
    public AdjacentStrategy() {
        super(Arrays.asList(new Position(-1,-1), new Position(-1,0), new Position(-1,1), new Position(0,-1),
                new Position(0,1), new Position(1,-1), new Position(1,1), new Position(1,0)));
    }
}
