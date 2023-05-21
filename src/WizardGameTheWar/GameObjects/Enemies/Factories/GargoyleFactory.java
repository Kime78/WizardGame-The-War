package WizardGameTheWar.GameObjects.Enemies.Factories;

import WizardGameTheWar.GameObjects.Enemies.Enemy;
import WizardGameTheWar.GameObjects.Enemies.Gargoyle;

public class GargoyleFactory extends EnemyFactory {
    @Override
    public Enemy createEnemy(int x, int y) {
        return new Gargoyle(x, y);
    }
}
