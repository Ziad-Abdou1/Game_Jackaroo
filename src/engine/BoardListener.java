package engine;
// in engine/Game.java (or engine/GameListener.java)
public interface BoardListener {
    /** Called whenever marbles fall into a trap. */
    void onTrap();

    /** You can add more callbacks for other events: */
    // void onTurnEnded(Colour nextPlayer);
    // void onCardPlayed(Card card);
}
