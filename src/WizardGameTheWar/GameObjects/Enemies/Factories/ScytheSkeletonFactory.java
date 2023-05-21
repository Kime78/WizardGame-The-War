package WizardGameTheWar.GameObjects.Enemies.Factories;

import WizardGameTheWar.GameObjects.Enemies.Enemy;
import WizardGameTheWar.GameObjects.Enemies.ScytheSkeleton;

public class ScytheSkeletonFactory extends EnemyFactory {
    @Override
    public Enemy createEnemy(int x, int y) {
        return new ScytheSkeleton(x, y);
    }
}
