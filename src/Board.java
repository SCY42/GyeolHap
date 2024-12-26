import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Board {
    private ArrayList<Card> deck = new ArrayList<>(Arrays.asList(Card.getCardDeck()));
    private Card[] slots = new Card[9];

    public Board() {
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            int randIdx = random.nextInt(this.deck.size());
            this.slots[i] = this.deck.remove(randIdx);
            this.slots[i].setIndex(i);
        }
    }

    public void printBoard() {
        for (Card card: slots) {
            Card.Pattern pattern = card.getPattern();
            Card.Color color = card.getColor();
            Card.Background background = card.getBackground();

            System.out.println(pattern + " " + color + " " + background);
        }
    }

    private boolean isAnswer(Card c1, Card c2, Card c3) {
        boolean pattern = isAllSame(c1.getPattern(), c2.getPattern(), c3.getPattern())
            || isAllDifferent(c1.getPattern(), c2.getPattern(), c3.getPattern());
        boolean color = isAllSame(c1.getColor(), c2.getColor(), c3.getColor())
            || isAllDifferent(c1.getColor(), c2.getColor(), c3.getColor());
        boolean background = isAllSame(c1.getBackground(), c2.getBackground(), c3.getBackground())
            || isAllDifferent(c1.getBackground(), c2.getBackground(), c3.getBackground());
        return pattern && color && background;
    }

    private boolean isAllSame (Object o1, Object o2, Object o3) {
        return o1.equals(o2) && o2.equals(o3) && o3.equals(o1);
    }

    private boolean isAllDifferent (Object o1, Object o2, Object o3) {
        return !o1.equals(o2) && !o2.equals(o3) && !o3.equals(o1);
    }

    public ArrayList<int[]> findAnswers() {
        ArrayList<int[]> answers = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            for (int j = i+1; j < 9; j++) {
                for (int k = j+1; k < 9; k++) {
                    if (isAnswer(this.slots[i], this.slots[j], this.slots[k]))
                        answers.add(new int[]{i, j, k});
                }
            }
        }
        return answers;
    }

    public void printAnswers(ArrayList<int[]> answers) {
        for (int[] answer : answers) {
            System.out.println(Arrays.toString(answer));
        }
    }

    public Card[] getSlots() {
        return this.slots;
    }
}
