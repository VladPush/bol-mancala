### Usage

1. Open IntelliJ IDEA and import this Spring Boot project.
2. Navigate to the main class MancalaGameApplication.java.
3. Right-click on the main class and select "Run" from the context menu.
4. Alternatively, you can click on the green arrow icon located on the left-hand side of the main class to run it.

> Note: you should have running Docker to start application.

#### Java Parameters

Below is a list of common Java parameters that can be used when running a Java application, along with explanations for
each parameter.

##### mancala_pits_min

- **Description:** Minimal value of pits in mancala game. Default value - 6.
- **Example:** mancala_pits_min=8 sets minimum value of pits to 8.

##### mancala_pits_max

- **Description:** Maximum value of pits in mancala game. Default value - 10.
- **Example:** mancala_pits_max=14 sets maximum value of pits to 14.

##### mancala_cache_initial_capacity

- **Description:** Starting capacity of mancala game cache. Default value - 100.
- **Example:** mancala_cache_initial_capacity=10 mean that initially size of cache will be 10.

##### mancala_cache_maximum_size

- **Description:** Maximum capacity of mancala game cache. Default value - 500.
- **Example:** mancala_cache_maximum_size=800 mean that maximum amount of games in cache can be 800.

##### mancala_cache_expire_after_write

- **Description:** Maximum life time for cache after last usage. Default value - 5 minute.
- **Example:** mancala_cache_expire_after_write=2 mean that object will remain in the cache for a maximum of 2 minutes
  after its last use.

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