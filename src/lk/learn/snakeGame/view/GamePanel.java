package lk.learn.snakeGame.view;

import lk.learn.snakeGame.controller.SnakeController;
import lk.learn.snakeGame.model.Apple;
import lk.learn.snakeGame.model.Score;
import lk.learn.snakeGame.model.Snake;
import lk.learn.snakeGame.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, Runnable {

    private StartGamePanel startGamePanel = new StartGamePanel();
    private GameOverPanel gameOverPanel = new GameOverPanel();
    private ScoreCard scoreCard = new ScoreCard();
    private Apple apple = new Apple();
    private Snake snake = new Snake();
    private Score score = new Score();
    private Thread thread;
    static int SCREEN_WIDTH = Util.SCREEN_WIDTH;
    static int SCREEN_HEIGHT = Util.SCREEN_HEIGHT;
    static int UNIT_SIZE = Util.UNIT_SIZE;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static final int DELAY = 175;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    boolean running = false;
    boolean isGameOver = false;
    Timer timer;
    Random random;

    public GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        this.addKeyListener(new SnakeController());
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
            g.setColor(apple.getAppleColor());
            g.fillOval(apple.getAppleX(), apple.getAppleY(), apple.getAppleSize(), apple.getAppleSize());

            for (int i = 0; i < snake.getSnakeBodyPart(); i++) {
                if (i == 0) {
                    g.setColor(snake.getSnakeHeadColor());
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(snake.getSnakeBodyColor());
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            scoreCard.renderScoreCard(g, score.getScore());
        } else if (isGameOver) {
            thread.stop();
            gameOverPanel.renderGameOverPanel(g, score.getScore());
        } else {
            startGamePanel.renderStartGamePanel(g);
        }
    }

    /**
     * Renders the apple randomly on the board
     */
    public void newApple() {
        apple.setAppleX(random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE);
        apple.setAppleY(random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE);
    }

    @Override
    public void run() {
        try {
            for (int i = 5; i > 0; i--) {
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

        for (int i = snake.getSnakeBodyPart(); i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (SnakeController.direction) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Extends body of snake, increase the score and render a new apple
     */
    public void checkApple() {
        if ((x[0] == apple.getAppleX()) && (y[0] == apple.getAppleY())) {
            thread.stop();
            snake.setSnakeBodyPart(snake.getSnakeBodyPart() + 1);
            score.setScore(score.getScore() + 1);
            start();
        }
    }


    /**
     * check if snake got hits
     */
    public void checkCollisions() {
        //checks if head collides with body
        for (int i = snake.getSnakeBodyPart(); i > 0; i--) {
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
                case KeyEvent.VK_ENTER:
                    if (!running) {
                        startGame();
                    }
                    break;
            }
        }
    }
}
