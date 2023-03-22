package WizardGameTheWar.Graphics;

import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
        /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage player;
    public static BufferedImage spellFireBall;
    public static BufferedImage spellBoulder;
    public static BufferedImage spellIcicle;
    public static BufferedImage spellWindPush;
    public static BufferedImage spellTornado;
    public static BufferedImage spellZap;
    public static BufferedImage spellManaBullet;
    public static BufferedImage spellManaFireBall;
    public static BufferedImage backgroundFlowers;
    public static BufferedImage backgroundGrass;
    public static BufferedImage backgroundRock;
    public static BufferedImage obstacleVine;
    public static BufferedImage obstacleTree;
    public static BufferedImage obstacleBoulder;
    public static BufferedImage map1enemy1;
    public static BufferedImage map1enemy2;
    public static BufferedImage map1enemy3;
    public static BufferedImage map2enemy1;
    public static BufferedImage map2enemy2;
    public static BufferedImage map2enemy3;
    public static BufferedImage map2enemy4;
    public static BufferedImage map3enemy1;
    public static BufferedImage map3enemy2;
    public static BufferedImage map3enemy3;

    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
            /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/spritesheet_principal.png"));

            /// Se obtin subimaginile corespunzatoare elementelor necesare.
        player = sheet.crop(0, 0);
        spellFireBall = sheet.crop(1, 0);
        spellBoulder = sheet.crop(2, 0);
        spellIcicle = sheet.crop(3, 0);
        spellWindPush = sheet.crop(4, 0);
        spellTornado = sheet.crop(5, 0);
        spellZap = sheet.crop(6, 0);
        spellManaBullet = sheet.crop(7, 0);
        spellManaFireBall = sheet.crop(8, 0);

        map1enemy1 = sheet.crop(0, 1);
        map1enemy2 = sheet.crop(1, 1);
        map1enemy3 = sheet.crop(2, 1);

        map2enemy1 = sheet.crop(0, 2);
        map2enemy2 = sheet.crop(1, 2);
        map2enemy3 = sheet.crop(2, 2);
        map2enemy4 = sheet.crop(3, 2);

        map3enemy1 = sheet.crop(0, 3);
        map3enemy2 = sheet.crop(1, 3);
        map3enemy3 = sheet.crop(2, 3);

        backgroundRock = sheet.crop(0, 4);
        backgroundGrass = sheet.crop(1, 4);
        backgroundFlowers = sheet.crop(2, 4);

        obstacleTree = sheet.crop(0, 5);
        obstacleBoulder = sheet.crop(1, 5);
        obstacleVine = sheet.crop(4, 5);
    }
}
