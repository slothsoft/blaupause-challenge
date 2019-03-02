package de.slothsoft.blaupause;

import java.util.HashSet;
import java.util.Set;

/**
 * A basic map with tiles.
 * 
 * @since 1.0.0
 */

public class Map {

	final Tile[][] tiles;
	final int width;
	final int height;

	public Map(int width, int height) {
		this(new Tile[width][height]);
	}

	public Map(Tile[][] tiles) {
		this.tiles = tiles;
		this.width = tiles.length;
		this.height = tiles[0].length;
	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}

	public Set<Contrib> getExistingContribs() {
		Set<Contrib> result = new HashSet<>();
		for (Tile[] row : this.tiles) {
			for (Tile tile : row) {
				if (tile instanceof ContribTile) {
					result.add(((ContribTile) tile).getContrib());
				}
			}
		}
		return result;
	}

	public Tile[][] getTiles() {
		return this.tiles;
	}
}
