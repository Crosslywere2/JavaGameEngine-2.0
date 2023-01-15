package com.crossly;

import com.crossly.utils.TimeManager;

public class GameContainer extends TimeManager implements Runnable {
    private String title = "Crosslywere's Java Game";
    private int width = 320;
    private int height = 240;
    private int scale = 4;
    private int ticksPerSec = 60;
    private double tickRate = 1.0 / ticksPerSec;
    private boolean initialized = false;
    private boolean running = false;

    private Thread thread;
    private Window window;
    private Renderer renderer;
    private Input input;
    private GameManager gameManager;

    public GameContainer() {
    }

    public GameContainer(String title, int width, int height, int scale) {
        this.title = title;
        this.width = Math.max(128, width);
        this.height = Math.max(128, height);
        this.scale = Math.max(1, scale);
    }
    public void initialize() {
        window = new Window(this);
        renderer = new Renderer(this);
        input = new Input(this);
        initialized = true;
    }

    public void start(GameManager gameManager) {
        if (!initialized) initialize();
        this.gameManager = gameManager;
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        running = true;
        double unprocessed = 0.0;
        TimeManager.init();
        while (running) {
            boolean render = false;
            TimeManager.tick();
            unprocessed += TimeManager.getTimeDelta();
            while (unprocessed >= tickRate) {
                unprocessed -= tickRate;
                gameManager.onUpdate();
                input.update();
                render = true;
            }
            if (render) {
                renderer.clear();
                gameManager.onRender();
                window.update();
            } else {
                try {
                    Thread.sleep(0, 10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public int getTicksPerSec() {
        return ticksPerSec;
    }

    public void setTicksPerSec(int ticksPerSec) {
        if (ticksPerSec >24) {
            this.ticksPerSec = ticksPerSec;
            tickRate = 1.0 / ticksPerSec;
        }
    }

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getScale() {
        return scale;
    }

    public Window getWindow() {
        return window;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public Input getInput() {
        return input;
    }
}
