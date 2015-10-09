/**
 * Copyright (c) 2015 Martin Geisse
 */
package name.martingeisse.blob.experiment.lanterna;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.TerminalSize;
import com.googlecode.lanterna.terminal.swing.SwingTerminal;
import com.googlecode.lanterna.terminal.swing.TerminalAppearance;

/**
 *
 */
public class MySwingTerminal extends SwingTerminal {

	private final Queue<Key> myKeyQueue;
	private final SwingKeyMapper swingKeyMapper;

	/**
	 * 
	 */
	public MySwingTerminal() {
		super();
		this.myKeyQueue = new ConcurrentLinkedQueue<Key>();
		this.swingKeyMapper = new SwingKeyMapper();
	}

	/**
	 * @param widthInColumns ...
	 * @param heightInRows ...
	 */
	public MySwingTerminal(int widthInColumns, int heightInRows) {
		super(widthInColumns, heightInRows);
		this.myKeyQueue = new ConcurrentLinkedQueue<Key>();
		this.swingKeyMapper = new SwingKeyMapper();
	}

	/**
	 * @param appearance ...
	 * @param widthInColumns ...
	 * @param heightInRows ...
	 */
	public MySwingTerminal(TerminalAppearance appearance, int widthInColumns, int heightInRows) {
		super(appearance, widthInColumns, heightInRows);
		this.myKeyQueue = new ConcurrentLinkedQueue<Key>();
		this.swingKeyMapper = new SwingKeyMapper();
	}

	/**
	 * @param appearance ...
	 */
	public MySwingTerminal(TerminalAppearance appearance) {
		super(appearance);
		this.myKeyQueue = new ConcurrentLinkedQueue<Key>();
		this.swingKeyMapper = new SwingKeyMapper();
	}

	/**
	 * @param terminalSize ...
	 */
	public MySwingTerminal(TerminalSize terminalSize) {
		super(terminalSize);
		this.myKeyQueue = new ConcurrentLinkedQueue<Key>();
		this.swingKeyMapper = new SwingKeyMapper();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.googlecode.lanterna.terminal.swing.SwingTerminal#enterPrivateMode()
	 */
	@Override
	public void enterPrivateMode() {
		super.enterPrivateMode();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				JFrame jFrame = getJFrame();
				jFrame.removeKeyListener(jFrame.getListeners(KeyListener.class)[0]);
				jFrame.addKeyListener(new FixedKeyCapturer());
			}
		};
		if (SwingUtilities.isEventDispatchThread()) {
			runnable.run();
		} else {
			try {
				SwingUtilities.invokeAndWait(runnable);
			} catch (Exception e) {
				throw new RuntimeException("exception while fixing key listener", e);
			}
		}
	}

	private class FixedKeyCapturer extends KeyAdapter {

		@Override
		public void keyTyped(KeyEvent event) {
			Key mappedEvent = swingKeyMapper.mapKeyTyped(event);
			if (mappedEvent != null) {
				myKeyQueue.add(mappedEvent);
			}
		}

		@Override
		public void keyPressed(KeyEvent event) {
			Key mappedEvent = swingKeyMapper.mapKeyPressed(event);
			if (mappedEvent != null) {
				myKeyQueue.add(mappedEvent);
			}
		}
		
	}

	@Override
	public Key readInput() {
		return myKeyQueue.poll();
	}

}
