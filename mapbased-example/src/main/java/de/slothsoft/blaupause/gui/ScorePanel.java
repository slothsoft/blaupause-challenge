package de.slothsoft.blaupause.gui;

import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

import de.slothsoft.blaupause.Contrib;
import de.slothsoft.blaupause.Map;

public class ScorePanel extends JComponent {

	private static final long serialVersionUID = -6297202903243792595L;
	private Map map;

	private void createControls() {
		setLayout(new FlowLayout());

		for (Contrib contrib : this.map.getExistingContribs()) {
			add(new ContribControl(contrib));
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

	class ContribControl extends JLabel {

		private static final long serialVersionUID = -1769442297405719987L;

		public ContribControl(Contrib contrib) {
			setText(contrib.getDisplayName());
			setToolTipText(contrib.getDisplayName());
			setIcon(createIcon(contrib));
		}

		private Icon createIcon(Contrib contrib) {
			final int size = 32;
			BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = image.createGraphics();
			g.scale(size / MapRenderer.WIDTH_IN_PIXELS, size / MapRenderer.HEIGHT_IN_PIXELS);
			new MapRenderer().paintContrib(g, contrib);
			g.dispose();
			return new ImageIcon(image);
		}

	}

}
