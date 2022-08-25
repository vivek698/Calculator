import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    JPanel header;
    JLabel lastOperation;
    JTextField textField;
    double firstNumber = 0;
    char operation;

    JPanel buttonPanel;
    JButton[] buttons=new JButton[30];
    public MainFrame() {
        this.setTitle("Calculator");
        this.setSize(500,500);


        header = new JPanel();
        header.setLayout(new GridLayout(2,1));


        lastOperation = new JLabel("history is mistery");
        //TODO FONT
        //TODO Foreground
        header.add(lastOperation);

        textField = new JTextField(20);
        //TODO FONT
        //TODO Foreground
        //TODO Validation
        header.add(textField);

        this.add(header,BorderLayout.NORTH);


        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6,5,5,5));

        //complex Operation
        buttons[0]=new JButton("C");
        buttons[1]=new JButton("π");
        buttons[2]=new JButton("e");
        buttons[3]=new JButton("|x|");
        buttons[4]=new JButton("⌫");
        buttons[5]=new JButton("^2");
        buttons[6]=new JButton("log");
        buttons[7]=new JButton("ln");
        buttons[8]=new JButton("10^x");
        buttons[10]=new JButton("^y");
        buttons[15]=new JButton("²√");
        buttons[20]=new JButton("ᵃ√");
        buttons[25]=new JButton("1/x");

        //simple Operation
        buttons[9]=new JButton("/");
        buttons[14]=new JButton("*");
        buttons[19]=new JButton("-");
        buttons[24]=new JButton("+");
        buttons[29]=new JButton("="); //different color

        //number Buttons
        buttons[11]= new JButton("7");
        buttons[12]= new JButton("8");
        buttons[13]= new JButton("9");
        buttons[16]= new JButton("4");
        buttons[17]= new JButton("5");
        buttons[18]= new JButton("6");
        buttons[21]= new JButton("1");
        buttons[22]= new JButton("2");
        buttons[23]= new JButton("3");
        buttons[26]= new JButton(".");
        buttons[27]= new JButton("0");
        buttons[28]= new JButton("+/-");

        //init complex buttons
        //TODO
        for (int i = 0; i < 9; i++) {
            buttons[i].setBackground(Color.LIGHT_GRAY);
        }

        for (int i = 10; i < 26; i+=5) {
            buttons[i].setBackground(Color.LIGHT_GRAY);
        }

        //Action Listeners
        buttons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                lastOperation.setText("");
                firstNumber = 0;
            }
        });

        buttons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstNumber=Math.PI;
                textField.setText(firstNumber + "");
            }
        });

        buttons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstNumber=Math.E;
                textField.setText(firstNumber + "");
            }
        });

        buttons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().charAt(0)=='-'){
                    textField.setText(textField.getText().substring(1,textField.getText().length()));
                }
            }
        });

        buttons[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField.getText().length()>=1){
                    textField.setText(textField.getText().substring(0,textField.getText().length()-1));
                }
            }
        });

        buttons[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double num=getNum();
                textField.setText(removeBeforeDot(Math.pow(num,2)) + "");
                lastOperation.setText(removeBeforeDot(num) + "^2");
            }
        });
        buttons[6].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double num=getNum();

                firstNumber = Math.log10(num);

                lastOperation.setText("log"+removeBeforeDot(num)+" = "+ removeBeforeDot(firstNumber));
            }
        });
        buttons[7].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double num=getNum();

                firstNumber = Math.log10(num)/Math.log10(Math.E);

                lastOperation.setText("log"+removeBeforeDot(num)+" = "+ removeBeforeDot(firstNumber));
            }
        });

        buttons[8].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double num=getNum();

                firstNumber = Math.pow(10,num);

                lastOperation.setText("10^"+removeBeforeDot(num)+" = "+ removeBeforeDot(firstNumber));
            }
        });

        //init simple operation
        buttons[29].setBackground(new Color(0x00F2DE));
        for (int i = 9; i < 29; i+=5) {
            buttons[i].addActionListener(simpleOperationActionListener);
        }
        buttons[29].addActionListener(equalsActionListener);


        //number Buttons
        for (int i = 11; i < 29; i++) {
            if ((i-11)%5<3){
                buttons[i].setBackground(Color.WHITE);
                buttons[i].addActionListener(numberActionListener);
            }
        }

        //add all Buttons
        for (JButton button:buttons) {
            buttonPanel.add(button);
        }

        this.add(buttonPanel);
        this.setVisible(true);
    }

    ActionListener numberActionListener=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 11; i < 28; i++) {
                if (e.getSource()==buttons[i]){
                    textField.setText(textField.getText()+buttons[i].getText());
                }
            }
            if (e.getSource()==buttons[28]){
                if (textField.getText().charAt(0)=='-'){
                    textField.setText(textField.getText().substring(1));
                }else {
                    textField.setText("-"+textField.getText());
                }
            }
        }
    };

    ActionListener simpleOperationActionListener =new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            equalsActionListener.actionPerformed(e);
            if (!textField.getText().equals("")) {
                firstNumber = Double.parseDouble(textField.getText());
            }

            JButton button = (JButton) e.getSource();
            operation = button.getText().charAt(0);

            lastOperation.setText(removeBeforeDot(firstNumber) + " " + operation);

            textField.setText("");
        }
    };
    ActionListener equalsActionListener=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            double secondNumber = 0;
            if (firstNumber!=0){
                if (!textField.getText().equals("")) {
                    secondNumber = Double.parseDouble(textField.getText());
                    lastOperation.setText( removeBeforeDot(firstNumber) +" " +operation +" "+ removeBeforeDot(secondNumber));
                    double result = firstNumber;
                    switch (operation) {
                        case '+': result +=  secondNumber ;
                            break;
                        case '-': result -= secondNumber ;
                            break;
                        case '*': result *=  secondNumber ;
                            break;
                        case '/': result /= secondNumber;
                            break;
                    }
                    lastOperation.setText(lastOperation.getText() +" = " + removeBeforeDot(result));
                    firstNumber = result;
                    textField.setText("");
                }
            }
        }
    };

    public String removeBeforeDot(double num){
        if ((int)num==num){
            return "" + (int)num;
        }
        else {
            return ""+num;
        }
    }

    private double getNum(){
        double num;
        if (!textField.getText().equals("")) {
            num = Double.parseDouble(textField.getText());
            textField.setText("");
        }else{
            num = firstNumber;
        }
        return num;
    }
    public static void main(String[] args) {
        new MainFrame();
    }
}
