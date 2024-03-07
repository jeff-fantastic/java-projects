package game;

import java.util.Vector;

public class Ball extends Sprite {
    // Declare variables
    private Vector2 direction;

    public Ball(){
        initBall();
    }

    private void initBall() {
        // Set direction
        this.direction = new Vector2(1, -1);

        // Load sprite
        loadImage("src/resources/ball.png");
        confSpriteDimension();
        resetState();
    }

    private void resetState() {
        setPos(Commons.INIT_BALL);
    }

    public void move() {
        // Move ball
        setPos(getPos().x + direction.x, getPos().y + direction.y);

        // Check for collisions
        if (getPos().x == 0) {
            setDirection(1, direction.y);
        }

        if (getPos().x >= Commons.WIDTH - getRect().width) {
            setDirection(-1, direction.y);
        }

        if (getPos().y == 0) {
            setDirection(direction.x, 1);
        }
    }

    public void setDirection(int x, int y) { this.direction = new Vector2(x, y); }
    public Vector2 getDirection() { return this.direction; }

}
