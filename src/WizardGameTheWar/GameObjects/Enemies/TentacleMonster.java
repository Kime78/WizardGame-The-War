package WizardGameTheWar.GameObjects.Enemies;

import WizardGameTheWar.GameObjects.GameObject;
import WizardGameTheWar.GameObjects.GameObjectManager;
import WizardGameTheWar.GameObjects.Player;
import WizardGameTheWar.Graphics.Assets;

/***
 * Clasa reprezinta inamicul monstru cu tentacule din harta Padure
 */
public class TentacleMonster extends Enemy {
    public TentacleMonster(int x, int y) {
        //FIXME: This is just a carbon copy of the wolf enemy.
        sprite = Assets.map2enemy1;
        this.x = x;
        this.y = y;
        health = 3;
        name = "TentacleMonster";
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
