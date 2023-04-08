package WizardGameTheWar.GameObjects;

import WizardGameTheWar.Graphics.Assets;

public class ZapSpell extends GameObject {
    public ZapSpell(int x, int y) {
        this.sprite = Assets.spellZap;
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {
        this.y += 2;
    }
}
