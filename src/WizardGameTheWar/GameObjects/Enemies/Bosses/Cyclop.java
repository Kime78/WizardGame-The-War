package WizardGameTheWar.GameObjects.Enemies.Bosses;

import WizardGameTheWar.Cooldown;
import WizardGameTheWar.GameObjects.Enemies.Enemy;
import WizardGameTheWar.GameObjects.GameObject;
import WizardGameTheWar.GameObjects.GameObjectManager;
import WizardGameTheWar.GameObjects.Player;
import WizardGameTheWar.GameObjects.Spells.Boulder;
import WizardGameTheWar.GameObjects.Spells.ManaBullet;
import WizardGameTheWar.GameObjects.Spells.SpellTarget;
import WizardGameTheWar.Graphics.Assets;
import WizardGameTheWar.Graphics.Utils;

import java.awt.*;

public class Cyclop extends Enemy {
    private int bulletCount = 12;
    private int spreadAngle = 20;
    private Cooldown shootCooldown = new Cooldown(2000);
    public Cyclop(int x, int y) {
        //FIXME: This is just a carbon copy of the wolf enemy.
        sprite = Assets.map1boss;
        this.x = x;
        this.y = y;
        health = 40;
        sprite = Utils.scaleImage(sprite);
    }

    /***
     * Metoda face ca inamicul sa urmărească jucătorul, verifica coliziunea cu spell-urile, si isi actualizeaza viata
     */
    @Override
    public void update() {
        super.update();
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

                GameObjectManager.spawn(new Boulder(x, y, new Point(targetX, targetY), SpellTarget.Player));
            }
        }
    }
}
