package WizardGameTheWar.Levels;

import WizardGameTheWar.GameObjects.Backgrounds.Background;
import WizardGameTheWar.GameObjects.GameObject;

import java.util.ArrayList;



public class Level {
    public int id; //this is used to link the levels together
    public ArrayList<GameObject> objects; //this is a list of level specific objects(e.g. Enemies & Obstacles)
    public ArrayList<Background> backgrounds;
    public LevelType type;
    public int[] links; //this stores where the level leads to

    public Level() {
        objects = new ArrayList<>();
        backgrounds = new ArrayList<>();
        links = new int[4];
    }
}
