package WizardGameTheWar.GameObjects;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/*****
 * Aceasta clasa abstractizează ideea de un obiect al jocului
 */

public abstract class GameObject {
    /***
     * Coordonatele obiectului
     */
    public int x, y;
    /***
     * Imaginea obiectului
     */
    protected BufferedImage sprite;
    /***
     * O referinta pentru grafica
     */
    public static Graphics graphics;


    public static ArrayList<GameObject> objects;

    /*******
     * Metoda draw desenează pe ecran un sprite(o dală)
     */
    public void draw() {
        graphics.drawImage(sprite, x, y, 48, 48, null);
    }

    /****
     * Metoda draw este una abstracta ce lasă clasele derivate sa-si actualizeze logica odata pe cadru
     */
    public void update() {

    }

    /****
     * Aceasta metoda verifica daca un obiect este in coliziune cu alt obiect
     * @param o Obiectul cu care se verifica interactiunea de coliziune
     * @return Daca Un {@link GameObject} este in coliziune cu alt GameObject
     */
    public boolean collidesWith(GameObject o){
        return !(x >= o.x + 48 || x + 48 <= o.x || y >= o.y + 48 || y + 48 <= o.y);
    }
}
