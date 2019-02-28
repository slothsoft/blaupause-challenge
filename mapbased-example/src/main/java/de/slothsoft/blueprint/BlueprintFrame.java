package de.slothsoft.blueprint;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.slothsoft.challenger.core.Contrib;
import de.slothsoft.challenger.mapbased.AbstractGame;
import de.slothsoft.challenger.mapbased.Map;
import de.slothsoft.challenger.mapbased.Tile;
import de.slothsoft.challenger.mapbased.gui.GridBagData;
import de.slothsoft.challenger.mapbased.gui.HighScorePanel;
import de.slothsoft.challenger.mapbased.gui.MapPanel;
import de.slothsoft.challenger.mapbased.gui.MapRenderer;

/**
 * This class is the window that holds everything. Most importantly, it holds the
 * {@link Map}.
 */

public class BlueprintFrame extends JFrame {

	private static final long serialVersionUID = -2165255329208901685L;

	/**
	 * This class can render all kinds of {@link Tile}s.
	 */

	public class BlueprintMapRenderer extends MapRenderer {

		@Override
		public void paintContrib(Graphics2D graphics, Contrib contrib) {
			final BlueprintContrib blueprint = (BlueprintContrib) contrib;
			graphics.setColor(blueprint.getColor());
			graphics.fillRect(0, 0, this.tileWidth, this.tileHeight);
		}

	}

	private final SettingsPanel settingsPanel = new SettingsPanel();
	private final HighScorePanel highScorePanel = new HighScorePanel();
	private final MapPanel mapPanel = new MapPanel(new BlueprintMapRenderer());

	private AbstractGame game;

	public BlueprintFrame() {
		setTitle("Blueprint");
	}

	private void createMainPanel() {
		setLayout(new BorderLayout());

		final JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(this.mapPanel, BorderLayout.CENTER);

		add(panel, BorderLayout.CENTER);
		add(this.settingsPanel, BorderLayout.WEST);
		add(this.highScorePanel, BorderLayout.EAST);

		final JButton restartButton = new JButton("Restart");
		restartButton.addActionListener(e -> restart());
		this.settingsPanel.add(restartButton, GridBagData.forControl(1, 10));

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(1300, 600));
		setSize(getPreferredSize());
		setLocationRelativeTo(null);
	}

	@Override
	public void setVisible(boolean b) {
		if (!isVisible() && b) {
			createMainPanel();
			start();
			doLayout();
		}
		super.setVisible(b);
	}

	public void restart() {
		stop();
		start();
	}

	public void start() {
		this.game = this.settingsPanel.createGame();
		this.mapPanel.setMap(this.game.getMap());
		this.game.onFinish(this::gameFinished);
		this.game.start();
	}

	private void gameFinished(Contrib winner) {
		if (winner == null) {
			System.out.println("Game finished. There were no survivors.");
		} else {
			System.out.println("Game finished. " + winner + " won.");
			this.highScorePanel.propagateWinner(winner);
		}
		start();
	}

	public void stop() {
		this.game.stopGame();
		this.game = null;
	}

	public boolean isShowSettings() {
		return this.settingsPanel.isVisible();
	}

	public BlueprintFrame showSettings(boolean showSettings) {
		setShowSettings(showSettings);
		return this;
	}

	public void setShowSettings(boolean showSettings) {
		this.settingsPanel.setVisible(showSettings);
	}
}
