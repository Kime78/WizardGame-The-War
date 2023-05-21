package WizardGameTheWar.GameObjects.Items;

import WizardGameTheWar.Game;
import WizardGameTheWar.GameObjects.GameObject;
import WizardGameTheWar.GameObjects.GameObjectManager;
import WizardGameTheWar.GameObjects.Player;
import WizardGameTheWar.Mouse;

import javax.swing.text.Position;
import java.awt.*;

public abstract class Item extends GameObject {
    public String name;
    public String description;

    @Override
    public final void update() {
        for(GameObject obj : GameObjectManager.getObjects()) {
            if(obj instanceof Player) {
                if(obj.collidesWith(this)) {
                    onPickup();
                    GameObjectManager.despawn(this);
                }
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
                    graphics.fillRect(x, y - 48, 250, 50);
                    graphics.setColor(Color.BLACK);
                    graphics.setFont(new Font("MS Comic Sans", Font.BOLD, 15));
                    graphics.drawString(name, x + 5, y - 30);
                    graphics.drawString(description, x + 5, y - 5);
                }
            }
        }
    }

    public void onPickup() {

    }
}
