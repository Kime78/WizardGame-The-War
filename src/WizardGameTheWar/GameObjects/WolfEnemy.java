package WizardGameTheWar.GameObjects;

import WizardGameTheWar.Graphics.Assets;

public class WolfEnemy extends GameObject {
    int health;
    public WolfEnemy(int x, int y) {
        sprite = Assets.map1enemy2;
        this.x = x;
        this.y = y;
        health = 3;
    }
    @Override
    public void update() {
        for(GameObject obj : GameObjectManager.getObjects()) {
            if(obj instanceof ZapSpell) {
                if(this.collidesWith(obj)) {
                    health--;
                    GameObjectManager.despawn(obj);
                }
            }
            if(obj instanceof Player) {
                float dx = obj.x - this.x;
                float dy = obj.y - this.y;
                double angle = Math.atan2(dy, dx);
                x += 3 *  Math.cos(angle);
                y += 3 * Math.sin(angle);
            }
        }

        if(health <= 0) {
            GameObjectManager.despawn(this);
        }
    }
}
