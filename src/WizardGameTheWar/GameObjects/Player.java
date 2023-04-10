package WizardGameTheWar.GameObjects;

import WizardGameTheWar.Cooldown;
import WizardGameTheWar.GameObjects.Obstacles.Obstacle;
import WizardGameTheWar.GameObjects.Spells.Zap;
import WizardGameTheWar.Graphics.Assets;
import WizardGameTheWar.Keyboard;
import WizardGameTheWar.Mouse;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/***
 * Aceasta clasa se ocupa de jucator
 */
public class Player extends GameObject {
    private Cooldown zapSpell;
    public Player(int x, int y) {
        sprite = Assets.player;
        this.x = x;
        this.y = y;

        zapSpell = new Cooldown(500);
    }
    @Override
    /***
     * Metoda se ocupa cu verificarea inputului(mouse, tastatura), si verifica coliziuni
     */
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
        if(Mouse.isButtonPressed(MouseEvent.BUTTON1)) {
            if(zapSpell.isAvailable()) {
                GameObjectManager.spawn(new Zap(this.x, this.y, Mouse.getPosition()));
                zapSpell.use();
            }
        }
        for(GameObject obj : GameObjectManager.getObjects()) {
            if(obj instanceof Obstacle) {
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
