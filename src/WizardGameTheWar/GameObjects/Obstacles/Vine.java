package WizardGameTheWar.GameObjects.Obstacles;

import WizardGameTheWar.Graphics.Assets;

public class Vine extends Obstacle {
    public Vine(int x, int y) {
        this.x = x;
        this.y = y;
        sprite = Assets.obstacleVine;
    }
}
