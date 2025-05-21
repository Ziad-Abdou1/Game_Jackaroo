package view;

import java.util.ArrayList;

import model.Colour;
import model.player.Player;
import engine.Game;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DetailsView extends StackPane {
	private final Label nxtPlayerLabel;
    private final Label detailsLabel;
    private final Game game;

    public DetailsView(Game game) {
        this.game = game;
        Font gameFont = Font.loadFont(
            getClass().getResourceAsStream("/fonts/ArcadeClassic.ttf"), 18
        );
        if (gameFont == null) {
            gameFont = Font.font("Verdana", FontWeight.BOLD, 18);
        }

        nxtPlayerLabel = new Label();
        nxtPlayerLabel.setFont(Font.font(gameFont.getFamily(), FontWeight.BOLD, 16));
        nxtPlayerLabel.setTextFill(Color.WHITE);
        nxtPlayerLabel.setAlignment(Pos.TOP_LEFT);

        detailsLabel = new Label();
        detailsLabel.setFont(gameFont);
        detailsLabel.setTextFill(Color.LIGHTGRAY);
        detailsLabel.setWrapText(true);
        detailsLabel.setAlignment(Pos.TOP_LEFT);
        detailsLabel.setMaxWidth(250);

        VBox contentBox = new VBox(10, nxtPlayerLabel, detailsLabel);
        contentBox.setAlignment(Pos.TOP_LEFT);

        setBackground(new Background(new BackgroundFill(
            Color.rgb(20, 20, 20, 0.75),
            new CornerRadii(12),
            Insets.EMPTY
        )));
        setBorder(new Border(new BorderStroke(
            Color.GRAY,
            BorderStrokeStyle.SOLID,
            new CornerRadii(12),
            new BorderWidths(2)
        )));
        setEffect(new DropShadow(10, Color.BLACK));
        setPadding(new Insets(16));
        getChildren().add(contentBox);


        setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        // Initialize
        refresh();
    }

	/**
	 * Updates both labels based on current game state.
	 */
	public void refresh() {
		// update next-player
		Colour nxtColour = game.getNextPlayerColour();
		String nxtName = "";
		for (Player p : game.getPlayers()) {
			if (p.getColour() == nxtColour) {
				nxtName = p.getName();
				break;
			}
		}
		nxtPlayerLabel.setText("Next: " + nxtName);

		// detailsLabel text remains set via setDetails()
	}

	/**
	 * Set the main details text.
	 */
	public void setDetails(String text) {
		detailsLabel.setText(text);
		refresh();
	}

	/**
	 * Change wrap width at runtime.
	 */
	public void setWrapWidth(double maxWidth) {
		detailsLabel.setMaxWidth(maxWidth);
	}
}
