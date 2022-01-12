package ru.vsu.cs.PoryadinAV;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameGraphics {
    private Graphics2D g2d;
    private BufferedImage img;
    private int size;

    public GameGraphics(int size){
        this.size = countSize(size);
        this.img = new BufferedImage(this.size, this.size, BufferedImage.TYPE_INT_BGR);
        this.g2d = img.createGraphics();
    }

    private int countSize(int size) {
        for (int i = 400; true; i++) {
            if (i % size == 0) {
                return i;
            }
        }
    }

    public void updateField(Cell[][] field) {
        int edgeLength = this.size / field.length;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, size, size);

        for (int row = 0; row < field.length; row++) {
            for (int col = 0; col < field.length; col++) {
                g2d.setColor(field[row][col].getColor());
                g2d.fillRect(edgeLength * col,edgeLength * row, edgeLength, edgeLength);
            }
        }
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setSize(int size) {
        this.size = countSize(size);
        this.img = new BufferedImage(this.size, this.size, BufferedImage.TYPE_INT_BGR);
        this.g2d = img.createGraphics();
    }
}
