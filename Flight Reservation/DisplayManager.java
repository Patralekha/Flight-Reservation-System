public class DisplayManager {
    private FRSManager mgr;
    Screen0 obj0;
    Screen1 obj1;
    Screen2 obj2;

    public String chosenFlight, passenname;

    DisplayManager(FRSManager mgr) {
        this.mgr = mgr;
        obj0 = new Screen0(mgr);
        //obj1 = new Screen1(mgr);
        //obj2 = new Screen2(mgr);
    }

    public void callScreen0() {
        obj0.setVisible(true);
    }

    public void callScreen1() {
        obj0.setVisible(false);

        obj1 = new Screen1(mgr);
        obj2 = new Screen2(mgr);

        obj1.setVisible(true);
    }

    public void callScreen2() {
        obj1.setVisible(false);

        chosenFlight = obj1.getChosenFlight();
        // System.out.println(chosenFlight);
        passenname = obj1.getPassenname();
        obj2.setFlightLabel();
        obj2.setVisible(true);
    }

    public String getChosenFlight() {
        return chosenFlight;
    }

    public String getPassenname() {
        return passenname;
    }
}
