package view;

import java.util.ArrayList;

import engine.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import model.player.Player;

public class HomesView extends StackPane {
	Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	double screenWidth = screenBounds.getWidth();
	double screenHeight = screenBounds.getHeight();

	Game game;
	private ArrayList<Player> players;
	private ArrayList<HomeZoneView> homes;

	public HomesView(ArrayList<Player> players, Game game) {
		this.game = game;
		this.players = players;
		draw();
	}

	public void draw() {
		homes = new ArrayList<>();
		for (int i = 0; i < players.size(); i++) {
			homes.add(new HomeZoneView(players.get(i), game));
			this.getChildren().addAll(homes.get(i));
		}
		this.setAlignment(homes.get(0), Pos.BOTTOM_CENTER);
		this.setAlignment(homes.get(1), Pos.CENTER_LEFT);
		this.setAlignment(homes.get(2), Pos.TOP_CENTER);
		this.setAlignment(homes.get(3), Pos.CENTER_RIGHT);

	}

	public void refresh() {
		for (int i = 0; i < players.size(); i++) {
			homes.get(i).refresh();
		}
	}
//
//	private Pane wrap(HomeZoneView view, Pos alignment) {
//		StackPane wrapper = new StackPane(view);
//		wrapper.setAlignment(alignment);
//		return wrapper;
//	}

	public Rectangle2D getScreenBounds() {
		return screenBounds;
	}

	public void setScreenBounds(Rectangle2D screenBounds) {
		this.screenBounds = screenBounds;
	}

	public double getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(double screenWidth) {
		this.screenWidth = screenWidth;
	}

	public double getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(double screenHeight) {
		this.screenHeight = screenHeight;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public ArrayList<HomeZoneView> getHomes() {
		return homes;
	}

	public void setHomes(ArrayList<HomeZoneView> homes) {
		this.homes = homes;
	}

}
