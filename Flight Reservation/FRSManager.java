import java.util.*;
import java.io.*;

public class FRSManager {
    public DataManager dataMgr;
    public LogicManager logicMgr;
    public DisplayManager displayMgr;
    public String SPICEJET_CSV_PATH;
    public String SILKAIR_CSV_PATH;
    public int N_SEATS;
    public String USER_DATE;
    public String USER_SOURCE;
    public String USER_DESTINATION;
    public ArrayList<Flight> DomesticFlights;
    public ArrayList<Flight> InternationalFlights;
    public ArrayList<Flight> allFlights;
    public ArrayList<CombinedFlights> matchedFlights;

    FRSManager() {
        allFlights = new ArrayList<Flight>();
        /*SPICEJET_CSV_PATH = "src/2016.spicejet.csv";
        SILKAIR_CSV_PATH = "src/2016.silkair.csv";*/
        SPICEJET_CSV_PATH = "2016.spicejet.csv";
        SILKAIR_CSV_PATH = "2016.silkair.csv";
    }

    public static void main(String[] args) throws Exception {
        FRSManager frsMgr = new FRSManager();

        frsMgr.dataMgr = new DataManager(frsMgr);
        frsMgr.logicMgr = new LogicManager(frsMgr);
        frsMgr.displayMgr = new DisplayManager(frsMgr);

        frsMgr.DomesticFlights = frsMgr.dataMgr.readSpiceJet();
        frsMgr.InternationalFlights = frsMgr.dataMgr.readSilkAir();

        frsMgr.allFlights.addAll(frsMgr.DomesticFlights);
        frsMgr.allFlights.addAll(frsMgr.InternationalFlights);

        frsMgr.displayMgr.callScreen0();

        while (!frsMgr.displayMgr.obj0.running)
            Thread.sleep(10);

        /*frsMgr.USER_SOURCE = "DELHI";
        frsMgr.USER_DESTINATION = "SINGAPORE";
        frsMgr.USER_DATE = "15-10-2016";
        frsMgr.N_SEATS = 10;*/

        boolean checkNoOfSeats = frsMgr.logicMgr.checkNSeats();
        if (checkNoOfSeats == true) {
            frsMgr.matchedFlights = frsMgr.logicMgr.checkFlights();
            //for (int i = 0; i < frsMgr.matchedFlights.size(); i++)
            // System.out.println(frsMgr.matchedFlights.get(i).printCombinedFlights());
        } else {
            System.out.println("No of Seats selected is not between 1 and 10.");
        }
        frsMgr.displayMgr.callScreen1();
    }
}
