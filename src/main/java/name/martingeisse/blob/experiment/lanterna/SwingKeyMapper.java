/**
 * Copyright (c) 2015 Martin Geisse
 */
package name.martingeisse.blob.experiment.lanterna;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.Key.Kind;

/**
 *
 */
public class SwingKeyMapper {

	private static final Map<Integer, Kind> specialKeyMapping = new HashMap<>();

	static {
		specialKeyMapping.put(KeyEvent.VK_F1, Kind.F1);
		specialKeyMapping.put(KeyEvent.VK_F2, Kind.F2);
		specialKeyMapping.put(KeyEvent.VK_F3, Kind.F3);
		specialKeyMapping.put(KeyEvent.VK_F4, Kind.F4);
		specialKeyMapping.put(KeyEvent.VK_F5, Kind.F5);
		specialKeyMapping.put(KeyEvent.VK_F6, Kind.F6);
		specialKeyMapping.put(KeyEvent.VK_F7, Kind.F7);
		specialKeyMapping.put(KeyEvent.VK_F8, Kind.F8);
		specialKeyMapping.put(KeyEvent.VK_F9, Kind.F9);
		specialKeyMapping.put(KeyEvent.VK_F10, Kind.F10);
		specialKeyMapping.put(KeyEvent.VK_F11, Kind.F11);
		specialKeyMapping.put(KeyEvent.VK_F12, Kind.F12);
		specialKeyMapping.put(KeyEvent.VK_UP, Kind.ArrowUp);
		specialKeyMapping.put(KeyEvent.VK_DOWN, Kind.ArrowDown);
		specialKeyMapping.put(KeyEvent.VK_LEFT, Kind.ArrowLeft);
		specialKeyMapping.put(KeyEvent.VK_RIGHT, Kind.ArrowRight);
		specialKeyMapping.put(KeyEvent.VK_INSERT, Kind.Insert);
		specialKeyMapping.put(KeyEvent.VK_DELETE, Kind.Delete);
		specialKeyMapping.put(KeyEvent.VK_HOME, Kind.Home);
		specialKeyMapping.put(KeyEvent.VK_END, Kind.End);
		specialKeyMapping.put(KeyEvent.VK_PAGE_UP, Kind.PageUp);
		specialKeyMapping.put(KeyEvent.VK_PAGE_DOWN, Kind.PageDown);
	}

	/**
	 * Maps the specified AWT key-pressed event to a Lanterna key event.
	 * 
	 * @param event
	 *            the AWT key event
	 * @return the Lanterna key event, or null to ignore this event
	 */
	public Key mapKeyPressed(KeyEvent event) {
		Kind kind = specialKeyMapping.get(event.getKeyCode());
		return (kind == null ? null : map(event, kind));
	}

	/**
	 * Maps the specified AWT key-typed event to a Lanterna key event.
	 * 
	 * @param event
	 *            the AWT key event
	 * @return the Lanterna key event, or null to ignore this event
	 */
	public Key mapKeyTyped(KeyEvent event) {
		char c = event.getKeyChar();
		boolean shift = event.isShiftDown();
		switch (c) {

		case 8:
			return map(event, Kind.Backspace);

		case 9:
			return map(event, shift ? Kind.ReverseTab : Kind.Tab);

		case 10:
			return map(event, Kind.Enter);

		case 27:
			return map(event, Kind.Escape);

		default:
			if (event.isControlDown()) {
				c = (char) ('a' - 1 + c);
			}
			return (c >= 32 ? map(event, c) : null);
		}
	}

	private Key map(KeyEvent event, Kind kind) {
		boolean ctrlDown = event.isControlDown();
		boolean altDown = event.isAltDown();
		return new Key(kind, ctrlDown, altDown);
	}

	private Key map(KeyEvent event, char c) {
		boolean ctrlDown = event.isControlDown();
		boolean altDown = event.isAltDown();
		return new Key(c, ctrlDown, altDown);
	}

}
