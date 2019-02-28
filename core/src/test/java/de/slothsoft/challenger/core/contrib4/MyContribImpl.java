package de.slothsoft.challenger.core.contrib4;

import de.slothsoft.challenger.core.MyContrib;

public class MyContribImpl implements MyContrib {

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		return true;
	}
}
