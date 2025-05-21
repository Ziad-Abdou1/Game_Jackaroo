package view;

import engine.Game;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.util.Duration;
import model.Colour;
import model.player.Marble;

public class MarbleView extends StackPane {
	Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	double screenWidth = screenBounds.getWidth();
	double screenHeight = screenBounds.getHeight();
	boolean selected = false;
	Game game;

	private Marble marble;
	private final double radius = 14 * screenWidth / 4000;
	private Circle circle;

	public MarbleView(Marble marble, Game game) {
		this.game = game;
		circle = new Circle();
		this.marble = marble;
		refresh();
		this.getChildren().add(circle);

		handle();

	}

	public void handle() {

		if (marble != null) {
			circle.setOnMouseClicked(e -> {
				if (game.getActivePlayerColour()==game.getPlayers().get(0).getColour()){
					try {
						System.out.println("marble is selected");
						selected = !selected;
						if (selected) {
							circle.setEffect(null);
							selectEffect();
						}
						else deselectEffect();
						//game.selectMarble(this.marble);
					} catch (Exception exc) {
						System.out.println(exc.getMessage());
						((GameView)this.getScene().getRoot()).showExceptionWindow(exc.getMessage());
						
					}
				}
				
			});

			DropShadow shadow = new DropShadow();

			shadow.setColor(getFXColor(marble.getColour()));
			shadow.setRadius(20);

			circle.setOnMouseEntered(e -> {
				if (!selected){
					if (game.getActivePlayerColour()==game.getPlayers().get(0).getColour()){
						circle.setEffect(shadow);
						this.setScaleX(1.3);
						this.setScaleY(1.3);
					}
				}



			});

			circle.setOnMouseExited(e -> {
				if (!selected){
					if (game.getActivePlayerColour()==game.getPlayers().get(0).getColour()){
						circle.setEffect(null);
						this.setScaleX(1);
						this.setScaleY(1);
					}

				}

			});
		}
	}
	
	public void resetSelect(){
		this.selected = false;
	}
	
	private Color getFXColor(Colour clr) {
		switch (clr) {
		case BLUE:
			return Color.BLUE;
		case RED:
			return Color.RED;
		case GREEN:
			return Color.GREEN;
		case YELLOW:
			return Color.YELLOW;
		default:
			return Color.GRAY;
		}
	}

	public void selectEffect() {
	    // compute a lighter version of the fill colour
	    Color fill = (Color) circle.getFill();
	    Color lighter = fill.brighter();
	    // apply a stroke (border) of that lighter colour
	    circle.setStroke(Color.WHITE);
	    circle.setStrokeWidth(radius * 0.1);  // adjust thickness as you like
	    
	}

	public void deselectEffect() {
	    // remove the border
	    circle.setStroke(null);
	    circle.setStrokeWidth(0);
	}
	
	public void refresh() {
		handle();
		circle.setRadius(radius);
		if (marble == null) {
			circle.setOpacity(0);
		} else {
			circle.setOpacity(1);
			if (selected) selectEffect();
			else deselectEffect();
			Colour clr = marble.getColour();
			if (clr == Colour.BLUE)
				circle.setFill(Color.BLUE);
			if (clr == Colour.YELLOW)
				circle.setFill(Color.YELLOW);
			if (clr == Colour.GREEN)
				circle.setFill(Color.GREEN);
			if (clr == Colour.RED)
				circle.setFill(Color.RED);
		}
	}

	public Marble getMarble() {
		return this.marble;
	}

	public void setMarble(Marble marble) {
		this.marble = marble;
		refresh();
	}

}