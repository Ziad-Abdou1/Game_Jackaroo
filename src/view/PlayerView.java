package view;

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
	
	
	private Player player;
	private boolean active;
	private Circle circle;
	private Label name;
	public PlayerView(Player player){
		this.player = player;
		active = false;
		draw();
	}
	void setActive(boolean a){this.active = a;}
	void draw(){
		circle = new Circle();
		if (active) circle.setRadius(screenWidth/50);
		else circle.setRadius(screenWidth/70);
		Image image = new Image(getClass().getResource("/user_icon.png").toExternalForm());
		circle.setFill(new ImagePattern(image));
		//circle.setFill(Color.BLACK);
		name = new Label();
		name.setText(player.getName());
		
		addNode(circle,0,0);
		addNode(name,0,1);
		for (int i = 0; i < 10; i++){
			Circle c = new Circle();
			c.setRadius(screenWidth/70);
			c.setOpacity(0);
			addNode(c,i,0);
		}
		
        this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
		
	}
	
    public void addNode(Node node, int col, int row) {
        this.add(node, col, row);
    }
}
