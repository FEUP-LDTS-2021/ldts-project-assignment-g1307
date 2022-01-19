package chess.model.game.pieces.movingBehaviours

class SizeMoveTest<T extends MovingBehaviour> {
    T behaviour
    SizeMoveTest(T behaviour){
        this.behaviour = behaviour
    }
    def getMoves(board, position){
        return behaviour.getMoves(board, position).size()
    }
}