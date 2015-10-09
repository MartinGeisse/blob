/**
 * Copyright (c) 2010 Martin Geisse
 *
 * This file is distributed under the terms of the MIT license.
 */

package name.martingeisse.blob.experiment.lanterna.filebrowser;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import com.googlecode.lanterna.gui.component.AbstractListBox;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.Key.Kind;
import com.googlecode.lanterna.terminal.TerminalSize;

/**
 *
 */
public class FileBrowserListBox extends AbstractListBox {

	private final Set<File> expandedFolders = new HashSet<>();
	private KeyHandler keyHandler;
	private FileSelectionListener selectionListener;
	private File rootFolder;

	/**
	 * Constructor.
	 */
	public FileBrowserListBox() {
		super();
	}

	/**
	 * Constructor.
	 * @param preferredSize ...
	 */
	public FileBrowserListBox(final TerminalSize preferredSize) {
		super(preferredSize);
	}

	/**
	 * Getter method for the keyHandler.
	 * @return the keyHandler
	 */
	public KeyHandler getKeyHandler() {
		return keyHandler;
	}

	/**
	 * Setter method for the keyHandler.
	 * @param keyHandler the keyHandler to set
	 */
	public void setKeyHandler(final KeyHandler keyHandler) {
		this.keyHandler = keyHandler;
	}
	
	/**
	 * Getter method for the selectionListener.
	 * @return the selectionListener
	 */
	public FileSelectionListener getSelectionListener() {
		return selectionListener;
	}
	
	/**
	 * Setter method for the selectionListener.
	 * @param selectionListener the selectionListener to set
	 */
	public void setSelectionListener(FileSelectionListener selectionListener) {
		final File selectedFolderEntry = getSelectedFolderEntry();
		if (this.selectionListener != null && selectedFolderEntry != null) {
			this.selectionListener.onFileSelectionChanged(selectedFolderEntry, null);
		}
		this.selectionListener = selectionListener;
		if (this.selectionListener != null && selectedFolderEntry != null) {
			this.selectionListener.onFileSelectionChanged(null, selectedFolderEntry);
		}
	}
	
	/**
	 * Getter method for the rootFolder.
	 * @return the rootFolder
	 */
	public File getRootFolder() {
		return rootFolder;
	}
	
	/**
	 * Setter method for the rootFolder.
	 * @param rootFolder the rootFolder to set
	 */
	public void setRootFolder(File rootFolder) {
		this.rootFolder = rootFolder;
	}

	/**
	 * Getter method for the selectedFolderEntry.
	 * @return the selectedFolderEntry
	 */
	public File getSelectedFolderEntry() {
		final ListBoxEntry listBoxEntry = (ListBoxEntry)getSelectedItem();
		return (listBoxEntry == null ? null : listBoxEntry.folderEntry);
	}

	/**
	 * Populates this list box. A root folder must be set before calling this method.
	 */
	public void populate() {
		if (rootFolder == null) {
			throw new IllegalStateException("trying to populate file browser list box without root folder");
		}
		clearItems();
		populateHelper(rootFolder, 0);
		invalidate();
	}

	private void populateHelper(final File folder, final int depth) {
		if (!folder.isDirectory()) {
			throw new IllegalArgumentException("not a folder: " + folder);
		}
		for (final File folderEntry : folder.listFiles()) {
			final boolean expanded = (folderEntry.isDirectory() && expandedFolders != null && expandedFolders.contains(folderEntry));
			final ListBoxEntry listBoxEntry = new ListBoxEntry();
			listBoxEntry.folderEntry = folderEntry;
			listBoxEntry.depth = depth;
			listBoxEntry.expanded = expanded;
			addItem(listBoxEntry);
			if (expanded) {
				populateHelper(folderEntry, depth + 1);
			}
		}
	}

	// override
	@Override
	protected String createItemString(final int index) {
		final ListBoxEntry listBoxEntry = (ListBoxEntry)getItemAt(index);
		final StringBuilder builder = new StringBuilder();
		for (int i = 0; i < listBoxEntry.depth; i++) {
			builder.append("| ");
		}
		if (listBoxEntry.folderEntry.isDirectory()) {
			builder.append(listBoxEntry.expanded ? "- " : "+ ");
		} else {
			builder.append("  ");
		}
		builder.append(listBoxEntry.folderEntry.getName());
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see com.googlecode.lanterna.gui.component.AbstractListBox#afterEnteredFocus(com.googlecode.lanterna.gui.Interactable.FocusChangeDirection)
	 */
	@Override
	protected void afterEnteredFocus(FocusChangeDirection direction) {
		File previousSelection = getSelectedFolderEntry();
		try {
			super.afterEnteredFocus(direction);
		} finally {
			File newSelection = getSelectedFolderEntry();
			if (!previousSelection.equals(newSelection) && selectionListener != null) {
				selectionListener.onFileSelectionChanged(previousSelection, newSelection);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.googlecode.lanterna.gui.component.AbstractListBox#keyboardInteraction(com.googlecode.lanterna.input.Key)
	 */
	@Override
	public Result keyboardInteraction(Key key) {
		final ListBoxEntry previouslySelectedListBoxEntry = (ListBoxEntry)getSelectedItem();
		File previousSelection = getSelectedFolderEntry();
		try {
			if (key.getKind() == Kind.ArrowLeft) {
				if (previouslySelectedListBoxEntry != null && previouslySelectedListBoxEntry.folderEntry.isDirectory() && previouslySelectedListBoxEntry.expanded) {
					int selectedIndex = getSelectedIndex();
					expandedFolders.remove(previouslySelectedListBoxEntry.folderEntry);
					populate();
					setSelectedItem(selectedIndex);
				} else {
					File parent = previouslySelectedListBoxEntry.folderEntry.getParentFile();
					if (parent != null && !parent.equals(rootFolder)) {
						for (int i=0; i<getNrOfItems(); i++) {
							ListBoxEntry item = (ListBoxEntry)getItemAt(i);
							if (item.folderEntry.equals(parent)) {
								setSelectedItem(i);
								break;
							}
						}
					}
				}
				return Result.EVENT_HANDLED;
			} else if (key.getKind() == Kind.ArrowRight) {
				int selectedIndex = getSelectedIndex();
				if (previouslySelectedListBoxEntry.folderEntry.isDirectory() && !previouslySelectedListBoxEntry.expanded) {
					expandedFolders.add(previouslySelectedListBoxEntry.folderEntry);
					populate();
				}
				setSelectedItem(selectedIndex);
				return Result.EVENT_HANDLED;
			} else {
				return super.keyboardInteraction(key);
			}
		} finally {
			File newSelection = getSelectedFolderEntry();
			if (!previousSelection.equals(newSelection) && selectionListener != null) {
				selectionListener.onFileSelectionChanged(previousSelection, newSelection);
			}
		}
	}
	
	// override
	@Override
	protected Result unhandledKeyboardEvent(final Key key) {
		final ListBoxEntry listBoxEntry = (ListBoxEntry)getSelectedItem();
		if (listBoxEntry != null && keyHandler != null) {
			final boolean handled = keyHandler.handleKey(listBoxEntry.folderEntry, key);
			return (handled ? Result.EVENT_HANDLED : Result.EVENT_NOT_HANDLED);
		}
		return Result.EVENT_NOT_HANDLED;
	}

	// internal entry type
	static class ListBoxEntry {
		File folderEntry;
		int depth;
		boolean expanded;
	}

	/**
	 * The handler for key events on files and folders in this list box.
	 */
	public static interface KeyHandler {

		/**
		 * @param folderEntry the selected file or folder
		 * @param key the key that was pressed
		 * @return true if the key event was handled, false if not
		 */
		public boolean handleKey(File folderEntry, Key key);

	}

}
