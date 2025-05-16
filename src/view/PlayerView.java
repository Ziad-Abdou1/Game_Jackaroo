package view;

import engine.Game;
import model.player.Player;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;

public class PlayerView extends GridPane{
	Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();
	
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
		refresh();

		
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
	private void refresh(){
		circle.setRadius(screenWidth/70);
		Image image = new Image(getClass().getResource("/user_icon.png").toExternalForm());
		circle.setFill(new ImagePattern(image));
		//circle.setFill(Color.BLACK);	
		StackPane wrapper1 = new StackPane(circle);
		wrapper1.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
		if (active()) {
			
			wrapper1.setStyle(
				    "-fx-border-color: yellow;" +
				    "-fx-border-width: 20;" 
				);
		}
        this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
		addNode(wrapper1,0,0);
		StackPane wrapper = new StackPane(name);

		// Optionally set size to fill the cell if needed
		wrapper.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
		addNode(wrapper,0,1);
	}
	
    public void addNode(Node node, int col, int row) {
        this.add(node, col, row);
    }
}
