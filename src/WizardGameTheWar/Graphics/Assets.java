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
    public static BufferedImage bookFireBall;
    public static BufferedImage bookIcicle;
    public static BufferedImage bookBoulder;
    public static BufferedImage bookWindPush;
    public static BufferedImage bookTornado;
    public static BufferedImage bookZap;
    public static BufferedImage bookManaBullet;
    public static BufferedImage bookManaFireBall;
    public static BufferedImage objectChestClosed;
    public static BufferedImage objectChestOpen;
    public static BufferedImage pickupLifePotion;
    public static BufferedImage pickupStone;
    public static BufferedImage pickupManaPotion;
    public static BufferedImage map1boss;
    public static BufferedImage map2boss;
    public static BufferedImage map3boss;

    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
            /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/spritesheet_principal.png"), 32);
        SpriteSheet bossSheet = new SpriteSheet(ImageLoader.LoadImage("/textures/spritesheet_bosi.png"), 64);

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

        objectChestClosed = sheet.crop(2, 5);
        objectChestOpen = sheet.crop(3, 5);

        bookFireBall = sheet.crop(0, 6);
        bookIcicle = sheet.crop(1, 6);
        bookBoulder = sheet.crop(2, 6);
        bookWindPush = sheet.crop(3, 6);
        bookTornado = sheet.crop(4, 6);
        bookZap = sheet.crop(5, 6);
        bookManaBullet = sheet.crop(6, 6);
        bookManaFireBall = sheet.crop(7, 6);

        pickupLifePotion = sheet.crop(0, 7);
        pickupManaPotion = sheet.crop(1, 7);
        pickupStone = sheet.crop(2, 7);

        map1boss = bossSheet.crop(0, 1);
        map2boss = bossSheet.crop(0, 2);
        map3boss = bossSheet.crop(0, 0);
    }
}
