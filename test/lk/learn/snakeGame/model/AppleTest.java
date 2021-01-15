package lk.learn.snakeGame.model;

import lk.learn.snakeGame.utils.Util;
import org.junit.Test;

import static org.junit.Assert.*;

public class AppleTest {

    @Test
    public void testGetAppleY() {
        Apple apple = new Apple();
        apple.setAppleY(1);
        assertEquals(1, apple.getAppleY());
    }

    @Test
    public void testGetAppleX() {
        Apple apple = new Apple();
        apple.setAppleX(2);
        assertEquals(2, apple.getAppleX());
    }

    @Test
    public void testGetAppleSize() {
        Apple apple = new Apple();
        assertEquals(Util.UNIT_SIZE, apple.getAppleSize());
    }

    @Test
    public void testGetAppleColor() {
        Apple apple = new Apple();
        assertEquals(Util.APPLE_COLOR, apple.getAppleColor());
    }
}
