package controller;

import java.util.*;

import model.card.Card;
import model.player.Marble;
import model.player.Player;
import engine.Game;
import engine.board.Cell;
import exception.ActionException;
import exception.GameException;
import exception.InvalidMarbleException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            Game game = new Game("Adham");

            while (game.checkWin() == null) {
            	int playerIdx = -1;
    			for (int i = 0; i < 4; i++){
    				if (game.getPlayers().get(i).getColour() == game.getActivePlayerColour()){
    					playerIdx = i;
    					break;
    				}
    			}
				Player curr_palyer = game.getPlayers().get(playerIdx);


              
                System.out.println("Current hands:");
                for (int i = 0; i < game.getPlayers().size(); i++) {
                    Player p = game.getPlayers().get(i);
                    System.out.print(p.getName() + ": ");
                    for (Card c : p.getHand()) {
                        System.out.print(c.getName() + " ");
                    }
                    System.out.println();
                }
                System.out.println("Current palyer " +curr_palyer.getName()  );

                System.out.println("Select a card index (0-" + (curr_palyer.getHand().size() - 1) + "):");
                int cardIdx = sc.nextInt();
                Card selectedCard = curr_palyer.getHand().get(cardIdx);
                game.selectCard(selectedCard);

                ArrayList<Marble> actionableMarbles = game.getBoard().getActionableMarbles();
                System.out.println("Available marbles:");
                for (int i = 0; i < actionableMarbles.size(); i++) {
                    Marble m = actionableMarbles.get(i);
                    System.out.println(i + ": " + m.getColour());
                }

                System.out.println("How many marbles to select?");
                int marbleCount = sc.nextInt();
                ArrayList<Marble> selectedMarbles = new ArrayList<>();
                for (int i = 0; i < marbleCount; i++) {
                    System.out.println("Enter marble index:");
                    int idx = sc.nextInt();
                    selectedMarbles.add(actionableMarbles.get(idx));
                }

                for (Marble m : selectedMarbles) {
                    game.selectMarble(m);
                }

                try {
                    game.playPlayerTurn();
                } catch (GameException e) {
                    System.out.println("Error: " + e.getMessage());
                }

                game.endPlayerTurn();

                System.out.println("Board state:");
                for (Cell cell : game.getBoard().getTrack()) {
                    System.out.print(cell.getMarble() != null ? cell.getMarble().getColour().name().charAt(0) : ".");
                }
                System.out.println();
            }

            System.out.println("Winner: " + game.checkWin());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
