import java.util.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

class CombinedFlights
{
  private int nSeats ;
  private int nFlights;
  private Calendar startDateAndTime;
  private Calendar endDateAndTime;
  private long layOverInMinutes;
  private long totalJourneyDurationInMinutes;
  private ArrayList<Flight> flightArr = new ArrayList<Flight>();

  public void addFlight(Flight flight)
  {
    flightArr.add(flight);
  }

  public void setDetails(Calendar userDate, int userSeats, long layOverInMinutes)
  {
    this.nSeats = userSeats;
    this.nFlights = flightArr.size();
    this.layOverInMinutes = layOverInMinutes;
    Calendar startTime = flightArr.get(0).getDepartureTime();
    Calendar endTime = flightArr.get(nFlights-1).getArrivalTime();
    this.startDateAndTime = (Calendar)userDate.clone();
    this.endDateAndTime = (Calendar)userDate.clone();
    this.startDateAndTime.set(Calendar.HOUR_OF_DAY, startTime.get(Calendar.HOUR_OF_DAY));
    this.startDateAndTime.set(Calendar.MINUTE, startTime.get(Calendar.MINUTE));
    this.endDateAndTime.set(Calendar.HOUR_OF_DAY, endTime.get(Calendar.HOUR_OF_DAY));
    this.endDateAndTime.set(Calendar.MINUTE, endTime.get(Calendar.MINUTE));
    if (startTime.compareTo(endTime) > 0)
      this.endDateAndTime.add(Calendar.DAY_OF_MONTH,1);
    long diffTimeInMilliSecs = this.endDateAndTime.getTimeInMillis() - this.startDateAndTime.getTimeInMillis();
    this.totalJourneyDurationInMinutes = diffTimeInMilliSecs / (1000 * 60);
    if (flightArr.get(nFlights-1).getDestination().equals("SINGAPORE"))
      this.totalJourneyDurationInMinutes += 150;
  }

  public String printCombinedFlights()
  {
    String outp = "";
    //System.out.println("No of Seats: " + nSeats);
    outp += "No of Seats: " + nSeats + "\n";
	//System.out.println("No of flights: " + nFlights);
    outp += "No of flights: " + nFlights + "\n";
    //System.out.println("Starting Date: " + startDateAndTime.getTime());
    outp += "Starting Date: " + startDateAndTime.getTime() + "\n";
    //System.out.println("Ending Date: " + endDateAndTime.getTime());
    outp += "Ending Date: " + endDateAndTime.getTime() + "\n";
    //System.out.println("Total Journey Duration in Minutes: " + totalJourneyDurationInMinutes);
    outp += "Total Journey Duration in Minutes: " + totalJourneyDurationInMinutes + "\n";
    //System.out.println("Layover Time in Minutes: " + layOverInMinutes);
    outp += "Layover Time in Minutes: " + layOverInMinutes + "\n\n";
    //System.out.println();
    for (int i = 0; i < flightArr.size(); i++)
      outp += flightArr.get(i).printFlightInfo();
   // System.out.println("------------------------------------------------------------------");
    outp += "";
    return outp;
  }

  public int getNoOfSeats()
  {
    return nSeats;
  }

  public int getNoOfFlights()
  {
    return nFlights;
  }

  public Calendar getStartDateAndTime()
  {
    return startDateAndTime;
  }

  public Calendar getEndDateAndTime()
  {
    return endDateAndTime;
  }

  public long getLayOverInMinutes()
  {
    return layOverInMinutes;
  }

  public long getTotalJourneyDurationInMinutes()
  {
    return totalJourneyDurationInMinutes;
  }

  public ArrayList<Flight> getFlights()
  {
    return flightArr;
  }

}
