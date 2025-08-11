import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int score;
    private List<Card> hand;  // Player's current hand

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void drawCard(GroupOfCards deck) {
        Card drawn = deck.drawCard();
        hand.add(drawn);
        System.out.println(name + " drew: " + drawn);
    }

    public void showHand() {
        System.out.println(name + "'s hand: ");
        for (Card card : hand) {
            System.out.println("  " + card);
        }
    }

    // For this simple game, we can pick the first card to "play"
    public Card playCard() {
        if (hand.isEmpty()) {
            return null;
        }
        return hand.remove(0);
    }
}
