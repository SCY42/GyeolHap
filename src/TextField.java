import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextField extends JTextField {
    public TextField(Font font) {
        this.setBounds(950, 800, 125, 50);
        this.setFont(font);
    }
}
