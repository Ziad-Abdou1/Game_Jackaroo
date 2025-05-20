package view;

import java.util.ArrayList;
import java.util.Objects;

import com.sun.javafx.scene.control.SelectedCellsMap;

import model.Colour;
import model.card.standard.*;
import model.card.*;
import model.player.Marble;
import model.player.Player;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;
import engine.Game;
import engine.board.Board;
import engine.board.Cell;
import engine.board.SafeZone;

public class GameView extends StackPane {
	Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	double screenWidth = screenBounds.getWidth();
	double screenHeight = screenBounds.getHeight();
	ImageView PlayButton;
	private Game game;
	private BoardView boardView;
	private HandsView handsView;
	private HomesView homesView;
	private PlayerViews playerViews;
	private FirePitView firePitView;
	int idx = 0;
	private boolean efficient = true;
	private DetailsView detailsView;
	private DeckView deckView;
	
	
	public GameView(Game game) {
		this.game = game;
		detailsView=new DetailsView(game);
		initPlayButton();
		draw();
		handleSelectedCards();
	}

	public void handleSelectedCards() {
		for (CardView cv : this.getHandsView().getHands().get(0).getCardViews()) {
//			cv.setOnMouseClicked(e -> {
//				try {
//					// redraw();
//					game.deselectAll();
//					game.selectCard(cv.getCard());
//					refresh();
//					System.out.println("card is selected");
//				} catch (Exception exc) {
//					System.out.println(exc.getMessage());
//					showExceptionWindow(exc.getMessage());
//				}
//			});
			cv.setOnMouseEntered(e -> {
				cv.hover(true);
				String s = "";
				if (cv.getCard() instanceof Standard){
					s += ((Standard)cv.getCard()).getRank()+" | " + ((Standard)cv.getCard()).getSuit() + " | ";
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
		PlayButton.setScaleX(0.4);
		PlayButton.setScaleY(0.4);

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

		PlayButton.setOnMouseClicked(e -> {
			playAll();
			Circle ripple = new Circle(0, Color.web("#ffffff50"));
			ripple.setCenterX(e.getX());
			ripple.setCenterY(e.getY());
			((Pane) PlayButton.getParent()).getChildren().add(ripple);

//			Timeline tl = new Timeline(new KeyFrame(Duration.ZERO,
//					new KeyValue(ripple.radiusProperty(), 0), new KeyValue(
//							ripple.opacityProperty(), 0.5)), new KeyFrame(
//					Duration.seconds(0.4), new KeyValue(
//							ripple.radiusProperty(), 100), new KeyValue(ripple
//							.opacityProperty(), 0)));
//			tl.setOnFinished(evt -> ((Pane) PlayButton.getParent())
//					.getChildren().remove(ripple));
//			tl.play();
//
//			ScaleTransition pulse = new ScaleTransition(Duration.seconds(1.2),
//					PlayButton);
//			pulse.setFromX(0.4);
//			pulse.setFromY(0.4);
//			pulse.setToX(0.45);
//			pulse.setToY(0.45);
//			pulse.setAutoReverse(true);
//			pulse.setCycleCount(Animation.INDEFINITE);
//			pulse.play();
		});
		PlayButton.setOnMouseEntered(e -> {
			PlayButton.setScaleX(0.5);
			PlayButton.setScaleY(0.5);
		});
		PlayButton.setOnMouseExited(e -> {
			PlayButton.setScaleX(0.4);
			PlayButton.setScaleY(0.4);
		});
	}
	public void printMarbles(){
		for(Player p: game.getPlayers()){
			System.out.println(p.getName());
			for(int i=0;i<game.getBoard().getTrack().size();i++){
				Cell c=game.getBoard().getTrack().get(i);
				Marble m=c.getMarble();
				if(m!=null && m.getColour()==p.getColour()) System.out.print("track_"+i+" ");
			}
			ArrayList<SafeZone> safeZones=game.getBoard().getSafeZones();
			SafeZone sfCur=null;
			for(SafeZone sf:safeZones){
				if(sf.getColour()==p.getColour()) sfCur=sf;
			}
			for(int i=0;i<sfCur.getCells().size();i++){
				Marble m=sfCur.getCells().get(i).getMarble();
				if(m!=null) System.out.print("safe_"+i+" ");
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
		 ((GameView)this.getScene().getRoot()).refresh();
		if (done) {
			playCPU();
			PlayButton.setDisable(true);
		}
		System.out.println(game.checkWin()+"------");
	}

	public void playPlayer() throws Exception {
		ArrayList<Marble> selectedMarbles = new ArrayList<>();
		if (game.canPlayTurn()) {
			//to select marbles at the real game model
			ArrayList<CellView> cellViewsOriginal = boardView.getTrackView();
			ArrayList<CellView>[] safeZoneViews = boardView.getSafeZoneView();
			ArrayList<CellView> cellViews=new ArrayList<CellView>();
			for(int i=0;i<cellViewsOriginal.size();i++) cellViews.add(cellViewsOriginal.get(i));
			for (int i = 0; i < safeZoneViews.length; i++){
				for (CellView cv : safeZoneViews[i]) cellViews.add(cv);
			}
			
			for (CellView cv : cellViews){
				if (cv.getMarbleView().getMarble()!=null){
					if (cv.getMarbleView().selected){
						game.selectMarble(cv.getMarbleView().getMarble());
						selectedMarbles.add(cv.getMarbleView().getMarble());
					}
					cv.getMarbleView().resetSelect();
				}
			}
			
			
			//handle 7 card
			Card selected=game.getPlayers().get(0).getSelectedCard();
			if(selected instanceof Seven && selectedMarbles.size()==2){
				game.getBoard().setSplitDistance(handle7Window());
			}
			game.playPlayerTurn();
			if (efficient)
				refresh();
			else
				draw();

		}
		
		//handle trap logic
		 ArrayList<Cell> trackOrigianl = game.getBoard().getTrack();
		 ArrayList<SafeZone> safeZones = game.getBoard().getSafeZones();
		 ArrayList<Cell> track=new ArrayList<Cell>();
		 for(int i=0;i<trackOrigianl.size();i++) track.add(trackOrigianl.get(i));
		 for(int i=0;i<safeZones.size();i++){
			 for(Cell cell:safeZones.get(i).getCells()){
				 track.add(cell);
			 }
		 }
		 int trap=0;
		 loop:for(Marble m:selectedMarbles){
			 boolean found=false;
			 for(Cell cell:track){
				 if(cell.getMarble()==m){
					break loop;
				 }
			 }
			 trap++;
		 }
		if(trap>0){
			showExceptionWindow(trap+" marbles fell into trap");
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
		boardView = new BoardView(game.getBoard(), game);

		handsView = new HandsView(game);
		handsView.setMaxSize(1300, 1000);
		homesView = new HomesView(game.getPlayers(), game);
		homesView.setMaxSize(700, 700);
		playerViews = new PlayerViews(game);
		playerViews.setMaxSize(1100, 900);

		firePitView = new FirePitView(game);
		
		deckView = new DeckView(game);
		deckView.setMaxSize(500, 500);
		
		this.getChildren().addAll(detailsView, playerViews, homesView, handsView, boardView,
				PlayButton, firePitView,deckView);
		StackPane.setAlignment(homesView, Pos.CENTER);
		StackPane.setAlignment(handsView, Pos.CENTER);
		StackPane.setAlignment(boardView, Pos.CENTER);
		StackPane.setAlignment(playerViews, Pos.CENTER);
		StackPane.setAlignment(PlayButton, Pos.BOTTOM_RIGHT);
		StackPane.setAlignment(detailsView, Pos.CENTER_LEFT);
		StackPane.setAlignment(deckView, Pos.CENTER_LEFT);

	}

	public void refresh() {
		for (Cell cell : game.getBoard().getTrack()) {
			System.out.print(cell.isTrap() ? "1" : "0");
		}
		System.out.println();
		boardView.refresh();
		handsView.refresh();
		homesView.refresh();
		playerViews.refresh();
		firePitView.refresh();
		handleSelectedCards();
		detailsView.refresh();
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

	public BoardView getBoardView() {
		return boardView;
	}

	public void setBoardView(BoardView boardView) {
		this.boardView = boardView;
	}

	public HandsView getHandsView() {
		return handsView;
	}

	public void setHandsView(HandsView handsView) {
		this.handsView = handsView;
	}

	public HomesView getHomesView() {
		return homesView;
	}

	public void setHomesView(HomesView homesView) {
		this.homesView = homesView;
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
	public int handle7Window() {
	    // we'll stash the result here
	    final int[] choice = {1};

	    // 1) Build the modal stage
	    Stage dialog = new Stage(StageStyle.UNDECORATED);
	    dialog.initModality(Modality.APPLICATION_MODAL);
	    dialog.setResizable(false);

	    // 2) Create and style the prompt label
	    Label prompt = new Label("Roll the die: pick a number 1–6");
	    prompt.setFont(Font.font("ArcadeClassic", FontWeight.BOLD, 20));
	    prompt.setTextFill(Color.web("#222222"));

	    // 3) Create the combo-box
	    ComboBox<Integer> combo = new ComboBox<>(
	        FXCollections.observableArrayList(1,2,3,4,5,6)
	    );
	    combo.setValue(1);
	    combo.setStyle(
	      "-fx-font-family: 'ArcadeClassic';" +
	      "-fx-font-weight: bold;" +
	      "-fx-font-size: 18px;" +
	      "-fx-background-radius: 5;" +
	      "-fx-border-radius: 5;" +
	      "-fx-border-color: #333333;" +
	      "-fx-border-width: 2;"
	    );

	    // 4) OK button
	    Button ok = new Button("OK");
	    ok.setFont(Font.font("ArcadeClassic", FontWeight.BOLD, 18));
	    ok.setTextFill(Color.WHITE);
	    ok.setBackground(new Background(new BackgroundFill(
	      Color.web("#4A90E2"), new CornerRadii(5), Insets.EMPTY
	    )));
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
	      new LinearGradient(
	        0,0,1,1,true,CycleMethod.NO_CYCLE,
	        new Stop(0, Color.web("#FFF5E5")),
	        new Stop(1, Color.web("#FFD88C"))
	      ),
	      new CornerRadii(10), Insets.EMPTY
	    )));
	    root.setBorder(new Border(new BorderStroke(
	      Color.web("#333333"), BorderStrokeStyle.SOLID,
	      new CornerRadii(10), new BorderWidths(2)
	    )));

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
	
	// public void animateMove(MarbleView marble, CellView target) {
	// // compute start/end in GameView’s local coordinates
	// Bounds startScene = marble.localToScene(marble.getBoundsInLocal());
	// Bounds endScene = target.localToScene(target.getBoundsInLocal());
	// Point2D startLocal = this.sceneToLocal(startScene.getMinX(),
	// startScene.getMinY());
	// Point2D endLocal = this.sceneToLocal(endScene.getMinX(),
	// endScene.getMinY());
	//
	// // 1) detach marble out of its cell into the animation layer
	// Parent oldParent = marble.getParent();
	// ((Pane)oldParent).getChildren().remove(marble);
	// animationLayer.getChildren().add(marble);
	//
	// // 2) place it at the exact starting spot
	// marble.setLayoutX(startLocal.getX());
	// marble.setLayoutY(startLocal.getY());
	//
	// // 3) animate to the end spot
	// TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5),
	// marble);
	// tt.setToX(endLocal.getX() - startLocal.getX());
	// tt.setToY(endLocal.getY() - startLocal.getY());
	// tt.setOnFinished(e -> {
	// // 4) snap it back into the target cell
	// animationLayer.getChildren().remove(marble);
	// target.getChildren().add(marble);
	// marble.setTranslateX(0);
	// marble.setTranslateY(0);
	// marble.setLayoutX(0);
	// marble.setLayoutY(0);
	// });
	// tt.play();
	// }

}