package com.crossly;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Window {
    private BufferedImage image;
    private Canvas canvas;
    private BufferStrategy strategy;
    private Graphics graphics;
    private JFrame frame;

    public Window(GameContainer gc) {
        image = new BufferedImage(gc.getWidth(), gc.getHeight(), BufferedImage.TYPE_INT_RGB);
        canvas = new Canvas();
        Dimension d = new Dimension((int)(gc.getWidth() * gc.getScale()), (int)(gc.getHeight() * gc.getScale()));
        canvas.setPreferredSize(d);
        canvas.setMaximumSize(d);
        canvas.setMinimumSize(d);

        frame = new JFrame(gc.getTitle());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(canvas, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        canvas.createBufferStrategy(2);
        strategy = canvas.getBufferStrategy();
        graphics = strategy.getDrawGraphics();
    }

    public void update() {
        graphics.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
        strategy.show();
    }

    public BufferedImage getImage() {
        return image;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void exit() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}
