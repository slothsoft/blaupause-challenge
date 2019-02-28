package de.slothsoft.challenger.mapbased;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import de.slothsoft.challenger.core.Contrib;

/**
 * A basic map with tiles.
 *
 * @author Stef Schulz
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

	// TODO: test getExistingContribs

	public Set<Contrib> getExistingContribs() {
		final Set<Contrib> result = new HashSet<>();
		for (final Tile[] row : this.tiles) {
			for (final Tile tile : row) {
				if (tile instanceof ContribTile) {
					result.add(((ContribTile) tile).getContrib());
				}
			}
		}
		return result;
	}

	// TODO: test generateFreePosition

	public Point generateFreePosition() {
		final Point point = new Point();
		do {
			point.x = MapGenerator.RND.nextInt(this.width);
			point.y = MapGenerator.RND.nextInt(this.height);
		} while (this.tiles[point.x][point.y] != null);
		return point;
	}

	public Tile[][] getTiles() {
		return this.tiles;
	}
}
