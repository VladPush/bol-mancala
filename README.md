### Usage

1. Open IntelliJ IDEA and import this Spring Boot project.
2. Navigate to the main class MancalaGameApplication.java.
3. Right-click on the main class and select "Run" from the context menu.
4. Alternatively, you can click on the green arrow icon located on the left-hand side of the main class to run it.

### Api

Use [swagger-ui](http://localhost:8080/swagger-ui/index.html) to explore api.

### Test

Use command `mvn test` to start unit tests.

Use command `mvn verify` to start unit + integration tests.

<details>
<summary> About Mancala game</summary>

### Board Setup
Each of the two players has his six pits in front of him. To the right of the six pits,
each player has a larger pit. At the start of the game, there are six stones in each
of the six round pits.
### Rules
### Game Play
The player who begins with the first move picks up all the stones in any of his own
six pits, and sows the stones on to the right, one in each of the following pits,
including his own big pit. No stones are put in the opponents' big pit. If the player's
last stone lands in his own big pit, he gets another turn. This can be repeated
several times before it's the other player's turn.
### Capturing Stones
During the game the pits are emptied on both sides. Always when the last stone
lands in an own empty pit, the player captures his own stone and all stones in the
opposite pit (the other player’s pit) and puts them in his own (big or little?) pit.
### The Game Ends
The game is over as soon as one of the sides runs out of stones. The player who
still has stones in his pits keeps them and puts them in his big pit. The winner of
the game is the player who has the most stones in his big pit.

</details>