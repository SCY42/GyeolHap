import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;


public class BoardPanel extends JPanel {
    static HashMap<Card.Color, Color> colorMap = new HashMap<>();
    static HashMap<Card.Background, Color> backgroundMap = new HashMap<>();
    private Board board;

    Font fontBig = new Font("Arial", Font.PLAIN, 50);
    Font fontSmall = new Font("Arial", Font.PLAIN, 30);
    FontMetrics fm;

    TextField textField;
    Button button;

    ArrayList<int[]> foundAnswers = new ArrayList<>();

    static {
        colorMap.put(Card.Color.RED, Color.RED);
        colorMap.put(Card.Color.GREEN, Color.GREEN);
        colorMap.put(Card.Color.BLUE, Color.BLUE);

        backgroundMap.put(Card.Background.WHITE, Color.WHITE);
        backgroundMap.put(Card.Background.GRAY, Color.GRAY);
        backgroundMap.put(Card.Background.BLACK, Color.BLACK);
    }

    public BoardPanel(Board board) {
        this.board = board;
        this.setLayout(null);
        setPreferredSize(new Dimension(1200,900));
        fm = this.getFontMetrics(fontBig);

        textField = new TextField(fontBig);
        this.add(textField);

        button = new Button(this, textField);
        this.add(button);
    }

    @Override
    public void paintComponent(Graphics g) {
        for (Card card : this.board.getSlots()) {
            switch (card.getPattern()) {
                case SQUARE:
                    paintSquareCard(g, card);
                    break;
                case TRIANGLE:
                    paintTriangleCard(g, card);
                    break;
                case CIRCLE:
                    paintCircleCard(g, card);
                    break;
            }
        }
        g.setColor(Color.BLACK);
        g.setFont(fontBig);
        int height = 100;
        for (int[] answer : this.foundAnswers) {
            int ans1 = answer[0] + 1;
            int ans2 = answer[1] + 1;
            int ans3 = answer[2] + 1;
            String ans = ans1 + " " + ans2 + " " + ans3;
            drawCenteredString(g, ans, height);
            height += 100;
        }
    }

    private void paintSquareCard(Graphics g, Card card) {
        int[] pos = card.getPosition();
        int posX = pos[0];
        int posY = pos[1];

        g.setColor(backgroundMap.get(card.getBackground()));
        g.fillRect(posX, posY, 300, 300);
        g.setColor(colorMap.get(card.getColor()));
        g.fillRect(posX+50, posY+50, 200, 200);

        g.setColor(Color.CYAN);
        g.setFont(fontSmall);
        g.drawString(Integer.toString(card.getIndex()+1), posX+10, posY+30);
    }

    private void paintTriangleCard(Graphics g, Card card) {
        int[] pos = card.getPosition();
        int posX = pos[0];
        int posY = pos[1];

        g.setColor(backgroundMap.get(card.getBackground()));
        g.fillRect(posX, posY, 300, 300);
        g.setColor(colorMap.get(card.getColor()));
        int[] xPoints = { posX+50, posX+150, posX+250 };
        int[] yPoints = { posY+250, posY+50, posY+250 };
        g.fillPolygon(xPoints, yPoints, 3);

        g.setColor(Color.CYAN);
        g.setFont(fontSmall);
        g.drawString(Integer.toString(card.getIndex()+1), posX+10, posY+30);
    }

    private void paintCircleCard(Graphics g, Card card) {
        int[] pos = card.getPosition();
        int posX = pos[0];
        int posY = pos[1];

        g.setColor(backgroundMap.get(card.getBackground()));
        g.fillRect(posX, posY, 300, 300);
        g.setColor(colorMap.get(card.getColor()));
        g.fillOval(posX+50, posY+50, 200, 200);

        g.setColor(Color.CYAN);
        g.setFont(fontSmall);
        g.drawString(Integer.toString(card.getIndex()+1), posX+10, posY+30);
    }

    private void drawCenteredString(Graphics g, String str, int height) {
        int width = fm.stringWidth(str);
        g.drawString(str, 900 + (300 - width) / 2, height);
    }

    public Board getBoard() {
        return board;
    }

    public void updateFoundAnswers(int[] foundAnswer) {
        this.foundAnswers.add(foundAnswer);
        Graphics g = this.getGraphics();
        this.paintComponent(g);
    }

    public ArrayList<int[]> getFoundAnswers() {
        return foundAnswers;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("결! 합!");
        BoardPanel boardPanel = new BoardPanel(new Board());

//        boardPanel.getBoard().printAnswers(boardPanel.getBoard().findAnswers());

        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(100, 200);
        frame.add(boardPanel); // JPanel 추가
        frame.pack();
        frame.setVisible(true);
    }
}
