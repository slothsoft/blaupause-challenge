package de.slothsoft.blaupause;

import java.util.Objects;

/**
 * A tile on the {@link Map} with a {@link Contrib}.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public class ContribTile implements Tile {

	private final Contrib contrib;

	public ContribTile(Contrib contrib) {
		this.contrib = Objects.requireNonNull(contrib);
	}

	public Contrib getContrib() {
		return this.contrib;
	}

}
