package WizardGameTheWar.GameObjects;

import WizardGameTheWar.Cooldown;
import WizardGameTheWar.GameObjects.Obstacles.Obstacle;
import WizardGameTheWar.GameObjects.Spells.*;
import WizardGameTheWar.Graphics.Assets;
import WizardGameTheWar.Keyboard;
import WizardGameTheWar.Mouse;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/***
 * Aceasta clasa se ocupa de jucator
 */
public class Player extends GameObject {
    private ArrayList<PlayerPositionObserver> observers = new ArrayList<>();
    private final Cooldown zapSpell;
    public float speed = 2;
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
        int deltaX = 0;
        int deltaY = 0;
        if(Keyboard.isKeyPressed(KeyEvent.VK_D)) {
            deltaX += speed;
        }
        if(Keyboard.isKeyPressed(KeyEvent.VK_A)) {
            deltaX -= speed;
        }
        if(Keyboard.isKeyPressed(KeyEvent.VK_W)) {
            deltaY -= speed;
        }
        if(Keyboard.isKeyPressed(KeyEvent.VK_S)) {
            deltaY += speed;
        }
        if(Mouse.isButtonPressed(MouseEvent.BUTTON1)) {
            if(zapSpell.isAvailable()) {
                GameObjectManager.spawn(new ManaFireBall(this.x, this.y, Mouse.getPosition(), SpellTarget.Enemy));
                zapSpell.use();
            }
        }
        this.x += deltaX;
        this.y += deltaY;
        for(GameObject obj : GameObjectManager.getObjects()) {
            if(obj instanceof Obstacle) {
                if(this.collidesWith(obj)) {
                    deltaX = -deltaX;
                    deltaY = -deltaY;
                    break;
                }
            }
        }
        this.x += deltaX;
        this.y += deltaY;

        for(PlayerPositionObserver observer : observers) {
            observer.updatePosition(new Point(x, y));
        }
    }

    public void addPlayerPositionObserver(PlayerPositionObserver observer) {
        observers.add(observer);
    }
}
