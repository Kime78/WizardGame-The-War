package WizardGameTheWar.GameObjects.Enemies;

import WizardGameTheWar.GameObjects.GameObject;
import WizardGameTheWar.GameObjects.GameObjectManager;
import WizardGameTheWar.GameObjects.Player;
import WizardGameTheWar.Graphics.Assets;

/***
 * Clasa reprezinta inamicul spirit din harta Padure
 */
public class Spirit extends Enemy {
    //FIXME: This is just a carbon copy of the wolf enemy.
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
