package view;

import engine.Game;
import model.Colour;
import model.player.Player;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.util.Duration;

public class PlayerView extends GridPane{
	Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();
	
    StackPane wrapper1;
    StackPane wrapper;
    StackPane wrapper2;
	private Game game;
	private Player player;
	private Circle circle;
	private Label name;
	private Label nextLabel;
	public PlayerView(Player player, Game game){
		this.game = game;
		this.player = player;
		circle = new Circle();
		name = new Label();
		name.setText(player.getName());
		nextLabel = new Label("Next");
		name.getStyleClass().add("player-name");
		nextLabel.getStyleClass().add("next-label");
		draw();

	}
	public boolean active(){
		return game.getActivePlayerColour()==player.getColour();
	}
	
	
	public boolean isNext(){
		Colour nxtColour = game.getNextPlayerColour();
        Player nxtPlayer=null;
        for (Player p : game.getPlayers()) {
            if (p.getColour() == nxtColour) {
                nxtPlayer = p;
                break;
            }
        }
        if (player==nxtPlayer) return true;
        return false;
	}
	
	public void refresh(){
		if (active()){
			activeEffect();
		}
		else {
		    wrapper1.setEffect(null);
		    wrapper1.getChildren().retainAll(circle);
		    wrapper1.setScaleX(1.0);
		    wrapper1.setScaleY(1.0);
		    wrapper1.setRotate(0);
		}		
		if (isNext()){
			wrapper2.setOpacity(1);
		}
		else{
			wrapper2.setOpacity(0);
		}
	}
	private void draw(){
		this.getChildren().clear();
		circle.setRadius(screenWidth/70);
		Image image = new Image(getClass().getResource("/user_icon.png").toExternalForm());
		//circle.setFill(new ImagePattern(image));
		circle.setFill(GameView.toFxColor(player.getColour()));	
		wrapper1 = new StackPane(circle);
		wrapper1.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
		if (active()) {
			activeEffect();
		}
        this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
		addNode(wrapper1,0,0);
		wrapper = new StackPane(name);

		// Optionally set size to fill the cell if needed
		wrapper.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
		addNode(wrapper,0,1);
		
		
		wrapper2 = new StackPane(nextLabel);
		wrapper2.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
		addNode(wrapper2,0,2);
		if (isNext()){
			wrapper2.setOpacity(1);
		}
		else{
			wrapper2.setOpacity(0);
		}
	}
	
	public void activeEffect(){
		//part 1 : image
		Image gif = new Image(getClass().getResource("/cardss/ff.png").toExternalForm());
	
		ImageView gifView = new ImageView(gif);
		gifView.setPreserveRatio(true);
		gifView.setFitWidth(100);

		wrapper1.getChildren().add(gifView);
		
		//part 2: glow around the player
		DropShadow glow = new DropShadow();
		glow.setColor(Color.GOLD);
		glow.setOffsetX(0);
		glow.setOffsetY(0);
		glow.setRadius(0);
		wrapper1.setEffect(glow);

		
		//part 3: a rotatin ring 
		Circle ring = new Circle(circle.getRadius() + 6);
		ring.setStroke(Color.LIME);
		ring.setStrokeWidth(3);
		ring.setFill(null);
		wrapper1.getChildren().add(ring);


	}
	
    public void addNode(Node node, int col, int row) {
        this.add(node, col, row);
    }
}
