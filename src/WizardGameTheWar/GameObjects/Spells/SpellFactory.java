package WizardGameTheWar.GameObjects.Spells;

import WizardGameTheWar.FactoryException;
import WizardGameTheWar.GameObjects.Enemies.*;
import WizardGameTheWar.GameObjects.Enemies.Bosses.Cyclop;
import WizardGameTheWar.GameObjects.Enemies.Bosses.DarkWizard;
import WizardGameTheWar.GameObjects.Enemies.Bosses.Zalmoxis;

import java.awt.*;

public class SpellFactory {
    public static Spell createSpell(String spellName, int x, int y) throws FactoryException {
        switch (spellName) {
            case "Boulder": return new Boulder(x, y, new Point(), SpellTarget.Enemy);
            case "Fireball": return new Fireball(x, y, new Point(), SpellTarget.Enemy);
            case "Icicle": return new Icicle(x, y, new Point(), SpellTarget.Enemy);
            case "ManaBullet": return new ManaBullet(x, y, new Point(), SpellTarget.Enemy);
            case "ManaFireBall": return new ManaFireBall(x, y, new Point(), SpellTarget.Enemy);
            case "Tornado": return new Tornado(x, y, new Point(), SpellTarget.Enemy);
            case "Windpush": return new Windpush(x, y, new Point(), SpellTarget.Enemy);
            case "Zap": return new Zap(x, y, new Point(), SpellTarget.Enemy);
            default: throw new FactoryException("Invalid spell: " + spellName);
        }
    }
}
