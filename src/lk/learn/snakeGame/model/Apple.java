package lk.learn.snakeGame.model;

import lk.learn.snakeGame.utils.Config;

public class Apple {

    int appleX;
    int appleY;
    int appleSize = Config.UNIT_SIZE;

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
}
