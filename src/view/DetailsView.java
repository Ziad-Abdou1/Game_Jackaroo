package view;

import java.util.ArrayList;

import model.Colour;
import model.player.Player;
import engine.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DetailsView extends StackPane {
    private final Label nxtPlayerLabel;
    private final Label detailsLabel;
    private static final double DEFAULT_MAX_WIDTH = 300;
    private final Game game;

    public DetailsView(Game game) {
        this(game, DEFAULT_MAX_WIDTH);
    }

    public DetailsView(Game game, double maxWidth) {
        this.game = game;

        // Load game font or fallback
        Font gameFont = Font.loadFont(
            getClass().getResourceAsStream("/fonts/ArcadeClassic.ttf"), 18
        );
        if (gameFont == null) {
            gameFont = Font.font("Verdana", FontWeight.BOLD, 18);
        }

        // Next-player label style
        nxtPlayerLabel = new Label();
        nxtPlayerLabel.setFont(Font.font(gameFont.getFamily(), FontWeight.BOLD, 16));
        nxtPlayerLabel.setTextFill(Color.web("#333333"));
        nxtPlayerLabel.setAlignment(Pos.TOP_LEFT);

        // Details label style (same as before)
        detailsLabel = new Label();
        detailsLabel.setFont(gameFont);
        detailsLabel.setMaxWidth(maxWidth);
        detailsLabel.setWrapText(true);
        detailsLabel.setTextFill(Color.web("#000000"));
        detailsLabel.setAlignment(Pos.CENTER_LEFT);

        // Container VBox: next on top, details below
        VBox box = new VBox(6, nxtPlayerLabel, detailsLabel);
        box.setAlignment(Pos.TOP_LEFT);

        // Box styling
        BackgroundFill bgFill = new BackgroundFill(
            Color.web("#FFFFCC"), new CornerRadii(8), Insets.EMPTY
        );
        BorderStroke borderStroke = new BorderStroke(
            Color.web("#333333"), BorderStrokeStyle.SOLID,
            new CornerRadii(8), new BorderWidths(2)
        );
        setBackground(new Background(bgFill));
        setBorder(new Border(borderStroke));
        setPadding(new Insets(12));
        getChildren().add(box);

        // initialize
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
