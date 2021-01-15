package lk.learn.snakeGame.model;

import lk.learn.snakeGame.utils.Util;
import org.junit.Test;

import static org.junit.Assert.*;

public class SnakeTest {


    @Test
    public void testGetSnakeBodyPart() {
        Snake snake = new Snake();
        assertEquals(Util.SNAKE_BODY_PART, snake.getSnakeBodyPart());
    }

    @Test
    public void testSetSnakeBodyPart() {
        Snake snake = new Snake();
        snake.setSnakeBodyPart(10);
        assertEquals(10, snake.getSnakeBodyPart());
    }

    @Test
    public void testGetSnakeBodyColor() {
        Snake snake = new Snake();
        assertEquals(Util.SNAKE_BODY_COLOR, snake.getSnakeBodyColor());
    }

    @Test
    public void testGetSnakeHeadColor() {
        Snake snake = new Snake();
        assertEquals(Util.SNAKE_HEAD_COLOR, snake.getSnakeHeadColor());
    }

}
