package WizardGameTheWar;

import WizardGameTheWar.GameObjects.Backgrounds.Background;
import WizardGameTheWar.GameWindow.GameWindow;
import WizardGameTheWar.Graphics.Assets;
import WizardGameTheWar.GameObjects.*;
import WizardGameTheWar.Levels.InvalidLevelException;
import WizardGameTheWar.Levels.Level;
import WizardGameTheWar.Levels.LevelEditor;
import WizardGameTheWar.Levels.LevelLoader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;


public class Game implements Runnable, MouseListener, KeyListener
{
    private static Game instance;
    private GameWindow      wnd;        /*!< Fereastra in care se va desena tabla jocului*/
    private boolean         runState;   /*!< Flag ce starea firului de executie.*/
    private Thread          gameThread; /*!< Referinta catre thread-ul de update si draw al ferestrei*/
    private BufferStrategy  bs;         /*!< Referinta catre un mecanism cu care se organizeaza memoria complexa pentru un canvas.*/
    private Graphics gfx;          /*!< Referinta catre un context grafic.*/
    private ArrayList<Background> backgrounds;
    private final ArrayList<Level> levels = new ArrayList<>();
    private Level loadedLevel;
    private boolean isPaused = false;
    private boolean isSaveLoadSelect = false;
    private boolean isSaveWriteSelect = false;
    private Player currentPlayer;
    private boolean isOver = false;
    
    LevelEditor editor = new LevelEditor();
    //private Tile tile; /*!< variabila membra temporara. Este folosita in aceasta etapa doar pentru a desena ceva pe ecran.*/

    /*! \fn public Game(String title, int width, int height)
        \brief Constructor de initializare al clasei Game.

        Acest constructor primeste ca parametri titlul ferestrei, latimea si inaltimea
        acesteia avand in vedere ca fereastra va fi construita/creata in cadrul clasei Game.

        \param title Titlul ferestrei.
        \param width Latimea ferestrei in pixeli.
        \param height Inaltimea ferestrei in pixeli.
     */

    public static Game getInstance() {
        if(instance == null) {
            instance = new Game("WizardGame The War", 816, 624);
        }
        return instance;
    }

    private Game(String title, int width, int height)
    {
            /// Obiectul GameWindow este creat insa fereastra nu este construita
            /// Acest lucru va fi realizat in metoda init() prin apelul
            /// functiei BuildGameWindow();
        wnd = new GameWindow(title, width, height);

            /// Resetarea flagului runState ce indica starea firului de executie (started/stoped)
        runState = false;
        GameObjectManager.init();
    }

    /*! \fn private void init()
        \brief  Metoda construieste fereastra jocului, initializeaza aseturile, listenerul de tastatura etc.

        Fereastra jocului va fi construita prin apelul functiei BuildGameWindow();
        Sunt construite elementele grafice (assets): dale, player, elemente active si pasive.

     */
    private void InitGame()  {
        wnd = new GameWindow("WizardGame: The war", 816, 624 + 120);
            /// Este construita fereastra grafica.
        wnd.BuildGameWindow();
            /// Se incarca toate elementele grafice (dale)
        Assets.Init();

        try {
            var levelFile = new File("level1.txt");
            var reader = new BufferedReader(new FileReader(levelFile));
            var levelStrings = reader.lines().toArray();
            for (var levelString3: levelStrings) {
                if(levelString3 != "")
                    levels.add(LevelLoader.loadLevelFromString((String) levelString3));
            }
        }
        catch (InvalidLevelException e) {
            System.out.println("Invalid level string: ");
            System.out.println(e);
            System.exit(-1);
        }
        catch (FileNotFoundException e) {
            System.exit(-1);
        }

        int x = 0;
        backgrounds = levels.get(x).backgrounds;
        changeLevel(levels.get(x), 3);
        Mouse.canvas = wnd.GetCanvas();
        Mouse.addMouseListener();
        editor.wnd = wnd;
        editor.init();
        //Level l = SaveManager.loadSaveFromDB();
        //SaveManager.writeLevelToDB(l);
        wnd.GetCanvas().addKeyListener(this);
        wnd.GetCanvas().addMouseListener(this);
    }

    /*! \fn public void run()
        \brief Functia ce va rula in thread-ul creat.

        Aceasta functie va actualiza starea jocului si va redesena tabla de joc (va actualiza fereastra grafica)
     */
    public void run()
    {
            /// Initializeaza obiectul game

        InitGame();

        long oldTime = System.nanoTime();   /*!< Retine timpul in nanosecunde aferent frame-ului anterior.*/
        long curentTime;                    /*!< Retine timpul curent de executie.*/

            /// Apelul functiilor Update() & Draw() trebuie realizat la fiecare 16.7 ms
            /// sau mai bine spus de 60 ori pe secunda.

        final int framesPerSecond   = 60; /*!< Constanta intreaga initializata cu numarul de frame-uri pe secunda.*/
        final double timeFrame      = 1000000000 / framesPerSecond; /*!< Durata unui frame in nanosecunde.*/

            /// Atat timp timp cat threadul este pornit Update() & Draw()
        while (runState)
        {
                /// Se obtine timpul curent
            curentTime = System.nanoTime();
                /// Daca diferenta de timp dintre curentTime si oldTime mai mare decat 16.6 ms
            if((curentTime - oldTime) > timeFrame)
            {
                /// Actualizeaza pozitiile elementelor
                if(!isPaused && !isSaveLoadSelect && !isSaveWriteSelect && !isOver)
                    Update();
                /// Deseneaza elementele grafica in fereastra.
                Draw();
                oldTime = curentTime;
            }
        }

    }

    /*! \fn public synchronized void start()
        \brief Creaza si starteaza firul separat de executie (thread).

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
     */
    private void changeLevel(Level lev, int dir) {
        loadedLevel = lev;
        Player tmp = null;
        for(GameObject object : GameObjectManager.getObjects()) {
            GameObjectManager.despawn(object);
        }
        GameObjectManager.updateObjects();
        backgrounds = lev.backgrounds;

        if(dir == 1) {
            tmp = new Player(816 / 2, 48 + 5);
        }
        else if(dir == 2) {
            tmp = new Player(816 / 2, 624 - 48 * 2 - 5);
        }
        else if(dir == 3) {
            tmp = new Player(48 + 5, 624 / 2 + 5);
        }
        else if(dir == 4) {
            tmp = new Player(816 - 48 * 2 - 5, 624 / 2 + 5);
        }
        if(currentPlayer != null) {
            tmp.health = currentPlayer.health;
            tmp.mana = currentPlayer.mana;
            tmp.equipedSpells = currentPlayer.equipedSpells;
            tmp.spellCooldowns = currentPlayer.spellCooldowns;
            tmp.manaRegen = currentPlayer.manaRegen;
            tmp.healthRegen = currentPlayer.healthRegen;
        }
        currentPlayer = tmp;
        GameObjectManager.player = tmp;
        GameObjectManager.spawn(tmp);
        for(GameObject object : lev.objects) {
            GameObjectManager.spawn(object);
        }
        for(GameObject object : lev.rim) {
            GameObjectManager.spawn(object);
        }
    }
    public synchronized void StartGame()
    {
        if(!runState)
        {
                /// Se actualizeaza flagul de stare a threadului
            runState = true;
                /// Se construieste threadul avand ca parametru obiectul Game. De retinut faptul ca Game class
                /// implementeaza interfata Runnable. Threadul creat va executa functia run() suprascrisa in clasa Game.
            gameThread = new Thread(this);
                /// Threadul creat este lansat in executie (va executa metoda run())
            gameThread.start();
        }
        else
        {
                /// Thread-ul este creat si pornit deja
            return;
        }
    }

    /*! \fn public synchronized void stop()
        \brief Opreste executie thread-ului.

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.

     */
    public synchronized void StopGame()
    {
        if(runState)
        {
                /// Actualizare stare thread
            runState = false;
                /// Metoda join() arunca exceptii motiv pentru care trebuie incadrata intr-un block try - catch.
            try
            {
                    /// Metoda join() pune un thread in asteptare panca cand un altul isi termina executie.
                    /// Totusi, in situatia de fata efectul apelului este de oprire a threadului.
                gameThread.join();
            }
            catch(InterruptedException ex)
            {
                    /// In situatia in care apare o exceptie pe ecran vor fi afisate informatii utile pentru depanare.
                ex.printStackTrace();
            }
        }
        else
        {
                /// Thread-ul este oprit deja.
            return;
        }
    }

    /*! \fn private void Update()
        \brief Actualizeaza starea elementelor din joc.

        Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
     */
    private void Update()  {

        GameObjectManager.updateObjects();
        for(GameObject gameObject : GameObjectManager.getObjects()) {
            gameObject.update();
            if(gameObject instanceof Player) {
                currentPlayer = (Player) gameObject;
            }
        }
        if(currentPlayer.health <= 0) {
            currentPlayer.health = 0;
            isOver = true;
        }
        //System.out.println(loadedLevel.id);
        for(GameObject obj : GameObjectManager.getObjects()) {
            if (obj instanceof Player) {
                //System.out.println(obj.x + " " + obj.y + ": " + (816 / 48 / 2 - 1) * 48 + " " + (816 / 48 / 2 + 1) * 48);

                if (((obj.x >= (816 / 48 / 2 - 1) * 48) && (obj.x <= (816 / 48 / 2 + 1) * 48))) {
                    if(obj.y < 48) {
                        if(loadedLevel.links[0] != 0)
                            changeLevel(levels.get(loadedLevel.links[0] - 1), 2);
                        break;
                    }
                }
                if (((obj.x >= (816 / 48 / 2 - 1) * 48) && (obj.x <= (816 / 48 / 2 + 1) * 48))) {
                    if(obj.y > 624 - 48) {
                        if(loadedLevel.links[1] != 0)
                            changeLevel(levels.get(loadedLevel.links[1] - 1), 1);
                        break;
                    }
                }
                if(!((obj.y >= 624 / 48 / 2  - 1) && (obj.y <= 624 / 48 / 2 + 1))) {
                    if(obj.x < 0) {
                        if(loadedLevel.links[2] != 0)
                            changeLevel(levels.get(loadedLevel.links[2] - 1), 4);
                        break;
                    }
                }
                if(!((obj.y >= 624 / 48 / 2  - 1) && (obj.y <= 624 / 48 / 2 + 1))) {
                    if(obj.x > 816 - 48) {
                        if(loadedLevel.links[3] != 0)
                            changeLevel(levels.get(loadedLevel.links[3] - 1), 3);
                        break;
                    }
                }
            }
        }

    }

    /*! \fn private void Draw()
        \brief Deseneaza elementele grafice in fereastra coresponzator starilor actualizate ale elementelor.

        Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
     */
    private void drawGUI(Player player) {
        gfx.setColor(Color.red);
        gfx.fillRect(100, 630, (int) ((double)player.health / player.maxHealth * 600), 20);
        gfx.setColor(Color.blue.brighter());

        gfx.fillRect(100, 655, (int) ((double)player.mana / player.maxMana * 600), 20);
        gfx.setColor(Color.black);
        gfx.drawRect(100,630, 600, 20);
        gfx.drawRect(100,655, 600, 20);
        gfx.setFont(new Font("Comic Sans", Font.BOLD, 14));
        gfx.drawString("Health: " + player.health, 350, 645);
        gfx.drawString("Mana: " + player.mana, 350, 670);

        gfx.setColor(Color.black);
        gfx.drawRect(260, 685, 48, 48);
        gfx.drawRect(340, 685, 48, 48);
        gfx.drawRect(420, 685, 48, 48);
        gfx.drawRect(500, 685, 48, 48);
    }
    private void drawPauseUI() {
        gfx.setColor(Color.white);
        if(isPaused) {
            gfx.fillRect(200,100, 400, 300);
            gfx.setColor(Color.black);
            gfx.setFont(new Font("Comic Sans", Font.BOLD, 20));
            gfx.drawString("Paused", 360, 120);
            gfx.drawRect(300,200, 200, 50);
            gfx.drawString("Save Game", 345, 235);
            gfx.drawRect(300,290, 200, 50);
            gfx.drawString("Load Game", 345, 320);
        }
    }
    private void drawSaveLoadSelect() {
        if(isSaveLoadSelect) {
            gfx.setColor(Color.white);
            gfx.fillRect(200,100, 400, 400);
            gfx.setColor(Color.black);
            gfx.setFont(new Font("Comic Sans", Font.BOLD, 20));
            gfx.drawString("Select a save slot", 330, 120);
            gfx.drawRect(300,200, 200, 50);
            gfx.drawString("Slot 1", 345, 235);
            gfx.drawRect(300,290, 200, 50);
            gfx.drawString("Slot 2", 345, 320);
            gfx.drawRect(300,380, 200, 50);
            gfx.drawString("Slot 3", 345, 410);
        }
    }
    private void drawSaveWriteSelect() {
        if(isSaveWriteSelect) {
            gfx.setColor(Color.white);
            gfx.fillRect(200,100, 400, 400);
            gfx.setColor(Color.black);
            gfx.setFont(new Font("Comic Sans", Font.BOLD, 20));
            gfx.drawString("Select a save slot", 330, 120);
            gfx.drawRect(300,200, 200, 50);
            gfx.drawString("Slot 1", 345, 235);
            gfx.drawRect(300,290, 200, 50);
            gfx.drawString("Slot 2", 345, 320);
            gfx.drawRect(300,380, 200, 50);
            gfx.drawString("Slot 3", 345, 410);
        }
    }
    private void drawSpellUI(Player player) {
        if(player.equipedSpells[0] != null) {
            if(player.spellCooldowns[0].isAvailable())
                gfx.drawImage(player.equipedSpells[0].spell.sprite, 260,685,  48, 48, null);
            else {
                BufferedImage grayscaleImage = new BufferedImage(player.equipedSpells[0].spell.sprite.getWidth(), player.equipedSpells[0].spell.sprite.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
                Graphics2D g2d = grayscaleImage.createGraphics();
                g2d.drawImage(player.equipedSpells[0].spell.sprite, 0, 0, null);
                g2d.dispose();
                gfx.drawImage(grayscaleImage, 260,685,  48, 48, null);
            }
        }
        if(player.equipedSpells[1] != null) {
            if(player.spellCooldowns[1].isAvailable())
                gfx.drawImage(player.equipedSpells[1].spell.sprite, 340,685,  48, 48, null);
            else {
                BufferedImage grayscaleImage = new BufferedImage(player.equipedSpells[1].spell.sprite.getWidth(), player.equipedSpells[0].spell.sprite.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
                Graphics2D g2d = grayscaleImage.createGraphics();
                g2d.drawImage(player.equipedSpells[1].spell.sprite, 0, 0, null);
                g2d.dispose();
                gfx.drawImage(grayscaleImage, 340,685,  48, 48, null);
            }
        }
        if(player.equipedSpells[2] != null) {
            if(player.spellCooldowns[2].isAvailable())
                gfx.drawImage(player.equipedSpells[2].spell.sprite, 420,685,  48, 48, null);
            else {
                BufferedImage grayscaleImage = new BufferedImage(player.equipedSpells[2].spell.sprite.getWidth(), player.equipedSpells[0].spell.sprite.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
                Graphics2D g2d = grayscaleImage.createGraphics();
                g2d.drawImage(player.equipedSpells[2].spell.sprite, 0, 0, null);
                g2d.dispose();
                gfx.drawImage(grayscaleImage, 420,685,  48, 48, null);
            }
        }
        if(player.equipedSpells[3] != null) {
            if(player.spellCooldowns[3].isAvailable())
                gfx.drawImage(player.equipedSpells[3].spell.sprite, 500,685,  48, 48, null);
            else {
                BufferedImage grayscaleImage = new BufferedImage(player.equipedSpells[3].spell.sprite.getWidth(), player.equipedSpells[0].spell.sprite.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
                Graphics2D g2d = grayscaleImage.createGraphics();
                g2d.drawImage(player.equipedSpells[3].spell.sprite, 0, 0, null);
                g2d.dispose();
                gfx.drawImage(grayscaleImage, 500,685,  48, 48, null);
            }
        }
    }
    private void Draw()
    {
            /// Returnez bufferStrategy pentru canvasul existent
        bs = wnd.GetCanvas().getBufferStrategy();
            /// Verific daca buffer strategy a fost construit sau nu
        if(bs == null)
        {
                /// Se executa doar la primul apel al metodei Draw()
            try
            {
                    /// Se construieste tripul buffer
                wnd.GetCanvas().createBufferStrategy(3);
                return;
            }
            catch (Exception e)
            {
                    /// Afisez informatii despre problema aparuta pentru depanare.
                e.printStackTrace();
            }
        }
            /// Se obtine contextul grafic curent in care se poate desena.
        gfx = bs.getDrawGraphics();
        GameObject.graphics = gfx;
        editor.gfx = gfx;
            /// Se sterge ce era
        gfx.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());




            /// operatie de desenare
            // ...............
//            Tile.grassTile.Draw(g, 0 * Tile.TILE_WIDTH, 0);
//            Tile.soilTile.Draw(g, 1 * Tile.TILE_WIDTH, 0);
//            Tile.waterTile.Draw(g, 2 * Tile.TILE_WIDTH, 0);
//            Tile.mountainTile.Draw(g, 3 * Tile.TILE_WIDTH, 0);
//            Tile.treeTile.Draw(g, 4 * Tile.TILE_WIDTH, 0);

            //System.out.println("=".repeat(20) + "LOOP" + "=".repeat(20));
            ArrayList<Background> bkgToDraw = backgrounds;
            if(editor.isEditing) {
                bkgToDraw = editor.getBackgrounds();
            }
            for(Background bkg : bkgToDraw) {
                bkg.draw();
            }
            for(GameObject gameObject : GameObjectManager.getObjects()) {
                gameObject.draw();
            }

            editor.run();

            Player player = null;
            for (GameObject obj : GameObjectManager.getObjects()) {
                if(obj instanceof Player) {
                    player = (Player) obj;
                    break;
                }
            }
            if(player != null)
            {
                drawSpellUI(player);
                drawGUI(player);
                drawPauseUI();
                drawSaveLoadSelect();
                drawSaveWriteSelect();
            }
            if(isOver) {
                gfx.setColor(Color.white);
                gfx.setFont(new Font("Comic Sans", Font.BOLD, 40));
                gfx.drawString("GAME OVER", 300, 300);
            }

            //g.drawRect(1 * Tile.TILE_WIDTH, 1 * Tile.TILE_HEIGHT, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);


            // end operatie de desenare
            /// Se afiseaza pe ecran
        bs.show();

            /// Elibereaza resursele de memorie aferente contextului grafic curent (zonele de memorie ocupate de
            /// elementele grafice ce au fost desenate pe canvas).
        gfx.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            isPaused = !isPaused;
            if(isSaveLoadSelect || isSaveWriteSelect)
                isPaused = false;
            isSaveWriteSelect = false;
            isSaveLoadSelect = false;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(isPaused) {
            Rectangle saveRect = new Rectangle(300,200, 200, 50);
            if(saveRect.contains(Mouse.getPosition())) {
                System.out.println("Save Game");
                isPaused = false;
                isSaveWriteSelect = true;
            }
            Rectangle loadRect = new Rectangle(300,290, 200, 50);
            if(loadRect.contains(Mouse.getPosition())) {
                System.out.println("Load Game");
                isPaused = false;
                isSaveLoadSelect = true;
            }


        }
        else if(isSaveLoadSelect) {
            Rectangle slot1rect = new Rectangle(300,200, 200, 50);
            if(slot1rect.contains(Mouse.getPosition())) {
                Save save = SaveManager.loadSaveFromDB(0);
                changeLevel(levels.get(save.currentLevel - 1), 1);
                currentPlayer = save.player;
                isSaveLoadSelect = false;
            }
            Rectangle slot2rect = new Rectangle(300,290, 200, 50);
            if(slot2rect.contains(Mouse.getPosition())) {
                Save save = SaveManager.loadSaveFromDB(1);
                changeLevel(levels.get(save.currentLevel - 1), 1);
                currentPlayer = save.player;
                isSaveLoadSelect = false;
            }
            Rectangle slot3rect = new Rectangle(300,380, 200, 50);
            if(slot3rect.contains(Mouse.getPosition())) {
                Save save = SaveManager.loadSaveFromDB(2);
                changeLevel(levels.get(save.currentLevel - 1), 1);
                currentPlayer = save.player;
                isSaveLoadSelect = false;
            }
        }
        else if(isSaveWriteSelect) {
            Rectangle slot1rect = new Rectangle(300,200, 200, 50);
            if(slot1rect.contains(Mouse.getPosition())) {
                SaveManager.writeSaveToDB(currentPlayer, 0, loadedLevel.id);
                isSaveWriteSelect = false;
            }
            Rectangle slot2rect = new Rectangle(300,290, 200, 50);
            if(slot2rect.contains(Mouse.getPosition())) {
                SaveManager.writeSaveToDB(currentPlayer, 1, loadedLevel.id);
                isSaveWriteSelect = false;
            }
            Rectangle slot3rect = new Rectangle(300,380, 200, 50);
            if(slot3rect.contains(Mouse.getPosition())) {
                SaveManager.writeSaveToDB(currentPlayer, 2, loadedLevel.id);
                isSaveWriteSelect = false;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

