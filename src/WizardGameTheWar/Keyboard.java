package WizardGameTheWar;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class Keyboard {
    // <https://stackoverflow.com/a/69120392>

    private static final Map<Integer, Boolean> pressedKeys = new HashMap<>();

    static {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(event -> {
            synchronized (Keyboard.class) {
                if (event.getID() == KeyEvent.KEY_PRESSED) pressedKeys.put(event.getKeyCode(), true);
                else if (event.getID() == KeyEvent.KEY_RELEASED) pressedKeys.put(event.getKeyCode(), false);
                return false;
            }
        });
    }

    /**
     * Verifica daca o tasta este apasata
     * @param keyCode codul intern al tastei din clasa {@link java.awt.event.KeyEvent}
     * @return Daca tasta data este apasata
     */
    public static boolean isKeyPressed(int keyCode) { // Any key code from the KeyEvent class
        return pressedKeys.getOrDefault(keyCode, false);
    }
}