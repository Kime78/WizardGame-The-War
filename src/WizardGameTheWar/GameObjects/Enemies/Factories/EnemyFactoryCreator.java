package WizardGameTheWar.GameObjects.Enemies.Factories;

import WizardGameTheWar.FactoryException;
import WizardGameTheWar.GameObjects.Enemies.Enemy;

public class EnemyFactoryCreator {
    public Enemy createEnemyFactory(String enemyName, int x, int y) {
        switch (enemyName) {
            case "Wolf": return new WolfFactory().createEnemy(x, y);
            case "Frog": return new FrogFactory().createEnemy(x, y);
            case "Gargoyle": return new GargoyleFactory().createEnemy(x, y);
            case "TreeMonster": return new TreeMonsterFactory().createEnemy(x, y);
            case "GhostSkeleton": return new GhostSkeletonFactory().createEnemy(x, y);
            case "Leprechaun": return new LeprechaunFactory().createEnemy(x, y);
            case "ScytheSkeleton": return new ScytheSkeletonFactory().createEnemy(x, y);
            case "Spirit": return new SpiritFactory().createEnemy(x, y);
            case "TentacleMonster": return new TentacleMonsterFactory().createEnemy(x, y);
            case "ElectricitySkeleton": return new ElectricitySkeletonFactory().createEnemy(x, y);
            case "Cyclop": return new CyclopFactory().createEnemy(x, y);
            case "DarkWizard": return new DarkWizardFactory().createEnemy(x, y);
            case "Zalmoxis": return new ZalmoxisFactory().createEnemy(x, y);
            default: throw new FactoryException("Invalid enemy: " + enemyName);
        }
    }
}
