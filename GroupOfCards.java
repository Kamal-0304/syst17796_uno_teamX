import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class GroupOfCards {
    private List<Card> cards;

    public GroupOfCards() {
        cards = new ArrayList<>();
        String[] colors = {"Red", "Yellow", "Green", "Blue"};
        // Numbers 0-9, two of each number except 0
        for (String color : colors) {
            // One zero per color
            cards.add(new Card(color, "0"));
            // Two of each number 1-9 per color
            for (int i = 1; i <= 9; i++) {
                cards.add(new Card(color, Integer.toString(i)));
                cards.add(new Card(color, Integer.toString(i)));
            }
            // Add action cards: Skip, Reverse, Draw Two (two each per color)
            String[] actionCards = {"Skip", "Reverse", "Draw Two"};
            for (String action : actionCards) {
                cards.add(new Card(color, action));
                cards.add(new Card(color, action));
            }
        }
        // Add Wild and Wild Draw Four cards (4 each)
        for (int i = 0; i < 4; i++) {
            cards.add(new Card("Wild", "Wild"));
            cards.add(new Card("Wild", "Wild Draw Four"));
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new NoSuchElementException("No cards left to draw.");
        }
        return cards.remove(0);
    }

    public int remainingCards() {
        return cards.size();
    }
}
