package WizardGameTheWar.GameObjects.Enemies;

import WizardGameTheWar.Cooldown;
import WizardGameTheWar.GameObjects.GameObject;
import WizardGameTheWar.GameObjects.GameObjectManager;
import WizardGameTheWar.GameObjects.Player;
import WizardGameTheWar.GameObjects.Spells.ManaBullet;
import WizardGameTheWar.GameObjects.Spells.SpellTarget;
import WizardGameTheWar.Graphics.Assets;

import java.awt.*;

/***
 * Clasa reprezinta inamicul schelet cu electricitate din harta Pestera
 */
public class ElectricitySkeleton extends Enemy {
    private int bulletCount = 6;
    private int spreadAngle = 20;
    private Cooldown teleportCooldown = new Cooldown(2500);
    private Cooldown shootCooldown = new Cooldown(2000);
    public ElectricitySkeleton(int x, int y) {
        //FIXME: This is just a carbon copy of the wolf enemy.
        sprite = Assets.map3enemy1;
        this.x = x;
        this.y = y;
        health = 3;
    }

    /***
     * Metoda face ca inamicul sa urmărească jucătorul, verifica coliziunea cu spell-urile, si isi actualizeaza viata
     */
    @Override
    public void update() {
        super.update();
        if (teleportCooldown.isAvailable()) {
            teleportCooldown.use();
            //FIXME: ADD PLAYER POSITION OBSERVER
            // BAD ************
            Point playerPos = new Point();
            for (GameObject obj : GameObjectManager.getObjects()) {
                if (obj instanceof Player) {
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
        if (shootCooldown.isAvailable()) {
            shootCooldown.use();
            Point playerPos = new Point();
            for (GameObject obj : GameObjectManager.getObjects()) {
                if (obj instanceof Player) {
                    playerPos.x = obj.x;
                    playerPos.y = obj.y;
                }
            }

            double startAngle = Math.toRadians(-spreadAngle * (bulletCount - 1) / 2);
            double angleStep = Math.toRadians(spreadAngle);

            for (int i = 0; i < bulletCount; i++) {
                double angle = startAngle + i * angleStep +  Math.atan2(playerPos.y - y, playerPos.x - x);
                double dx = Math.cos(angle);
                double dy = Math.sin(angle);

                int targetX = (int) (x + dx * 150);
                int targetY = (int) (y + dy * 150);

                GameObjectManager.spawn(new ManaBullet(x, y, new Point(targetX, targetY), SpellTarget.Player));
            }
        }
    }
}
