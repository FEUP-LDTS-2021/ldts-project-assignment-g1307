package chess.model.game.rules

import chess.model.game.GameModel
import chess.model.game.Position
import chess.model.game.board.BoardModel
import chess.model.game.board.SquareBoard
import chess.model.game.move.Move
import chess.model.game.pieces.King
import chess.model.game.pieces.Piece
import chess.model.game.pieces.Queen
import chess.model.game.pieces.Rook
import spock.lang.Specification

class CastleTest extends Specification {
    GameModel gameModel
    Piece piece
    Rook rook

    def setup() {
        Set<Piece> pieces = new HashSet<>()
        Set<Rule> rules = new HashSet<>()
        gameModel = new GameModel()
        gameModel.setBoardModel(new SquareBoard(8))

        rules.add(new Castle(gameModel.getBoardModel() as SquareBoard,gameModel.getPiecesInGame()))

        'a king in e8 with a rook in h8 - castle short'
        Piece king = new King(Piece.COLOR.BLACK, new Position(1,5))
        rook = new Rook(Piece.COLOR.BLACK, new Position(1,8))
        pieces.add(king)
        pieces.add(rook)

        piece = Stub(King)
        piece.getMovesPositions(_ as BoardModel) >> new HashSet<Position>()
        piece.getPosition() >> new Position(1,5)
        piece.getColor() >> Piece.COLOR.BLACK

        gameModel.setPiecesInGame(pieces)
    }

    def "Castling when king is not under threat"() {
        given:
        def castleFilter = new Castle(gameModel.getBoardModel() as SquareBoard,gameModel.getPiecesInGame())
        when:
        Set<Move> legalMoves = piece.getMoves(gameModel.getBoardModel())
        castleFilter.obyRule(legalMoves, piece)
        legalMoves[0].execute()
        then:
        legalMoves.size() == 1
        'when castling the pos of the king would be g8 and rook new pos f8'
        // maybe make a Move class to implement this behaviour ... or extend position(it seems better) ?
        rook.getPosition() == new Position(1,6)
    }

    def "Not Castling when king is in threat"() {
        given:
        Set<Piece> pieces = gameModel.getPiecesInGame()
        pieces.add(new Queen(Piece.COLOR.White, new Position(3,5)))
        gameModel.setPiecesInGame(pieces) // just to readability
        def castleFilter = new Castle(gameModel.getBoardModel() as SquareBoard,gameModel.getPiecesInGame())
        def k = (King) piece
        'The responsibility to set the king in check should be of the game'
        k.inCheck() >> true
        when:
        Set<Move> legalMoves = piece.getMoves(gameModel.getBoardModel())
        castleFilter.obyRule(legalMoves, piece)
        then: 'No legal moves to king'
        legalMoves.size() == 0
    }

    def "Not Castling after one of the pieces as moved"() {
        given:
        def aPiece= gameModel.getPiecesInGame()[0]
        def pos = aPiece.getPosition()
        aPiece.moveToPosition(new Position(0,0))
        aPiece.moveToPosition(pos)
        def castleFilter = new Castle(gameModel.getBoardModel() as SquareBoard,gameModel.getPiecesInGame())
        piece.isMoved() >> true
        when:
        Set<Move> legalMoves = piece.getMoves(gameModel.getBoardModel())
        castleFilter.obyRule(legalMoves, piece)
        then: 'No legal moves to king'
        legalMoves.size() == 0
    }

    def "noPieceBetween true"(){
        Set<Piece> pieces = new HashSet<>()
        Set<Rule> rules = new HashSet<>()
        def gameModel2 = new GameModel()
        gameModel2.setBoardModel(new SquareBoard(8))

        rules.add(new Castle(gameModel2.getBoardModel() as SquareBoard,gameModel2.getPiecesInGame()))

        'a king in e8 with a rook in h8 - castle short'
        Piece king = new King(Piece.COLOR.BLACK, new Position(1,5))
        def rook = new Rook(Piece.COLOR.BLACK, new Position(1,8))
        def rook3 = new Rook(Piece.COLOR.BLACK, new Position(1,1))
        pieces.add(king)
        pieces.add(rook)
        pieces.add(rook3)

        Piece king2 = new King(Piece.COLOR.BLACK, new Position(8,5))
        def rook2 = new Rook(Piece.COLOR.BLACK, new Position(8,8))
        def rook4 = new Rook(Piece.COLOR.BLACK, new Position(8,1))
        pieces.add(king2)
        pieces.add(rook2)
        pieces.add(rook4)

        gameModel2.setPiecesInGame(pieces)

        def castleFilter = new Castle(gameModel2.getBoardModel() as SquareBoard,gameModel2.getPiecesInGame())
        expect:
        castleFilter.noPieceBetween(king.getPosition(), rook.getPosition().getCol())
        castleFilter.noPieceBetween(king2.getPosition(), rook2.getPosition().getCol())
        castleFilter.noPieceBetween(king.getPosition(), rook3.getPosition().getCol())
        castleFilter.noPieceBetween(king2.getPosition(), rook4.getPosition().getCol())
    }

    def "noPieceBetween false"(){
        Set<Piece> pieces = new HashSet<>()
        Set<Rule> rules = new HashSet<>()
        def gameModel2 = new GameModel()
        gameModel2.setBoardModel(new SquareBoard(8))

        rules.add(new Castle(gameModel2.getBoardModel() as SquareBoard,gameModel2.getPiecesInGame()))

        'a king in e8 with a rook in h8 - castle short'
        Piece king = new King(Piece.COLOR.BLACK, new Position(1,5))
        def rook = new Rook(Piece.COLOR.BLACK, new Position(1,8))
        def rook3 = new Rook(Piece.COLOR.BLACK, new Position(1,1))
        pieces.add(king)
        pieces.add(rook)
        pieces.add(rook3)

        Piece king2 = new King(Piece.COLOR.BLACK, new Position(8,5))
        def rook2 = new Rook(Piece.COLOR.BLACK, new Position(8,8))
        def rook4 = new Rook(Piece.COLOR.BLACK, new Position(8,1))
        pieces.add(king2)
        pieces.add(rook2)
        pieces.add(rook4)

        def piece = Stub(Piece)
        piece.getPosition() >> new Position(1,6)
        pieces.add(piece)

        def piece2 = Stub(Piece)
        piece2.getPosition() >> new Position(8,6)
        pieces.add(piece2)

        def piece3 = Stub(Piece)
        piece3.getPosition() >> new Position(1,3)
        pieces.add(piece3)

        def piece4 = Stub(Piece)
        piece4.getPosition() >> new Position(8,3)
        pieces.add(piece4)

        gameModel2.setPiecesInGame(pieces)


        def castleFilter = new Castle(gameModel2.getBoardModel() as SquareBoard,gameModel2.getPiecesInGame())
        expect:
        !castleFilter.noPieceBetween(king.getPosition(), rook.getPosition().getCol())
        !castleFilter.noPieceBetween(king2.getPosition(), rook2.getPosition().getCol())
        !castleFilter.noPieceBetween(king.getPosition(), rook3.getPosition().getCol())
        !castleFilter.noPieceBetween(king2.getPosition(), rook4.getPosition().getCol())
    }
}
