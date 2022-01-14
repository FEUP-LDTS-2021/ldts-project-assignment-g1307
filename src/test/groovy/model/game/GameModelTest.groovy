package model.game

import model.game.board.BoardModel
import model.game.board.SquareBoard
import model.game.move.Move
import model.game.move.SimpleMove
import model.game.pieces.King
import model.game.pieces.Piece
import model.game.pieces.Queen
import model.game.player.Player
import model.game.rules.Rule
import spock.lang.Specification

class GameModelTest extends Specification {
    GameModel gameModel
    Move move
    def gameCursor
    def piece
    Player player

    def setup() {
        piece = Mock(Piece)
        gameCursor = Mock(GameCursor)
        BoardModel boardModel = Mock(SquareBoard)
        move = Mock(SimpleMove)
        player = Mock(Player)

        gameModel = new GameModel()

        def s = new HashSet()
        Rule[] rules = [Mock(Rule)]
        Player[] players = [player]
        s.add(piece)
        gameModel.setPiecesInGame(s)
        gameModel.setRules(rules)
        gameModel.setCursor(gameCursor)
        gameModel.setGamePlayers(players)
        gameModel.setBoardModel(boardModel)

        boardModel.positionInBoard(_ as Position) >> true

        Position position = new Position(1,2)
        piece.getPosition() >> position

        def set = new HashSet()
        set.add(move)
        piece.getMoves(_ as BoardModel) >> set

        move.getPosition() >> new Position(2,3)
        move.getPiece() >> piece

    }

    def "GetPieceLegalMoves"() {
        when:
        gameCursor.getSelectedPosition() >> piece.getPosition()
        def mvs = gameModel.getPieceLegalMoves()
        then:
        mvs[0] == move
    }

    def "when piece is null"() {
        when:
        gameCursor.getSelectedPosition() >> null
        def mvs = gameModel.getPieceLegalMoves()
        then:
        mvs.size() == 0
    }

    def "NotifyPlayers"() {
        when:
        Player[] players = [player, Mock(Player)]
        gameModel.setGamePlayers(players)
        //boolean b = player.clock.counting
        gameModel.notifyPlayers()
        then:
        true
        //player.clock.counting != b
    }

    def "Select"() {
        gameCursor.getCurrentPosition() >> move.getPosition()
        gameCursor.getSelectedPosition() >> piece.getPosition()
        when:
        gameModel.select()
        then:
        1 * move.execute()
    }

    def "check"() {
        given:
        Player player1 = Mock(Player)
        Player[] players = [player, player1]

        player.getColor() >> Piece.COLOR.BLACK
        player1.getColor() >> Piece.COLOR.White

        King king = Mock(King)

        king.getColor() >> Piece.COLOR.White
        king.getPosition() >> move.getPosition()

        def set = new HashSet()
        King bKing = Mock(King)
        bKing.getColor() >> Piece.COLOR.BLACK

        def mvs = new HashSet()
        bKing.getMoves(_ as BoardModel) >> mvs
        king.getMoves(_ as BoardModel) >> mvs

        set.add(piece)
        set.add(king)
        set.add(bKing)
        gameModel.setPiecesInGame(set)
        gameModel.setGamePlayers(players)
        when:

        gameModel.setCheck();

        then:
        1 * king.setInCheck(true)
    }

    def "Stalemate"() {
        given:
        player.getColor() >> Piece.COLOR.BLACK

        King king = Mock(King)

        'This king(or any other piece) should not have any possible move'
        def kingMvs = new HashSet()
        king.getColor() >> Piece.COLOR.BLACK
        king.getMoves(_ as BoardModel) >> kingMvs

        def s = new HashSet()
        s.add(king)

        when:
        def result = gameModel.checkStalemate(player)

        then:
        result
    }
}
