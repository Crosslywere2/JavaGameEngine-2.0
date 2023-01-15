package com.crossly.gfx;

import com.crossly.utils.Coordinate;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Image {
    private final int width, height;
    private final int[] pixels;

    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

    public Image(String path) {
        BufferedImage img;
        try {
            img = ImageIO.read(Image.class.getResourceAsStream(path));
            width = img.getWidth();
            height = img.getHeight();
            pixels = img.getRGB(0, 0, width, height, null, 0, width);
            img.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] getPixels() {
        return pixels;
    }

    public int getPixel(int x, int y) {
        return pixels[x + y * width];
    }

    public void setPixel(int x, int y, int value) {
        pixels[x + y * width] = value;
    }

    public void setPixel(Coordinate pos, int value) {
        setPixel(pos.getX(), pos.getY(), value);
    }
}
