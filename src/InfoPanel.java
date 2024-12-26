import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {
    Font font = new Font("Arial", Font.PLAIN, 50);
    FontMetrics fm;

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(font);
        drawCenteredString(g, "1 2 3", 100);
        drawCenteredString(g, "5 6 7", 200);
    }

    public InfoPanel() {
        setPreferredSize(new Dimension(300,900));
        fm = this.getFontMetrics(font);
    }

    private void drawCenteredString(Graphics g, String str, int height) {
        int width = fm.stringWidth(str);
        g.drawString(str, (300 - width) / 2, height);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("InfoPanel");
        InfoPanel panel = new InfoPanel();

        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(panel); // JPanel 추가
        frame.pack();
        frame.setVisible(true);
    }
}
