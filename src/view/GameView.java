package view;

import java.util.ArrayList;
import java.util.Objects;

import org.omg.CORBA.MARSHAL;

import model.Colour;
import model.card.standard.*;
import model.card.*;
import model.player.Marble;
import model.player.Player;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;
import engine.BoardListener;
//import engine.BoardListener;
import engine.Game;
import engine.GameListener;
//import engine.GameListener;
import engine.board.Board;
import engine.board.Cell;
import engine.board.SafeZone;

public class GameView extends StackPane implements BoardListener, GameListener {
	Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	double screenWidth = screenBounds.getWidth();
	double screenHeight = screenBounds.getHeight();
	double ratioScreenWidth = screenWidth / 1920;
	double ratioScreenHeight = screenHeight / 1080;

	ImageView PlayButton;
	static Game game;
	BoardView boardView;
	HomesView homesView;
	BoardAndHomeView boardAndHomeView;
	HandsView handsView;
	PlayerViews playerViews;
	FirePitView firePitView;
	int idx = 0;
	boolean efficient = true;
	DetailsView detailsView;
	DeckView deckView;
	MarblesView marblesView ;

	// what i add  
	boardView2 boardView2; 

	public GameView(Game game) {
		this.game = game;
		game.addListener(this);
		game.getBoard().addListener(this);
		detailsView = new DetailsView(game);
		initPlayButton();
		draw();
		handleSelectedCards();
	}

	public void handleSelectedCards() {
		for (CardView cv : this.getHandsView().getHands().get(0).getCardViews()) {
			cv.setOnMouseEntered(e -> {
				cv.hover(true);
				String s = "";
				if (cv.getCard() instanceof Standard) {
					s += ((Standard) cv.getCard()).getRank() + " | "
							+ ((Standard) cv.getCard()).getSuit() + " | ";
				}
				s += cv.getCard().getDescription();
				detailsView.setDetails(s);
			});
			cv.setOnMouseExited(e -> {
				cv.hover(false);
				detailsView.setDetails("");
			});
		}
	}

	public void initPlayButton() {
		Image img = new Image("playButton.png");
		PlayButton = new ImageView(img);
		PlayButton.setPreserveRatio(true);

		// Set preferred base size
		double baseScaleX = 0.4 * ratioScreenWidth;
		double baseScaleY = 0.4 * ratioScreenHeight;
		PlayButton.setFitWidth(200 * ratioScreenWidth); // Or img.getWidth() *
														// scale
		PlayButton.setFitHeight(200 * ratioScreenHeight);

		// Subtle glow effect
		DropShadow glow = new DropShadow(25, Color.web("#ffffff40"));
		PlayButton.setEffect(glow);

		// Game timer logic
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100),
				e -> {
					if (!game.canPlayTurn()
							&& game.getActivePlayerColour() == game
									.getPlayers().get(0).getColour()) {
						cleanFirePitFromNull();
						game.endPlayerTurn();
						playCPU();
					}
				}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();

		// Hover animation with scale transition
		ScaleTransition hoverIn = new ScaleTransition(Duration.millis(150),
				PlayButton);
		hoverIn.setToX(1.15);
		hoverIn.setToY(1.15);

		ScaleTransition hoverOut = new ScaleTransition(Duration.millis(150),
				PlayButton);
		hoverOut.setToX(1.0);
		hoverOut.setToY(1.0);

		PlayButton.setOnMouseEntered(e -> hoverIn.playFromStart());
		PlayButton.setOnMouseExited(e -> hoverOut.playFromStart());

		PlayButton.setOnMouseClicked(e -> {
			playAll();

			Circle ripple = new Circle(0, Color.web("#ffffff60"));
			ripple.setCenterX(PlayButton.getFitWidth() / 2);
			ripple.setCenterY(PlayButton.getFitHeight() / 2);
			ripple.setRadius(0);

			Timeline rippleAnim = new Timeline(new KeyFrame(Duration.ZERO,
					new KeyValue(ripple.radiusProperty(), 0), new KeyValue(
							ripple.opacityProperty(), 1.0)),
			// change this time
					new KeyFrame(Duration.millis(100), new KeyValue(ripple
							.radiusProperty(), 60), new KeyValue(ripple
							.opacityProperty(), 0)));
			rippleAnim.setOnFinished(ev -> {
				((Pane) PlayButton.getParent()).getChildren().remove(ripple);
			});

			((Pane) PlayButton.getParent()).getChildren().add(ripple);
			ripple.toBack();
			rippleAnim.play();
		});

	}

	public void printMarbles() {
		for (Player p : game.getPlayers()) {
			System.out.println(p.getName());
			for (int i = 0; i < game.getBoard().getTrack().size(); i++) {
				Cell c = game.getBoard().getTrack().get(i);
				Marble m = c.getMarble();
				if (m != null && m.getColour() == p.getColour())
					System.out.print("track_" + i + " ");
			}
			ArrayList<SafeZone> safeZones = game.getBoard().getSafeZones();
			SafeZone sfCur = null;
			for (SafeZone sf : safeZones) {
				if (sf.getColour() == p.getColour())
					sfCur = sf;
			}
			for (int i = 0; i < sfCur.getCells().size(); i++) {
				Marble m = sfCur.getCells().get(i).getMarble();
				if (m != null)
					System.out.print("safe_" + i + " ");
			}
			System.out.println();
		}
	}

	public void playAll() {

		boolean done = false;
		try {
			System.out.println("selectd card is "
					+ game.getPlayers().get(0).getSelectedCard().getName());
			playPlayer();
			done = true;
		} catch (Exception ex) {
			Card selected = game.getPlayers().get(0).getSelectedCard();
			game.deselectAll();
			System.out.println(ex.getMessage());

			if (selected == null) {
				showExceptionWindow(ex.getMessage());

			} else {
				boolean discard = makeExceptionWindowDiscardCard(ex
						.getMessage());
				if (discard) {
					game.getPlayers().get(0).getHand().remove(selected);
					game.getFirePit().add(selected);
					cleanFirePitFromNull();
					game.endPlayerTurn();
					this.refresh();
					done = true;
				}
			}
		}
		((GameView) this.getScene().getRoot()).refresh();
		if (done) {
			playCPU();
			PlayButton.setDisable(true);
		}
		System.out.println(game.checkWin() + "------");
	}

	public void playPlayer() throws Exception {
		ArrayList<Marble> selectedMarbles = new ArrayList<>();
		if (game.canPlayTurn()) {
			// to select marbles at the real game model
			ArrayList<CellView> cellViewsOriginal = boardAndHomeView.boardView
					.getTrackView();
			ArrayList<CellView>[] safeZoneViews = boardAndHomeView.boardView
					.getSafeZoneView();
			ArrayList<CellView> cellViews = new ArrayList<CellView>();
			for (int i = 0; i < cellViewsOriginal.size(); i++)
				cellViews.add(cellViewsOriginal.get(i));
			for (int i = 0; i < safeZoneViews.length; i++) {
				for (CellView cv : safeZoneViews[i])
					cellViews.add(cv);
			}

			for (CellView cv : cellViews) {
				if (cv.getMarbleView().getMarble() != null) {
					if (cv.getMarbleView().selected) {
						game.selectMarble(cv.getMarbleView().getMarble());
						selectedMarbles.add(cv.getMarbleView().getMarble());
					}
					cv.getMarbleView().resetSelect();
				}
			}

			// handle 7 card
			Card selected = game.getPlayers().get(0).getSelectedCard();
			if (selected instanceof Seven && selectedMarbles.size() == 2) {
				game.getBoard().setSplitDistance(handle7Window());
			}
			game.playPlayerTurn();
			if (efficient)
				refresh();
			else
				draw();

		}
		cleanFirePitFromNull();
		game.endPlayerTurn();
		game.deselectAll();
		if (efficient)
			refresh();
		else
			draw();

		printMarbles();
	}

	public void playCPU() {
		if (efficient)
			refresh();
		else
			draw();
		Timeline replay = new Timeline(new KeyFrame(Duration.seconds(3),
				ev -> {

					if (game.canPlayTurn()) {
						try {
							game.playPlayerTurn();
						} catch (Exception exc) {
							showExceptionWindow(exc.getMessage());
						}

					}
					cleanFirePitFromNull();
					game.endPlayerTurn();
					if (efficient)
						refresh();
					else
						draw();
					printMarbles();

					System.out.println("");
					System.out.println("Current hands:");
					for (int i = 0; i < game.getPlayers().size(); i++) {
						Player p = game.getPlayers().get(i);
						System.out.print(p.getName() + ": ");
						for (Card c : p.getHand()) {
							System.out.print(c.getName() + " ");
						}
						System.out.println();
					}
				}));
		replay.setCycleCount(3);
		replay.setOnFinished(e -> {
			PlayButton.setDisable(false);
		});

		replay.play();
	}

	public void draw() {
		this.getChildren().clear();
		
		boardAndHomeView = new BoardAndHomeView(game.getPlayers(),
				game.getBoard(), game);
		Image logo = new Image("cardss/background3.jpg");
		ImageView logoView = new ImageView(logo);
		logoView.setFitWidth(1980 * ratioScreenWidth);
		logoView.setFitHeight(1080 * ratioScreenHeight);
		logoView.setPreserveRatio(false); // important: disables maintaining
											// original aspect ratio
		handsView = new HandsView(game);
		handsView.setMaxSize(1300 * ratioScreenWidth, 1000 * ratioScreenHeight);
		
		boardAndHomeView.setMaxSize(500 * ratioScreenWidth,
				500 * ratioScreenHeight);
		playerViews = new PlayerViews(game);
		playerViews
				.setMaxSize(1100 * ratioScreenWidth, 900 * ratioScreenHeight);

		firePitView = new FirePitView(game);

		deckView = new DeckView(game);
		deckView.setMaxSize(500 * ratioScreenWidth, 500 * ratioScreenHeight);

		
		// what i  add 
		int startx = 912;
		int starty = 827;
		int step = 22;
		int strokeWidth = 3;
		int r = 9;
		boardView2 = new boardView2(game, game.getBoard(), 9 );
		boardView2.initTrackCells(startx, starty, step, r);
		boardView2.initHomecells(step, r, strokeWidth);
		boardView2.initSafeZone(step, r, strokeWidth);

		marblesView = new MarblesView(game, boardView2);
		marblesView.update();
		
		this.getChildren().addAll(logoView, detailsView, playerViews,
				handsView, boardAndHomeView,   boardView2 ,  PlayButton, firePitView, deckView );

		this.getHandsView().getHands().get(0).canPlayEffect();
		
		
	
		
		
		// Insets(top, right, bottom, left)
		StackPane.setAlignment(boardAndHomeView, Pos.CENTER);
		StackPane.setAlignment(handsView, Pos.CENTER);
		StackPane.setAlignment(playerViews, Pos.CENTER);
		StackPane.setAlignment(PlayButton, Pos.BOTTOM_RIGHT);
		StackPane.setMargin(PlayButton, new Insets(0, 250, 75, 0));
		StackPane.setAlignment(detailsView, Pos.TOP_LEFT);
		StackPane.setAlignment(deckView, Pos.CENTER_LEFT);

	}

	public void refresh() {
		for (Cell cell : game.getBoard().getTrack()) {
			System.out.print(cell.isTrap() ? "1" : "0");
		}
		System.out.println();
		boardAndHomeView.refresh();
		handsView.refresh();
		// homesView.refresh();
		playerViews.refresh();
		firePitView.refresh();
		handleSelectedCards();
		detailsView.refresh();
		this.getHandsView().getHands().get(0).canPlayEffect();
	}

	public void showExceptionWindow(String message) {
		// 1) Create a new window (Stage)
		Stage dialog = new Stage(StageStyle.UNDECORATED);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.initOwner(this.getScene().getWindow());
		dialog.setResizable(false);

		// 2) Build the content
		Label lbl = new Label(message);
		lbl.setWrapText(true);
		lbl.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		lbl.setTextFill(Color.web("#0D2B3E"));
		lbl.setAlignment(Pos.CENTER);

		Label lbl2 = new Label("Please press Continue to proceed.");
		lbl2.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
		lbl2.setTextFill(Color.web("#0D2B3E"));

		Button continueBtn = new Button("Continue");
		continueBtn.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
		continueBtn.setOnAction(e -> dialog.close());

		HBox buttons = new HBox(15, continueBtn);
		buttons.setAlignment(Pos.CENTER);

		VBox root = new VBox(20, lbl, lbl2, buttons);
		root.setPadding(new Insets(20));
		root.setAlignment(Pos.CENTER);
		root.setBackground(new Background(new BackgroundFill(
				new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
						new Stop(0, Color.web("#4A90E2")), // Medium blue
						new Stop(1, Color.web("#D0E6FF")) // Light blue
				), new CornerRadii(10), Insets.EMPTY)));

		Scene scene = new Scene(root);

		// 3) Keyboard shortcuts
		scene.setOnKeyPressed(evt -> {
			if (evt.getCode() == KeyCode.ENTER
					|| evt.getCode() == KeyCode.ESCAPE) {
				dialog.close();
			}
		});

		// 4) Show the dialog
		dialog.setScene(scene);
		dialog.centerOnScreen();
		dialog.showAndWait();
	}

	public boolean makeExceptionWindowDiscardCard(String message) {
		// To store the user choice
		final boolean[] discardCard = { false };

		// 1) Create a new window (Stage)
		Stage dialog = new Stage(StageStyle.UNDECORATED);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.initOwner(this.getScene().getWindow());
		dialog.setResizable(false);

		// 2) Build the content
		Label lbl = new Label(message);
		lbl.setWrapText(true);
		lbl.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		lbl.setTextFill(Color.DARKRED);
		lbl.setAlignment(Pos.CENTER);

		Label lbl2 = new Label("Do you want to discard this card?");
		lbl2.setFont(Font.font("Arial", FontWeight.NORMAL, 12));

		lbl.setTextFill(Color.web("#0D2B3E")); // Dark navy blue
		lbl2.setTextFill(Color.web("#0D2B3E"));

		Button continueBtn = new Button("Continue");
		continueBtn.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
		continueBtn.setOnAction(e -> {
			discardCard[0] = false;
			dialog.close();
		});

		Button discardBtn = new Button("Discard Card");
		discardBtn.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
		discardBtn.setOnAction(e -> {
			discardCard[0] = true;
			dialog.close();
		});

		HBox buttons = new HBox(15, continueBtn, discardBtn);
		buttons.setAlignment(Pos.CENTER);

		VBox root = new VBox(20, lbl, lbl2, buttons);
		root.setPadding(new Insets(20));
		root.setAlignment(Pos.CENTER);
		root.setBackground(new Background(new BackgroundFill(Color
				.web("#FFF5F5"), new CornerRadii(10), Insets.EMPTY)));

		Scene scene = new Scene(root);

		// 3) Keyboard shortcuts
		scene.setOnKeyPressed(evt -> {
			if (evt.getCode() == KeyCode.ENTER) {
				discardCard[0] = false;
				dialog.close();
			} else if (evt.getCode() == KeyCode.D) {
				discardCard[0] = true;
				dialog.close();
			} else if (evt.getCode() == KeyCode.ESCAPE) {
				discardCard[0] = false;
				dialog.close();
			}
		});
		root.setBackground(new Background(new BackgroundFill(Color
				.web("#FFF5F5"), new CornerRadii(10), Insets.EMPTY)));
		root.setBackground(new Background(new BackgroundFill(
				new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
						new Stop(0, Color.web("#4A90E2")), // Medium blue
						new Stop(1, Color.web("#D0E6FF")) // Light blue
				), new CornerRadii(10), Insets.EMPTY)));

		// 4) Show it centered over your main window
		dialog.setScene(scene);
		dialog.centerOnScreen();
		dialog.showAndWait();

		return discardCard[0]; // true if user chose "Discard Card"
	}

	public int handle7Window() {
		// we'll stash the result here
		final int[] choice = { 1 };

		// 1) Build the modal stage
		Stage dialog = new Stage(StageStyle.UNDECORATED);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.initOwner(this.getScene().getWindow());
		dialog.setResizable(false);

		// 2) Create and style the prompt label
		Label prompt = new Label("Roll the die: pick a number 1–6");
		prompt.setFont(Font.font("ArcadeClassic", FontWeight.BOLD, 20));
		prompt.setTextFill(Color.web("#222222"));

		// 3) Create the combo-box
		ComboBox<Integer> combo = new ComboBox<>(
				FXCollections.observableArrayList(1, 2, 3, 4, 5, 6));
		combo.setValue(1);
		combo.setStyle("-fx-font-family: 'ArcadeClassic';"
				+ "-fx-font-weight: bold;" + "-fx-font-size: 18px;"
				+ "-fx-background-radius: 5;" + "-fx-border-radius: 5;"
				+ "-fx-border-color: #333333;" + "-fx-border-width: 2;");

		// 4) OK button
		Button ok = new Button("OK");
		ok.setFont(Font.font("ArcadeClassic", FontWeight.BOLD, 18));
		ok.setTextFill(Color.WHITE);
		ok.setBackground(new Background(new BackgroundFill(
				Color.web("#4A90E2"), new CornerRadii(5), Insets.EMPTY)));
		ok.setOnAction(e -> {
			choice[0] = combo.getValue();
			dialog.close();
		});

		HBox controls = new HBox(10, combo, ok);
		controls.setAlignment(Pos.CENTER);

		// 5) Put it all in a VBox with a gradient background and rounded box
		VBox root = new VBox(20, prompt, controls);
		root.setPadding(new Insets(20));
		root.setAlignment(Pos.CENTER);
		root.setBackground(new Background(new BackgroundFill(
				new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
						new Stop(0, Color.web("#FFF5E5")), new Stop(1, Color
								.web("#FFD88C"))), new CornerRadii(10),
				Insets.EMPTY)));
		root.setBorder(new Border(new BorderStroke(Color.web("#333333"),
				BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(
						2))));

		// 6) Show it
		Scene scene = new Scene(root);
		dialog.setScene(scene);
		dialog.centerOnScreen();
		dialog.showAndWait();

		return choice[0];
	}

	public static Color toFxColor(Colour c) {
		switch (c) {
		case GREEN:
			return Color.GREEN;
		case RED:
			return Color.RED;
		case YELLOW:
			return Color.YELLOW;
		case BLUE:
			return Color.BLUE;
		default:
			// fallback, though all enum values are covered
			return Color.BLACK;
		}
	}

	public SequentialTransition showDimmedMessage(String message,
			long visibleMillis) {
		// 1) Create a transparent Pane to hold overlay content
		Pane overlayPane = new Pane();
		overlayPane.setPickOnBounds(false); // Let clicks go through
		overlayPane.prefWidthProperty().bind(widthProperty());
		overlayPane.prefHeightProperty().bind(heightProperty());

		// 2) Full-screen dark rectangle (mask)
		Rectangle mask = new Rectangle();
		mask.setFill(Color.web("#000000", 0.6)); // 60% opaque black
		mask.widthProperty().bind(widthProperty());
		mask.heightProperty().bind(heightProperty());
		mask.setMouseTransparent(true);

		// 3) Centered label
		Label lbl = new Label(message);
		lbl.setTextFill(Color.WHITE);
		lbl.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
		lbl.setMouseTransparent(true);

		// 4) Bind label position AFTER scene is rendered
		Platform.runLater(() -> {
			lbl.layoutXProperty().bind(
					widthProperty().subtract(lbl.widthProperty()).divide(2));
			lbl.layoutYProperty().bind(
					heightProperty().subtract(lbl.heightProperty()).divide(2));
		});

		// 5) Add everything to overlay pane, then to this parent
		overlayPane.getChildren().addAll(mask, lbl);
		this.getChildren().add(overlayPane);

		// 6) Transitions (fade in, pause, fade out)
		FadeTransition inMask = new FadeTransition(Duration.millis(300), mask);
		FadeTransition inLabel = new FadeTransition(Duration.millis(300), lbl);
		inMask.setFromValue(0);
		inMask.setToValue(0.6);
		inLabel.setFromValue(0);
		inLabel.setToValue(1);

		PauseTransition wait = new PauseTransition(
				Duration.millis(visibleMillis));

		FadeTransition outMask = new FadeTransition(Duration.millis(300), mask);
		FadeTransition outLabel = new FadeTransition(Duration.millis(300), lbl);
		outMask.setFromValue(0.6);
		outMask.setToValue(0);
		outLabel.setFromValue(1);
		outLabel.setToValue(0);

		outLabel.setOnFinished(e -> this.getChildren().remove(overlayPane));

		SequentialTransition seq = new SequentialTransition(
				new ParallelTransition(inMask, inLabel), wait,
				new ParallelTransition(outMask, outLabel));
		return seq;
	}

	// new method to add effect on the playable marbles

	// @Override
	public void onTrap() {
		Platform.runLater(() -> {
			String msg = "A marble fell into trap!";
			showDimmedMessage(msg, 1500).play();
		});
	}

	public HandsView getHandView() {
		return this.handsView;
	}

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

	public HandsView getHandsView() {
		return handsView;
	}

	public void setHandsView(HandsView handsView) {
		this.handsView = handsView;
	}

	public PlayerViews getPlayerViews() {
		return playerViews;
	}

	public void setPlayerViews(PlayerViews playerViews) {
		this.playerViews = playerViews;
	}

	public void cleanFirePitFromNull() {
		ArrayList<Card> firePit = game.getFirePit();
		firePit.removeIf(Objects::isNull);
	}

}