/*
    GRAPHICS AND TEXT RENDERING
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main extends Canvas {
    public Image test = load_image("src/asset/image.png");

    public static void main(String[] args) {
        // Create window
        JFrame frame = new JFrame("Window");
        Canvas canvas = new Main();
        canvas.setSize(320, 240);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    public void paint(Graphics g) {
        // Draw background
        g.drawImage(test, 0, 0, null);

        // Draw some text
        if (g instanceof Graphics2D g2d) {
            // Get font and set common setting
            Font font = new Font("Serif", Font.BOLD, 24);
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            draw_string_with_shadow(
                    g2d,
                    "Testing, testing...",
                    30,
                    40,
                    font,
                    Color.WHITE
            );
            draw_string_with_shadow(
                    g2d,
                    "123... 123...",
                    30,
                    70,
                    font,
                    Color.RED
            );
        }
    }

    // FUNCTION
    //--------------------------------------------------------------------

    // Loads an image based on provided path.
    public Image load_image(String path) {
        BufferedImage img = null;
        try {
            return img = ImageIO.read(new File(path));
        } catch (IOException e) {
            return null;
        }
    }

    // Renders text with drop shadow.
    public void draw_string_with_shadow(
            Graphics2D g2d,
            String text,
            int i0,
            int i1,
            Font f,
            Color c
    ) {
        // Draw text 1
        g2d.setColor(Color.BLACK);
        g2d.setFont(f);
        g2d.drawString(text, i0 + 2, i1 + 2);

        // Draw text 2
        g2d.setColor(c);
        g2d.setFont(f);
        g2d.drawString(text, i0, i1);
    }
}