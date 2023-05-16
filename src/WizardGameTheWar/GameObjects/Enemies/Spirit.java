package WizardGameTheWar.GameObjects.Enemies;

import WizardGameTheWar.GameObjects.GameObject;
import WizardGameTheWar.GameObjects.GameObjectManager;
import WizardGameTheWar.GameObjects.Player;
import WizardGameTheWar.GameObjects.PlayerPositionObserver;
import WizardGameTheWar.Graphics.Assets;

import java.awt.*;

/***
 * Clasa reprezinta inamicul spirit din harta Padure
 */
public class Spirit extends Enemy implements PlayerPositionObserver {
    //FIXME: This is just a carbon copy of the wolf enemy.
    private Point playerPos = new Point();
    public Spirit(int x, int y) {
        sprite = Assets.map2enemy2;
        this.x = x;
        this.y = y;
        health = 3;
        name = "Spirit";
    }

    /***
     * Metoda face ca inamicul sa urmareasca jucatorul, verifica coliziunea cu spell-urile, si isi actualizeaza viata
     */
    @Override
    public void update() {
        super.update();

        float dx = playerPos.x - x;
        float dy = playerPos.y - y;
        double angle = Math.atan2(dy, dx);
        x += 3.5 * Math.cos(angle);
        y += 3.5 * Math.sin(angle);
    }

    @Override
    public void updatePosition(Point playerPosition) {
        playerPos = playerPosition;
    }
}
