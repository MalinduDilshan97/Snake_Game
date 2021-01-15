package lk.learn.snakeGame.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreTest {
    @Test
    public void testGetScore() {
        Score score = new Score();
        score.setScore(1);
        assertEquals(1, score.getScore());
    }
}
