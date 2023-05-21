package WizardGameTheWar.GameObjects.Enemies.Factories;

import WizardGameTheWar.GameObjects.Enemies.Enemy;
import WizardGameTheWar.GameObjects.Enemies.Frog;

public class FrogFactory extends EnemyFactory {
    @Override
    public Enemy createEnemy(int x, int y) {
        return new Frog(x, y);
    }
}
