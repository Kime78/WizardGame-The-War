package WizardGameTheWar.Levels;

import WizardGameTheWar.GameObjects.Backgrounds.Background;
import WizardGameTheWar.GameObjects.Backgrounds.BackgroundFactory;
import WizardGameTheWar.GameObjects.Enemies.Enemy;
import WizardGameTheWar.GameObjects.Enemies.EnemyFactory;
import WizardGameTheWar.GameObjects.GameObject;
import WizardGameTheWar.GameObjects.GameObjectManager;
import WizardGameTheWar.GameObjects.Obstacles.Obstacle;
import WizardGameTheWar.GameObjects.Obstacles.ObstacleFactory;
import WizardGameTheWar.GameWindow.GameWindow;
import WizardGameTheWar.Mouse;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LevelEditor implements KeyListener, MouseListener {
    public GameWindow wnd;
    public Graphics gfx;
    public boolean isEditing;
    private boolean isEnemy;
    private final ArrayList<Obstacle> obstaclesToBeAdded = new ArrayList<>();
    private final ArrayList<Enemy> enemiesToBeAdded = new ArrayList<>();
    private LevelType selectedLevel= LevelType.Campie;
    private String selectedEnemy = "";

    private final ArrayList<Background> campieBackgrounds = new ArrayList<>();
    private final ArrayList<Background> padureBackgrounds = new ArrayList<>();
    private final ArrayList<Background> pesteraBackgrounds = new ArrayList<>();

    public void init() {
        wnd.GetCanvas().addKeyListener(this);
        wnd.GetCanvas().addMouseListener(this);
        for (int i = 0; i <= 800 / 48; i++) {
            for (int j = 0; j <= 600 / 48; j++) {
                //Tile.backgroundGrassTile.Draw(g, i * 48, j * 48);
                //GameObjectManager.spawn(new Grass(i * 48, j * 48));
                campieBackgrounds.add(BackgroundFactory.createBackground(LevelType.Campie, i * 48, j * 48));
                padureBackgrounds.add(BackgroundFactory.createBackground(LevelType.Padure, i * 48, j * 48));
                pesteraBackgrounds.add(BackgroundFactory.createBackground(LevelType.Pestera, i * 48, j * 48));
                //gameObjects.add();
            }
        }
    }
    public ArrayList<Background> getBackgrounds() {
        switch (selectedLevel) {
            case Campie: return campieBackgrounds;
            case Pestera: return pesteraBackgrounds;
            case Padure: return padureBackgrounds;
            default: throw new RuntimeException("Valeu");
        }
    }
    public void run() {
        if(isEditing) {
            gfx.setColor(Color.WHITE);
            for(int i = 0; i <= 816 / 48; i++) {
                for(int j = 0; j <= 624 / 48; j++) {
                    gfx.drawRect(i * 48, j * 48, 48, 48);
                    gfx.drawString(i + "    " + (j - 1), i * 48, j * 48);
                }
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_F12) {
            isEditing = !isEditing;
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER && isEditing) {
            try {
                save();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_F11) {
            if(isEditing) {
                isEnemy = !isEnemy;
                System.out.println("IsEnemy: " + isEnemy);
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_1) {
            selectedLevel = LevelType.Campie;
            if(isEditing) {
                for(GameObject obj : GameObjectManager.getObjects()) {
                    if(obj instanceof Obstacle) {
                        GameObject newobj = ObstacleFactory.createObstacle(selectedLevel, obj.x, obj. y);
                        GameObjectManager.despawn(obj);
                        GameObjectManager.spawn(newobj);
                    }
                }
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_2) {
            selectedLevel = LevelType.Padure;
            if(isEditing) {
                for(GameObject obj : GameObjectManager.getObjects()) {
                    if(obj instanceof Obstacle) {
                        GameObject newobj = ObstacleFactory.createObstacle(selectedLevel, obj.x, obj. y);
                        GameObjectManager.despawn(obj);
                        GameObjectManager.spawn(newobj);
                    }
                }
            }

        }
        if(e.getKeyCode() == KeyEvent.VK_3) {
            selectedLevel = LevelType.Pestera;
            if(isEditing) {
                for(GameObject obj : GameObjectManager.getObjects()) {
                    if(obj instanceof Obstacle) {
                        GameObject newobj = ObstacleFactory.createObstacle(selectedLevel, obj.x, obj. y);
                        GameObjectManager.despawn(obj);
                        GameObjectManager.spawn(newobj);
                    }
                }
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_Q) {
            if(isEnemy && isEditing) {
                selectedEnemy = "Frog";
                System.out.println("Selected enemy: " + selectedEnemy);
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_W) {
            if(isEnemy && isEditing) {
                selectedEnemy = "Leprechaun";
                System.out.println("Selected enemy: " + selectedEnemy);
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_E) {
            if(isEnemy && isEditing) {
                selectedEnemy = "Wolf";
                System.out.println("Selected enemy: " + selectedEnemy);
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_R) {
            if(isEnemy && isEditing) {
                selectedEnemy = "TreeMonster";
                System.out.println("Selected enemy: " + selectedEnemy);
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_T) {
            if(isEnemy && isEditing) {
                selectedEnemy = "TentacleMonster";
                System.out.println("Selected enemy: " + selectedEnemy);
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_Y) {
            if(isEnemy && isEditing) {
                selectedEnemy = "Spirit";
                System.out.println("Selected enemy: " + selectedEnemy);
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_U) {
            if(isEnemy && isEditing) {
                selectedEnemy = "Gargoyle";
                System.out.println("Selected enemy: " + selectedEnemy);
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_I) {
            if(isEnemy && isEditing) {
                selectedEnemy = "GhostSkeleton";
                System.out.println("Selected enemy: " + selectedEnemy);
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_O) {
            if(isEnemy && isEditing) {
                selectedEnemy = "ElectricitySkeleton";
                System.out.println("Selected enemy: " + selectedEnemy);
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_P) {
            if(isEnemy && isEditing) {
                selectedEnemy = "ScytheSkeleton";
                System.out.println("Selected enemy: " + selectedEnemy);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    private void save() throws IOException {
        System.out.println("Saved");
        StringBuilder levelString = new StringBuilder("id ");
        switch (selectedLevel) {
            case Campie: levelString.append("Campie "); break;
            case Padure: levelString.append("Padure "); break;
            case Pestera: levelString.append("Pestera "); break;
        }
        levelString.append(obstaclesToBeAdded.size()).append(" ");
        for(GameObject obstacle : obstaclesToBeAdded) {
            levelString.append(obstacle.x / 48).append(" ").append(obstacle.y / 48).append(" ");
        }
        levelString.append(enemiesToBeAdded.size()).append(" ");
        for(Enemy enemy : enemiesToBeAdded) {
            levelString.append(enemy.name).append(" ").append(enemy.x / 48).append(" ").append(enemy.y / 48).append(" ");
        }
        levelString.append("id1 id2 id3 id4");
        System.out.println(levelString);
        BufferedWriter out = new BufferedWriter(new FileWriter("level1.txt", true));
        out.write(levelString + "\n");
        out.close();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(isEditing) {
            Point pos = Mouse.getPosition();
            if(!isEnemy) {
                Obstacle obj = ObstacleFactory.createObstacle(selectedLevel,(pos.x / 48) * 48, (pos.y / 48) * 48);
                GameObjectManager.spawn(obj);
                obstaclesToBeAdded.add(obj);
            }
            else {
                Enemy obj = EnemyFactory.createEnemy(selectedEnemy,(pos.x / 48) * 48, (pos.y / 48) * 48);
                GameObjectManager.spawn(obj);
                enemiesToBeAdded.add(obj);
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
