import javax.swing.*;
import java.awt.*;
import java.net.URI;

public class pay_bill extends JFrame {

    public pay_bill() {
        super("Pay Electricity Bill");

        JLabel label = new JLabel("<html><h2>Click the button below to pay your bill.</h2></html>", SwingConstants.CENTER);
        JButton button = new JButton("Go to Paytm");

        button.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://paytm.com/electricity-bill-payment"));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Unable to open browser. Please check your system settings.");
                ex.printStackTrace();
            }
        });

        setLayout(new BorderLayout());
        add(label, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);

        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(pay_bill::new);
    }
}
