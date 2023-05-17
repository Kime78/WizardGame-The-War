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
    public Zap(int x, int y, Point target, SpellTarget targetType) {
        this.sprite = Assets.spellZap;
        this.x = x;
        this.y = y;
        origin.x = x;
        origin.y = y;
        this.target = target;
        range = 500;
        this.targetType = targetType;

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
        if(!isWithinCircle(origin, new Point(x, y), 200)) {
            GameObjectManager.despawn(this);
        }
        if(this.x == target.x) {
            if(!(x >= target.x + 5 || x + 5 <= target.x || y >= target.y + 5 || y + 5 <= target.y)) {
                GameObjectManager.despawn(this);
            }
        }
    }
}
