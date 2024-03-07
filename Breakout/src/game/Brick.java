package game;

public class Brick extends Sprite {
    // Declare variables
    private boolean destroyed;

    public Brick(Vector2 position) {
        initializeBrick(position);
    }

    // SET-GET
    //------------------------------------------------------------------------

    public boolean isDestroyed() {return this.destroyed;}
    public void setDestroyed(boolean destroyed) {this.destroyed = destroyed;}

    // FUNCTION
    //-------------------------------------------------------------------------

    // Initializes brick.
    private void initializeBrick(Vector2 position) {
        // Initialize values
        this.setPos(position);
        destroyed = false;

        // Create sprite
        loadImage("src/resources/brick.png");
        confSpriteDimension();
    }
}
