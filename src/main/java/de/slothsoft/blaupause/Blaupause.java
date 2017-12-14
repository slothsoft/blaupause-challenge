package de.slothsoft.blaupause;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import de.slothsoft.blaupause.gui.BlaupauseFrame;

/**
 * This class is the starting point for this application. It opens a "nice" GUI with
 * settings, a game field and the high scores.
 * 
 * @since 1.0.0
 */

public class Blaupause {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		SwingUtilities.invokeLater(() -> createAndShowGui());
	}

	private static void createAndShowGui() {
		BlaupauseFrame mainFrame = new BlaupauseFrame();
		mainFrame.start();
		mainFrame.pack();
	}

}
