package WizardGameTheWar;

/***
 * Clasa cooldown este o clasa ce temporizeaza un eveniment
 */
public class Cooldown {
    private long lastUsedTime;
    private final long cooldownDuration; // in milliseconds

    /***
     * Creaza un now cooldown
     * @param cooldownDuration cat sa dureze cooldown-ul in milisecunde
     */
    public Cooldown(long cooldownDuration) {
        this.cooldownDuration = cooldownDuration;
        lastUsedTime = 0;
    }

    /***
     * Metoda verifica daca cooldown-ul a expirat
     * @return Daca cooldownul a expirat
     */
    public boolean isAvailable() {
        long currentTime = System.currentTimeMillis();
        return (currentTime - lastUsedTime >= cooldownDuration);
    }

    /***
     * Metoda reactualizeaza cooldownul
     */
    public void use() {
        lastUsedTime = System.currentTimeMillis();
    }
}
