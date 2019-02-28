package de.slothsoft.blueprint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

import de.slothsoft.challenger.core.Contrib;
import de.slothsoft.challenger.mapbased.ContribTile;
import de.slothsoft.challenger.mapbased.Map;
import de.slothsoft.challenger.mapbased.MapGenerator;
import de.slothsoft.challenger.mapbased.gui.ContribModel;
import de.slothsoft.challenger.mapbased.gui.GridBagData;

public class SettingsPanel extends JPanel {

	private static final long serialVersionUID = -2165255329208901685L;

	private final ContribModel contribModel = new ContribModel(BlueprintChallenge.fetchAllImplementations());

	private JSpinner sleepTime;
	private JSpinner mapWidth;
	private JSpinner mapHeight;

	private BlueprintGame lastGame;

	public SettingsPanel() {
		setLayout(new BorderLayout());
		createControls();
	}

	private void createControls() {
		final TitledBorder titleBorder = BorderFactory.createTitledBorder("Settings");
		titleBorder.setTitleColor(Color.DARK_GRAY);

		setBorder(titleBorder);
		setLayout(new GridBagLayout());

		int y = 0;
		final JTable table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.setModel(this.contribModel);
		table.getColumnModel().getColumn(ContribModel.COLUMN_SELECTED).setMaxWidth(40);
		table.getColumnModel().getColumn(ContribModel.COLUMN_NAME).setPreferredWidth(200);
		table.getColumnModel().getColumn(ContribModel.COLUMN_NAME).setMaxWidth(200);
		table.getColumnModel().getColumn(ContribModel.COLUMN_AUTHOR).setPreferredWidth(100);
		table.getColumnModel().getColumn(ContribModel.COLUMN_CLASS).setPreferredWidth(200);

		final JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(250, 100));
		add(scrollPane, GridBagData.forPanel(0, y++).gridwidth(2));

		this.sleepTime = new JSpinner();
		this.sleepTime.setModel(new SpinnerNumberModel(100, 0, 10_000, 100));
		this.sleepTime.addChangeListener(e -> this.lastGame.setSleepTime((int) this.sleepTime.getValue()));

		add(new JLabel("Sleep time:"), GridBagData.forLabel(0, y));
		add(this.sleepTime, GridBagData.forControl(1, y));
		y++;

		this.mapWidth = new JSpinner();
		this.mapWidth.setModel(new SpinnerNumberModel(20, 10, 100, 1));

		add(new JLabel("Map width:"), GridBagData.forLabel(0, y));
		add(this.mapWidth, GridBagData.forControl(1, y));
		y++;

		this.mapHeight = new JSpinner();
		this.mapHeight.setModel(new SpinnerNumberModel(15, 7, 100, 1));

		add(new JLabel("Map height:"), GridBagData.forLabel(0, y));
		add(this.mapHeight, GridBagData.forControl(1, y));
		y++;
	}

	public BlueprintGame createGame() {
		final MapGenerator generator = new MapGenerator();
		generator.setWidth((int) this.mapWidth.getValue());
		generator.setHeight((int) this.mapHeight.getValue());

		final Map map = generator.generate();
		for (final Contrib contrib : BlueprintChallenge.fetchAllImplementations()) {
			final Point position = map.generateFreePosition();
			map.getTiles()[position.x][position.y] = new ContribTile(contrib);
		}

		this.lastGame = new BlueprintGame(map);
		this.lastGame.setSleepTime((int) this.sleepTime.getValue());
		return this.lastGame;
	}
}
