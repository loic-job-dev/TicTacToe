# SquareGames

School project in Java

---

## Description
This project aims to create a TicTacToe game or a Gomoku game, or a Connect4 game, playable in console.

The first menu allows the user to choose the type of game he wants to play, and a second menu allows the user to choose between 3 different game modes :
- human versus human
- human versus computer
- computer versus computer

---

## Prerequisites

To build and run this project, you will need:

- **Java Development Kit (JDK)**: version 17 or higher.
- **Gradle**: the project is compatible with Gradle 8+ and it is recommended to use the **Gradle Wrapper** provided (`./gradlew`).
- **Recommended IDE**: IntelliJ IDEA (or any other Gradle-compatible Java IDE).
- **Internet connection**: required to download dependencies from Maven Central (JUnit 5).

---

### Main Dependencies

- JUnit 5 (`org.junit.jupiter:junit-jupiter`) for unit testing.
- JUnit Platform (`org.junit.platform:junit-platform-launcher`) for test runtime.

---

## Build and Run

To compile and run the project using Gradle:

```bash
# Build the project
./gradlew build

# Run the application
java -cp build/libs/TicTacToe-1.0-SNAPSHOT.jar fr.campus.loic.tictactoe.Main
```

---

## Class diagram before MVC pattern

```mermaid
classDiagram
    direction BT
    class ArtificialPlayer {
        - SecureRandom RANDOM
        + randomCoordinatePlayed(int) int
    }
    class Board {
        - int HEIGHT
        - int WIDTH
        - int SIZE
        - Tile[] tiles
        - createBoard() void
        + getSize() int
        + getWidth() int
        + getTile(int, int) Tile
        + getHeight() int
    }
    class Connect4 {
        + getMoveFromPlayer(Player) int[]
        + nextTileEmpty(int) int
    }
    class ConsoleColors {
        + String CYAN
        + String YELLOW
        + String RED
        + String RESET
        + String BOLD_RED
        + String PURPLE
        + String WHITE
        + String BOLD_GREEN
        + String GREEN
        + String BLUE
    }
    class Fr {
        + String chooseGameMode
        + String turnOfPlayer
        + String wrongCoordinate
        + String separator
        + String choose
        + String victory
        + String chooseGameType
        + String rulesGomoku
        + String colFull
        + String coordinateX
        + String wrongChoice
        + String rulesConnect4
        + String coordinateY
        + String tileAlreadyTaken
        + String rulesTicTacToe
        + String exceptionIntMessage
    }
    class Game {
        # InteractionUtilisateur clavier
        # Player player1
        # View view
        # int vicortyCondition
        # Player player2
        # String rules
        # Board board
        # Player[] players
        + getMoveFromPlayer(Player) int[]
        + checkWinnerCondition(int) boolean
        + getBoard() Board
        + sameOwner(int, int, int, int) boolean
        + playerTurn(Player) void
        + isNotEmpty(int, int) boolean
        + play() void
        + setOwner(int, int, Player) void
        + display() void
        + chooseGameMode() void
    }
    class Gomoku
    class HumanPlayer
    class InteractionUtilisateur {
        - Scanner clavier
        + nextLine() void
        + nextInt() int
    }
    class Main {
        + main(String[]) void
    }
    class Player {
        ~ String representation
        ~ String color
        ~ int number
        + getNumber() int
        + getRepresentation() String
    }
    class RandomCoordinateCapable {
        <<Interface>>
        + randomCoordinatePlayed(int) int
    }
    class TicTacToe
    class Tile {
        - int coordinateX
        - boolean hasPawn
        - String representation
        - int coordinateY
        + getCoordinateY() int
        + getRepresentation() String
        + setPawn(boolean) void
        + setRepresentation(String) void
        + getCoordinateX() int
        + hasPawn() boolean
    }
    class View {
        + println(String) void
        + print(String) void
    }

    ArtificialPlayer  -->  Player
    ArtificialPlayer  ..>  RandomCoordinateCapable
    Connect4  -->  Game
    Gomoku  -->  Game
    HumanPlayer  -->  Player
    TicTacToe  -->  Game

```

---  

## Class diagram after MVC pattern

```mermaid
classDiagram
direction BT
class ArtificialPlayer {
- SecureRandom RANDOM
+ randomCoordinatePlayed(int) int
  }
  class Board {
- int HEIGHT
- int WIDTH
- int SIZE
- Tile[] tiles
- createBoard() void
+ getSize() int
+ getWidth() int
+ getTile(int, int) Tile
+ getHeight() int
  }
  class Connect4 {
+ getMoveFromPlayer(Player) int[]
+ nextTileEmpty(int) int
  }
  class Connect4Controller
  class ConsoleColors {
+ String CYAN
+ String YELLOW
+ String RED
+ String RESET
+ String BOLD_RED
+ String PURPLE
+ String WHITE
+ String BOLD_GREEN
+ String GREEN
+ String BLUE
  }
  class Fr {
+ String chooseGameMode
+ String turnOfPlayer
+ String wrongCoordinate
+ String separator
+ String choose
+ String victory
+ String chooseGameType
+ String rulesGomoku
+ String colFull
+ String coordinateX
+ String wrongChoice
+ String rulesConnect4
+ String coordinateY
+ String tileAlreadyTaken
+ String rulesTicTacToe
+ String exceptionIntMessage
  }
  class Game {
# GameController clavier
# Player player1
# View view
# int vicortyCondition
# Player player2
# String rules
# Board board
# Player[] players
+ getMoveFromPlayer(Player) int[]
+ checkWinnerCondition(int) boolean
+ getBoard() Board
+ sameOwner(int, int, int, int) boolean
+ playerTurn(Player) void
+ isNotEmpty(int, int) boolean
+ play() void
+ setOwner(int, int, Player) void
+ display() void
+ chooseGameMode() void
  }
  class GameController {
- Scanner clavier
+ nextLine() void
+ nextInt() int
  }
  class Gomoku
  class GomokuController
  class HumanPlayer
  class Main {
+ main(String[]) void
  }
  class Player {
  ~ String representation
  ~ String color
  ~ int number
+ getNumber() int
+ getRepresentation() String
  }
  class RandomCoordinateCapable {
  <<Interface>>
+ randomCoordinatePlayed(int) int
  }
  class TicTacToe
  class TicTacToeController
  class TicTacToeTest {
+ testFirstLineSecondColumn() void
+ testNewBoardIsEmpty() void
+ testTileCaptured() void
+ testCoordinateTooHigh() void
  }
  class Tile {
- int coordinateX
- boolean hasPawn
- String representation
- int coordinateY
+ getCoordinateY() int
+ getRepresentation() String
+ setPawn(boolean) void
+ setRepresentation(String) void
+ getCoordinateX() int
+ hasPawn() boolean
  }
  class View {
+ println(String) void
+ print(String) void
  }

ArtificialPlayer  -->  Player
ArtificialPlayer  ..>  RandomCoordinateCapable
Connect4  -->  Game
Connect4Controller  -->  GameController
Gomoku  -->  Game
GomokuController  -->  GameController
HumanPlayer  -->  Player
TicTacToe  -->  Game
TicTacToeController  -->  GameController 
```

---

## Algorithm for the checkWinnerCondition() method

```mermaid
flowchart TD
    Start([Start checkWinnerCondition])
    iLoop{For each row i}
    jLoop{For each column j}
    TileCheck{Tile i_j not empty?}
    DirectionsLoop{For each direction -dx,dy-}
SetCount[Set count = 1]
kLoop{For k from 1 to condition-1}
CheckNextTile{Next tile in direction dx_dy within board?}
SameOwner{Same owner as start tile?}
IncrementCount[Increment count]
CountCheck{count == condition?}
ReturnTrue[Return true]
ContinueDirection[Continue to next direction]
End([Return false])

%% Flèches
Start --> iLoop
iLoop --> jLoop
jLoop --> TileCheck
TileCheck -- No --> jLoop
TileCheck -- Yes --> DirectionsLoop
DirectionsLoop --> SetCount
SetCount --> kLoop
kLoop --> CheckNextTile
CheckNextTile -- No --> ContinueDirection
CheckNextTile -- Yes --> SameOwner
SameOwner -- Yes --> IncrementCount --> kLoop
SameOwner -- No --> ContinueDirection
kLoop --> CountCheck
CountCheck -- Yes --> ReturnTrue
CountCheck -- No --> DirectionsLoop
ContinueDirection --> DirectionsLoop
DirectionsLoop --> jLoop
jLoop --> iLoop
iLoop --> End
```