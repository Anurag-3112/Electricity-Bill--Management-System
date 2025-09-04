import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.HashSet;

public class LastBill extends JFrame implements ActionListener {
    JLabel l1;
    JTextArea t1;
    JButton b1;
    Choice c1;
    JPanel p1;

    LastBill() {
        setSize(500, 700);
        setLayout(new BorderLayout());

        p1 = new JPanel();
        l1 = new JLabel("Generate Bill");

        c1 = new Choice();
        // Add meter numbers (assumed same as emp_id in emp table)
        for (int i = 1001; i <= 1010; i++) {
            c1.add(String.valueOf(i));
        }

        t1 = new JTextArea(50, 15);
        JScrollPane jsp = new JScrollPane(t1);
        t1.setFont(new Font("Senserif", Font.ITALIC, 18));

        b1 = new JButton("Generate Bill");
        b1.addActionListener(this);

        p1.add(l1);
        p1.add(c1);
        add(p1, "North");
        add(jsp, "Center");
        add(b1, "South");

        setLocation(350, 40);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            conn c = new conn();
            String meter = c1.getSelectedItem();
            t1.setText("");  // Clear previous content

            // Fetch customer details from emp table
            ResultSet rs = c.s.executeQuery("SELECT * FROM emp WHERE emp_id = '" + meter + "'");
            if (rs.next()) {
                t1.append("\n    Customer Name: " + rs.getString("name"));
                t1.append("\n    Meter Number:  " + meter);
                t1.append("\n    Address:       " + rs.getString("address"));
                t1.append("\n    State:         " + rs.getString("state"));
                t1.append("\n    City:          " + rs.getString("city"));
                t1.append("\n    Email:         " + rs.getString("email"));
                t1.append("\n    Phone Number:  " + rs.getString("phone"));
                t1.append("\n-------------------------------------------------------------\n");
            }

            // Fetch bill details from bill table
            t1.append("\nDetails of the Last Bills\n\n");

            rs = c.s.executeQuery("SELECT * FROM bill WHERE meter_number = '" + meter + "'");
            HashSet<String> seen = new HashSet<>();

            while (rs.next()) {
                String key = rs.getString("month") + "|" + rs.getString("units") + "|" + rs.getString("total_bill") + "|" + rs.getString("status");
                if (!seen.contains(key)) {
                    t1.append("   Month: " + rs.getString("month")
                            + "   | Units: " + rs.getString("units")
                            + "   | Total Bill: ₹" + rs.getString("total_bill")
                            + "   | Status: " + rs.getString("status") + "\n");
                    seen.add(key);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LastBill().setVisible(true);
    }
}
