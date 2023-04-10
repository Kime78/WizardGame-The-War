package WizardGameTheWar.GameObjects.Backgrounds;

import WizardGameTheWar.GameObjects.GameObject;
import WizardGameTheWar.Graphics.Assets;

/***
 * Aceasta clasa este un obiect de background al nivelului Padure
 */
public class Grass extends GameObject {
    public Grass(int x, int y) {
        sprite = Assets.backgroundGrass;
        this.x = x;
        this.y = y;
    }
}
