import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Button extends JButton {
    BoardPanel boardPanel;
    TextField textField;

    public Button(BoardPanel boardPanel, TextField textField) {
        this.setBounds(1100, 800, 50, 50);
        this.boardPanel = boardPanel;
        this.textField = textField;
        myActionListener();
    }

    private void myActionListener() {
        ActionListener myActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputString = textField.getText();
                if (inputString.equals("999") && isHap(boardPanel)) {
                    textField.setText("FIN!");
                } else {
                    int[] inputArray = new int[3];
                    for (int i = 0; i < Math.min(inputArray.length, inputString.length()); i++) {
                        if (Character.isDigit(inputString.charAt(i))) {
                            inputArray[i] = Integer.parseInt(inputString.charAt(i) + "") - 1;
                        }
                    }
                    int[] checkResult = checkAnswer(boardPanel.getBoard(), inputArray);
                    if (checkResult != null) {
                        boardPanel.updateFoundAnswers(checkResult);
                        textField.setText("");
                    } else {
                        textField.setText("X");
                    }
                    setEnabled(true);
                }
            }
        };
        this.addActionListener(myActionListener);
    }

    private int[] checkAnswer(Board board, int[] input) {
        ArrayList<int[]> rawAnswers = board.findAnswers();
        for (int[] foundAnswer : this.boardPanel.foundAnswers) {
            if (Arrays.equals(input, foundAnswer)) {
                return null;
            }
        }
        for (int[] rawAnswer : rawAnswers) {
            if (Arrays.equals(input, rawAnswer)) {
                return input;
            }
        }
        return null;
    }

    private boolean isHap(BoardPanel boardPanel) {
        return boardPanel.getFoundAnswers().size() == boardPanel.getBoard().findAnswers().size();
    }
}
