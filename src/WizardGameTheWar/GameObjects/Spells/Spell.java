package WizardGameTheWar.GameObjects.Spells;

import WizardGameTheWar.GameObjects.Enemies.Enemy;
import WizardGameTheWar.GameObjects.GameObject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Spell extends GameObject {
    protected int range;
    public String name = "";
    public SpellTarget targetType = SpellTarget.Enemy;
    public Point origin = new Point();

    public static boolean isWithinCircle(Point firstPoint, Point secondPoint, double range) {
        // Calculate the distance between the first point and the second point
        double distance = Math.sqrt(Math.pow((secondPoint.x - firstPoint.x), 2) + Math.pow((secondPoint.y - firstPoint.y), 2));

        // Check if the distance is less than or equal to the range
        return distance <= range;
    }

}
