package WizardGameTheWar.GameObjects.Enemies;

import WizardGameTheWar.GameObjects.GameObject;
import WizardGameTheWar.GameObjects.GameObjectManager;
import WizardGameTheWar.GameObjects.Player;
import WizardGameTheWar.Graphics.Assets;

/***
 * Clasa reprezinta inamicul schelet fantoma din harta Pestera
 */
public class GhostSkeleton extends Enemy {
    public GhostSkeleton(int x, int y) {
        sprite = Assets.map3enemy3;
        this.x = x;
        this.y = y;
        health = 20;
        name = "GhostSkeleton";
    }

    /***
     * Metoda face ca inamicul sa urmareasca jucatorul, verifica coliziunea cu spell-urile, si isi actualizeaza viata
     */
    @Override
    public void update() {
        super.update();
        for(GameObject obj : GameObjectManager.getObjects()) {
            if(obj instanceof Player) {
                float dx = obj.x - x;
                float dy = obj.y - y;
                double angle = Math.atan2(dy, dx);
                x += 1.5 * Math.cos(angle);
                y += 1.5 * Math.sin(angle);
            }
        }
    }
}
