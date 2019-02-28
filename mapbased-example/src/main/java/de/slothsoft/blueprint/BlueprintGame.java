package de.slothsoft.blueprint;

import java.util.Random;

import de.slothsoft.challenger.core.Contrib;
import de.slothsoft.challenger.mapbased.AbstractGame;
import de.slothsoft.challenger.mapbased.ContribTile;
import de.slothsoft.challenger.mapbased.Map;
import de.slothsoft.challenger.mapbased.Tile;

/**
 * This class holds the game logic.
 */

public class BlueprintGame extends AbstractGame {

	private static Random RND = new Random();

	private final Map map;

	public BlueprintGame(Map map) {
		super(map);
		this.map = map;
	}

	@Override
	protected void executeRoundOnMap() {
		final Tile[][] tiles = this.map.getTiles();
		for (int row = 0; row < tiles.length; row++) {
			for (int col = 0; col < tiles[row].length; col++) {
				final Tile tile = tiles[row][col];
				if (tile instanceof ContribTile) {
					final Contrib contrib = ((ContribTile) tile).getContrib();

					final int xInc = RND.nextInt(3) - 1;
					final int yInc = RND.nextInt(3) - 1;

					try {
						tiles[row + yInc][col + xInc] = tiles[row][col];
					} catch (final Exception e) {
						// I <3 misusing try-catch for if
						System.out.println(contrib.getDisplayName() + " died.");
					}
					tiles[row][col] = null;
				}
			}
		}
	}

	@Override
	protected boolean isGameOver() {
		return this.map.getExistingContribs().size() <= 1;
	}

}
