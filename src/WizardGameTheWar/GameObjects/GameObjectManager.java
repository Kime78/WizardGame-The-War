package WizardGameTheWar.GameObjects;

import WizardGameTheWar.GameObjects.Enemies.Enemy;
import WizardGameTheWar.GameObjects.Enemies.TreeMonster;
import WizardGameTheWar.GameObjects.Items.HealingPotion;
import WizardGameTheWar.GameObjects.Items.ManaPotion;
import WizardGameTheWar.GameObjects.Items.SpellPickup;
import WizardGameTheWar.GameObjects.Spells.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/***
 * Aceasta clasa se ocupa cu Managementul tuturor obiectelor din joc, adica aparitia si disparitia lor
 */

public class GameObjectManager {
    static ArrayList<GameObject> objects;
    private static boolean changeLevel = false;
    static Queue<GameObject> toBeSpawned;
    static Queue<GameObject> toBeDespawned;
    public static Player player;

    public static void setChangeLevel(boolean changeLevel1) {
        changeLevel = changeLevel1;
    }
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
        if(o instanceof PlayerPositionObserver)
            player.addPlayerPositionObserver((PlayerPositionObserver) o);
    }

    /***
     * Metoda sterge un obiect din joc
     * @param o Obiectul ce va fi sters
     */
    public static void despawn(GameObject o) {
        toBeDespawned.add(o);
        //fixme plsss player :(
//        if(o instanceof TreeMonster) {
//            for(GameObject obj : GameObjectManager.getObjects()) {
//                if(obj instanceof Player) {
//                    Point playerPos = new Point(obj.x, obj.y);
//                    GameObjectManager.despawn(obj);
//                    GameObjectManager.spawn(new Player(playerPos.x, playerPos.y));
//                }
//            }
//        }
        if(o instanceof Enemy && !changeLevel) {
            //Thread.dumpStack();
            int rng = new Random().nextInt(100);
            if(rng < 10) {
                int rng2 = new Random().nextInt(100);
                if(rng2 < 40) {
                    GameObjectManager.spawn(new HealingPotion(o.x, o.y));
                }
                if(rng2 >= 40 && rng2 < 80) {
                    GameObjectManager.spawn(new ManaPotion(o.x, o.y));
                }
                else {
                    int rng3 = new Random().nextInt(100);
                    do {

                        int rng4 = new Random().nextInt(10);
                        int level = 0;
                        if (rng4 < 4) {
                            level = 1;
                        } else if (rng4 < 6) {
                            level = 2;
                        } else if (rng4 < 8) {
                            level = 3;
                        } else if (rng4 < 9) {
                            level = 4;
                        }
                        if (rng3 < 10) {
                            GameObjectManager.spawn(new SpellPickup(new EquipableSpell(new Boulder(0, 0, new Point(), SpellTarget.Enemy), level), o.x, o.y));
                            break;
                        }
                        else if (rng3 < 20) {
                            GameObjectManager.spawn(new SpellPickup(new EquipableSpell(new Fireball(0, 0, new Point(), SpellTarget.Enemy), level), o.x, o.y));
                            break;
                        }
                        else if (rng3 < 30) {
                            GameObjectManager.spawn(new SpellPickup(new EquipableSpell(new Icicle(0, 0, new Point(), SpellTarget.Enemy), level), o.x, o.y));
                            break;
                        }
                        else if (rng3 < 40) {
                            GameObjectManager.spawn(new SpellPickup(new EquipableSpell(new ManaBullet(0, 0, new Point(), SpellTarget.Enemy), level), o.x, o.y));
                            break;
                        }
                        else if (rng3 < 50) {
                            GameObjectManager.spawn(new SpellPickup(new EquipableSpell(new ManaFireBall(0, 0, new Point(), SpellTarget.Enemy), level), o.x, o.y));
                            break;
                        }
                        else if (rng3 < 60) {
                            GameObjectManager.spawn(new SpellPickup(new EquipableSpell(new Tornado(0, 0, new Point(), SpellTarget.Enemy), level), o.x, o.y));
                            break;
                        }
                        else if (rng3 < 70) {
                            GameObjectManager.spawn(new SpellPickup(new EquipableSpell(new Windpush(0, 0, new Point(), SpellTarget.Enemy), level), o.x, o.y));
                            break;
                        }
                        rng3 = new Random().nextInt(100);
                    }
                    while (rng3 < 70);
                }
            }
        }
    }

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
