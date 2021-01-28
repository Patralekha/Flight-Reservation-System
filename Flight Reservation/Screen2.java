import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen2 extends JFrame implements ActionListener {
    private FRSManager mgr;

    JPanel p1, p2;
    JLabel name, disp;
    JButton button2;

    Screen2(FRSManager mgr) {
        this.mgr = mgr;
        p1 = new JPanel();
        p1.setLayout(new BorderLayout());
        p1.setBackground(Color.WHITE);

        p2 = new JPanel();
        p2.setLayout(new BorderLayout());
        p2.setBackground(Color.YELLOW);

        name = new JLabel("Anonymous");
        disp = new JLabel("");
        button2 = new JButton("EXIT");

        button2.addActionListener(this);

        p1.add(name, BorderLayout.NORTH);
        p1.add(disp, BorderLayout.CENTER);
        p2.add(button2, BorderLayout.EAST);

        JScrollPane pane = new JScrollPane(p1);
        add(pane, BorderLayout.CENTER);
        add(p2, BorderLayout.SOUTH);

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);
    }

    public void actionPerformed(ActionEvent e) {
        String selectedButton = e.getActionCommand();
        if (selectedButton == "EXIT")
            System.exit(0);
    }

    public void setFlightLabel() {
        disp.setText(mgr.displayMgr.getChosenFlight());
        name.setText(mgr.displayMgr.getPassenname());
    }
}
