package de.slothsoft.blaupause;

import java.util.Objects;

public class ContribTile implements Tile {

	private Contrib contrib;

	public ContribTile(Contrib contrib) {
		this.contrib = Objects.requireNonNull(contrib);
	}

	public Contrib getContrib() {
		return this.contrib;
	}

}
