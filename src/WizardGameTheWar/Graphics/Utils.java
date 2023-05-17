package WizardGameTheWar.Graphics;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Utils {
    public static BufferedImage scaleImage(BufferedImage image) {
        int newWidth = image.getWidth() * 2;
        int newHeight = image.getHeight() * 2;

        BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = scaledImage.createGraphics();
        g2d.drawImage(image, 0, 0, newWidth, newHeight, null);
        g2d.dispose();

        return scaledImage;
    }

    public static BufferedImage rotateToAngle(BufferedImage sprite, double angle) {
        BufferedImage rotated = new BufferedImage(sprite.getWidth(), sprite.getHeight(), sprite.getType());
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform transform = new AffineTransform();
        transform.rotate(angle, sprite.getWidth()/2, sprite.getHeight()/2);
        g2d.setTransform(transform);
        g2d.drawImage(sprite, 0, 0, null);
        g2d.dispose();
        return rotated;
    }
}
