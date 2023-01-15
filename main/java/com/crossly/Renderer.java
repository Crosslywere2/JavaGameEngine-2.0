package com.crossly;

import com.crossly.gfx.Image;
import com.crossly.utils.Coordinate;

import java.awt.image.DataBufferInt;

public class Renderer {
    private int width, height;
    private int[] pixels;

    public Renderer(GameContainer gc) {
        width = gc.getWidth();
        height = gc.getHeight();
        pixels = ((DataBufferInt) gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public void setPixel(int x, int y, int color) {
        if (x < 0 || y < 0 || x >= width || y >= height) return;
        pixels[x + y * width] = color;
    }

    public void setPixel(Coordinate pos, int color) {
        setPixel(pos.getX(), pos.getY(), color);
    }

    public void drawImage(Image image, int posX, int posY) {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                setPixel(posX + x, posY + y, image.getPixels()[x + y * image.getWidth()]);
            }
        }
    }

    public void drawLine(int startX, int startY, int endX, int endY, int color) {
        boolean vert = startX == endX;
        boolean hori = startY == endY;
        if (vert || hori) {
            if (startX > endX) {
                startX ^= endX ^= startX;
                endX ^= startX;
            }
            if (startY > endY) {
                startY ^= endY ^= startY;
                endY ^= startY;
            }
        }
        if (vert && !hori) {
            for (int y = 0; y < endY - startY; y++) {
                setPixel(startX, startY + y, color);
            }
        } else if (!vert && hori) {
            for (int x = 0; x < endX - startX; x++) {
                setPixel(startX + x, startY, color);
            }
        } else if (vert && hori) {
            setPixel(startX, startY, color);
        } else {
            int width = endX - startX;
            int height = endY - startY;
            double distance = Math.sqrt(width * width + height * height);
            for (int t = 0; t < distance; t++) {
                int x = startX + (int) ((t / distance) * width);
                int y = startY + (int) ((t / distance) * height);
                setPixel(x, y, color);
            }
        }
    }

    public void drawLine(Coordinate start, Coordinate end, int color) {
        drawLine(start.getX(), start.getY(), end.getX(), end.getY(), color);
    }

    public void drawRectangle(int posX, int posY, int width, int height, int color) {
        // Horizontal lines
        drawLine(posX, posY, posX + width, posY, color);
        drawLine(posX, posY + height, posX + width + 1, posY + height, color);
        // Vertical lines
        drawLine(posX, posY, posX, posY + height, color);
        drawLine(posX + width, posY, posX + width, posY + height, color);
    }

    public void drawRectangle(Coordinate pos, int width, int height, int color) {
        drawRectangle(pos.getX(), pos.getY(), width, height, color);
    }

    public void drawShape(Coordinate[] pos, int color) {
        for (int i = 1; i <= pos.length; i++) {
            drawLine(pos[i - 1], pos[i % pos.length], color);
        }
    }

    public void drawArc(int posX, int posY, int radius, int startAngle, int endAngle, int color) {
        for (int i = startAngle; i <= endAngle; i++) {
            int x = (int) (radius * Math.cos(Math.toRadians(i)));
            int y = (int) (radius * Math.sin(Math.toRadians(i)));
            setPixel(x + posX, y + posY, color);
        }
    }

    public void drawArc(Coordinate pos, int radius, int startAngle, int endAngle, int color) {
        drawArc(pos.getX(), pos.getY(), radius, startAngle, endAngle, color);
    }

    public void drawCircle(int posX, int posY, int radius, int color) {
        drawArc(posX, posY, radius, 0, 360, color);
    }

    public void drawCircle(Coordinate pos, int radius, int color) {
        drawCircle(pos.getX(), pos.getY(), radius, color);
    }

}
