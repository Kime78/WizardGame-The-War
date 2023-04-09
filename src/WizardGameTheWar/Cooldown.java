package WizardGameTheWar;
public class Cooldown {
    private long lastUsedTime;
    private final long cooldownDuration; // in milliseconds

    public Cooldown(long cooldownDuration) {
        this.cooldownDuration = cooldownDuration;
        lastUsedTime = 0;
    }

    public boolean isAvailable() {
        long currentTime = System.currentTimeMillis();
        return (currentTime - lastUsedTime >= cooldownDuration);
    }

    public void use() {
        lastUsedTime = System.currentTimeMillis();
    }
}
