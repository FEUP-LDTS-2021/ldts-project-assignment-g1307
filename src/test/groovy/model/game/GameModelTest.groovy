package model.game

import model.game.board.BoardModel
import model.game.board.SquareBoard
import model.game.move.Move
import model.game.move.SimpleMove
import model.game.pieces.Piece
import model.game.player.Player
import model.game.rules.Rule
import spock.lang.Specification

class GameModelTest extends Specification {
    GameModel gameModel
    Move move
    def gameCursor
    def piece
    def player

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
        boolean b = player.clock.counting
        gameModel.notifyPlayers()
        then:
        player.clock.counting != b
    }

    def "Select"() {
        gameCursor.getCurrentPosition() >> move.getPosition()
        gameCursor.getSelectedPosition() >> piece.getPosition()
        when:
        gameModel.select()
        then:
        1 * move.execute()
    }
}
