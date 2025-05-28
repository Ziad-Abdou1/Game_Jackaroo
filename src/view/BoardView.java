package view;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.Node;

import java.util.*;

import model.Colour;
import model.player.Marble;
import engine.Game;
import engine.board.Board;
import engine.board.Cell;
import engine.board.SafeZone;

public class BoardView extends GridPane {
	private Board board;
	private Game game;
	private Map<Cell, CellView> cellToView = new HashMap<>();

	public BoardView(Board board, Game game) {
		this.game = game;
		safeZoneView = new ArrayList[4];
		for (int i = 0; i < 4; i++)
			safeZoneView[i] = new ArrayList<>();
		trackView = new ArrayList<>();
		this.board = board;
		draw();
	}

	public CellView getBaseCellView(Colour clr) {
		int idx = getBasePosition(clr);
		return trackView.get(idx);
	}

	private int getBasePosition(Colour colour) {
		ArrayList<SafeZone> safeZones = board.getSafeZones();
		for (int i = 0; i < safeZones.size(); i++) {
			if (safeZones.get(i).getColour() == colour)
				return i * 25;
		}

		return -1;
	}

	private ArrayList<CellView> trackView;

	int[] dy = { 0, 1, 0, -1, -1, 1, -1, 1 };
	int[] dx = { -1, 0, 1, 0, -1, -1, 1, 1 };
	int[] segment = new int[] { 7,7,7, 4 };
	int[][] directions = { { 0, 4, 3, 0 }, { 1, 5, 0, 1 }, { 2, 7, 1, 2 },
			{ 3, 6, 2, 3 } };

	int initI = 61;
	int initJ = 61;

	private void initTrack() {
		trackView.clear();
		int curIdx = 0;
		int curi = initI, curj = initJ;
		for (int i = 0; i < directions.length; i++) {
			for (int j = 0; j < directions[i].length; j++) {
				for (int k = 0; k < segment[j]; k++) {
					Cell cell = board.getTrack().get(curIdx);

					CellView cv = new CellView(cell, game);
					cellToView.put(cell, cv);
					trackView.add(cv);
					this.addNode(cv, curj, curi);
					curi += dx[directions[i][j]];
					curj += dy[directions[i][j]];
					curIdx++;

				}
			}
		}
	}

	ArrayList<CellView>[] safeZoneView;

	private void initSafeZone() {

		int curIdx = 98;
		int[] direction = { 0, 1, 2, 3 };
		for (int i = 0; i < 4; i++) {
			safeZoneView[i].clear();
			int curi = this.getRowIndex(trackView.get(curIdx));
			int curj = this.getColumnIndex(trackView.get(curIdx));
			for (int j = 0; j < 4; j++) {
				curi += dx[direction[i]];
				curj += dy[direction[i]];
				Cell cell = board.getSafeZones().get(i).getCells().get(j);
				CellView cv = new CellView(cell, game);
				cellToView.put(cell, cv);

				safeZoneView[i].add(cv);
				this.addNode(cv, curj, curi);
			}
			curIdx = (curIdx + 25) % 100;
		}

	}

	// //----------------------------------------------------------------------------

	public void refresh() {
		for (int i = 0; i < 100; i++) {
			Cell cell = board.getTrack().get(i);
			trackView.get(i).setCell(cell);
//			if (trackView.get(i).getMarbleView().getMarble() == null)
//				System.out.print("-null-");
//			else
//				System.out.print("-"
//						+ trackView.get(i).getMarbleView().getMarble()
//								.getColour() + '-');
			cellToView.put(cell, trackView.get(i));
		}
//		System.out.println();
		for (int i = 0; i < safeZoneView.length; i++) {
			for (int j = 0; j < safeZoneView[i].size(); j++) {
				Cell cell = board.getSafeZones().get(i).getCells().get(j);
				safeZoneView[i].get(j).setCell(cell);
				cellToView.put(cell, safeZoneView[i].get(j));
			}
		}
	}

	public void draw() {

		Image backgroundImage = new Image("/wood2.png");

		BackgroundSize backgroundSize = new BackgroundSize(100, 100,
				true, true,
				true, true 
		);

		BackgroundImage background = new BackgroundImage(backgroundImage,
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.CENTER, backgroundSize);

		this.setBackground(new Background(background));

		initTrack();
		initSafeZone();
		this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
	}

	public void addNode(Node node, int col, int row) {
		this.add(node, col, row);
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Map<Cell, CellView> getCellToView() {
		return cellToView;
	}

	public void setCellToView(Map<Cell, CellView> cellToView) {
		this.cellToView = cellToView;
	}

	public ArrayList<CellView> getTrackView() {
		return trackView;
	}

	public ArrayList<CellView>[] getSafeZoneView() {
		return safeZoneView;
	}

	

}
