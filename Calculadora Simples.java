import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraSimples {
    private JFrame frame;
    private JTextField textField;
    private String operator = "";
    private double num1, num2, result;
    private boolean isOperatorClicked = false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraSimples().initialize());
    }

    public void initialize() {
        frame = new JFrame("Calculadora");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout());

        // Text field
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        textField.setEditable(false);
        frame.add(textField, BorderLayout.NORTH);

        // Buttons panel
        JPanel panel = new JPanel(new GridLayout(4, 4));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 24));
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("C")) {
                textField.setText("");
                num1 = num2 = result = 0;
                operator = "";
                isOperatorClicked = false;
            } else if (command.equals("=")) {
                num2 = Double.parseDouble(textField.getText());
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            textField.setText("Erro");
                            return;
                        }
                        break;
                }
                textField.setText(String.valueOf(result));
                operator = "";
                isOperatorClicked = false;
            } else if ("+-*/".contains(command)) {
                if (!isOperatorClicked) {
                    num1 = Double.parseDouble(textField.getText());
                    operator = command;
                    textField.setText("");
                    isOperatorClicked = true;
                }
            } else {
                textField.setText(textField.getText() + command);
            }
        }
    }
}