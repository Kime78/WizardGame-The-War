package WizardGameTheWar.GameObjects.Enemies;

import WizardGameTheWar.Cooldown;
import WizardGameTheWar.GameObjects.Backgrounds.Grass;
import WizardGameTheWar.GameObjects.GameObject;
import WizardGameTheWar.GameObjects.GameObjectManager;
import WizardGameTheWar.GameObjects.Spells.Spell;
import WizardGameTheWar.Graphics.Assets;

import java.awt.*;

/***
 * Clasa reprezinta inamicul broasca din harta Campie
 */
public class Frog extends Enemy {
    //FIXME: This is just a carbon copy of the wolf enemy.
    private Cooldown jumpCooldown;
    private boolean isJumping;
    private Point jumpTarget;
    public Frog(int x, int y) {
        sprite = Assets.map1enemy3;
        this.x = x;
        this.y = y;
        health = 3;
        name = "Frog";

        jumpCooldown = new Cooldown(1500);
        jumpTarget = new Point();
        jumpTarget.x = 48 + (int) (Math.random() * 500);
        jumpTarget.y = 48 + (int) (Math.random() * 500);
        isJumping = false;

    }

    /***
     * Metoda face ca inamicul sa urmareasca jucatorul, verifica coliziunea cu spell-urile, si isi actualizeaza viata
     */
    @Override
    public void update() {
        //super.update();
        for(GameObject obj: GameObjectManager.getObjects()) {
            if(obj instanceof Spell) {
                if(this.collidesWith(obj)) {
                    health--;
                    GameObjectManager.despawn(obj);
                }
            }
        }
        if(health <= 0) {
            GameObjectManager.despawn(this);
        }
        if(jumpCooldown.isAvailable() && !isJumping) {
            jumpCooldown.use();
            jumpTarget.x = 48 + (int) (Math.random() * 500);
            jumpTarget.y = 48 + (int) (Math.random() * 500);
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
            x += 10 * Math.cos(angle);
            y += 10 * Math.sin(angle);

        }
    }
}
