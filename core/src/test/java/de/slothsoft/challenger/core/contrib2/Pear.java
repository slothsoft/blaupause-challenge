package de.slothsoft.blaupause.contrib2;

import de.slothsoft.blaupause.Contrib;

public class Pear implements Contrib {

	@Override
	public Action execute(Context context) {
		return null;
	}

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
		return "PearPositioner";
	}

}
