package de.slothsoft.challenger.mapbased.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.JPanel;

import de.slothsoft.challenger.mapbased.Map;

/**
 * A {@link JPanel} that displays a {@link Map}.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public class MapPanel extends JComponent {

	private static final long serialVersionUID = -4607180702570402004L;
	private static final long REPAINT_IN_MS = 1000 / 24; // 24 frames per second

	private Map map;
	private final MapRenderer renderer;

	private int width;
	private int height;

	public MapPanel(MapRenderer renderer) {
		this.renderer = renderer;
		setDoubleBuffered(true);
	}

	@Override
	public void paint(Graphics graphics) {
		if (getBackground() != null) {
			graphics.setColor(getBackground());
			graphics.fillRect(0, 0, getWidth(), getHeight());
		}
		if (this.map != null) {
			final double ratio = (float) Math.min((double) getWidth() / this.width, (double) getHeight() / this.height);
			((Graphics2D) graphics).translate(this.renderer.tileWidth, this.renderer.tileHeight);
			((Graphics2D) graphics).scale(ratio, ratio);
			this.renderer.paintMap((Graphics2D) graphics, this.map);
		}
		repaint(REPAINT_IN_MS);
	}

	public Map getMap() {
		return this.map;
	}

	// TODO test setMap

	public void setMap(Map map) {
		this.map = map;
		this.width = map == null ? 0 : map.getWidth() * this.renderer.tileWidth + 2 * this.renderer.tileWidth;
		this.height = map == null ? 0 : map.getHeight() * this.renderer.tileHeight + 2 * this.renderer.tileHeight;
		setPreferredSize(new Dimension(this.width, this.height));
		repaint();
	}

	public int getPreferredWidth() {
		return this.width;
	}

	public int getPreferredHeight() {
		return this.height;
	}
}
