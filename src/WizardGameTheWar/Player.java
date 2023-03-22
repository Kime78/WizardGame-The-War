package WizardGameTheWar;

import WizardGameTheWar.Graphics.Assets;
import WizardGameTheWar.Tiles.Tile;

public class Player extends Tile {
    int x;
    int y;
    public Player(int idd) {
        super(Assets.player, idd);
        x = 12;
        y = 12;
    }
}
