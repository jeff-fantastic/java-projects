package game;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Rectangle;

public class Sprite {
    // Declare variables
    private Vector2 spritePos;
    private Vector2 spriteDimension;
    Image sprite;

    // SET-GET
    //------------------------------------------------------------------------

    public void setPos(int x, int y) {this.spritePos = new Vector2(x, y);}
    public void setPos(Vector2 pos) {this.spritePos = pos;}
    public Vector2 getPos() {return this.spritePos;}

    public void setDimension(int width, int height) {this.spriteDimension = new Vector2(width, height);}
    public void setDimension(Vector2 dimension) {this.spriteDimension = dimension;}
    public Vector2 getDimension() {return this.spriteDimension;}

    // FUNCTION
    //-------------------------------------------------------------------------

    // Returns a rectangle based on position and dimension of sprite
    public Rectangle getRect() {
        return new Rectangle(
                spritePos.x, spritePos.y,
                spriteDimension.x, spriteDimension.y
        );
    }

    // Configures sprite dimension based on sprite variable
    public void confSpriteDimension() {
        this.spriteDimension = new Vector2(
                sprite.getWidth(null),
                sprite.getHeight(null)
        );
    }

    // Loads image to be used as sprite.
    public void loadImage(String path) {
        var ii = new ImageIcon(path);
        sprite = ii.getImage();
    }

}
