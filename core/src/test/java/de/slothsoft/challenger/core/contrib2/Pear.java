package de.slothsoft.challenger.core.contrib2;

import de.slothsoft.challenger.core.Contrib;

public class Pear implements Contrib {

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Pear;
	}

	@Override
	public int hashCode() {
		return 42;
	}

	@Override
	public String toString() {
		return "Pear";
	}

}
