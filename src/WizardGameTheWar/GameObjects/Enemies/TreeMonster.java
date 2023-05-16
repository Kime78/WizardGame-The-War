package WizardGameTheWar.GameObjects.Enemies;

import WizardGameTheWar.GameObjects.GameObject;
import WizardGameTheWar.GameObjects.GameObjectManager;
import WizardGameTheWar.GameObjects.Player;
import WizardGameTheWar.GameObjects.PlayerPositionObserver;
import WizardGameTheWar.Graphics.Assets;

import java.awt.*;

/***
 * Clasa reprezinta inamicul monstru copac din harta Padure
 */
public class TreeMonster extends Enemy  {
    private Point playerPos = new Point();
    public TreeMonster(int x, int y) {
        sprite = Assets.map2enemy4;
        this.x = x;
        this.y = y;
        health = 3;
        name = "TreeMonster";
    }

    /***
     * Metoda face ca inamicul sa urmareasca jucatorul, verifica coliziunea cu spell-urile, si isi actualizeaza viata
     */
    @Override
    public void update() {
        super.update();
        //FIXME: ADD PLAYER SPEED? OBSERVER
        // BAD ************
        for(GameObject obj : GameObjectManager.getObjects()) {
            if(obj instanceof Player) {
                ((Player) obj).speed = 1;
            }
        }
        //*****************
    }
}
