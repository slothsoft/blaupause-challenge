package ${package};

import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

/**
 * A start class for each game.
 * 
 * @author Stef Schulz
 * @since 1.0.0
 */

public class Game {

	private final Map map;
	private final Thread thread = new Thread(this::run);

	private boolean stop = true;
	private long sleepTime = 100L;
	private Consumer<${contribName}> onFinish = winner -> System.out
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

	private void run() {
		while (!this.stop) {
			executeRound();
		}
	}

	private void executeRound() {
		executeRoundOnMap();

		if (isGameOver()) {
			finishGame();
			return;
		}

		try {
			Thread.sleep(this.sleepTime);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private void executeRoundOnMap() {
		// TODO: what to do in each round
	}

	private boolean isGameOver() {
		return false; // TODO: when to stop
	}

	private void finishGame() {
		stopGame();
		Set<${contribName}> existingContribs = this.map.getExisting${contribName}s();
		this.onFinish.accept(existingContribs.isEmpty() ? null : existingContribs.iterator().next());
	}

	/**
	 * Stops the current game
	 */

	public void stopGame() {
		this.stop = true;
	}

	public Map getMap() {
		return this.map;
	}

	public Consumer<${contribName}> getOnFinish() {
		return this.onFinish;
	}

	public Game onFinish(Consumer<${contribName}> newOnFinish) {
		setOnFinish(newOnFinish);
		return this;
	}

	public void setOnFinish(Consumer<${contribName}> onFinish) {
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

	class ContextImpl implements ${contribName}.Context {

		@Override
		public int getValueA() {
			return 0;
		}

		@Override
		public int getValueB() {
			return 42;
		}

	}
}
