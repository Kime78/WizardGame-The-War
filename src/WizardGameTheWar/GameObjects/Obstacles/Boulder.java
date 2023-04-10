package WizardGameTheWar.GameObjects.Obstacles;

import WizardGameTheWar.Graphics.Assets;

/***
 * Aceasta clasa reprezinta un obstacol din nivelul 3
 */
public class Boulder extends Obstacle {
    public Boulder(int x, int y) {
        this.x = x;
        this.y = y;
        sprite = Assets.obstacleBoulder;
    }
}
