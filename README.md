# Snake-Game

Snake, written in Java with Swing. 2016.

Small and self-contained — no game engine, no external dependencies, no build tool. Five classes.

## Running it

Download `snake.jar` and run it (needs a Java JRE):

```
java -jar snake.jar
```

Or build from source:

```
javac *.java
java RunGame
```

## Structure

| File | What it does |
|---|---|
| `RunGame.java` | Entry point — starts the Swing event loop |
| `DrawFrame.java` | The `JFrame` window, 500×500 |
| `MainPanel.java` | `JPanel` + `KeyListener` — rendering, input, and the game/​over state |
| `Snake.java` | Snake body as a `LinkedList<int[]>` of segments, plus heading and growth |
| `Food.java` | Random food placement and score |

Two details worth pointing at, since they're the reason the thing works:

- The body is a **linked list of coordinates**, so moving is a push-front and a pop-back rather than
  shifting an array — and growing is just skipping the pop.
- **Food placement checks against every body segment** before it commits to a location, so food never
  spawns underneath the snake.
