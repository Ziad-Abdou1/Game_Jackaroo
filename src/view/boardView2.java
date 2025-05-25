package view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

import model.Colour;
import engine.Game;
import engine.board.Board;
import engine.board.Cell;

public class boardView2 extends Pane {
	Board board;
	Game game;
	ArrayList<Circle> track;
	ArrayList<Circle> safeZone;
	ArrayList<Circle> homezone;
	ArrayList<Pair> indexToPoint;
	ArrayList<ArrayList<Pair>> safeZoneIndexToPoint;
	ArrayList<ArrayList<Pair>> homeindexToPoint;
	int radius;
	public static final Color CELL_STROKE_COLOR = Color.ANTIQUEWHITE;
	public static final Color CELL_COLOR = Color.BLACK;

	public boardView2(Game game, Board baord, int radius) {
		this.game = game;
		this.board = board;
		track = new ArrayList<Circle>();
		safeZone = new ArrayList<Circle>();
		homezone = new ArrayList<Circle>();
		indexToPoint = new ArrayList<Pair>();
		safeZoneIndexToPoint = new ArrayList<ArrayList<Pair>>();
		homeindexToPoint = new ArrayList<ArrayList<Pair>>();
		this.radius = radius;
	}

	public Color toFXColor(Colour colour) {
		switch (colour) {
		case GREEN:
			return Color.GREEN;
		case RED:
			return Color.RED;
		case YELLOW:
			return Color.YELLOW;
		case BLUE:
			return Color.BLUE;
		default:
			throw new IllegalArgumentException("Unknown colour: " + colour);
		}
	}

	private Circle createCell(int x, int y) {
		Circle cell = new Circle(radius);
		cell.setFill(CELL_COLOR);
		cell.setStroke(CELL_STROKE_COLOR);
		cell.setCenterX(x);
		cell.setCenterY(y);
		track.add(cell);
		indexToPoint.add(new Pair((int) x, (int) y));
		return cell;
	}

	private void drawPath(int steps, int dx, int dy, int[] pos) {
		for (int i = 0; i < steps; i++) {
			pos[0] += dx;
			pos[1] += dy;
			createCell(pos[0], pos[1]);
		}
	}

	public void initTrackCells(int startX, int startY, int step, int r) {
		createCell(startX, startY);
		int diagStep = (int) ((1 / Math.sqrt(2)) * step);
		int[] pos = { startX, startY };

		drawPath(8, 0, -step, pos);
		drawPath(5, -diagStep, -diagStep, pos);
		drawPath(8, -step, 0, pos);
		drawPath(4, 0, -step, pos);
		drawPath(8, step, 0, pos);
		drawPath(5, diagStep, -diagStep, pos);
		drawPath(8, 0, -step, pos);
		drawPath(4, step, 0, pos);
		drawPath(8, 0, step, pos);
		drawPath(5, diagStep, diagStep, pos);
		drawPath(8, step, 0, pos);
		drawPath(4, 0, step, pos);
		drawPath(8, -step, 0, pos);
		drawPath(5, -diagStep, diagStep, pos);
		drawPath(8, 0, step, pos);
		drawPath(3, -step, 0, pos);

		this.getChildren().addAll(track);
	}

	public void initSafeZone(int step, int r, int w) {
		for (int i = 0; i < 4; i++)
			safeZoneIndexToPoint.add(new ArrayList<>());
		int diagStep = (int) ((1 / Math.sqrt(2)) * step);
		int[] positions = { 23, 48, 73, 98 };

		int[][] directions = { { step, 0 }, { 0, step }, { -step, 0 },
				{ 0, -step } };

		for (int i = 0; i < 4; i++) {
			Pair p = indexToPoint.get(positions[i]);
			int x = p.x, y = p.y;
			for (int j = 0; j < 4; j++) {
				x += directions[i][0];
				y += directions[i][1];
				safeZoneIndexToPoint.get((i + 1) % 4).add(new Pair(x, y));
				Circle cell = new Circle(r);
				Color color = toFXColor(game.getPlayers().get((i + 1) % 4)
						.getColour());
				cell.setStroke(color);
				cell.setStrokeWidth(w);
				cell.setCenterX(x);
				cell.setCenterY(y);
				safeZone.add(cell);
			}
		}

		this.getChildren().addAll(safeZone);
	}

	public void initHomecells(int step, int r, int w) {
		for (int i = 0; i < 4; i++)
			homeindexToPoint.add(new ArrayList<>());

		int[] indexes = { 2, 27, 52, 77 };
		int[][] offsets = { { -80, 0 }, { 0, -100 }, { 80, 0 }, { 0, 50 } };

		for (int i = 0; i < 4; i++) {
			Pair p = indexToPoint.get(indexes[i]);
			int x = p.x + offsets[i][0];
			int y = p.y + offsets[i][1];

			int[][] pattern = { { 0, 0 }, { 0, 2 * step }, { step, step },
					{ -step, step } };

			for (int[] offset : pattern) {
				int cx = x + offset[0];
				int cy = y + offset[1];
				Circle cell = new Circle(r);
				cell.setCenterX(cx);
				cell.setCenterY(cy);
				Color color = toFXColor(game.getPlayers().get(i).getColour());
				cell.setStroke(color);
				cell.setStrokeWidth(w);
				homeindexToPoint.get(i).add(new Pair(cx, cy));
				homezone.add(cell);
			}
		}
		this.getChildren().addAll(homezone);
	}
}