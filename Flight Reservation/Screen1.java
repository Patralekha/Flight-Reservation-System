import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Screen1 extends JFrame implements ActionListener {
    private FRSManager mgr;

    JPanel p1, p2, p3;
    JButton button;
    JLabel errorDisp;
    JTextField t1;

    int size;
    JRadioButton[] r;
    String[] flightDetails;
    String chosenFlight = "", passenname="";

    Screen1(FRSManager mgr) {

        size = mgr.matchedFlights.size();
        r = new JRadioButton[size];
        flightDetails = new String[size];

        this.mgr = mgr;
        p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        p1.setBackground(Color.WHITE);

        p2 = new JPanel();
        p2.setLayout(new GridLayout(1, 0));
        p2.setBackground(Color.WHITE);

        p3 = new JPanel();
        p3.setLayout(new BorderLayout());
        p3.setBackground(Color.YELLOW);


        /*for (int i = 0; i < mgr.matchedFlights.size(); i++)
            mgr.matchedFlights.get(i).printCombinedFlights();*/

        t1 = new JTextField(25);
        button = new JButton("Select Flight");
        errorDisp = new JLabel("");

        for (int i = 0; i < size; i++) {
            flightDetails[i] = "<html>";
            flightDetails[i] += mgr.matchedFlights.get(i).printCombinedFlights().replace("\n", "<br>");
            flightDetails[i] += "</html>";
        }


        for (int i = 0; i < size; i++)
            r[i] = new JRadioButton(flightDetails[i]);


        ButtonGroup bg = new ButtonGroup();
        for (int i = 0; i < size; i++)
            bg.add(r[i]);


        for (int i = 0; i < size; i++)
            p2.add(r[i]);


        p1.add(t1);
        p3.add(button, BorderLayout.EAST);
        p3.add(errorDisp, BorderLayout.WEST);

        add(p1, BorderLayout.NORTH);
        button.addActionListener(this);
        JScrollPane pane = new JScrollPane(p2);
        add(pane, BorderLayout.CENTER);
        //add(p2, BorderLayout.CENTER);
        add(p3, BorderLayout.SOUTH);

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);
    }

    public void actionPerformed(ActionEvent e) {
        passenname = t1.getText();
        boolean checkClicked = false;
        for (int i = 0; i < size; i++) {
            if (r[i].isSelected()) {
                chosenFlight = r[i].getText();
                checkClicked = true;
            }
        }
        if (!checkClicked) {
            errorDisp.setText("Select a flight!");
            return;
        }
        mgr.displayMgr.callScreen2();
    }

    public String getChosenFlight() {
        return chosenFlight;
    }

    public String getPassenname() {
        return passenname;
    }
}
