public class Card {
    private String color; // Red, Blue, Green, Yellow, Wild
    private String value; // 0-9, Skip, Reverse, Draw Two, Wild, Wild Draw Four

    public Card(String color, String value) {
        this.color = color;
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return color + " " + value;
    }
}
