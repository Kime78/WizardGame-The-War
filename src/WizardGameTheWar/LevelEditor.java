package WizardGameTheWar;

import WizardGameTheWar.GameObjects.GameObject;
import WizardGameTheWar.GameObjects.Obstacles.Boulder;
import WizardGameTheWar.GameWindow.GameWindow;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LevelEditor implements KeyListener {
    public GameWindow wnd;
    public Graphics gfx;
    private boolean isEditing;
    private final ArrayList<GameObject> toBeAdded = new ArrayList<>();

    public void init() {
        wnd.GetCanvas().addKeyListener(this);
    }
    public void run() {
        if(isEditing) {
            //System.out.println("Editing");
            if(Mouse.isButtonPressed(MouseEvent.BUTTON1)) {
                Point pos = Mouse.getPosition();
                toBeAdded.add(new Boulder((pos.x / 48) * 48, (pos.y / 48) * 48));
            }
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
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    private void save() throws IOException {
        System.out.println("Saved");
        BufferedWriter out = new BufferedWriter(new FileWriter("level1.txt", true));
        out.write("Test");
        out.close();
    }
}
