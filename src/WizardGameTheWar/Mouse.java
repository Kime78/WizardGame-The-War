package WizardGameTheWar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class Mouse extends MouseAdapter {
    // <https://stackoverflow.com/a/69120392>
    public static Canvas canvas;
    private static final Map<Integer, Boolean> pressedButtons = new HashMap<>();

    public static void addMouseListener() {
        canvas.addMouseListener(new Mouse());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        synchronized (Mouse.class) {
            pressedButtons.put(e.getButton(), true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        synchronized (Mouse.class) {
            pressedButtons.put(e.getButton(), false);
        }
    }

    /**
     * Verifica daca un buton al mouse-ului este apasat
     * @param button codul intern al butonului din clasa {@link java.awt.event.MouseEvent}
     * @return Daca butonul dat este apasat
     */
    public static boolean isButtonPressed(int button) { // Any button code from the MouseEvent class
        return pressedButtons.getOrDefault(button, false);
    }

    public static Point getPosition() {
        return canvas.getMousePosition();
    }
}