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
        Game paooGame = Game.getInstance();
        paooGame.StartGame();
    }
}
