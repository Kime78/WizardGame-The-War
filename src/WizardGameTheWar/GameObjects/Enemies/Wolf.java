package WizardGameTheWar.GameObjects.Enemies;

import WizardGameTheWar.Graphics.Assets;

/***
 * Clasa reprezinta inamicul lup din harta Campie
 */
public class Wolf extends Enemy {
    public Wolf(int x, int y) {
        sprite = Assets.map1enemy2;
        this.x = x;
        this.y = y;
        health = 3;
    }

    /***
     * Metoda face ca lupul sa urmareasca jucatorul, verifica coliziunea cu spell-urile, si isi actualizeaza viata
     */
    @Override
    public void update() {
        super.update();
    }
}
