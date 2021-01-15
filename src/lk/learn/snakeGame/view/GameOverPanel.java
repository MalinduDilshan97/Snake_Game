package lk.learn.snakeGame.view;

import lk.learn.snakeGame.utils.Util;

import java.awt.*;

public class GameOverPanel {

    int WIDTH = Util.SCREEN_WIDTH;
    int HEIGHT = Util.SCREEN_HEIGHT;

    /**
     * renders game over screen
     */
    public void renderGameOverPanel(Graphics g, int score) {
        //Score
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = g.getFontMetrics(g.getFont());
        g.drawString("Score: " + score, (WIDTH - metrics1.stringWidth("Score: " + score)) / 2,
                g.getFont().getSize());
        //Game Over text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = g.getFontMetrics(g.getFont());
        g.drawString("Wasted", (WIDTH - metrics2.stringWidth("Wasted")) / 2, HEIGHT / 2);
    }

}
