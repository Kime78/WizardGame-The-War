package WizardGameTheWar.GameObjects.Enemies;

import WizardGameTheWar.GameObjects.GameObject;
import WizardGameTheWar.GameObjects.GameObjectManager;
import WizardGameTheWar.GameObjects.Player;
import WizardGameTheWar.GameObjects.Spells.Spell;

public class Enemy extends GameObject {
    protected int health;
    public String name;
    @Override
    public void update() {
        for(GameObject obj : GameObjectManager.getObjects()) {
            if(obj instanceof Spell) {
                if(this.collidesWith(obj)) {
                    health--;
                    GameObjectManager.despawn(obj);
                }
            }
            if(obj instanceof Player) {
                float dx = obj.x - this.x;
                float dy = obj.y - this.y;
                double angle = Math.atan2(dy, dx);
                x += 3 * Math.cos(angle);
                y += 3 * Math.sin(angle);
            }
        }

        if(health <= 0) {
            GameObjectManager.despawn(this);
        }
    }
}
