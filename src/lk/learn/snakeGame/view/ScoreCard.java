package lk.learn.snakeGame.view;

import lk.learn.snakeGame.utils.Util;

import java.awt.*;

public class ScoreCard {

    int WIDTH = Util.SCREEN_WIDTH;

    public void renderScoreCard(Graphics g, int score){
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        g.drawString("Score: " + score, (WIDTH - metrics.stringWidth("Score: " + score)) / 2,
                g.getFont().getSize());
    }

}
