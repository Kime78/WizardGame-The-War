package WizardGameTheWar.GameObjects.Obstacles;

import WizardGameTheWar.Graphics.Assets;

public class Tree extends Obstacle {
    public Tree(int x, int y) {
        this.x = x;
        this.y = y;
        sprite = Assets.obstacleTree;
    }
}
