package WizardGameTheWar.GameObjects.Items;

import WizardGameTheWar.GameObjects.GameObject;
import WizardGameTheWar.GameObjects.GameObjectManager;
import WizardGameTheWar.GameObjects.Player;
import WizardGameTheWar.Graphics.Assets;

public class ManaPotion extends Item {
    public ManaPotion(int x, int y) {
        this.x = x;

        this.y = y;
        name = "Mana potion";
        description = "Restores the fatigued with 10 MP";
        sprite = Assets.pickupManaPotion;
    }

    @Override
    public void onPickup() {
        for (GameObject obj : GameObjectManager.getObjects()) {
            if(obj instanceof Player) {
                ((Player) obj).mana += 10;
                if(((Player) obj).mana >= ((Player) obj).maxMana) {
                    ((Player) obj).mana = ((Player) obj).maxMana;
                }
            }
        }
    }
}
