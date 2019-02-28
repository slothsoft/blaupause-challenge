package de.slothsoft.blueprint;

import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import de.slothsoft.blueprint.contrib.BlueContrib;
import de.slothsoft.challenger.core.Contrib;
import de.slothsoft.challenger.core.Contribs;

/**
 * This class is the starting point for this application. It opens a "nice" GUI with
 * settings, a game field and the high scores.
 */

public class BlueprintChallenge {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (final Exception ex) {
			System.err.println(ex.getMessage());
		}
		SwingUtilities.invokeLater(() -> createAndShowGui());
	}

	private static void createAndShowGui() {
		final BlueprintFrame mainFrame = new BlueprintFrame();
		mainFrame.setVisible(true);
	}

	public static List<Contrib> fetchAllImplementations() {
		return Contribs.fetchContribImplementations(BlueContrib.class.getPackage());
	}

}
