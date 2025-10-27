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

## Main Dependencies

- JUnit 5 (`org.junit.jupiter:junit-jupiter`) for unit testing.
- JUnit Platform (`org.junit.platform:junit-platform-launcher`) for test runtime.

---

## Naming Conventions

This project follows the official Java naming conventions for clarity and consistency.

### General Rules

- Use English for all identifiers.
- Be descriptive and concise.
- Avoid unnecessary abbreviations.
- Prefer meaningful names (e.g., boardWidth instead of bw).

### Conventions Summary

| Element Type         | Convention   | Example                        |
|-----------------------|--------------|----------------------------------|
| Package               | lowercase    | `fr.campus.loic.squaregames`       |
| Class / Interface     | PascalCase   | `GameController`, `Player`       |
| Method                | camelCase    | `checkWinnerCondition()`         |
| Variable / Attribute  | camelCase    | `victoryCondition`               |
| Constant              | UPPER_CASE   | `MAX_SCORE`                      |

---

### Notes

- **Classes** are nouns (e.g., `Board`, `Player`).
- **Methods** are verbs or verb phrases (e.g., `setOwner()`, `getBoard()`).
- **Constants** are written in uppercase and declared as `public static final`.
- **Packages** are all lowercase and reflect the project structure.
- **Names** should be clear, descriptive, and written in English.

---

## Build and Run

To compile and run the project using Gradle:

```bash
# Build the project
./gradlew build

# Run the application
java -cp build/libs/TicTacToe-1.0-SNAPSHOT.jar fr.campus.loic.squaregames.Main
```

---

## Sequence diagram

```mermaid
sequenceDiagram

    actor Main
    participant GameController
    participant View
    participant Game
    participant Board

    Main->>GameController: main(args)
    GameController->>View: println("Bienvenue dans le jeu !")
    GameController->>View: askInt("Choisir le mode de jeu")
    View-->>GameController: mode choisi

    GameController->>Game: initialiser la partie()
    GameController->>Board: afficher le plateau()
    Board-->>GameController: représentation du plateau

    GameController->>View: afficher le plateau()
    View-->>GameController: confirmation affichage

    GameController->>Game: getMoveFromPlayer(player)
    Game->>Board: vérifier case vide()
    Board-->>Game: true/false

    GameController->>Game: playerTurn(player, move)
    Game->>Board: setOwner(col, row, player)
    Board-->>Game: OK

    GameController->>Game: checkWinnerCondition()
    Game-->>GameController: victoire / égalité / en cours

    GameController->>View: println("Fin de partie")
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
---
config:
  theme: neo
---
classDiagram
    direction BT
    class ArtificialPlayer {
        - SecureRandom RANDOM
        + randomCoordinatePlayed(int) int
    }
    class ArtificialPlayerFactory {
        + createPlayer(String, int) IPlayer
    }
    class Board {
        - int HEIGHT
        - int WIDTH
        - int SIZE
        - Tile[] TILES
        + getWidth() int
        + hasPawnAt(int, int) boolean
        + getHeight() int
        - createBoard() void
        + getTile(int, int) Tile
        + getSize() int
    }
    class Connect4
    class Connect4Factory {
        + createGame() IGame
    }
    class ConsoleColors {
        + String WHITE
        + String BOLD_RED
        + String YELLOW
        + String RED
        + String GREEN
        + String RESET
        + String BOLD_GREEN
        + String BLUE
        + String PURPLE
        + String CYAN
    }
    class Fr {
        + String victory
        + String rulesTicTacToe
        + String wrongChoice
        + String rulesGomoku
        + String chooseGameMode
        + String chooseGameType
        + String wrongCoordinate
        + String coordinateX
        + String colFull
        + String choose
        + String coordinateY
        + String tileAlreadyTaken
        + String turnOfPlayer
        + String exceptionIntMessage
        + String separator
        + String rulesConnect4
    }
    class Game {
        - boolean GRAVITY
        - IPlayer currentPlayer
        ~ int countTurn
        - Board BOARD
        - int VICTORY_CONDITION
        - String RULES
        - IPlayer[] players
        + setCountTurn(int) void
        + sameOwner(int, int, int, int) boolean
        + getCountTurn() int
        + checkWinnerCondition(int) boolean
        + getBoard() Board
        + getVictoryCondition() int
        + isNotEmpty(int, int) boolean
        + getRules() String
        + playerTurn(IPlayer, int[]) void
        + getBoardTile(int, int) Tile
        + getCurrentPlayer() IPlayer
        + setCurrentPlayer(IPlayer) void
        + hasPawnAt(int, int) boolean
        + getGravity() boolean
        + setOwner(int, int, IPlayer) void
        + getBoardSize() int
        + getPlayers() IPlayer[]
        + setPlayers(IPlayer[]) void
    }
    class GameController {
        - View VIEW
        + States state
        - IGame game
        - GameFactory gameFactory
        + nextTileEmpty(int) int
        + chooseGameMode() void
        + playState() void
        + getMoveFromPlayer(IPlayer) int[]
        - getCoordinates() int[]
        + chooseGameType() void
        + changeCurrentPlayer() void
        - getRow() int[]
        + winner(IPlayer) void
        + checkState() void
        + display() void
        + playTurn() void
    }
    class GameFactory {
        + createGame() IGame
    }
    class Gomoku
    class GomokuFactory {
        + createGame() IGame
    }
    class HumanPlayer
    class HumanPlayerFactory {
        + createPlayer(String, int) IPlayer
    }
    class IGame {
        <<Interface>>
        + getBoard() Board
        + getVictoryCondition() int
        + hasPawnAt(int, int) boolean
        + setCountTurn(int) void
        + getRules() String
        + setOwner(int, int, IPlayer) void
        + getBoardSize() int
        + setPlayers(IPlayer[]) void
        + getPlayers() IPlayer[]
        + playerTurn(IPlayer, int[]) void
        + getBoardTile(int, int) Tile
        + getCountTurn() int
        + getCurrentPlayer() IPlayer
        + checkWinnerCondition(int) boolean
        + sameOwner(int, int, int, int) boolean
        + getGravity() boolean
        + setCurrentPlayer(IPlayer) void
        + isNotEmpty(int, int) boolean
    }
    class IPlayer {
        <<Interface>>
        + getRepresentation() String
        + isHuman() boolean
        + getNumber() int
    }
    class Main {
        + main(String[]) void
    }
    class OutOfBoardException
    class Player {
        - String COLOR
        - String REPRESENTATION
        - int NUMBER
        - boolean IS_HUMAN
        + isHuman() boolean
        + getRepresentation() String
        + getNumber() int
    }
    class PlayerFactory {
        + createPlayer(String, int) IPlayer
    }
    class RandomCoordinateCapable {
        <<Interface>>
        + randomCoordinatePlayed(int) int
    }
    class States {
        <<enumeration>>
        +  WAIT_MODE
        +  WINNER
        +  DRAW
        +  WAIT_STYLE
        +  NEXT
        +  END
        +  WAIT_COORDINATES
        + values() States[]
        + valueOf(String) States
    }
    class TicTacToe
    class TicTacToeFactory {
        + createGame() IGame
    }
    class Tile {
        - boolean hasPawn
        - String representation
        - int COORDINATE_X
        - int COORDINATE_Y
        + setPawn(boolean) void
        + setRepresentation(String) void
        + hasPawn() boolean
        + getRepresentation() String
    }
    class View {
        - Scanner CLAVIER
        + askInt(String) int
        + println(String) void
        + print(String) void
        + askCoordinates(String) int[]
        + clearScreen() void
    }

    ArtificialPlayer  -->  Player
    ArtificialPlayer  ..>  RandomCoordinateCapable
    ArtificialPlayerFactory  -->  PlayerFactory
    Connect4  -->  Game
    Connect4Factory  -->  GameFactory
    Game  ..>  IGame
    Gomoku  -->  Game
    GomokuFactory  -->  GameFactory
    HumanPlayer  -->  Player
    HumanPlayerFactory  -->  PlayerFactory
    Player  ..>  IPlayer
    TicTacToe  -->  Game
    TicTacToeFactory  -->  GameFactory
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

---

## State-machine description

| **État source**     | **Condition / Événement**      | **État suivant**       | **Remarques** |
|--------------------|--------------------------------|----------------------|----------------|
| `[*]`              | —                              | WAIT_STYLE           | État initial |
| WAIT_STYLE          | Type de jeu choisi             | WAIT_MODE            | TicTacToe / Gomoku / Connect4 |
| WAIT_MODE           | Mode choisi                    | WAIT_COORDINATES     | Solo / Multi / AI |
| WAIT_COORDINATES    | Coordonnées valides            | VérificationFinDeTour| Tour du joueur joué |
| VérificationFinDeTour | !winner && !draw             | WAIT_COORDINATES     | Continue le jeu |
| VérificationFinDeTour | boardFull && !winner         | DRAW                 | Partie nulle |
| VérificationFinDeTour | winner                        | WINNER               | Partie terminée avec gagnant |
| DRAW               | Message affiché                | END                  | État terminal |
| WINNER             | Message affiché                | END                  | État terminal |
| END                | —                              | `[*]`                | Fin du cycle |


