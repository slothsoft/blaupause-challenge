package ${package}.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

import ${package}.Game;
import ${package}.Map;
import ${package}.MapGenerator;

public class SettingsPanel extends JPanel {

	private static final long serialVersionUID = -2165255329208901685L;

	private final ${contribName}Model model = new ${contribName}Model();

	private JSpinner sleepTime;
	private JSpinner mapWidth;
	private JSpinner mapHeight;

	private Game lastGame;

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
		table.setModel(this.model);
		table.getColumnModel().getColumn(${contribName}Model.COLUMN_SELECTED).setMaxWidth(40);
		table.getColumnModel().getColumn(${contribName}Model.COLUMN_NAME).setPreferredWidth(200);
		table.getColumnModel().getColumn(${contribName}Model.COLUMN_NAME).setMaxWidth(200);
		table.getColumnModel().getColumn(${contribName}Model.COLUMN_AUTHOR).setPreferredWidth(100);
		table.getColumnModel().getColumn(${contribName}Model.COLUMN_CLASS).setPreferredWidth(200);

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

	public Game createGame() {
		final MapGenerator generator = new MapGenerator();
		generator.setWidth((int) this.mapWidth.getValue());
		generator.setHeight((int) this.mapHeight.getValue());

		final Map map = generator.generate();
		this.lastGame = new Game(map);
		this.lastGame.setSleepTime((int) this.sleepTime.getValue());
		return this.lastGame;
	}
}
