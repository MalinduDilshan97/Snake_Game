package lk.learn.snakeGame.model;

import lk.learn.snakeGame.utils.Util;

import java.awt.*;

public class Snake {

    int SnakeBodyPart = Util.SNAKE_BODY_PART;
    Color snakeBodyColor = Util.SNAKE_BODY_COLOR;
    Color snakeHeadColor = Util.SNAKE_HEAD_COLOR;

    public int getSnakeBodyPart() {
        return SnakeBodyPart;
    }

    public void setSnakeBodyPart(int snakeBodyPart) {
        SnakeBodyPart = snakeBodyPart;
    }

    public Color getSnakeBodyColor() {
        return snakeBodyColor;
    }

    public Color getSnakeHeadColor() {
        return snakeHeadColor;
    }
}
