package WizardGameTheWar.GameObjects.Enemies;

import WizardGameTheWar.FactoryException;
import WizardGameTheWar.GameObjects.Enemies.Bosses.Cyclop;
import WizardGameTheWar.GameObjects.Enemies.Bosses.DarkWizard;

public class EnemyFactory {
    public static Enemy createEnemy(String enemyName, int x, int y) throws FactoryException {
        switch (enemyName) {
            case "Wolf": return new Wolf(x, y);
            case "Frog": return new Frog(x, y);
            case "Gargoyle": return new Gargoyle(x, y);
            case "TreeMonster": return new TreeMonster(x, y);
            case "GhostSkeleton": return new GhostSkeleton(x, y);
            case "Leprechaun": return new Leprechaun(x, y);
            case "ScytheSkeleton": return new ScytheSkeleton(x, y);
            case "Spirit": return new Spirit(x, y);
            case "TentacleMonster": return new TentacleMonster(x, y);
            case "ElectricitySkeleton": return new ElectricitySkeleton(x, y);
            case "Cyclop": return new Cyclop(x, y);
            case "DarkWizard": return new DarkWizard(x, y);
            default: throw new FactoryException("Invalid enemy: " + enemyName);
        }
    }
}
