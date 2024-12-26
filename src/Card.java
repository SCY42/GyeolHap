public class Card {
    private final Pattern pattern;
    private final Color color;
    private final Background background;
    private int index;

    static Card[] deck = getCardDeck();

    public enum Pattern {
        CIRCLE,
        SQUARE,
        TRIANGLE
    }

    public enum Color {
        RED,
        GREEN,
        BLUE
    }

    public enum Background {
        BLACK,
        GRAY,
        WHITE
    }

    public Card(final Pattern pattern, final Color color, final Background background) {
        this.pattern = pattern;
        this.color = color;
        this.background = background;
    }

    public static Card[] getCardDeck() {
        Card[] deck = new Card[27];
        int i = 0;

        for (Pattern pattern: Pattern.values()) {
            for (Color color: Color.values()) {
                for (Background background: Background.values()) {
                    deck[i] = new Card(pattern, color, background);
                    i++;
                }
            }
        }
        return deck;
    }

    public static void printCardDeck() {
        for (Card card: deck) {
            Pattern pattern = card.pattern;
            Color color = card.color;
            Background background = card.background;

            System.out.println(pattern + " " + color + " " + background);
        }
    }

    public Pattern getPattern() {
        return this.pattern;
    }

    public Color getColor() {
        return this.color;
    }

    public Background getBackground() {
        return this.background;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(final int index) {
        this.index = index;
    }

    public int[] getPosition() {
        int posX = 300 * (this.getIndex() % 3);
        int posY = 300 * (this.getIndex() / 3);
        return new int[]{ posX, posY };
    }
}
