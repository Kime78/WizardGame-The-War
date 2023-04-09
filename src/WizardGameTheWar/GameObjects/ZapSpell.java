package WizardGameTheWar.GameObjects;

import WizardGameTheWar.Graphics.Assets;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ZapSpell extends GameObject {
    Point target;
    public ZapSpell(int x, int y, Point target) {
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
            if(obj instanceof ObstacleBoulder) {
                if(this.collidesWith(obj)) {
                    GameObjectManager.despawn(this);
                }
            }
        }
    }
}
