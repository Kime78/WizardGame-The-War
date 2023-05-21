package WizardGameTheWar.GameObjects.Enemies.Factories;


import WizardGameTheWar.GameObjects.Enemies.Bosses.Zalmoxis;
import WizardGameTheWar.GameObjects.Enemies.Enemy;

public class ZalmoxisFactory extends EnemyFactory {
    @Override
    public Enemy createEnemy(int x, int y) {
        return new Zalmoxis(x, y);
    }
}
