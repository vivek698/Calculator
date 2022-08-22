import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    JPanel header;
    JLabel lastOperation;
    JTextField textField;

    JPanel buttonPanel;
    JButton[] complexOperationButtons=new JButton[7];
    JButton[] simpleOperationButtons=new JButton[5];
    JButton[] numberPanel=new JButton[12];

    public MainFrame() {
        header = new JPanel();
        lastOperation = new JLabel();
        //TODO FONT
        //TODO Foreground

        textField = new JTextField();
        //TODO FONT
        //TODO Foreground
        //TODO Validation

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout());

        complexOperationButtons[0]=new JButton("^2");
        complexOperationButtons[0]=new JButton("^x");
        complexOperationButtons[0]=new JButton("²√");
        complexOperationButtons[0]=new JButton("ᵃ√");
        complexOperationButtons[0]=new JButton("(");
        complexOperationButtons[0]=new JButton(")");

    }
}
