package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DetailsView extends StackPane {
    private final Label detailsLabel;
    private static final double DEFAULT_MAX_WIDTH = 300;

    public DetailsView() {
        this(DEFAULT_MAX_WIDTH);
    }

    /**
     * @param maxWidth wrap text once it exceeds this many pixels
     */
    public DetailsView(double maxWidth) {
        // ——— Load custom font (fallback if missing) ———
        Font gameFont = Font.loadFont(
            getClass().getResourceAsStream("/fonts/ArcadeClassic.ttf"), 18
        );
        if (gameFont == null) {
            gameFont = Font.font("Verdana", FontWeight.BOLD, 18);
        }

        // ——— Create and style the label ———
        detailsLabel = new Label();
        detailsLabel.setFont(gameFont);
        detailsLabel.setMaxWidth(maxWidth);
        detailsLabel.setWrapText(true);
        detailsLabel.setTextFill(Color.web("#000000"));
        detailsLabel.setAlignment(Pos.CENTER_LEFT);

        // ——— Box styling ———
        // Background fill
        BackgroundFill bgFill = new BackgroundFill(
            Color.web("#FFFFCC"),    // pale yellow fill
            new CornerRadii(8),      // rounded corners
            Insets.EMPTY
        );
        // Border stroke
        BorderStroke borderStroke = new BorderStroke(
            Color.web("#333333"),    // dark grey border
            BorderStrokeStyle.SOLID,
            new CornerRadii(8),
            new BorderWidths(2)
        );

        setBackground(new Background(bgFill));
        setBorder(new Border(borderStroke));

        // ——— Layout ———
        setPadding(new Insets(12));       // padding inside the box
        setAlignment(Pos.CENTER_LEFT);
        getChildren().add(detailsLabel);
    }

    /**
     * Update the text shown in this DetailsView.
     * @param text the new detail string to display
     */
    public void setDetails(String text) {
        detailsLabel.setText(text);
    }

    /**
     * Change the wrap-threshold at runtime.
     */
    public void setWrapWidth(double maxWidth) {
        detailsLabel.setMaxWidth(maxWidth);
    }
}
