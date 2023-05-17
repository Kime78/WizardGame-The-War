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
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;


public class Game implements Runnable
{
    private static Game instance;
    private GameWindow      wnd;        /*!< Fereastra in care se va desena tabla jocului*/
    private boolean         runState;   /*!< Flag ce starea firului de executie.*/
    private Thread          gameThread; /*!< Referinta catre thread-ul de update si draw al ferestrei*/
    private BufferStrategy  bs;         /*!< Referinta catre un mecanism cu care se organizeaza memoria complexa pentru un canvas.*/
    private Graphics        g;          /*!< Referinta catre un context grafic.*/
    private ArrayList<Background> backgrounds;
    private final ArrayList<Level> levels = new ArrayList<>();
    private Level loadedLevel;
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
        wnd = new GameWindow("WizardGame: The war", 816, 624);
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

        int x = 23;
        backgrounds = levels.get(x).backgrounds;
        changeLevel(levels.get(x), 3);
        Mouse.canvas = wnd.GetCanvas();
        Mouse.addMouseListener();
        editor.wnd = wnd;
        editor.init();
        //Level l = SaveManager.loadSaveFromDB();
        //SaveManager.writeLevelToDB(l);
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
        for(GameObject object : GameObjectManager.getObjects()) {
            GameObjectManager.despawn(object);
        }
        GameObjectManager.updateObjects();
        backgrounds = lev.backgrounds;
        Player tmp = null;
        for (GameObject obj : GameObjectManager.getObjects()) {
            if(obj instanceof Player)
                GameObjectManager.despawn(obj);
        }
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
        }
        System.out.println(loadedLevel.id);
        for(GameObject obj : GameObjectManager.getObjects()) {
            if (obj instanceof Player) {
                //System.out.println(obj.x + " " + obj.y + ": " + (816 / 48 / 2 - 1) * 48 + " " + (816 / 48 / 2 + 1) * 48);

                if (((obj.x >= (816 / 48 / 2 - 1) * 48) && (obj.x <= (816 / 48 / 2 + 1) * 48))) {
                    if(obj.y < 48) {
                        changeLevel(levels.get(loadedLevel.links[0] - 1), 2);
                        break;
                    }
                }
                if (((obj.x >= (816 / 48 / 2 - 1) * 48) && (obj.x <= (816 / 48 / 2 + 1) * 48))) {
                    if(obj.y > 624 - 48) {
                        changeLevel(levels.get(loadedLevel.links[1] - 1), 1);
                        break;
                    }
                }
                if(!((obj.y >= 624 / 48 / 2  - 1) && (obj.y <= 624 / 48 / 2 + 1))) {
                    if(obj.x < 0) {
                        changeLevel(levels.get(loadedLevel.links[2] - 1), 4);
                        break;
                    }
                }
                if(!((obj.y >= 624 / 48 / 2  - 1) && (obj.y <= 624 / 48 / 2 + 1))) {
                    if(obj.x > 816 - 48) {
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
        g = bs.getDrawGraphics();
        GameObject.graphics = g;
        editor.gfx = g;
            /// Se sterge ce era
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());




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

            //g.drawRect(1 * Tile.TILE_WIDTH, 1 * Tile.TILE_HEIGHT, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);


            // end operatie de desenare
            /// Se afiseaza pe ecran
        bs.show();

            /// Elibereaza resursele de memorie aferente contextului grafic curent (zonele de memorie ocupate de
            /// elementele grafice ce au fost desenate pe canvas).
        g.dispose();
    }
}

