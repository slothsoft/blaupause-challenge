package ${package}.gui;

import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

import ${package}.${contribName};
import ${package}.Map;

public class ScorePanel extends JComponent {

	private static final long serialVersionUID = -6297202903243792595L;
	private Map map;

	private void createControls() {
		setLayout(new FlowLayout());

		for (${contribName} contrib : this.map.getExisting${contribName}s()) {
			add(new ${contribName}Control(contrib));
		}
	}

	public Map getMap() {
		return this.map;
	}

	public void setMap(Map map) {
		this.map = map;
		removeAll();
		createControls();
	}

	class ${contribName}Control extends JLabel {

		private static final long serialVersionUID = -1769442297405719987L;

		public ${contribName}Control(${contribName} contrib) {
			setText(contrib.getDisplayName());
			setToolTipText(contrib.getDisplayName());
			setIcon(createIcon(contrib));
		}

		private Icon createIcon(${contribName} contrib) {
			final int size = 32;
			BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = image.createGraphics();
			g.scale(size / MapRenderer.WIDTH_IN_PIXELS, size / MapRenderer.HEIGHT_IN_PIXELS);
			new MapRenderer().paint${contribName}(g, contrib);
			g.dispose();
			return new ImageIcon(image);
		}

	}

}
