package WizardGameTheWar.GameObjects.Enemies.Factories;

import WizardGameTheWar.GameObjects.Enemies.ElectricitySkeleton;
import WizardGameTheWar.GameObjects.Enemies.Enemy;

public class ElectricitySkeletonFactory extends EnemyFactory {
    @Override
    public Enemy createEnemy(int x, int y) {
        return new ElectricitySkeleton(x, y);
    }
}
