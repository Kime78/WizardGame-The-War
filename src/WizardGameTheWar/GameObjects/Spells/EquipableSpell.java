package WizardGameTheWar.GameObjects.Spells;

public class EquipableSpell {
    public Spell spell;
    public int level;
    public EquipableSpell(Spell spell, int level) {
        this.spell = spell;
        this.level = level;
    }
};