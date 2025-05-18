package view;

import engine.Game;
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
	private Game game;
	private Player player;
	private Circle circle;
	private Label name;
	public PlayerView(Player player, Game game){
		this.game = game;
		this.player = player;
		circle = new Circle();
		name = new Label();
		name.setText(player.getName());
		draw();

		
//		for (int i = 0; i < 10; i++){
//			Circle c = new Circle();
//			c.setRadius(screenWidth/70);
//			c.setOpacity(0);
//			addNode(c,i,0);
//		}
	}
	public boolean active(){
		return game.getActivePlayerColour()==player.getColour();
	}
	public void refresh(){
		if (active()){
			activeEffect();
		}
		else {
		    // Remove visual effects
		    wrapper1.setEffect(null);

		    // Stop any active animations (optional if you track them)

		    // Remove any extra children added during activeEffect
		    // Keep only the circle as the base image
		    wrapper1.getChildren().retainAll(circle);

		    // Reset scale and rotation
		    wrapper1.setScaleX(1.0);
		    wrapper1.setScaleY(1.0);
		    wrapper1.setRotate(0);
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

		// animate the glow radius
		Timeline pulse = new Timeline(
		    new KeyFrame(Duration.ZERO,    new KeyValue(glow.radiusProperty(), 0)),
		    new KeyFrame(Duration.seconds(0.5), new KeyValue(glow.radiusProperty(), 70)),
		    new KeyFrame(Duration.seconds(1),   new KeyValue(glow.radiusProperty(), 0))
		);
		pulse.setCycleCount(Animation.INDEFINITE);
		pulse.play();
		
		//part 3: a rotatin ring 
		Circle ring = new Circle(circle.getRadius() + 6);
		ring.setStroke(Color.LIME);
		ring.setStrokeWidth(3);
		ring.setFill(null);
		wrapper1.getChildren().add(ring);

		// rotate the ring continuously
		RotateTransition rot = new RotateTransition(Duration.seconds(2), ring);
		rot.setByAngle(360);
		rot.setCycleCount(Animation.INDEFINITE);
		rot.play();
		
		//part 4: scale continuously when active
		ScaleTransition bounce = new ScaleTransition(Duration.seconds(0.5), wrapper1);
		bounce.setFromY(1.0);
		bounce.setToY(1.2);
		bounce.setFromX(1.0);
		bounce.setToX(1.2);
		bounce.setAutoReverse(true);
		bounce.setCycleCount(Animation.INDEFINITE);
		bounce.play();
	}
	
    public void addNode(Node node, int col, int row) {
        this.add(node, col, row);
    }
}
