package WizardGameTheWar.GameObjects.Enemies;

import WizardGameTheWar.Cooldown;
import WizardGameTheWar.GameObjects.Backgrounds.Grass;
import WizardGameTheWar.GameObjects.GameObject;
import WizardGameTheWar.GameObjects.GameObjectManager;
import WizardGameTheWar.GameObjects.Player;
import WizardGameTheWar.GameObjects.Spells.ManaBullet;
import WizardGameTheWar.GameObjects.Spells.ManaFireBall;
import WizardGameTheWar.GameObjects.Spells.Spell;
import WizardGameTheWar.GameObjects.Spells.SpellTarget;
import WizardGameTheWar.Graphics.Assets;

import java.awt.*;
import java.util.concurrent.TimeUnit;

/***
 * Clasa reprezinta inamicul gargoyle din harta Padure
 */
public class Gargoyle extends Enemy {

    private final Cooldown[] shootCooldowns = {
            new Cooldown(4000),
            new Cooldown(2500),
            new Cooldown(2500),
            new Cooldown(2500),
            new Cooldown(2500)
    };
    private long delayBetweenSpells = 50; // milliseconds
    private long timeSinceLastSpell = 0;
    private int currentSpellIndex = 0;
    private Cooldown jumpCooldown = new Cooldown(2500);
    private boolean isJumping;
    private Point jumpTarget = new Point();
    public Gargoyle(int x, int y) {
        sprite = Assets.map2enemy3;
        this.x = x;
        this.y = y;
        health = 15;
        name = "Gargoyle";
        isJumping = false;
    }

    /***
     * Metoda face ca inamicul sa urmareasca jucatorul, verifica coliziunea cu spell-urile, si isi actualizeaza viata
     */
    @Override
    public void update() {
        super.update();
        if(jumpCooldown.isAvailable() && !isJumping) {
            jumpCooldown.use();
            do {
                jumpTarget.x = 48 + (int) (Math.random() * 500);
                jumpTarget.y = 48 + (int) (Math.random() * 500);
            } while (isValidPosition(jumpTarget));
            isJumping = true;
        }
        if(isJumping) {
            GameObject phantom = new Grass(jumpTarget.x, jumpTarget.y);
            if(this.collidesWith(phantom)) {
                isJumping = false;
            }

            float dx = jumpTarget.x - this.x;
            float dy = jumpTarget.y - this.y;
            double angle = Math.atan2(dy, dx);
            x += 6 * Math.cos(angle);
            y += 6 * Math.sin(angle);
        }
        Point playerPos = new Point();
        for(GameObject obj : GameObjectManager.getObjects()) {
            if(obj instanceof Player) {
                playerPos.x = obj.x;
                playerPos.y = obj.y;
            }
        }

        long deltaTime = System.currentTimeMillis() - timeSinceLastSpell;
        if (deltaTime >= delayBetweenSpells) {
            if (shootCooldowns[currentSpellIndex].isAvailable()) {
                shootCooldowns[currentSpellIndex].use();
                GameObjectManager.spawn(new ManaFireBall(x, y, playerPos, SpellTarget.Player));
                currentSpellIndex = (currentSpellIndex + 1) % shootCooldowns.length;
                timeSinceLastSpell = System.currentTimeMillis();
            }
        }
    }
}
