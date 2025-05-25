package model.player;

import java.util.ArrayList;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import model.Colour;

public class Marble {

	private final Colour colour;
	public Circle circle;

	public Marble(Colour colour) {
		this.colour = colour;

		if (colour == Colour.BLUE)
			circle = createMarble(Color.BLUE);
		if (colour == Colour.GREEN)
			circle = createMarble(Color.GREEN);
		if (colour == Colour.RED)
			circle = createMarble(Color.RED);
		if (colour == Colour.YELLOW)
			circle = createMarble(Color.web("#B8860B"));

	}

	// what i add
	private Circle createMarble(Color baseColor) {
		double radius = 9;

		// Create a 3D-like gradient fill
		RadialGradient gradient = new RadialGradient(45, // focus angle
															// (direction of
															// light)
				0.1, // focus distance
				0.3, 0.3, // center X, Y (offset for highlight)
				1.0, // radius of the gradient
				true, // proportional to shape size
				CycleMethod.NO_CYCLE, new Stop(0, Color.WHITE), new Stop(0.3,
						baseColor.brighter()),
				new Stop(1.0, baseColor.darker()));

		Circle marble = new Circle(radius);
		marble.setFill(gradient);

		DropShadow glow = new DropShadow();
		glow.setColor(baseColor.deriveColor(0, 1.0, 1.5, 0.8));
		glow.setRadius(12); // the radius
		glow.setSpread(0.5);
		marble.setOnMouseEntered(e -> marble.setEffect(glow));
		marble.setOnMouseExited(e -> marble.setEffect(null));

		return marble;
	}

	public Colour getColour() {
		return this.colour;
	}
}