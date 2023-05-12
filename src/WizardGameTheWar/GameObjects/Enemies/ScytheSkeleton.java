package WizardGameTheWar.GameObjects.Enemies;

import WizardGameTheWar.Graphics.Assets;

/***
 * Clasa reprezinta inamicul schelet cu coasa din harta Pestera
 */
public class ScytheSkeleton extends Enemy {
    //FIXME: This is just a carbon copy of the wolf enemy.
    public ScytheSkeleton(int x, int y) {
        sprite = Assets.map3enemy2;
        this.x = x;
        this.y = y;
        health = 3;
        name = "ScytheSkeleton";
    }

    /***
     * Metoda face ca inamicul sa urmareasca jucatorul, verifica coliziunea cu spell-urile, si isi actualizeaza viata
     */
    @Override
    public void update() {
        super.update();
    }
}
