import java.util.*;

public class UnoGame {
    private Deck deck;
    private List<Player> players;

    public UnoGame(String[] playerNames) {
        deck = new Deck();
        players = new ArrayList<>();

        for (String name : playerNames) {
            Player p = new Player(name);
            for (int i = 0; i < 7; i++) {
                p.drawCard(deck);
            }
            players.add(p);
        }
    }

    public void startGame() {
        for (Player p : players) {
            p.showHand();
        }
    }
}
