package WizardGameTheWar.GameObjects.Enemies;

import WizardGameTheWar.Graphics.Assets;

/***
 * Clasa reprezinta inamicul schelet cu electricitate din harta Pestera
 */
public class ElectricitySkeleton extends Enemy {
    public ElectricitySkeleton(int x, int y) {
        //FIXME: This is just a carbon copy of the wolf enemy.
        sprite = Assets.map3enemy1;
        this.x = x;
        this.y = y;
        health = 3;
    }

    /***
     * Metoda face ca inamicul sa urmărească jucătorul, verifica coliziunea cu spell-urile, si isi actualizeaza viata
     */
    @Override
    public void update() {
        super.update();
    }
}
