package de.slothsoft.blaupause.gui;

import de.slothsoft.blaupause.Game;
import de.slothsoft.blaupause.Map;

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
