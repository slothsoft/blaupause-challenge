package de.slothsoft.blaupause.contrib;

import java.util.Objects;

import de.slothsoft.blaupause.Contrib;

public abstract class AbstractContrib implements Contrib {

	@Override
	public int hashCode() {
		return 37 * (getDisplayName() == null ? 3 : getDisplayName().hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AbstractContrib that = (AbstractContrib) obj;
		if (!Objects.equals(getDisplayName(), that.getDisplayName())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return getDisplayName();
	}

}
