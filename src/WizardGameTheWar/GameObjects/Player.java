package WizardGameTheWar.GameObjects;

import WizardGameTheWar.Cooldown;
import WizardGameTheWar.GameObjects.Enemies.Enemy;
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
    private final ArrayList<PlayerPositionObserver> observers = new ArrayList<>();
    private final Cooldown zapSpell;
    public float speed = 2;
    public int health = 50;
    public int mana = 50;
    public Cooldown iframes = new Cooldown(500);
    public Cooldown manaRegen = new Cooldown(1500);
    public Cooldown healthRegen = new Cooldown(4000);
    public int maxMana = 50;
    public int maxHealth = 50;


    public EquipableSpell[] equipedSpells = new EquipableSpell[4];
    public Cooldown[] spellCooldowns = new Cooldown[4];
    public Player(int x, int y) {
        sprite = Assets.player;
        this.x = x;
        this.y = y;
        equipedSpells[0] = new EquipableSpell(new ManaFireBall(-48, -48, new Point(), SpellTarget.Player), 1);
        zapSpell = new Cooldown(500);
        spellCooldowns[0] = new Cooldown(6000);
        spellCooldowns[1] = new Cooldown(6000);
        spellCooldowns[2] = new Cooldown(6000);
        spellCooldowns[3] = new Cooldown(6000);
    }
    /***
     * Metoda se ocupa cu verificarea inputului(mouse, tastatura), si verifica coliziuni
     */
    @Override
    public void update() {
        int deltaX = 0;
        int deltaY = 0;
        if(healthRegen.isAvailable()) {
            healthRegen.use();
            if(health < maxHealth)
                health++;
        }
        if(manaRegen.isAvailable()) {
            manaRegen.use();
            if(mana < maxMana)
                mana++;
        }
        for(GameObject obj : GameObjectManager.getObjects()) {
            if(obj instanceof Enemy) {
                if(this.collidesWith(obj))
                    if(iframes.isAvailable()) {
                        iframes.use();
                        health--;
                    }

            }
            if(obj instanceof Spell)
            {
                if(((Spell) obj).targetType == SpellTarget.Player) {
                    if(this.collidesWith(obj)) {
                        if(iframes.isAvailable()) {
                            iframes.use();
                            health--;
                        }
                        GameObjectManager.despawn(obj);
                    }
                }
            }
        }
        if(Keyboard.isKeyPressed(KeyEvent.VK_1)) {
            if(equipedSpells[0] != null) {
                System.out.println("asdasd");
                if(spellCooldowns[0].isAvailable() && mana >= 10) {
                    mana -= 10;
                    spellCooldowns[0].use();
                    System.out.println("asdasd2");
                }
            }
        }
        if(Keyboard.isKeyPressed(KeyEvent.VK_2)) {
            if(equipedSpells[1] != null) {
                System.out.println("asdasd");
                if(spellCooldowns[1].isAvailable() && mana >= 10) {
                    mana -= 10;
                    spellCooldowns[1].use();
                    System.out.println("asdasd2");
                }
            }
        }
        if(Keyboard.isKeyPressed(KeyEvent.VK_3)) {
            if(equipedSpells[2] != null) {
                System.out.println("asdasd");
                if(spellCooldowns[2].isAvailable() && mana >= 10) {
                    mana -= 10;
                    spellCooldowns[2].use();
                    System.out.println("asdasd2");
                }
            }
        }
        if(Keyboard.isKeyPressed(KeyEvent.VK_4)) {
            if(equipedSpells[3] != null) {
                System.out.println("asdasd");
                if(spellCooldowns[3].isAvailable() && mana >= 10) {
                    mana -= 10;
                    spellCooldowns[3].use();
                    System.out.println("asdasd2");
                }
            }
        }

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
