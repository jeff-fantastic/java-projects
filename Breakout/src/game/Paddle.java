package game;

import java.awt.event.KeyEvent;

public class Paddle extends Sprite {
    // Declare variables
    private int dx;

    public Paddle() {
        initPaddle();
    }

    private void initPaddle() {
        loadImage("src/resources/paddle.png");
        confSpriteDimension();
        resetState();
    }

    private void resetState() {
        setPos(Commons.INIT_PADDLE);
    }

    public void move() {
        // Move based on input
        setPos(getPos().x + dx, getPos().y);

        // Make sure paddle stays on screen
        if (getPos().x <= 0) {
            setPos(0, getPos().y);
        }

        if (getPos().x >= Commons.WIDTH - getDimension().x) {
            setPos(
                    Commons.WIDTH - getDimension().x,
                    getPos().y
            );
        }
    }

    public void keyPressed(KeyEvent e) {
        // Get keycode
        int key = e.getKeyCode();

        // Determine action based on keycode
        switch (key) {
            case KeyEvent.VK_LEFT:
                setInputDirection(-1);
                break;
            case KeyEvent.VK_RIGHT:
                setInputDirection(1);
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        // Get keycode
        int key = e.getKeyCode();

        // Determine action based on keycode
        switch (key) {
            default:
                setInputDirection(0);
                break;
        }
    }

    public void setInputDirection(int dir) { this.dx = dir; }
    public int getInputDirection() { return this.dx; }
}
