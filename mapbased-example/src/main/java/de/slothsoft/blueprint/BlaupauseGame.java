package de.slothsoft.blueprint;

import de.slothsoft.challenger.mapbased.Game;
import de.slothsoft.challenger.mapbased.Map;

public class BlaupauseGame extends Game {

	public BlaupauseGame(Map map) {
		super(map);
	}

	@Override
	protected void executeRoundOnMap() {
		// nothing to execute
	}

	@Override
	protected boolean isGameOver() {
		return false;
	}

}
