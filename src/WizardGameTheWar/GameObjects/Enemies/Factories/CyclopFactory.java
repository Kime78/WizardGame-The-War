package WizardGameTheWar.GameObjects.Enemies.Factories;

import WizardGameTheWar.GameObjects.Enemies.Bosses.Cyclop;
import WizardGameTheWar.GameObjects.Enemies.Enemy;

public class CyclopFactory extends EnemyFactory {
    @Override
    public Enemy createEnemy(int x, int y) {
        return new Cyclop(x, y);
    }
}

