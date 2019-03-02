package ${package};

import java.util.Objects;

public class ${contribName}Tile implements Tile {

	private ${contribName} delegate;

	public ${contribName}Tile(${contribName} delegate) {
		this.delegate = Objects.requireNonNull(delegate);
	}

	public ${contribName} get${contribName}() {
		return this.delegate;
	}

}
