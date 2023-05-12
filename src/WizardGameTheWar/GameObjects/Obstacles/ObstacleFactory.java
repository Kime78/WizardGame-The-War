package WizardGameTheWar.GameObjects.Obstacles;

import WizardGameTheWar.FactoryException;
import WizardGameTheWar.Levels.LevelType;

public class ObstacleFactory {
    public static Obstacle createObstacle(LevelType level, int x, int y) throws FactoryException {
        switch (level) {
            case Campie: return new Vine(x, y);
            case Padure: return new Tree(x, y);
            case Pestera: return new Boulder(x, y);
            default: throw new FactoryException("Unknown Level type");
        }
    }
}
