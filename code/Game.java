import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players;
    private GroupOfCards deck;
    private Scoreboard scoreboard;
    private int currentRound;

    public Game(List<String> playerNames) {
        players = new ArrayList<>();
        for (String name : playerNames) {
            players.add(new Player(name));
        }
        deck = new GroupOfCards();
        scoreboard = new Scoreboard(players);
        currentRound = 0;
    }

    public void startGame() {
        deck.shuffle();
        currentRound = 1;
        System.out.println("Starting Uno Game - Round " + currentRound);

        // Deal 7 cards to each player
        for (Player p : players) {
            for (int i = 0; i < 7; i++) {
                p.drawCard(deck);
            }
            p.showHand();
        }
    }

    public void playRound() {
        System.out.println("\n--- Round " + currentRound + " ---");
        
        // Each player plays a card (for simplicity, first card in hand)
        List<Player> roundWinners = new ArrayList<>();
        Card highestCard = null;

        for (Player p : players) {
            Card played = p.playCard();
            System.out.println(p.getName() + " plays " + played);

            if (highestCard == null || compareCards(played, highestCard) > 0) {
                highestCard = played;
                roundWinners.clear();
                roundWinners.add(p);
            } else if (compareCards(played, highestCard) == 0) {
                roundWinners.add(p);
            }
        }

        if (roundWinners.size() == 1) {
            Player winner = roundWinners.get(0);
            System.out.println("Round winner: " + winner.getName());
            scoreboard.updateScore(winner, scoreboard.getScore(winner) + 1);
        } else {
            System.out.print("Round tied between: ");
            for (Player p : roundWinners) {
                System.out.print(p.getName() + " ");
                scoreboard.updateScore(p, scoreboard.getScore(p) + 1);
            }
            System.out.println();
        }

        scoreboard.displayScores();
        currentRound++;
    }

    public void declareWinner() {
        System.out.println("\nGame Over!");
        scoreboard.displayScores();

        int maxScore = -1;
        List<Player> winners = new ArrayList<>();
        for (Player p : players) {
            int score = scoreboard.getScore(p);
            if (score > maxScore) {
                maxScore = score;
                winners.clear();
                winners.add(p);
            } else if (score == maxScore) {
                winners.add(p);
            }
        }

        if (winners.size() == 1) {
            System.out.println("Winner is " + winners.get(0).getName() + " with score " + maxScore);
        } else {
            System.out.print("It's a tie between: ");
            for (Player p : winners) {
                System.out.print(p.getName() + " ");
            }
            System.out.println("with score " + maxScore);
        }
    }

    private int compareCards(Card c1, Card c2) {
        // Simple comparison: value order (numbers first), then color precedence
        // Assign numeric values to cards for comparison:

        int val1 = cardValueToInt(c1);
        int val2 = cardValueToInt(c2);

        return Integer.compare(val1, val2);
    }

    private int cardValueToInt(Card card) {
        // Map card values to int for comparison
        // Numbers 0-9 as is
        // Skip=20, Reverse=21, Draw Two=22, Wild=30, Wild Draw Four=31

        String val = card.getValue();
        try {
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            switch (val) {
                case "Skip": return 20;
                case "Reverse": return 21;
                case "Draw Two": return 22;
                case "Wild": return 30;
                case "Wild Draw Four": return 31;
                default: return 0;
            }
        }
    }
}
