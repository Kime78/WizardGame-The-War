package WizardGameTheWar.GameObjects.Items;

import WizardGameTheWar.GameObjects.GameObject;
import WizardGameTheWar.GameObjects.GameObjectManager;
import WizardGameTheWar.GameObjects.Player;
import WizardGameTheWar.GameObjects.Spells.EquipableSpell;
import WizardGameTheWar.Graphics.Assets;
import WizardGameTheWar.Mouse;

import java.awt.*;

public class SpellPickup extends Item {
    public EquipableSpell equipableSpell;
    public int level;
    private static int slot = 1;
    public SpellPickup(EquipableSpell spell, int x, int y) {
        this.x = x;
        this.equipableSpell = spell;
        this.level = spell.level;
        this.y = y;
        name = spell.spell.name + " Spell " + level;


        sprite = Assets.pickupLifePotion;
        switch (spell.spell.name) {
            case "Boulder": {
                sprite = Assets.bookBoulder;
                description = "Throws " + (spell.level + 3) + " boulders towards your enemy";
                break;
            }
            case "Fireball": {
                sprite = Assets.bookFireBall;
                description = "Throws " + (spell.level + 3) + " fireballs towards your enemy";
                break;
            }
            case "Icicle": {
                sprite = Assets.bookIcicle;
                description = "Throws " + (spell.level + 3) + " icicles towards your enemy";
                break;
            }
            case "ManaBullet": {
                sprite = Assets.bookManaBullet;
                description = "Throws " + (spell.level + 3) + " mana bullets towards your enemy";
                break;
            }
            case "ManaFireBall": {
                sprite = Assets.bookManaFireBall;
                description = "Throws " + (spell.level + 3) + " mana fireballs towards your enemy";
                break;
            }
            case "Tornado": {
                sprite = Assets.bookTornado;
                description = "Throws " + (spell.level + 3) + " tornadoes towards your enemy";
                break;
            }
            case "WindPush": {
                sprite = Assets.bookWindPush;
                description = "Throws " + (spell.level + 3) + " gusts of wind towards your enemy";
                break;
            }

        }

    }

    @Override
    public void onPickup() {
        for (GameObject obj : GameObjectManager.getObjects()) {
            if(obj instanceof Player) {
                ((Player) obj).equipedSpells[slot++ % 4] = equipableSpell;
            }
        }
    }

    @Override
    public void draw() {
        graphics.drawImage(sprite, x, y, null);
        //graphics.drawRect(x , y , 32, 32);
        Rectangle rect = new Rectangle(x, y, 32, 32);
        for(GameObject obj : GameObjectManager.getObjects()) {
            if(obj instanceof Player) {
                Point mousepos = Mouse.getPosition();
                if(mousepos != null && rect.contains(new Point(mousepos))) {
                    graphics.setColor(Color.white);
                    graphics.fillRect(x, y - 48, 350, 50);
                    graphics.setColor(Color.BLACK);
                    graphics.setFont(new Font("MS Comic Sans", Font.BOLD, 15));
                    graphics.drawString(name, x + 5, y - 30);
                    graphics.drawString(description, x + 5, y - 5);
                }
            }
        }
    }
}
