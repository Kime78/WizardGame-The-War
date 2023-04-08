package WizardGameTheWar.GameObjects;

import WizardGameTheWar.Graphics.Assets;
import WizardGameTheWar.Keyboard;

import java.awt.event.KeyEvent;

public class Player extends GameObject {
    public Player(int x, int y) {
        sprite = Assets.player;
        this.x = x;
        this.y = y;
    }
    @Override
    public void update() {
        int deltaX = this.x;
        int deltaY = this.y;
        if(Keyboard.isKeyPressed(KeyEvent.VK_D)) {
            deltaX += 5;
        }
        if(Keyboard.isKeyPressed(KeyEvent.VK_A)) {
            deltaX -= 5;
        }
        if(Keyboard.isKeyPressed(KeyEvent.VK_W)) {
            deltaY -= 5;
        }
        if(Keyboard.isKeyPressed(KeyEvent.VK_S)) {
            deltaY += 5;
        }
        if(Keyboard.isKeyPressed(KeyEvent.VK_1)) {
            GameObjectManager.spawn(new ZapSpell(this.x + 60, this.y + 60));
        }
        for(GameObject obj : GameObjectManager.getObjects()) {
            if(obj instanceof ObstacleBoulder) {
                if(this.collidesWith(obj)) {
                    if(this.x > obj.x)
                        deltaX = this.x + 1;
                    else
                        deltaX = this.x - 1;
                    if(this.y > obj.y)
                        deltaY = this.y + 1;
                    else
                        deltaY = this.y - 1;
                    break;
                }
            }
        }
        this.x = deltaX;
        this.y = deltaY;
    }
}
