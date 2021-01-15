package lk.learn.snakeGame.view;

import lk.learn.snakeGame.utils.Util;

import java.awt.*;

public class StartGamePanel {


    int WIDTH = Util.SCREEN_WIDTH;
    int HEIGHT = Util.SCREEN_HEIGHT;

    /**
     * Rendered the screen before game start
     */
    public void renderStartGamePanel(Graphics graphics) {
        //Game Start text
        graphics.setColor(Color.green);
        graphics.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics2 = graphics.getFontMetrics(graphics.getFont());
        graphics.drawString("Press Enter To Start The Game",
                (WIDTH - metrics2.stringWidth("Press Enter To Start The Game")) / 2, HEIGHT / 2);

    }

}
