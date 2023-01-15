package com.crossly;

public abstract class GameManager {
    private final GameContainer gc;
    protected final Renderer renderer;
    protected final Input input;
    public GameManager(GameContainer gc) {
        gc.initialize();
        this.gc = gc;
        renderer = gc.getRenderer();
        input = gc.getInput();
    }
    public final void play() {
        gc.start(this);
    }
    public abstract void onUpdate();
    public abstract void onRender();
}
