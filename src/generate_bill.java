import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class generate_bill extends JFrame implements ActionListener {
    JLabel l1;
    JTextArea t1;
    JButton b1, b2;  // Declare the second button for "Mark as Paid"
    Choice c1, c2;
    JPanel p1, buttonPanel; // Additional panel for the buttons

    generate_bill() {
        setSize(500, 700);
        setLayout(new BorderLayout());

        p1 = new JPanel();
        buttonPanel = new JPanel();  // Create a new panel for the buttons

        l1 = new JLabel("Generate Bill");

        c1 = new Choice();
        c2 = new Choice();

        // Add meter numbers
        c1.add("1001");
        c1.add("1002");
        c1.add("1003");
        c1.add("1004");
        c1.add("1005");
        c1.add("1006");
        c1.add("1007");
        c1.add("1008");
        c1.add("1009");
        c1.add("1010");

        // Add months
        c2.add("January");
        c2.add("February");
        c2.add("March");
        c2.add("April");
        c2.add("May");
        c2.add("June");
        c2.add("July");
        c2.add("August");
        c2.add("September");
        c2.add("October");
        c2.add("November");
        c2.add("December");

        t1 = new JTextArea(50, 15);
        JScrollPane jsp = new JScrollPane(t1);
        t1.setFont(new Font("Senserif", Font.ITALIC, 18));

        b1 = new JButton("Generate Bill");
        b2 = new JButton("Mark as Paid");  // Second button for marking as paid

        p1.add(l1);
        p1.add(c1);
        p1.add(c2);
        add(p1, "North");

        add(jsp, "Center");

        // Set FlowLayout for buttons and add both buttons to buttonPanel
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(b1);
        buttonPanel.add(b2);  // Add the second button to the panel
        add(buttonPanel, "South");  // Add the button panel to the bottom

        b1.addActionListener(this);
        b2.addActionListener(this);  // Add the action listener for the second button

        setLocation(350, 40);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            conn c = new conn();
            String month = c2.getSelectedItem();
            String meterNumber = c1.getSelectedItem();

            if (ae.getSource() == b1) {  // If the "Generate Bill" button is clicked
                t1.setText("\tReliance Power Limited\nELECTRICITY BILL FOR THE MONTH OF " + month + ", 2018\n\n\n");

                // Fetch customer info
                ResultSet rs = c.s.executeQuery("SELECT * FROM customer WHERE meter_number='" + meterNumber + "'");
                if (rs.next()) {
                    t1.append("\n    Customer Name: " + rs.getString("name"));
                    t1.append("\n    Meter Number:  " + rs.getString("meter_number"));
                    t1.append("\n    Address:       " + rs.getString("address"));
                    t1.append("\n    State:         " + rs.getString("state"));
                    t1.append("\n    City:          " + rs.getString("city"));
                    t1.append("\n    Email:         " + rs.getString("email"));
                    t1.append("\n    Phone Number:  " + rs.getString("phone"));
                    t1.append("\n-------------------------------------------------------------\n");
                }

                // Fetch meter info
                rs = c.s.executeQuery("SELECT * FROM meter_info WHERE meter_number='" + meterNumber + "'");
                if (rs.next()) {
                    t1.append("\n    Meter Location: " + rs.getString("location"));
                    t1.append("\n    Meter Type:     " + rs.getString("type"));
                    t1.append("\n    Phase Code:     " + rs.getString("phase_code"));
                    t1.append("\n    Bill Type:      " + rs.getString("bill_type"));
                    t1.append("\n    Days:           " + rs.getString("days"));
                    t1.append("\n-------------------------------------------------------------\n");
                }

                // Fetch tax info
                rs = c.s.executeQuery("SELECT * FROM tax");
                if (rs.next()) {
                    t1.append("\n    Meter Rent:\t\t" + rs.getString("meter_rent"));
                    t1.append("\n    Service Charge:\t" + rs.getString("service_charge"));
                    t1.append("\n    Service Tax:\t" + rs.getString("service_tax"));
                    t1.append("\n    Swachh Bharat Cess:\t" + rs.getString("swacch_bharat_cess"));
                    t1.append("\n    Fixed Tax:\t\t" + rs.getString("fixed_tax"));
                    t1.append("\n-------------------------------------------------------------\n");
                }

                // Fetch bill info
                rs = c.s.executeQuery("SELECT * FROM bill WHERE meter_number='" + meterNumber + "' AND month='" + month + "'");
                if (rs.next()) {
                    t1.append("\n    Current Month :\t" + rs.getString("month"));
                    t1.append("\n    Units Consumed:\t" + rs.getString("units"));
                    t1.append("\n    Total Charges :\t" + rs.getString("total_bill"));
                    t1.append("\n    Status :\t\t" + rs.getString("status"));
                    t1.append("\n-------------------------------------------------------------");
                    t1.append("\n    TOTAL PAYABLE :\t" + rs.getString("total_bill"));
                }
            }

            // Mark the bill as paid when the "Mark as Paid" button is clicked
            if (ae.getSource() == b2) {
                String updateQuery = "UPDATE bill SET status='Paid' WHERE meter_number='" + meterNumber + "' AND month='" + month + "'";
                c.s.executeUpdate(updateQuery);
                JOptionPane.showMessageDialog(null, "Bill marked as paid!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new generate_bill().setVisible(true);
    }
}
