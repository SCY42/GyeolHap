import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class BoardPanel extends JPanel {
    static HashMap<Card.Color, Color> colorMap = new HashMap<>();
    static HashMap<Card.Background, Color> backgroundMap = new HashMap<>();
    private Board board;

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
        setPreferredSize(new Dimension(900,900));
    }

    @Override
    protected void paintComponent(Graphics g) {
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
    }

    private void paintSquareCard(Graphics g, Card card) {
        int[] pos = card.getPosition();
        int posX = pos[0];
        int posY = pos[1];

        g.setColor(backgroundMap.get(card.getBackground()));
        g.fillRect(posX, posY, 300, 300);
        g.setColor(colorMap.get(card.getColor()));
        g.fillRect(posX+50, posY+50, 200, 200);
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
    }

    private void paintCircleCard(Graphics g, Card card) {
        int[] pos = card.getPosition();
        int posX = pos[0];
        int posY = pos[1];

        g.setColor(backgroundMap.get(card.getBackground()));
        g.fillRect(posX, posY, 300, 300);
        g.setColor(colorMap.get(card.getColor()));
        g.fillOval(posX+50, posY+50, 200, 200);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("결합");
        BoardPanel panel = new BoardPanel(new Board());

        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(panel); // JPanel 추가
        frame.pack();
        frame.setVisible(true);
    }
}
