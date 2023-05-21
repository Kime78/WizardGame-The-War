package WizardGameTheWar.GameObjects.Enemies.Factories;

import WizardGameTheWar.GameObjects.Enemies.Enemy;
import WizardGameTheWar.GameObjects.Enemies.Frog;
import WizardGameTheWar.GameObjects.Enemies.Leprechaun;

public class LeprechaunFactory extends EnemyFactory {
    @Override
    public Enemy createEnemy(int x, int y) {
        return new Leprechaun(x, y);
    }
}
