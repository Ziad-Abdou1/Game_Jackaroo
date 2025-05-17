package view;

import java.util.ArrayList;

import model.card.standard.*;
import model.card.*;
import model.player.Marble;
import model.player.Player;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
	int idx=0;
	

	public GameView(Game game){
		this.game = game;
		initPlayButton();
		draw();
	}
	
	public void initPlayButton(){
		Image img = new Image("playButton.png");
		PlayButton = new ImageView(img);
		PlayButton.setScaleX(0.4);
		PlayButton.setScaleY(0.4);
//		playerViews.getPlayerViews().get(0).setActive(true);
//		draw();
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
			if (!game.canPlayTurn() && game.getActivePlayerColour()==game.getPlayers().get(0).getColour()){
				game.endPlayerTurn();
				playCPU();
			}
		}));
		timeline.setCycleCount(Animation.INDEFINITE); // Repeat forever
		timeline.play();

	
		
		PlayButton.setOnMouseClicked(e -> {
			playAll();
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
	public void playAll() {
		try{
			playPlayer();
		System.out.println(game.getFirePit().size()+" "+game.getFirePit().get(game.getFirePit().size()-1).getName());
				
				playCPU();


		}catch (Exception ex){
			game.deselectAll();
			System.out.println(ex.getMessage());
			makeExceptionWindow(ex.getMessage());
		}
	
	}
	public void playPlayer() throws Exception{
		if (game.canPlayTurn()){
//			PauseTransition pause = new PauseTransition(Duration.seconds(1));
//			pause.setOnFinished(event -> {
//			    // this code runs 1 second later, on the JavaFX thread
//			});
//			pause.play();

//			MarbleView mv = this.getBoardView()
//                    .getCellToView()    // your Map<Cell,CellView>
//                    .get(modelCell)     // the Cell the marble *left*
//                    .getMarbleView();   // add a getter for this in CellView
			game.playPlayerTurn();
//			
//
//			CellView cv = this.getBoardView()
//			                      .getCellToView()
//			                      .get(targetCell);
			//Card store =firePitView.topCardView.getCard();
			draw();
			
//			playerViews.getPlayerViews().get(0).setActive(false);
		}
		
		game.endPlayerTurn();
		draw();
	}
	public void playCPU(){
		draw();
		Timeline replay = new Timeline(new KeyFrame(Duration.seconds(2), ev -> {
//	    	idx++;
//	    	playerViews.getPlayerViews().get(idx).setActive(true);
	    	if(game.canPlayTurn()){
		    	try {
					game.playPlayerTurn();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	
//		    	System.out.println(game.getFirePit().size()+" "+game.getFirePit().get(game.getFirePit().size()-1).getName());
		    	System.out.println(game.getActivePlayerColour());
	    	}
	    	game.endPlayerTurn();
	    	draw();
	    	//----------------------
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
	    })
	);
replay.setCycleCount(3);
replay.play();
	}
	
	public void draw(){
		this.getChildren().clear();
		boardView = new BoardView(game.getBoard(), game);
//		boardView.setMinSize(300, 300);
//		boardView.setMaxSize(300, 300);
		handsView = new HandsView(game);
		handsView.setMaxSize(1400, 1000);
		homesView = new HomesView(game.getPlayers(), game);
		homesView.setMaxSize(700, 700);
		playerViews = new PlayerViews(game);
		playerViews.setMaxSize(1100, 900);
//		playerViews.setStyle("-fx-background-color: green;");
//		for (Player p : game.getPlayers()){
//			System.out.println(p.getName()+": ");
//			for (Card card : p.getHand()){
//				System.out.println(card.getName());
//			}
//		}
		firePitView=new FirePitView(game);
		this.getChildren().addAll(playerViews,homesView,handsView, boardView,PlayButton,firePitView);
		StackPane.setAlignment(homesView, Pos.CENTER);
		StackPane.setAlignment(handsView, Pos.CENTER);
		StackPane.setAlignment(boardView, Pos.CENTER);
		StackPane.setAlignment(playerViews, Pos.CENTER);
		StackPane.setAlignment(PlayButton, Pos.BOTTOM_RIGHT);
		
//		this.getChildren().add(animationLayer);
//		this.setPadding(new Insets(10));
	}
	public void makeExceptionWindow(String message) {
	    // 1) Create a new window (Stage)
	    Stage dialog = new Stage(StageStyle.UNDECORATED);
	    dialog.initModality(Modality.APPLICATION_MODAL);
	    dialog.setResizable(false);

	    // 2) Build the content
	    Label lbl = new Label(message);
	    lbl.setWrapText(true);
	    lbl.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
	    lbl.setTextFill(Color.DARKRED);
	    lbl.setAlignment(Pos.CENTER);

	    Button ok = new Button("OK");
	    ok.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 12));
	    ok.setOnAction(e -> dialog.close());

	    VBox root = new VBox(20, lbl, ok);
	    root.setPadding(new Insets(20));
	    root.setAlignment(Pos.CENTER);
	    root.setBackground(new Background(new BackgroundFill(
	        Color.web("#FFF5F5"),  // very light red background
	        new CornerRadii(10),    // rounded corners
	        Insets.EMPTY)));

	    Scene scene = new Scene(root);
	    
	    // 3) Close on Enter or Esc
	    scene.setOnKeyPressed(evt -> {
	        if (evt.getCode() == KeyCode.ENTER || evt.getCode() == KeyCode.ESCAPE) {
	            dialog.close();
	        }
	    });

	    // 4) Show it centered over your main window
	    dialog.setScene(scene);
	    dialog.centerOnScreen();
	    dialog.showAndWait();
	}
	public HandsView getHandView(){return this.handsView;}
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
//    public void animateMove(MarbleView marble, CellView target) {
//        // compute start/end in GameView’s local coordinates
//        Bounds startScene = marble.localToScene(marble.getBoundsInLocal());
//        Bounds endScene   = target.localToScene(target.getBoundsInLocal());
//        Point2D startLocal = this.sceneToLocal(startScene.getMinX(), startScene.getMinY());
//        Point2D endLocal   = this.sceneToLocal(endScene.getMinX(),   endScene.getMinY());
//
//        // 1) detach marble out of its cell into the animation layer
//        Parent oldParent = marble.getParent();
//        ((Pane)oldParent).getChildren().remove(marble);
//        animationLayer.getChildren().add(marble);
//
//        // 2) place it at the exact starting spot
//        marble.setLayoutX(startLocal.getX());
//        marble.setLayoutY(startLocal.getY());
//
//        // 3) animate to the end spot
//        TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), marble);
//        tt.setToX(endLocal.getX() - startLocal.getX());
//        tt.setToY(endLocal.getY() - startLocal.getY());
//        tt.setOnFinished(e -> {
//            // 4) snap it back into the target cell
//            animationLayer.getChildren().remove(marble);
//            target.getChildren().add(marble);
//            marble.setTranslateX(0);
//            marble.setTranslateY(0);
//            marble.setLayoutX(0);
//            marble.setLayoutY(0);
//        });
//        tt.play();
//    }
	

	
	
}