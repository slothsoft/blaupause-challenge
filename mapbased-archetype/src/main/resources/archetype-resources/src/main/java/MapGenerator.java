package ${package};

import java.awt.Point;
import java.util.Random;
import de.slothsoft.challenger.core.Contributions;
import ${package}.contrib.Example${contribName};

/**
 * A generator for {@link Map}.
 * 
 * @author Stef Schulz
 * @since 1.0.0
 */

public class MapGenerator {

	private Random rnd = new Random();
	private int width = 20;
	private int height = 15;

	public Map generate() {
		Map map = new Map(this.width, this.height);

		int index = 0;
		for (${contribName} contrib : Contributions.fetchImplementations(Example${contribName}.class.getPackage(), 
				${contribName}.class)) {
			map.tiles[7][index++] = new ${contribName}Tile(contrib);
		}
		return map;
	}

	private Point generateStartPoint(boolean[][] tiles) {
		Point point = new Point();
		do {
			point.x = this.rnd.nextInt(this.width);
			point.y = this.rnd.nextInt(this.height);
		} while (tiles[point.x][point.y]);
		return point;
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
