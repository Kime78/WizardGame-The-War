package WizardGameTheWar.GameObjects;

import WizardGameTheWar.Graphics.Assets;
import WizardGameTheWar.Tiles.Tile;

public class Player extends Tile {
    public int x;
    public int y;
    public Player(int idd) {
        super(Assets.player, idd);
        x = 12;
        y = 12;
    }
}
