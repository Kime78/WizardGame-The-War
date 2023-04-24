package WizardGameTheWar.GameObjects.Backgrounds;

import WizardGameTheWar.GameObjects.GameObject;
import WizardGameTheWar.Graphics.Assets;

/***
 * Aceasta clasa este un obiect de background al nivelului Pestera
 */
public class Rock extends Background {
    public Rock(int x, int y) {
        sprite = Assets.backgroundRock;
        this.x = x;
        this.y = y;
    }
}
