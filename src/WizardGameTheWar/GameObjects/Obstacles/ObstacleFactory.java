package WizardGameTheWar.GameObjects.Obstacles;

import WizardGameTheWar.LevelType;

public class ObstacleFactory {
    public static Obstacle createObstacle(LevelType level, int x, int y) {
        switch (level) {
            case Campie: return new Vine(x, y);
            case Padure: return new Tree(x, y);
            case Pestera: return new Boulder(x, y);
            default: throw new RuntimeException("Unknown Level type");
        }
    }
}
