package view;

import model.Colour;
import engine.Game;
import engine.board.Cell;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;

public class CellView extends StackPane {
	Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	double screenWidth = screenBounds.getWidth();
	double screenHeight = screenBounds.getHeight();
	Game game;
	private Cell cell;
	private Circle circle;
	private final double radius = 14 * screenWidth / 3500;
	private MarbleView marbleView;

	public CellView(Cell c, Game game) {
		this.game = game;
		marbleView = new MarbleView(c.getMarble(), game);
		circle = new Circle();
		this.cell = c;
		circle.setRadius(radius);
		draw();
	}

	public void draw() {

		if (cell.getMarble() != null) {
			marbleView = new MarbleView(cell.getMarble(), game);
		} else {
			this.getChildren().removeAll();
			circle.setFill(Color.DARKGRAY);

		}
		if (cell.isTrap()) {
			circle.setFill(Color.BLACK);
		}
		this.getChildren().addAll(circle);
		if (marbleView.getMarble() != null)
			this.getChildren().addAll(marbleView);
	}

	public void refresh() {
		if (cell.isTrap()) {
			circle.setFill(Color.BLACK);
		} else {
			circle.setFill(Color.DARKGRAY);
		}
	}

	public void setCell(Cell cell) {
		this.cell = cell;
		circle.setFill(Color.DARKGRAY);
		this.getChildren().remove(marbleView);
		marbleView.setMarble(cell.getMarble());
		if (marbleView.getMarble() != null)
			this.getChildren().addAll(marbleView);
		refresh();
	}

	public MarbleView getMarbleView() {
		return this.marbleView;
	}

}
