import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraIMC {
    private JFrame frame;
    private JTextField pesoField, alturaField;
    private JLabel resultadoLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraIMC().initialize());
    }

    public void initialize() {
        frame = new JFrame("Calculadora de IMC");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        pesoField = new JTextField(10);
        alturaField = new JTextField(10);
        JButton calcularButton = new JButton("Calcular IMC");
        resultadoLabel = new JLabel("Resultado: ");

        frame.add(new JLabel("Peso (kg):"));
        frame.add(pesoField);
        frame.add(new JLabel("Altura (m):"));
        frame.add(alturaField);
        frame.add(calcularButton);
        frame.add(resultadoLabel);

        calcularButton.addActionListener(e -> calcularIMC());

        frame.setVisible(true);
    }

    private void calcularIMC() {
        try {
            double peso = Double.parseDouble(pesoField.getText());
            double altura = Double.parseDouble(alturaField.getText());

            if (peso <= 0 || altura <= 0) {
                JOptionPane.showMessageDialog(frame, "Digite valores válidos para peso e altura.");
                return;
            }

            double imc = peso / (altura * altura);
            String categoria = categoriaIMC(imc);
            resultadoLabel.setText("IMC: " + String.format("%.2f", imc) + " - " + categoria);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Digite valores válidos.");
        }
    }

    private String categoriaIMC(double imc) {
        if (imc < 18.5) {
            return "Baixo Peso";
        } else if (imc < 24.9) {
            return "Normal";
        } else if (imc < 29.9) {
            return "Sobrepeso";
        } else {
            return "Obesidade";
        }
    }
}