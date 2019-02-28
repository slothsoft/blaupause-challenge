package de.slothsoft.blueprint.contrib;

import java.awt.Color;

import de.slothsoft.blueprint.BlueprintContrib;
import de.slothsoft.challenger.core.AbstractContrib;

public class BlueContrib extends AbstractContrib implements BlueprintContrib {

	@Override
	public Color getColor() {
		return Color.BLUE;
	}

}
