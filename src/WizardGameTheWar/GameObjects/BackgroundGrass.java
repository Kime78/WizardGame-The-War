package WizardGameTheWar.GameObjects;

import WizardGameTheWar.Graphics.Assets;

public class BackgroundGrass extends GameObject {
    public BackgroundGrass(int x, int y) {
        sprite = Assets.backgroundGrass;
        this.x = x;
        this.y = y;
    }
}
