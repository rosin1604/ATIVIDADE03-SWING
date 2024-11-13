import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroDeContatos {
    private JFrame frame;
    private JTextField nomeField, telefoneField, emailField;
    private DefaultListModel<String> listaModel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CadastroDeContatos().initialize());
    }

    public void initialize() {
        frame = new JFrame("Cadastro de Contatos");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        nomeField = new JTextField(20);
        telefoneField = new JTextField(20);
        emailField = new JTextField(20);
        JButton adicionarButton = new JButton("Adicionar Contato");
        JButton removerButton = new JButton("Remover Contato");

        listaModel = new DefaultListModel<>();
        JList<String> listaContatos = new JList<>(listaModel);

        frame.add(new JLabel("Nome:"));
        frame.add(nomeField);
        frame.add(new JLabel("Telefone:"));
        frame.add(telefoneField);
        frame.add(new JLabel("E-mail:"));
        frame.add(emailField);
        frame.add(adicionarButton);
        frame.add(removerButton);
        frame.add(new JScrollPane(listaContatos));

        adicionarButton.addActionListener(e -> adicionarContato());
        removerButton.addActionListener(e -> removerContato(listaContatos));

        frame.setVisible(true);
    }

    private void adicionarContato() {
        String nome = nomeField.getText();
        String telefone = telefoneField.getText();
        String email = emailField.getText();

        if (!nome.isEmpty() && !telefone.isEmpty() && !email.isEmpty()) {
            listaModel.addElement("Nome: " + nome + " | Telefone: " + telefone + " | E-mail: " + email);
            nomeField.setText("");
            telefoneField.setText("");
            emailField.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "Preencha todos os campos.");
        }
    }

    private void removerContato(JList<String> listaContatos) {
        int selectedIndex = listaContatos.getSelectedIndex();
        if (selectedIndex != -1) {
            listaModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(frame, "Selecione um contato para remover.");
        }
    }
}