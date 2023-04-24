package WizardGameTheWar.GameObjects.Enemies;

public class EnemyFactory {
    public static Enemy createEnemy(String enemyName, int x, int y) {
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
            default: throw new RuntimeException("Invalid enemy: " + enemyName);
        }
    }
}
