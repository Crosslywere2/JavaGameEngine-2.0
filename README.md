# JavaGameEngine-2.0
### (A reproduction/extension of the now private JavaGameEngine)
This **"game engine"** is an implementation of a 2D pixel renderer with input handling, all packaged into a single library.
The `abstract class GameManager` is to be extended by the game's class.
The constructor of said class needs to take in, as a parameter, a `GameContainer` class.
To run the game simply use the following:

```java
import com.crossly.GameContainer;
import com.crossly.GameManager;

public class Game extends GameManager {
    // Constructor code
    // onUpdate code
    // onRender code
    public static void main(String[] args) {
        GameContainer gc = new GameContainer(title, width, height, scale);
        Game game = new Game(gc);
        game.play();
    }
}
```
Any class that extends the `GameManager` has access to variables for the `Renderer` class called `renderer` and the `Input` class called `input`.

## GameContainer
The `GameContainer` class is responsible for creating the window and setting up the renderer and input handling classes.
It is also responsible for the `TimeManager` which is used to determine when to update the game logic.
The `GameContainer` calls on the onUpdate and onRender functions of the `GameManager` class at a frame rate of 0.016 seconds (60fps).

## Renderer
The `Renderer` class is used to do basic pixel drawing code via its set pixel code.
The setPixel function is can be called using the x y values of the pixel or via the `Coordinate` class.
The color of each pixel is a 32bit integer which follows the ARGB pattern.
This class has a few implemented drawing methods like:
- drawLine
- drawRectangle
- fillRectangle
- drawShape
- etc...

## Input
The `Input` class is responsible for handling any input that occurs within the window of the game.
This works using the event system in java, meaning that when calling methods tha check on buttons/keys, you are to use the `MouseEvent`/`KeyEvent` to get the appropriate event.
The class holds data for 5 buttons and 65535 keys, whose states can be checked via: 
- is(Button/Key)Pressed
- is(Button/Key)Held
- is(Button/Key)Released

The class holds the coordinate position of the mouse as well, accessed via:
- getMousePos
