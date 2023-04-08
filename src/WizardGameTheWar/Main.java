package WizardGameTheWar;

public class Main
{
    /********
     * Functia main a jocului.
     * Initializeaza o variabilă de tip {@link WizardGameTheWar.Game}
     * @param args
     */
    public static void main(String[] args)
    {
        Game paooGame = new Game("WizardGame The War", 640, 480);
        paooGame.StartGame();
    }
}
