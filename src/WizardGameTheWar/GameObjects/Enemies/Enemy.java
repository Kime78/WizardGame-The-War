package WizardGameTheWar.GameObjects.Enemies;

import WizardGameTheWar.GameObjects.Backgrounds.Background;
import WizardGameTheWar.GameObjects.Backgrounds.Grass;
import WizardGameTheWar.GameObjects.GameObject;
import WizardGameTheWar.GameObjects.GameObjectManager;
import WizardGameTheWar.GameObjects.Obstacles.Obstacle;
import WizardGameTheWar.GameObjects.Player;
import WizardGameTheWar.GameObjects.Spells.Spell;
import WizardGameTheWar.GameObjects.Spells.SpellTarget;

import java.awt.*;

public class Enemy extends GameObject {
    protected int health;
    public String name;
    @Override
    public void update() {
        for(GameObject obj : GameObjectManager.getObjects()) {
            if(obj instanceof Spell) {
                if(this.collidesWith(obj)) {
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
    }
    public boolean isValidPosition(Point pos) {
        for(GameObject obj : GameObjectManager.getObjects()) {
            if(!(obj instanceof Background) && this != obj) {
                GameObject tester = new Grass(pos.x, pos.y);
                if(tester.collidesWith(obj)) {
                    return false;
                }
            }
        }
        if(x < 0 || x > 816 || y < 0 || y > 628)
            return false;
        return true;
    }
}
