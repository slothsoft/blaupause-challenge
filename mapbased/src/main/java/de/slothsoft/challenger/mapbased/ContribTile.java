package de.slothsoft.challenger.mapbased;

import java.util.Objects;

import de.slothsoft.challenger.core.Contrib;

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
