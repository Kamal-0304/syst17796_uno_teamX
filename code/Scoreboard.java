import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scoreboard {
    private Map<Player, Integer> scores;

    public Scoreboard(List<Player> players) {
        scores = new HashMap<>();
        for (Player p : players) {
            scores.put(p, 0);
        }
    }

    public void updateScore(Player player, int points) {
        scores.put(player, points);
        player.setScore(points);
    }

    public int getScore(Player player) {
        return scores.getOrDefault(player, 0);
    }

    public void displayScores() {
        System.out.println("Current Scores:");
        for (Map.Entry<Player, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey().getName() + ": " + entry.getValue());
        }
    }
}
