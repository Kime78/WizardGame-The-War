package WizardGameTheWar.GameObjects.Enemies.Factories;

import WizardGameTheWar.GameObjects.Enemies.Bosses.DarkWizard;

import WizardGameTheWar.GameObjects.Enemies.Enemy;

public class DarkWizardFactory extends EnemyFactory {
    @Override
    public Enemy createEnemy(int x, int y) {
        return new DarkWizard(x, y);
    }
}

