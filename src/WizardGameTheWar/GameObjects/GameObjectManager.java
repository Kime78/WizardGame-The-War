package WizardGameTheWar.GameObjects;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GameObjectManager {
    static ArrayList<GameObject> objects;
    static Queue<GameObject> toBeSpawned;
    static Queue<GameObject> toBeDespawned;
    public static void init() {
        objects = new ArrayList<>();
        toBeSpawned = new LinkedList<>();
        toBeDespawned = new LinkedList<>();
    }
    public static void spawn(GameObject o) {
        toBeSpawned.add(o);
    }
    public static void despawn(GameObject o) {toBeDespawned.add(o);}
    public static void updateObjects() {
        while(!toBeSpawned.isEmpty())
            objects.add(toBeSpawned.poll());
        while(!toBeDespawned.isEmpty())
            objects.remove(toBeDespawned.poll());
    }

    public static ArrayList<GameObject> getObjects() {
        return objects;
    }

}
