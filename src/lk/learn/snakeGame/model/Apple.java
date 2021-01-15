package lk.learn.snakeGame.model;

import lk.learn.snakeGame.utils.Util;

import java.awt.*;

public class Apple {

    int appleX;
    int appleY;
    int appleSize = Util.UNIT_SIZE;
    Color appleColor = Util.APPLE_COLOR;

    public int getAppleX() {
        return appleX;
    }

    public void setAppleX(int appleX) {
        this.appleX = appleX;
    }

    public int getAppleY() {
        return appleY;
    }

    public void setAppleY(int appleY) {
        this.appleY = appleY;
    }

    public int getAppleSize() {
        return appleSize;
    }

    public Color getAppleColor() {
        return appleColor;
    }
}
