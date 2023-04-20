package WizardGameTheWar.GameObjects.Enemies;

import WizardGameTheWar.Graphics.Assets;

/***
 * Clasa reprezinta inamicul spiridus din harta Campie
 */
public class Leprechaun extends Enemy {
    //FIXME: This is just a carbon copy of the wolf enemy.
    public Leprechaun(int x, int y) {
        sprite = Assets.map1enemy1;
        this.x = x;
        this.y = y;
        health = 3;
    }

    /***
     * Metoda face ca inamicul sa urmareasca jucatorul, verifica coliziunea cu spell-urile, si isi actualizeaza viata
     */
    @Override
    public void update() {
        super.update();
    }
}
