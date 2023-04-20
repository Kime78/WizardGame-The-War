package WizardGameTheWar.GameObjects.Backgrounds;

import WizardGameTheWar.GameObjects.GameObject;
import WizardGameTheWar.Graphics.Assets;

/***
 * Aceasta clasa este un obiect de background al nivelului Campie
 */
public class Flowers extends GameObject {
    public Flowers(int x, int y) {
        sprite = Assets.backgroundFlowers;
        this.x = x;
        this.y = y;
    }
}
