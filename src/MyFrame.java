import javax.swing.JFrame;

public class MyFrame extends JFrame {
    public MyFrame() {
        this.add(new BoardPanel(new Board()));
        setTitle("결! 합!");
        setSize(1200, 900);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        setVisible(true);
    }
}
