package com.crossly;

import com.crossly.utils.Coordinate;

import javax.swing.event.MouseInputListener;
import java.awt.event.*;

public class Input implements KeyListener, MouseWheelListener, MouseInputListener {

    private final int MAX_KEYS = KeyEvent.CHAR_UNDEFINED;
    private final int MAX_BUTTONS = 5;
    private final boolean[] keys = new boolean[MAX_KEYS];
    private final boolean[] keysLast = new boolean[MAX_KEYS];
    private final boolean[] buttons = new boolean[MAX_BUTTONS];
    private final boolean[] buttonsLast = new boolean[MAX_BUTTONS];

    private final Coordinate mousePos = new Coordinate();
    private int scroll = 0;
    private final int scale;

    public Input(GameContainer gc) {
        gc.getWindow().getCanvas().addKeyListener(this);
        gc.getWindow().getCanvas().addMouseListener(this);
        gc.getWindow().getCanvas().addMouseWheelListener(this);
        gc.getWindow().getCanvas().addMouseMotionListener(this);
        scale = gc.getScale();
    }


    public void update() {
        int i;
        for (i = 0; i < MAX_KEYS; i++) {
            keysLast[i] = keys[i];
        }
        for (i = 0; i < MAX_BUTTONS; i++) {
            buttonsLast[i] = buttons[i];
        }
        scroll = 0;
    }

    public int getScroll() {
        return scroll;
    }

    public Coordinate getMousePos() {
        return new Coordinate(mousePos);
    }

    public int getMouseX() {
        return mousePos.getX();
    }

    public int getMouseY() {
        return mousePos.getY();
    }

    public boolean isButtonPressed(int button) {
        return buttons[button] && !buttonsLast[button];
    }

    public boolean isButtonHeld(int button) {
        return buttons[button] && buttonsLast[button];
    }

    public boolean isButtonReleased(int button) {
        return !buttons[button] && buttonsLast[button];
    }

    public boolean isKeyPressed(int key) {
        return keys[key] && !keysLast[key];
    }

    public boolean isKeyHeld(int key) {
        return keys[key] && keysLast[key];
    }

    public boolean isKeyReleased(int key) {
        return !keys[key] && keysLast[key];
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        keys[keyEvent.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        keys[keyEvent.getKeyCode()] = false;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
        scroll = mouseWheelEvent.getScrollAmount();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        buttons[mouseEvent.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        buttons[mouseEvent.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        mousePos.setX(mouseEvent.getX() / scale);
        mousePos.setY(mouseEvent.getY() / scale);
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        mousePos.setX(mouseEvent.getX() / scale);
        mousePos.setY(mouseEvent.getY() / scale);
    }
}
