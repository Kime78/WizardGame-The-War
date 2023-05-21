package WizardGameTheWar.GameObjects.Items;

import WizardGameTheWar.GameObjects.GameObject;
import WizardGameTheWar.GameObjects.GameObjectManager;
import WizardGameTheWar.GameObjects.Player;
import WizardGameTheWar.Graphics.Assets;

public class HealingPotion extends Item {
    public HealingPotion(int x, int y) {
        this.x = x;

        this.y = y;
        name = "Healing potion";
        description = "Heals the wounded with 10 HP";
        sprite = Assets.pickupLifePotion;
    }

    @Override
    public void onPickup() {
        for (GameObject obj : GameObjectManager.getObjects()) {
            if(obj instanceof Player) {
                ((Player) obj).health += 10;
                if(((Player) obj).health >= ((Player) obj).maxHealth) {
                    ((Player) obj).health = ((Player) obj).maxHealth;
                }
            }
        }
    }
}
