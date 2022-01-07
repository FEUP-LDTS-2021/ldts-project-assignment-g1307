# LDTS_T13_G1307 - CHESS

## Game Description

This version of Chess is a representation of the classic strategy tabletop game Chess played between 2 players, in alternating turns. Each player has at their disposal 16 chess pieces (8 pawns, 2 rooks, 2 knights, 2 bishops, 1 queen and 1 king). Each type of piece has specific movement rules. When a player moves a piece to the same square as an enemy piece, the enemy piece is taken and the square is ocupied by the player piece. The king piece has to stay alive at all points, so when a check is initiated, the player needs to act in order to protect their king. Once the king has no safe squares from a check, it is a check-mate and the player loses.

This project was developed by Lucas Sousa (up202004682@edu.fe.up.pt), Vitor Cavaleiro (up202004724@edu.fe.up.pt) and Ricardo Matos (up202007962@edu.fe.up.pt) for LDTS 2021-22.

## Implemented Features

- **Menu** - When first running the game, the user will be met with a menu that can be navigated with the left and right arrows keys. It has the option to start a new game or to exit.
- **2 Player Game** - The game is will show a standard 8 by 8 square board, with 8 pawns, 2 rooks, 2 knights, 2 bishops, 1 queen and 1 king on either side, where 2 players take alternating turns moving their pieces.
- **Piece Movement** - The pieces of the game, when selected will be able to be moved to a correct, legal position based on the rules of the game (Ex: knight will only be able to move in an L shape, the bishop will only be able to move diagonaly).
- **Piece capturing** - When a Piece is moved to square occupied by an enemy piece, it will capture it. The enemy piece will be removed from the board and the other piece will take its square.
- **Cursor** - In each turn, the player will be able to select the desired piece, with the arrow keys, and then select a legal square for the piece to move into.

## Planned Features

- **Check** - When, in the current round, an enemy piece is threatening to capture the king a check is initiated. When this happens the player must act in order to protect the king and stop the check.

- **Checkmate** - During a check, when there is no possible way to protect the king and stop the check, there will be a Checkmate. The player who initiated the check will win and the game will return to the Main Menu.

- **Countdown Clock** - When starting a new game, the players will be asked the duration of the countdown clocks. During the game, each turn, the corresponding clock will start ticking down, stopping when the player has made their move. Subsequently, the other player's clock will do the same. When a player's clock reaches 0 the game will end and that player will lose.

## Design
### General Structure
#### Problem in Context:
In order to make the code <b> more reusable </b>, <b> organizable  </b>, and so that it wouldn't break the <b>
Single Principle Responsibility </b> (which could easily happen) we decided to use the <b> MVC Architecture Pattern </b>.

#### The Pattern:
The MVC pattern separates the code in three packages the Model, View and Control. The model represents the logical
part of the program, the View is responsible for showing the program (depending on the model) and the controller is 
responsible for controlling the states of the program (depending on the previous two).

#### Implementation:
- **Model** - Stores the data pertaining to the game, such as the positions of the pieces and their posible movements, aswell as the logic of the game and every piece.
- **View** - Handles the output of the graphical interface of the game through Lanterna, drawing the menu, board and its pieces.
- **Controller** - Controls the flow of the program.

<br>
<br />

<p align="center" justify="center">
  <img src="images/UML/VMC_UML.jpg"/>
</p>
<p align="center">
  <b><i>Fig 1. Model, Controller and Viewer pattern design </i></b>
</p>  

<br>
<br />

#### Consequences:
- Organization of source code, allowing for better development
- Modifications don't affect the entire program
- Better separation of the program logic from the view drawing
- Easier addition of features during development.
- Single Responsibility Principle is not violated
<br>
<br />

### Board Strategy
#### Problem in context:
Even though we only planned on the game having only one kind of board, the classic 8x8 board, we decided to develop it in a way that would allow for multiple types of boards.
In this way we protected ourselves from going against the <b> Open-Closed Principle </b> and we let the game
open for new variants of the game.

#### The Pattern:
We have applied the Strategy Pattern. In this way we prevent future violations of the <b> SOLID </b>principles.
We also go in line with the good practises, by not "working" for the implementation but for the interface.  

#### Implementation:


<br>
<br />

<p align="center" justify="center">
  <img src="images/UML/strategy_UML.jpg"/>
</p>
<p align="center">
  <b><i>Fig 2. BoardModel Strategy (only SquareBoard is implemented) </i></b>
</p>  

<br>
<br />

These classes can be found in the following files:
- [BoardModel](../src/main/java/model/game/board/BoardModel.java)
- [SquareBoard](../src/main/java/model/game/board/SquareBoard.java)

#### Consequences:
Benefits of applying the above pattern:
 - Allows for different types of boards that differ from the classical square board.
 - Isolate the implementation details. 
 - Obeys Open/Closed Principle. New strategies can be introduced without having to change the context.

<br>
<br />

### Possible moves for different pieces
#### Problem in context:
Each tipe of piece in the game can only move in very specific ways (Ex. Bishop can only move to diagonal squares), and some pieces move in similar ways to other pieces (Ex. Queen can move like a Bishop and like a Rook).

#### The pattern:
We have applied the Composite Pattern. This way we can create general behaviours for the more common moves, such as moving side-to-side or moving diagonaly, and more specific behaviours for pieces like the Pawn.
We also have moving behaviour groups that combine different behaviours (Ex. The MovingBehaviourGroup associated with the queen is made up of a side moving behaviour and an adjacent moving behaviour).

#### Implementation:
<br>
<br />

<p align="center" justify="center">
  <img src="images/UML/composite_UML.jpg"/>
</p>
<p align="center">
  <b><i>Fig 3. Composite Pattern in MovingBehaviours </i></b>
</p>  

<br>
<br />

These classes can be found in the following files:
- [MovingBehaviour](../src/main/java/model/game/pieces/movingBehaviours/MovingBehaviour.java)
- [MovingBehaviourGroup](../src/main/java/model/game/pieces/movingBehaviours/MovingBehaviourGroup.java)
- [PreDetermined](../src/main/java/model/game/pieces/movingBehaviours/PreDetermined.java)
- [AdjacentStrategy](../src/main/java/model/game/pieces/movingBehaviours/AdjacentStrategy.java)
- [LStrategy](../src/main/java/model/game/pieces/movingBehaviours/LStrategy.java)
- [DiagonalStrategy](../src/main/java/model/game/pieces/movingBehaviours/DiagonalStrategy.java)
- [SideStrategy](../src/main/java/model/game/pieces/movingBehaviours/SideStrategy.java)
- [TwoAndOneStrategy](../src/main/java/model/game/pieces/movingBehaviours/TwoAndOneStrategy.java)


#### Consequences:
Benefits of applying the above pattern:
 - Allows for simpler moving behaviours that can be composed into the more complex behaviours of the pieces.
 - Allows for the implementation of movingBehaviours that can be utilized by multiple pieces.
 - Isolate the implementation details.
 - Obeys Open/Closed Principle. New strategies can be introduced without having to change the context.