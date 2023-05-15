package WizardGameTheWar.GameObjects.Enemies;

import WizardGameTheWar.Cooldown;
import WizardGameTheWar.GameObjects.GameObject;
import WizardGameTheWar.GameObjects.GameObjectManager;
import WizardGameTheWar.GameObjects.Player;
import WizardGameTheWar.GameObjects.Spells.ManaBullet;
import WizardGameTheWar.GameObjects.Spells.Spell;
import WizardGameTheWar.GameObjects.Spells.SpellTarget;
import WizardGameTheWar.Graphics.Assets;

import java.awt.*;

/***
 * Clasa reprezinta inamicul spiridus din harta Campie
 */
public class Leprechaun extends Enemy {
    private Cooldown teleportCooldown = new Cooldown(1700);
    private Cooldown shootCooldown = new Cooldown(2000);
    public Leprechaun(int x, int y) {
        sprite = Assets.map1enemy1;
        this.x = x;
        this.y = y;
        health = 8;
        name = "Leprechaun";

    }

    /***
     * Metoda face ca inamicul sa urmareasca jucatorul, verifica coliziunea cu spell-urile, si isi actualizeaza viata
     */
    @Override
    public void update() {
        super.update();
        if(teleportCooldown.isAvailable()) {
            teleportCooldown.use();
            //FIXME: ADD PLAYER POSITION OBSERVER
            // BAD ************
            Point playerPos = new Point();
            for(GameObject obj : GameObjectManager.getObjects()) {
                if(obj instanceof Player) {
                    playerPos.x = obj.x;
                    playerPos.y = obj.y;
                }
            }
            //*****************
            do {
                x = (int) (playerPos.x + Math.random() * -350 + 150);
                y = (int) (playerPos.y + Math.random() * -350 + 150);
            } while (!isValidPosition(new Point(x, y)));


        }
        if(shootCooldown.isAvailable()) {
            shootCooldown.use();
            Point playerPos = new Point();
            for(GameObject obj : GameObjectManager.getObjects()) {
                if(obj instanceof Player) {
                    playerPos.x = obj.x;
                    playerPos.y = obj.y;
                }
            }
            GameObjectManager.spawn(new ManaBullet(x, y, playerPos, SpellTarget.Player));

        }
    }
}
