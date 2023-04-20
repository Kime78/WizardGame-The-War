package WizardGameTheWar.GameObjects.Enemies;

import WizardGameTheWar.Graphics.Assets;

/***
 * Clasa reprezinta inamicul spirit din harta Padure
 */
public class Spirit extends Enemy {
    //FIXME: This is just a carbon copy of the wolf enemy.
    public Spirit(int x, int y) {
        sprite = Assets.map2enemy2;
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
