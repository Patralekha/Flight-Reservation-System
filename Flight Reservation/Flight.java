import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Flight {
    private String name, source, destination, via;
    private Calendar arrivalTime, departureTime, effectiveFrom, effectiveTill;
    private int[] days;

    Flight(String name, String source, String destination, String via,
           Calendar arrivalTime, Calendar departureTime, Calendar effectiveFrom, Calendar effectiveTill, int[] days) {
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.via = via;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.days = days;
        this.effectiveFrom = effectiveFrom;
        this.effectiveTill = effectiveTill;
    }

    public String printFlightInfo() {
        String outp = "";
        Calendar cal = Calendar.getInstance();
        //System.out.println("NAME: " + name);
        outp += "NAME: " + name + "\n";
        //System.out.println("SOURCE: " + source);
        outp += "SOURCE: " + source + "\n";
       // System.out.println("DESTINATION: xxxx<br>" + destination);
        outp += "DESTINATION: " + destination + "\n";
       // System.out.println("VIA: " + via);
        outp += "VIA: " + via + "\n";
       // System.out.println("DEPARTURE TIME: " + departureTime.get(Calendar.HOUR_OF_DAY) + ":" + departureTime.get(Calendar.MINUTE));
        outp += "DEPARTURE TIME: " + departureTime.get(Calendar.HOUR_OF_DAY) + ":" + departureTime.get(Calendar.MINUTE) + "\n";
       // System.out.println("ARRIVAL TIME: " + arrivalTime.get(Calendar.HOUR_OF_DAY) + ":" + arrivalTime.get(Calendar.MINUTE));
        outp += "ARRIVAL TIME: " + arrivalTime.get(Calendar.HOUR_OF_DAY) + ":" + arrivalTime.get(Calendar.MINUTE) + "\n";
      //  System.out.print("DAYS: ");
        outp += "DAYS: " + "\n";
        for (int i = 0; i < days.length; i++) {
       //     System.out.print(String.valueOf(days[i]) + " ");
        }
      //  System.out.println();
       // System.out.println("EFFECTIVE FROM: " + effectiveFrom.get(Calendar.DAY_OF_MONTH) + "/" + (effectiveFrom.get(Calendar.MONTH) + 1) + "/" + effectiveFrom.get(Calendar.YEAR));
        outp += "EFFECTIVE FROM: " + effectiveFrom.get(Calendar.DAY_OF_MONTH) + "/" + (effectiveFrom.get(Calendar.MONTH) + 1) + "/" + effectiveFrom.get(Calendar.YEAR) + "\n";
       // System.out.println("EFFECTIVE TILL: " + effectiveTill.get(Calendar.DAY_OF_MONTH) + "/" + (effectiveTill.get(Calendar.MONTH) + 1) + "/" + effectiveTill.get(Calendar.YEAR));
        outp += "EFFECTIVE TILL: " + effectiveTill.get(Calendar.DAY_OF_MONTH) + "/" + (effectiveTill.get(Calendar.MONTH) + 1) + "/" + effectiveTill.get(Calendar.YEAR) + "\n\n";
       // System.out.println("\n");

        return outp;
    }

    public String getFlightno() {
        return name;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getVia() {
        return via;
    }

    public Calendar getArrivalTime() {
        return arrivalTime;
    }

    public Calendar getDepartureTime() {
        return departureTime;
    }

    public Calendar getEffectiveFrom() {
        return effectiveFrom;
    }

    public Calendar getEffectiveTill() {
        return effectiveTill;
    }

    public int[] getDays() {
        return days;
    }
}
