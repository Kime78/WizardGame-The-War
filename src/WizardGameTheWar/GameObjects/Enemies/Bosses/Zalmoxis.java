package WizardGameTheWar.GameObjects.Enemies.Bosses;

import WizardGameTheWar.Cooldown;
import WizardGameTheWar.Game;
import WizardGameTheWar.GameObjects.Backgrounds.Grass;
import WizardGameTheWar.GameObjects.Enemies.ElectricitySkeleton;
import WizardGameTheWar.GameObjects.Enemies.Enemy;
import WizardGameTheWar.GameObjects.Enemies.Spirit;
import WizardGameTheWar.GameObjects.GameObject;
import WizardGameTheWar.GameObjects.GameObjectManager;
import WizardGameTheWar.GameObjects.Player;
import WizardGameTheWar.GameObjects.PlayerPositionObserver;
import WizardGameTheWar.GameObjects.Spells.*;
import WizardGameTheWar.Graphics.Assets;
import WizardGameTheWar.Graphics.Utils;

import java.awt.*;

public class Zalmoxis extends Enemy implements PlayerPositionObserver {
    private int bulletCount = 24;
    private int spreadAngle = 45;
    private Cooldown shootCooldown = new Cooldown(4000);
    private Cooldown jumpCooldown = new Cooldown(2000);
    private Cooldown spawnCooldown = new Cooldown(7000);
    private boolean isJumping;
    private Point jumpTarget = new Point();
    private Point playerPos = new Point();

    public Zalmoxis(int x, int y) {
        //FIXME: This is just a carbon copy of the wolf enemy.
        sprite = Assets.map3boss;
        this.x = x;
        this.y = y;
        health = 200;

    }

    /***
     * Metoda face ca inamicul sa urmărească jucătorul, verifica coliziunea cu spell-urile, si isi actualizeaza viata
     */
    @Override
    public void update() {
        //super.update();
        for(GameObject obj : GameObjectManager.getObjects()) {
            if(obj instanceof Spell) {
                Rectangle rect = new Rectangle(x, y,64, 64);
                if(rect.contains(new Point(obj.x, obj.y))) {
                    Spell spell = (Spell) obj;
                    if(spell.targetType == SpellTarget.Enemy) {
                        health--;
                        GameObjectManager.despawn(obj);
                    }
                }
            }
        }
        if(health <= 0) {
            GameObjectManager.despawn(this);
        }
        if(isJumping) {
            GameObject phantom = new Grass(jumpTarget.x, jumpTarget.y);
            if(this.collidesWith(phantom)) {
                isJumping = false;

                double startAngle = Math.toRadians(-45 * (10 - 1) / 2);
                double angleStep = Math.toRadians(45);

                for (int i = 0; i < 10; i++) {
                    double angle = startAngle + i * angleStep +  Math.atan2(playerPos.y - y, playerPos.x - x);
                    double dx = Math.cos(angle);
                    double dy = Math.sin(angle);

                    int targetX = (int) (x + dx * 300);
                    int targetY = (int) (y + dy * 300);

                    GameObjectManager.spawn(new ManaBullet(x + 24, y + 24, new Point(targetX, targetY), SpellTarget.Player));
                }
            }

            float dx = jumpTarget.x - this.x;
            float dy = jumpTarget.y - this.y;
            double angle = Math.atan2(dy, dx);
            x += 6 * Math.cos(angle);
            y += 6 * Math.sin(angle);
        }
        if(spawnCooldown.isAvailable() && isJumping){
            spawnCooldown.use();
            GameObjectManager.spawn(new ElectricitySkeleton(48 * 4, 48 * 6));
            GameObjectManager.spawn(new ElectricitySkeleton(48 * 9, 48 * 6));
        }
        if(jumpCooldown.isAvailable() && !isJumping) {
            jumpCooldown.use();
            do {
                jumpTarget.x = 48 + (int) (Math.random() * 500);
                jumpTarget.y = 48 + (int) (Math.random() * 500);
            } while (isValidPosition(jumpTarget));
            isJumping = true;
        }
        if (shootCooldown.isAvailable()) {
            shootCooldown.use();

            double startAngle = Math.toRadians(-spreadAngle * (bulletCount - 1) / 2);
            double angleStep = Math.toRadians(spreadAngle);

            for (int i = 0; i < bulletCount; i++) {
                double angle = startAngle + i * angleStep +  Math.atan2(playerPos.y - y, playerPos.x - x);
                double dx = Math.cos(angle);
                double dy = Math.sin(angle);

                int targetX = (int) (x + dx * 300);
                int targetY = (int) (y + dy * 300);

                GameObjectManager.spawn(new ManaFireBall(x + 24, y + 24, new Point(targetX, targetY), SpellTarget.Player));
            }
        }
    }

    @Override
    public void draw(){
        graphics.drawImage(sprite, x, y, 2 * 48, 2 * 48, null);
        var color = graphics.getColor();
        graphics.setColor(Color.red);

        graphics.fillRect(100, 100, (int) ((double)health / 200 * 600), 20);
        graphics.setColor(Color.white);
        graphics.drawRect(100,100, 600, 20);
        graphics.setFont(new Font("Comic Sans", Font.BOLD, 14));
        graphics.drawString("Health: " + health, 350, 115);

    }

    @Override
    public void updatePosition(Point playerPosition) {
        playerPos = playerPosition;
    }
}
