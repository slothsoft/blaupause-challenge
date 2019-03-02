package ${package}.contrib;

import java.util.Random;

public class Example${contribName} extends Abstract${contribName} {

	private final Random rnd = new Random();
	private final Action[] actions = Action.values();

	@Override
	public String getDisplayName() {
		return "Example";
	}

	@Override
	public String getAuthor() {
		return "Slothsoft";
	}

	@Override
	public Action execute(Context context) {
		return this.actions[this.rnd.nextInt(this.actions.length)];
	}

}
