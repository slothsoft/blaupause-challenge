package de.slothsoft.challenger.core;

import java.util.Objects;

public abstract class AbstractContrib implements Contrib {

	@Override
	public int hashCode() {
		return 37 * (getDisplayName() == null ? 3 : getDisplayName().hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final AbstractContrib that = (AbstractContrib) obj;
		if (!Objects.equals(getDisplayName(), that.getDisplayName())) return false;
		return true;
	}

	@Override
	public String toString() {
		return getDisplayName();
	}

}
