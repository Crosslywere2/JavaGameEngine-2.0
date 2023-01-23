package com.crossly;

public abstract class GameManager {
    private final GameContainer gc;
    protected final Renderer renderer;
    protected final Input input;

    public GameManager(String title, int width, int height, float scale) {
        (gc = new GameContainer(title, width, height, scale)).initialize();
        renderer = gc.getRenderer();
        input = gc.getInput();
    }

    public final void play() {
        gc.start(this);
    }

    public abstract void onCreate();

    public abstract void onUpdate(double deltaTime);

    public abstract void onRender();

    public void dispose() {}

    public final void quit() {
        gc.stop();
    }
}
