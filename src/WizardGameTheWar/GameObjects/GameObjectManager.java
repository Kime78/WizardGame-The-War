package WizardGameTheWar.GameObjects;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/***
 * Aceasta clasa se ocupa cu Managementul tuturor obiectelor din joc, adica aparitia si disparitia lor
 */

public class GameObjectManager {
    static ArrayList<GameObject> objects;
    static Queue<GameObject> toBeSpawned;
    static Queue<GameObject> toBeDespawned;
    public static void init() {
        objects = new ArrayList<>();
        toBeSpawned = new LinkedList<>();
        toBeDespawned = new LinkedList<>();
    }

    /**
     * Metorda creaza un obiect in joc
     * @param o Obiectul pentru a fi creat
     */
    public static void spawn(GameObject o) {
        toBeSpawned.add(o);
    }

    /***
     * Metoda sterge un obiect din joc
     * @param o Obiectul ce va fi sters
     */
    public static void despawn(GameObject o) {toBeDespawned.add(o);}

    /**
     * Metoda actualizeaza lista de obiecte, adaugand si stergand obiectele obtinute din metodele spawn si despawn
     */
    public static void updateObjects() {
        while(!toBeSpawned.isEmpty())
            objects.add(toBeSpawned.poll());
        while(!toBeDespawned.isEmpty())
            objects.remove(toBeDespawned.poll());
    }

    /***
     * getter pentru lista de obiecte curenta a jocului
     * @return lista curenta de obiecte a jocului
     */
    public static ArrayList<GameObject> getObjects() {
        return objects;
    }

}
