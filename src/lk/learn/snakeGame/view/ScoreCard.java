package lk.learn.snakeGame.view;

import lk.learn.snakeGame.utils.Config;

import java.awt.*;

public class ScoreCard {

    int WIDTH = Config.SCREEN_WIDTH;

    public void renderScoreCard(Graphics g, int applesEaten){
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2,
                g.getFont().getSize());
    }

}
