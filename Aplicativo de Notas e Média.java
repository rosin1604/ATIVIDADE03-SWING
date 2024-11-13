import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AplicativoNotas {
    private JFrame frame;
    private JTextField notaField;
    private JTextArea notasArea;
    private ArrayList<Double> notas;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AplicativoNotas().initialize());
    }

    public void initialize() {
        frame = new JFrame("Aplicativo de Notas");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        notaField = new JTextField(10);
        JButton adicionarNotaButton = new JButton("Adicionar Nota");
        JButton calcularMediaButton = new JButton("Calcular Média");

        inputPanel.add(notaField);
        inputPanel.add(adicionarNotaButton);
        inputPanel.add(calcularMediaButton);

        // Notes area
        notasArea = new JTextArea();
        notasArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(notasArea);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Result label
        JLabel resultadoLabel = new JLabel("Média: ");
        frame.add(resultadoLabel, BorderLayout.SOUTH);

        // Action listeners
        adicionarNotaButton.addActionListener(e -> adicionarNota());
        calcularMediaButton.addActionListener(e -> calcularMedia(resultadoLabel));

        notas = new ArrayList<>();

        frame.setVisible(true);
    }

    private void adicionarNota() {
        try {
            double nota = Double.parseDouble(notaField.getText());
            if (nota < 0 || nota > 10) {
                JOptionPane.showMessageDialog(frame, "Nota deve estar entre 0 e 10.");
                return;
            }
            notas.add(nota);
            notasArea.append("Nota: " + nota + "\n");
            notaField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Digite um número válido.");
        }
    }

    private void calcularMedia(JLabel resultadoLabel) {
        if (notas.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Nenhuma nota adicionada.");
            return;
        }

        double soma = 0;
        for (double nota : notas) {
            soma += nota;
        }

        double media = soma / notas.size();
        String status = media >= 7.0 ? "Aprovado" : "Reprovado";
        resultadoLabel.setText("Média: " + media + " - Status: " + status);
    }
}