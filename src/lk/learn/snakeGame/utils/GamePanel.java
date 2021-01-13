package lk.learn.snakeGame.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, Runnable {

    private Thread thread;
    static final int SCREEN_WIDTH = 1280;
    static final int SCREEN_HEIGHT = 720;
    static final int UNIT_SIZE = 50;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static final int DELAY = 175;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyParts = 3;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    boolean isGameOver = false;
    Timer timer;
    Random random;

    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
    }

    /**
     * Rendered the screen before game start
     */
    public void initialRender(Graphics g) {
        //Game Start text
        g.setColor(Color.green);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Press Enter To Start The Game", (SCREEN_WIDTH - metrics2.stringWidth("Press Enter To Start The Game")) / 2, SCREEN_HEIGHT / 2);
    }

    /**
     * Start the game
     */
    public void startGame() {
        start();
        running = true;
        isGameOver = false;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    /**
     * Rendered game if running true
     * Rendered the game over screen if isGameOver is true
     * Rendered the initial screen if isGameOver is false and running false
     */
    public void draw(Graphics g) {

        if (running) {
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());
        } else if (isGameOver) {
            thread.stop();
            gameOver(g);
        } else {
            initialRender(g);
        }

    }

    /**
     * Renders the apple randomly on the board
     */
    public void newApple() {
        appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    @Override
    public void run() {
        try {
            for(int i = 5; i > 0; i--) {
                newApple();
                Thread.sleep(10000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * move the snake according to button pressed
     */
    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    public void start() {
        try {
            thread = new Thread(this);
            thread.start();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Extends body of snake, increase the score and render a new apple
     */
    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            thread.stop();
            System.out.println("stoped");
            bodyParts++;
            applesEaten++;
            start();
        }
    }


    /**
     * check if snake got hits
     */
    public void checkCollisions() {
        //checks if head collides with body
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
                isGameOver = true;
            }
        }
        //check if head touches left border
        if (x[0] < 0) {
            running = false;
            isGameOver = true;
        }
        //check if head touches right border
        if (x[0] > SCREEN_WIDTH) {
            running = false;
            isGameOver = true;
        }
        //check if head touches top border
        if (y[0] < 0) {
            running = false;
            isGameOver = true;
        }
        //check if head touches bottom border
        if (y[0] > SCREEN_HEIGHT) {
            running = false;
            isGameOver = true;
        }

        if (!running) {
            timer.stop();
        }
    }


    /**
     * renders game over screen
     */
    public void gameOver(Graphics g) {
        //Score
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());
        //Game Over text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Wasted", (SCREEN_WIDTH - metrics2.stringWidth("Wasted")) / 2, SCREEN_HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
                case KeyEvent.VK_ENTER:
                    if (!running) {
                        startGame();
                    }
                    break;
            }
        }
    }
}
