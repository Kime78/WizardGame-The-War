package WizardGameTheWar.GameObjects.Backgrounds;

import WizardGameTheWar.LevelType;

public class BackgroundFactory {
    public static Background createBackground(LevelType type, int x, int y) {
        switch (type) {
            case Campie: return new Flowers(x, y);
            case Padure: return new Grass(x, y);
            case Pestera: return new Rock(x, y);
            default: throw new RuntimeException("Invalid level: ");
        }
    }
}
