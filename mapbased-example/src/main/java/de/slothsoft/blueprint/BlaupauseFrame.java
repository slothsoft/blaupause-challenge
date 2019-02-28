package de.slothsoft.blueprint;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.slothsoft.challenger.core.Contrib;
import de.slothsoft.challenger.mapbased.Game;
import de.slothsoft.challenger.mapbased.gui.GridBagData;
import de.slothsoft.challenger.mapbased.gui.HighScorePanel;
import de.slothsoft.challenger.mapbased.gui.MapPanel;

public class BlaupauseFrame extends JFrame {

	private static final long serialVersionUID = -2165255329208901685L;

	private final SettingsPanel settingsPanel = new SettingsPanel();
	private final HighScorePanel highScorePanel = new HighScorePanel();
	private final MapPanel mapPanel = new MapPanel();
	private final ScorePanel scorePanel = new ScorePanel();

	private Game game;

	public BlaupauseFrame() {
		setTitle("Blaupause");
	}

	private void createMainPanel() {
		setLayout(new BorderLayout());

		final JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(this.mapPanel, BorderLayout.CENTER);
		panel.add(this.scorePanel, BorderLayout.SOUTH);

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

	public void restart() {
		stop();
		start();
	}

	public void start() {
		createMainPanel();
		setVisible(true);
		this.game = this.settingsPanel.createGame();
		this.mapPanel.setMap(this.game.getMap());
		this.scorePanel.setMap(this.game.getMap());
		this.game.onFinish(this::gameFinished);
		this.game.start();
		doLayout();
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

	public BlaupauseFrame showSettings(boolean showSettings) {
		setShowSettings(showSettings);
		return this;
	}

	public void setShowSettings(boolean showSettings) {
		this.settingsPanel.setVisible(showSettings);
	}
}
