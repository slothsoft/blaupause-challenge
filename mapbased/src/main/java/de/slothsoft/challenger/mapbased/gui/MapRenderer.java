package de.slothsoft.challenger.mapbased.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import de.slothsoft.challenger.core.Contrib;
import de.slothsoft.challenger.mapbased.ContribTile;
import de.slothsoft.challenger.mapbased.Map;
import de.slothsoft.challenger.mapbased.Tile;

/**
 * A class that is able to render a {@link Map} with tiles.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public class MapRenderer {

	static final int BORDER_WIDTH = 2;

	protected int tileWidth = 16;
	protected int tileHeight = 16;

	/**
	 * Paints a map
	 *
	 * @param graphics graphics
	 * @param map block array
	 */

	public void paintMap(Graphics2D graphics, Map map) {
		graphics.setStroke(new BasicStroke(BORDER_WIDTH));
		graphics.setColor(Color.DARK_GRAY);
		graphics.drawRect(-BORDER_WIDTH, -BORDER_WIDTH, map.getWidth() * this.tileWidth + 2 * BORDER_WIDTH - 1,
				map.getHeight() * this.tileHeight + 2 * BORDER_WIDTH - 1);

		paintTiles(graphics, map.getTiles());
	}

	/**
	 * Paints an entire tile array
	 *
	 * @param graphics graphics
	 * @param tiles tiles array
	 */

	protected void paintTiles(Graphics2D graphics, Tile[][] tiles) {
		for (int xi = 0; xi < tiles.length; xi++) {
			for (int yi = 0; yi < tiles[xi].length; yi++) {
				if (tiles[xi][yi] != null) {
					graphics.translate(xi * this.tileWidth, yi * this.tileHeight);
					paintTile(graphics, tiles[xi][yi]);
					graphics.translate(-xi * this.tileWidth, -yi * this.tileHeight);
				}
			}
		}
	}

	/**
	 * Paints a single tile of the map
	 *
	 * @param graphics graphics
	 * @param tile tile
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
	 * @param graphics graphics
	 * @param contrib the contrib to be painted
	 */

	public void paintContrib(Graphics2D graphics, Contrib contrib) {
		// this is a default behavior that's probably stupid...
		// but at least this class doesn't have to be abstract
		graphics.setColor(Color.RED);
		graphics.fillRect(0, 0, this.tileWidth, this.tileHeight);
	}

	/**
	 * Returns the width of a single {@link Tile} in pixels.
	 *
	 * @return a pixel value
	 */

	public int getTileWidth() {
		return this.tileWidth;
	}

	/**
	 * Sets the width of a single {@link Tile} in pixels.
	 *
	 * @param newTileWidth - a pixel value
	 * @return this instance
	 */

	public MapRenderer tileWidth(int newTileWidth) {
		setTileWidth(newTileWidth);
		return this;
	}

	/**
	 * Sets the width of a single {@link Tile} in pixels.
	 *
	 * @param tileWidth - a pixel value
	 */

	public void setTileWidth(int tileWidth) {
		this.tileWidth = tileWidth;
	}

	/**
	 * Returns the height of a single {@link Tile} in pixels.
	 *
	 * @return a pixel value
	 */

	public int getTileHeight() {
		return this.tileHeight;
	}

	/**
	 * Sets the height of a single {@link Tile} in pixels.
	 *
	 * @param newTileHeight - a pixel value
	 * @return this instance
	 */

	public MapRenderer tileHeight(int newTileHeight) {
		setTileHeight(newTileHeight);
		return this;
	}

	/**
	 * Sets the height of a single {@link Tile} in pixels.
	 *
	 * @param tileHeight - a pixel value
	 */

	public void setTileHeight(int tileHeight) {
		this.tileHeight = tileHeight;
	}

}
