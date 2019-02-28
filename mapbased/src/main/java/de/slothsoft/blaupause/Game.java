package de.slothsoft.blaupause;

import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

/**
 * A start class for each game.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public abstract class Game {

	private final Map map;
	private final Thread thread = new Thread(this::run);

	private boolean stop = true;
	private long sleepTime = 100L;
	private Consumer<Contrib> onFinish = winner -> System.out
			.println(winner == null ? "Game finished. There were no survivors." : "Game finished. " + winner + " won.");

	public Game(Map map) {
		this.map = Objects.requireNonNull(map);
	}

	/**
	 * Starts the current game
	 */

	public void start() {
		this.stop = false;
		this.thread.start();
	}

	protected void run() {
		while (!this.stop) {
			executeRound();
		}
	}

	protected void executeRound() {
		executeRoundOnMap();

		if (isGameOver()) {
			finishGame();
			return;
		}

		try {
			Thread.sleep(this.sleepTime);
		} catch (final InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Execute what to do in each round.
	 */

	protected abstract void executeRoundOnMap();

	/**
	 * Returns true when the game is over.
	 *
	 * @return true or false
	 */

	protected abstract boolean isGameOver();

	protected void finishGame() {
		stopGame();
		final Set<Contrib> existingContribs = this.map.getExistingContribs();
		this.onFinish.accept(existingContribs.isEmpty() ? null : existingContribs.iterator().next());
	}

	/**
	 * Stops the current game.
	 */

	public void stopGame() {
		this.stop = true;
	}

	public Map getMap() {
		return this.map;
	}

	public Consumer<Contrib> getOnFinish() {
		return this.onFinish;
	}

	public Game onFinish(Consumer<Contrib> newOnFinish) {
		setOnFinish(newOnFinish);
		return this;
	}

	public void setOnFinish(Consumer<Contrib> onFinish) {
		this.onFinish = Objects.requireNonNull(onFinish);
	}

	public long getSleepTime() {
		return this.sleepTime;
	}

	public Game sleepTime(long newSleepTime) {
		setSleepTime(newSleepTime);
		return this;
	}

	public void setSleepTime(long sleepTime) {
		this.sleepTime = sleepTime;
	}

}
