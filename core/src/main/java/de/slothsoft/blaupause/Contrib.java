package de.slothsoft.blaupause;

/**
 * The base class for all contributions for a challenge.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public interface Contrib {

	/**
	 * Returns the display name of the contribution.
	 *
	 * @return display name; or class name on default
	 */

	default String getDisplayName() {
		return getClass().getSimpleName();
	}

	/**
	 * Returns the author of the contribution.
	 *
	 * @return author; or "unknown" on default
	 */

	default String getAuthor() {
		return "unknown";
	}

}
