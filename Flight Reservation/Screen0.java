import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;


class Screen0 extends JFrame implements ActionListener {
    FRSManager mgr;

    final Integer[] DD = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
            16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
    final Integer[] MM = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    final Integer[] YYYY = {2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020};

    String sourceVal, destVal, date = "";
    int nseats;
    boolean running = false;

    JPanel p1, p2, p3;

    JLabel dispNSeats = new JLabel("Number Of Seats: ");
    JLabel dispDate = new JLabel("Departure Date: ");
    JLabel to = new JLabel(" ---> to ---> ", SwingConstants.CENTER);

    String[] sourceList = new String[]{"DELHI", "KOLKATA", "PUNE", "MUMBAI", "BENGALURU", "CHENNAI", "HYDERABAD"};
    JComboBox<String> source = new JComboBox<>(sourceList);
    String[] destList = new String[]{"SINGAPORE", "BENGALURU", "CHENNAI", "HYDERABAD", "KOLKATA"};
    JComboBox<String> dest = new JComboBox<>(destList);

    JComboBox<Integer> dd = new JComboBox<>(DD);
    JComboBox<Integer> mm = new JComboBox<>(MM);
    JComboBox<Integer> yyyy = new JComboBox<>(YYYY);
    JTextField t1;
    JButton button;
    JLabel errorDisp = new JLabel("");

    Screen0(FRSManager mgr) {
        this.mgr = mgr;

        p1 = new JPanel();
        p1.setLayout(new BorderLayout());
        p1.setBackground(Color.WHITE);

        p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        p2.setBackground(Color.WHITE);

        p3 = new JPanel();
        p3.setLayout(new FlowLayout());
        p3.setBackground(Color.YELLOW);

        t1 = new JTextField(5);
        button = new JButton("CONFIRM");

        p1.add(source, BorderLayout.WEST);
        p1.add(to, BorderLayout.CENTER);
        p1.add(dest, BorderLayout.EAST);

        p2.add(dispDate);
        p2.add(dd);
        p2.add(mm);
        p2.add(yyyy);
        p2.add(dispNSeats);
        p2.add(t1);

        p3.add(button, BorderLayout.WEST);
        p3.add(errorDisp, BorderLayout.EAST);

        button.addActionListener(this);

        add(p1, BorderLayout.NORTH);
        add(p2, BorderLayout.CENTER);
        add(p3, BorderLayout.SOUTH);

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);
    }

    public void actionPerformed(ActionEvent e) {
        sourceVal = source.getSelectedItem().toString();
        destVal = dest.getSelectedItem().toString();
        date = dd.getSelectedItem().toString() + "-" + mm.getSelectedItem().toString()
                + "-" + yyyy.getSelectedItem().toString();
        try {
            nseats = Integer.parseInt(t1.getText());
        } catch (NumberFormatException ex) {
            errorDisp.setText("Input valid seats number");
            return;
        }
        if (nseats > 10) {
            errorDisp.setText("10 is max seat size!");
            return;
        }

        running = true;
        mgr.N_SEATS = nseats;
        mgr.USER_DATE = date;
        mgr.USER_SOURCE = sourceVal;
        mgr.USER_DESTINATION = destVal;


        //mgr.displayMgr.callScreen1();
    }
}