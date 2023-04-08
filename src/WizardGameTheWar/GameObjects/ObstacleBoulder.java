package WizardGameTheWar.GameObjects;

import WizardGameTheWar.Graphics.Assets;

public class ObstacleBoulder extends GameObject {
    public ObstacleBoulder(int x, int y) {
        this.x = x;
        this.y = y;
        sprite = Assets.obstacleBoulder;
    }
}
