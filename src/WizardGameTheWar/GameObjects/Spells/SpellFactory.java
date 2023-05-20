package WizardGameTheWar.GameObjects.Spells;

import WizardGameTheWar.FactoryException;
import WizardGameTheWar.GameObjects.Enemies.*;
import WizardGameTheWar.GameObjects.Enemies.Bosses.Cyclop;
import WizardGameTheWar.GameObjects.Enemies.Bosses.DarkWizard;
import WizardGameTheWar.GameObjects.Enemies.Bosses.Zalmoxis;

import java.awt.*;

public class SpellFactory {
    public static Spell createSpell(String spellName, int x, int y, Point targetPos, SpellTarget target) throws FactoryException {
        switch (spellName) {
            case "Boulder": return new Boulder(x, y, targetPos, target);
            case "Fireball": return new Fireball(x, y, targetPos, target);
            case "Icicle": return new Icicle(x, y, targetPos, target);
            case "ManaBullet": return new ManaBullet(x, y, targetPos, target);
            case "ManaFireBall": return new ManaFireBall(x, y, targetPos, target);
            case "Tornado": return new Tornado(x, y, targetPos, target);
            case "Windpush": return new Windpush(x, y, targetPos, target);
            case "Zap": return new Zap(x, y, targetPos, target);
            default: throw new FactoryException("Invalid spell: " + spellName);
        }
    }
}
