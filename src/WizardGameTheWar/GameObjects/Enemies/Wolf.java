package WizardGameTheWar.GameObjects.Enemies;

import WizardGameTheWar.GameObjects.GameObject;
import WizardGameTheWar.GameObjects.GameObjectManager;
import WizardGameTheWar.GameObjects.Player;
import WizardGameTheWar.Graphics.Assets;

/***
 * Clasa reprezinta inamicul lup din harta Campie
 */
public class Wolf extends Enemy {
    public Wolf(int x, int y) {
        sprite = Assets.map1enemy2;
        this.x = x;
        this.y = y;
        health = 5;
        name = "Wolf";
    }

    /***
     * Metoda face ca lupul sa urmareasca jucatorul, verifica coliziunea cu spell-urile, si isi actualizeaza viata
     */
    @Override
    public void update() {
        super.update();
        for(GameObject obj : GameObjectManager.getObjects()) {
            if(obj instanceof Player) {
                float dx = obj.x - x;
                float dy = obj.y - y;
                double angle = Math.atan2(dy, dx);
                x += 3.5 * Math.cos(angle);
                y += 3.5 * Math.sin(angle);
            }
        }
    }
}
