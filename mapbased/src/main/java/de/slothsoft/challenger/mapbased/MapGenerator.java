package de.slothsoft.challenger.mapbased;

import java.util.Random;

/**
 * A generator for {@link Map}.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public class MapGenerator {

	public static final Random RND = new Random();

	private int width = 20;
	private int height = 15;

	public Map generate() {
		return new Map(this.width, this.height);
	}

	public int getHeight() {
		return this.height;
	}

	public MapGenerator height(int newHeight) {
		setHeight(newHeight);
		return this;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return this.width;
	}

	public MapGenerator width(int newWidth) {
		setWidth(newWidth);
		return this;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
