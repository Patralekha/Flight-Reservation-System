import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

 public class LogicManager
 {
    private FRSManager frsMgr;

    public LogicManager(FRSManager frsMgr)
    {
      this.frsMgr = frsMgr;
    }

    public boolean checkNSeats()
    {
      if (frsMgr.N_SEATS >= 1 && frsMgr.N_SEATS <= 10)
        {
          return true;
        }
      return false;
    }

    public  ArrayList<CombinedFlights> checkFlights() throws Exception
    {
      ArrayList<CombinedFlights> matchedFlights = new ArrayList<CombinedFlights>();
      SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MM-yyyy");
      Calendar userDate = Calendar.getInstance();
      userDate.setTime(simpleDate.parse(frsMgr.USER_DATE));
      int day = userDate.get(Calendar.DAY_OF_WEEK);
      for(int i = 0; i < frsMgr.allFlights.size(); i++)
      {
        Calendar effectiveFromFirstFlight = frsMgr.allFlights.get(i).getEffectiveFrom();
        Calendar effectiveTillFirstFlight = frsMgr.allFlights.get(i).getEffectiveTill();
      	if(frsMgr.allFlights.get(i).getSource().equals(frsMgr.USER_SOURCE) && frsMgr.allFlights.get(i).getDestination().equals(frsMgr.USER_DESTINATION)
                                    && userDate.compareTo(effectiveFromFirstFlight)>=0 && userDate.compareTo(effectiveTillFirstFlight)<=0 && Arrays.binarySearch(frsMgr.allFlights.get(i).getDays(), day) >= 0)
            {
              CombinedFlights combFlights = new CombinedFlights();
              combFlights.addFlight(frsMgr.allFlights.get(i));
              combFlights.setDetails(userDate, frsMgr.N_SEATS, 0);
              matchedFlights.add(combFlights);
            }

        if(frsMgr.allFlights.get(i).getSource().equals(frsMgr.USER_SOURCE) && userDate.compareTo(effectiveFromFirstFlight)>=0
                                    && userDate.compareTo(effectiveTillFirstFlight)<=0 && Arrays.binarySearch(frsMgr.allFlights.get(i).getDays(), day) >= 0)
              {
                for (int j=0; j < frsMgr.allFlights.size(); j++)
                {
                  Calendar effectiveFromSecondFlight = frsMgr.allFlights.get(i).getEffectiveFrom();
                  Calendar effectiveTillSecondFlight = frsMgr.allFlights.get(i).getEffectiveTill();

                  Calendar departureTime1 = frsMgr.allFlights.get(i).getDepartureTime();
                  Calendar arrivalTime1 = frsMgr.allFlights.get(i).getArrivalTime();
                  Calendar departureTime2 = frsMgr.allFlights.get(j).getDepartureTime();

                  departureTime1.set(userDate.get(Calendar.YEAR), userDate.get(Calendar.MONTH), userDate.get(Calendar.DAY_OF_MONTH));
                  arrivalTime1.set(userDate.get(Calendar.YEAR), userDate.get(Calendar.MONTH), userDate.get(Calendar.DAY_OF_MONTH));
                  departureTime2.set(userDate.get(Calendar.YEAR), userDate.get(Calendar.MONTH), userDate.get(Calendar.DAY_OF_MONTH));

                  if (departureTime1.compareTo(arrivalTime1) > 0)
                  {
                    arrivalTime1.add(Calendar.DAY_OF_MONTH,1);
                    departureTime2.add(Calendar.DAY_OF_MONTH,1);
                  }
                  if (departureTime1.compareTo(departureTime2) > 0)
                  {
                    departureTime2.add(Calendar.DAY_OF_MONTH,1);
                  }

                  long layOverInMinutes = (departureTime2.getTimeInMillis() - arrivalTime1.getTimeInMillis()) / (1000 * 60);
                  if (frsMgr.allFlights.get(j).getSource().equals(frsMgr.allFlights.get(i).getDestination()) && frsMgr.allFlights.get(j).getDestination().equals(frsMgr.USER_DESTINATION)
                                              && departureTime2.compareTo(effectiveFromSecondFlight) >= 0 && departureTime2.compareTo(effectiveTillSecondFlight) <= 0
                                                            && Arrays.binarySearch(frsMgr.allFlights.get(i).getDays(), day) >= 0 && layOverInMinutes >= 2*60 && layOverInMinutes <= 6*60)
                  {
                    CombinedFlights combFlights = new CombinedFlights();
                    combFlights.addFlight(frsMgr.allFlights.get(i));
                    combFlights.addFlight(frsMgr.allFlights.get(j));
                    combFlights.setDetails(userDate, frsMgr.N_SEATS, layOverInMinutes);
                    matchedFlights.add(combFlights);
                  }
                }
              }
      }
      matchedFlights.sort(Comparator.comparing(CombinedFlights::getTotalJourneyDurationInMinutes));
      return matchedFlights;
  	}
 }
