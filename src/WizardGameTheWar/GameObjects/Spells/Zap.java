package WizardGameTheWar.GameObjects.Spells;

import WizardGameTheWar.GameObjects.GameObject;
import WizardGameTheWar.GameObjects.GameObjectManager;
import WizardGameTheWar.GameObjects.Obstacles.Obstacle;
import WizardGameTheWar.Graphics.Assets;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/***
 * Clasa se ocupa de un spell care apare la click
 */
public class Zap extends Spell {
    Point target;
    public Zap(int x, int y, Point target) {
        this.sprite = Assets.spellZap;
        this.x = x;
        this.y = y;
        this.target = target;

        float dx = target.x - x;
        float dy = target.y - y;
        double angle = Math.atan2(dy, dx) + Math.toRadians(90);
        BufferedImage rotated = new BufferedImage(sprite.getWidth(), sprite.getHeight(), sprite.getType());
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform transform = new AffineTransform();
        transform.rotate(angle, sprite.getWidth()/2, sprite.getHeight()/2);
        g2d.setTransform(transform);
        g2d.drawImage(sprite, 0, 0, null);
        g2d.dispose();
        sprite = rotated;
    }

    @Override
    public void update() {
        float dx = target.x - x;
        float dy = target.y - y;
        double angle = Math.atan2(dy, dx);
        x += 7 * Math.cos(angle);
        y += 7 * Math.sin(angle);
        for(GameObject obj : GameObjectManager.getObjects()) {
            if(obj instanceof Obstacle) {
                if(this.collidesWith(obj)) {
                    GameObjectManager.despawn(this);
                }
            }
        }
    }
}
