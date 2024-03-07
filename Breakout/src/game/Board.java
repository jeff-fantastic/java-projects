package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel {
    // Declare variables
    private final String gameOverString = "Game Over";
    private boolean inGame = true;

    // Declare objects
    public Timer timer;
    public Ball ball;
    public Paddle paddle;
    public Brick[] bricks;

    public Board() {
        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setPreferredSize(new Dimension(Commons.WIDTH, Commons.HEIGHT));
        gameInit();
    }

    private void gameInit() {
        // Create objects
        bricks = new Brick[Commons.N_BRICKS];
        ball = new Ball();
        paddle = new Paddle();

        // Generate bricks
        int k = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                bricks[k] = new Brick(new Vector2(j * 40 + 30, i * 10 + 50));
                k++;
            }
        }

        // Create timer
        timer = new Timer(Commons.PERIOD, new GameCycle());
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        if (inGame) {
            drawObjects(g2d);
        } else {
            gameFinished(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics2D g2d) {
        drawObject(g2d, ball);
        drawObject(g2d, paddle);
        for (int i = 0; i < Commons.N_BRICKS; i++) {
            if (!bricks[i].isDestroyed()) {
                drawObject(g2d, bricks[i]);
            }
        }
    }

    private void drawObject(Graphics2D g2d, Sprite obj) {
        g2d.drawImage(
                obj.sprite, obj.getPos().x, obj.getPos().y,
                obj.getDimension().x, obj.getDimension().y, this
        );
    }

    private void gameFinished(Graphics2D g2d) {
        Font fnt = new Font("Arial", Font.BOLD, 24);
        FontMetrics fntMet = this.getFontMetrics(fnt);

        g2d.setColor(Color.BLACK);
        g2d.setFont(fnt);
        g2d.drawString(
                gameOverString,
                (Commons.WIDTH - fntMet.stringWidth(gameOverString)) / 2,
                Commons.HEIGHT / 2
        );
    }

    private class TAdapter extends KeyAdapter {
        public void keyReleased(KeyEvent e) {
            paddle.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            paddle.keyPressed(e);
        }
    }

    private class GameCycle implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            doGameCycle();
        }
    }

    private void doGameCycle() {
        ball.move();
        paddle.move();
        checkCollision();
        repaint();
    }

    public void stopGame() {
        inGame = false;
        timer.stop();
    }

    private void checkCollision() {
        if (ball.getRect().getMaxY() > Commons.BOTTOM_EDGE) {
            stopGame();
        }

        for (int i = 0, j = 0; i < Commons.N_BRICKS; i++) {
            // Skip if destroyed
            if (bricks[i].isDestroyed()) {
                j++;
                continue;
            }

            // Exit loop if all bricks are broken
            if (j == Commons.N_BRICKS) {
                stopGame();
                break;
            }

            // Check for collision
            if ((ball.getRect()).intersects(bricks[i].getRect())) {
                int ballLeft = (int) ball.getRect().getMinX();
                int ballHeight = (int) ball.getRect().getHeight();
                int ballWidth = (int) ball.getRect().getWidth();
                int ballTop = (int) ball.getRect().getMinY();

                Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
                Point pointLeft = new Point(ballLeft -1, ballTop);
                Point pointTop = new Point(ballLeft, ballTop - 1);
                Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

                if (!bricks[i].isDestroyed()) {
                    if (bricks[i].getRect().contains(pointRight)) {
                        ball.setDirection(-1, ball.getDirection().y);
                    } else if (bricks[i].getRect().contains(pointLeft)) {
                        ball.setDirection(1, ball.getDirection().y);
                    } else if (bricks[i].getRect().contains(pointTop)) {
                        ball.setDirection(ball.getDirection().x, 1);
                    } else if (bricks[i].getRect().contains(pointBottom)) {
                        ball.setDirection(ball.getDirection().x, -1);
                    }

                    bricks[i].setDestroyed(true);
                }
            }
        }

        if (ball.getRect().intersects(paddle.getRect())) {
            int paddleLPos = (int) paddle.getRect().getMinX();
            int ballLPos = (int) paddle.getRect().getMinX();

            int first = paddleLPos + 8;
            int second = paddleLPos + 16;
            int third = paddleLPos + 24;
            int fourth = paddleLPos + 32;

            if (ballLPos < first) {
                ball.setDirection(-1, -1);
            } else if (ballLPos < second) {
                ball.setDirection(-1, -1 * ball.getDirection().y);
            } else if (ballLPos < third) {
                ball.setDirection(0, -1);
            } else if (ballLPos < fourth) {
                ball.setDirection(-1, -1 * ball.getDirection().y);
            } else {
                ball.setDirection(1, -1);
            }
        }
    }

}

