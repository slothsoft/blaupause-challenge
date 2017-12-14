package de.slothsoft.blaupause.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import de.slothsoft.blaupause.Contrib;
import de.slothsoft.blaupause.ContribTile;
import de.slothsoft.blaupause.Map;
import de.slothsoft.blaupause.Tile;

public class MapRenderer {

	static final int BORDER_WIDTH = 2;
	static final int WIDTH_IN_PIXELS = 16;
	static final int HEIGHT_IN_PIXELS = 16;

	/**
	 * Paints a map
	 *
	 * @param graphics
	 *            graphics
	 * @param map
	 *            block array
	 */

	public void paintMap(Graphics2D graphics, Map map) {
		graphics.setStroke(new BasicStroke(BORDER_WIDTH));
		graphics.setColor(Color.DARK_GRAY);
		graphics.drawRect(-BORDER_WIDTH, -BORDER_WIDTH, map.getWidth() * WIDTH_IN_PIXELS + 2 * BORDER_WIDTH - 1,
				map.getHeight() * HEIGHT_IN_PIXELS + 2 * BORDER_WIDTH - 1);

		paintTiles(graphics, map.getTiles());
	}

	/**
	 * Paints an entire tile array
	 *
	 * @param graphics
	 *            graphics
	 * @param tiles
	 *            tiles array
	 */

	protected void paintTiles(Graphics2D graphics, Tile[][] tiles) {
		for (int xi = 0; xi < tiles.length; xi++) {
			for (int yi = 0; yi < tiles[xi].length; yi++) {
				if (tiles[xi][yi] != null) {
					graphics.translate(xi * WIDTH_IN_PIXELS, yi * HEIGHT_IN_PIXELS);
					paintTile(graphics, tiles[xi][yi]);
					graphics.translate(-xi * WIDTH_IN_PIXELS, -yi * HEIGHT_IN_PIXELS);
				}
			}
		}
	}

	/**
	 * Paints a single tile of the map
	 *
	 * @param graphics
	 *            graphics
	 * @param tile
	 *            tile
	 */

	protected void paintTile(Graphics2D graphics, Tile tile) {
		if (tile instanceof ContribTile) {
			paintContrib(graphics, ((ContribTile) tile).getContrib());
		} else
			throw new UnsupportedOperationException("Do not know how to paint " + tile + "!");
	}

	/**
	 * Paints a single tile of the map
	 *
	 * @param graphics
	 *            graphics
	 * @param contrib
	 *            the contrib to be painted
	 */

	public void paintContrib(Graphics2D graphics, Contrib contrib) {
		graphics.setColor(Color.RED);
		graphics.fillRect(0, 0, WIDTH_IN_PIXELS, HEIGHT_IN_PIXELS);
	}

}
