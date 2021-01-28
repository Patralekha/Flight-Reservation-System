import java.io.*;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

class DataManager
{
    private FRSManager frsMgr;
    public DataManager(FRSManager frsMgr)
    {
          this.frsMgr = frsMgr;
    }

    private int[] preProcessingDays(String days)
    {
      int[] preProcessedDays;
      if (days.equals("DAILY"))
      {
         preProcessedDays = new int[]{1,2,3,4,5,6,7};
      }
      else
      {
         String[] flightDays = days.split(", |,");
         preProcessedDays = new int[flightDays.length];
         for (int d=0; d < flightDays.length; d++)
         {
           if (flightDays[d].toLowerCase().contains("sun"))
               preProcessedDays[d] = 1;
            else if (flightDays[d].toLowerCase().contains("mon"))
                  preProcessedDays[d] = 2;
            else if (flightDays[d].toLowerCase().contains("tue"))
                preProcessedDays[d] = 3;
            else if (flightDays[d].toLowerCase().contains("wed"))
                preProcessedDays[d] = 4;
            else if (flightDays[d].toLowerCase().contains("thu"))
                preProcessedDays[d] = 5;
            else if (flightDays[d].toLowerCase().contains("fri"))
                preProcessedDays[d] = 6;
            else if (flightDays[d].toLowerCase().contains("sat"))
                preProcessedDays[d] = 7;
         }
      }
      Arrays.sort(preProcessedDays);
      return preProcessedDays;
    }

    private String preProcessingVia(String via)
    {
      String preProcessedVia;
      if (via.equals("-"))
         preProcessedVia = "None";
      else
         preProcessedVia = via;
      return preProcessedVia;
    }

    public ArrayList<Flight> readSpiceJet() throws Exception
    {
       BufferedReader br = new BufferedReader(new FileReader(new File(frsMgr.SPICEJET_CSV_PATH)));
       SimpleDateFormat time = new SimpleDateFormat("hh:mm aa");
       SimpleDateFormat date = new SimpleDateFormat("dd MMM yy");
       String line;
       String[] arrayOfLines = new String[100];
       int numOfLines = 0;
       while ((line = br.readLine()) != null)
       {
          arrayOfLines[numOfLines] = line;
          numOfLines++;
       }
       ArrayList<Flight> flightDomestic = new ArrayList<Flight>();
       for (int i=5; i<numOfLines;i++)
       {
         String[] subStrings = arrayOfLines[i].split("\\|");
         String flightSource = subStrings[0];
         String flightDestination = subStrings[1];
         String flightName = subStrings[3];
         String flightVia = preProcessingVia(subStrings[6]);
         int[] flightDays = preProcessingDays(subStrings[2]);
         Calendar flightDepartureTime = Calendar.getInstance();
         flightDepartureTime.setTime(time.parse(subStrings[4]));
         Calendar flightArrivalTime = Calendar.getInstance();
         flightArrivalTime.setTime(time.parse(subStrings[5]));
         Calendar flightEffectiveFrom = Calendar.getInstance();
         flightEffectiveFrom.setTime(date.parse(subStrings[7]));
         Calendar flightEffectiveTill = Calendar.getInstance();
         flightEffectiveTill.setTime(date.parse(subStrings[8]));
         Flight flight = new Flight(flightName, flightSource, flightDestination, flightVia, flightArrivalTime, flightDepartureTime, flightEffectiveFrom, flightEffectiveTill, flightDays);
         flightDomestic.add(flight);
         //flight.printFlightInfo();
       }
       return flightDomestic;
    }

    public ArrayList<Flight> readSilkAir() throws Exception
    {
        BufferedReader br = new BufferedReader(new FileReader(new File(frsMgr.SILKAIR_CSV_PATH)));
        SimpleDateFormat time = new SimpleDateFormat("HHmm");
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        Calendar effFrom = Calendar.getInstance();
        effFrom.setTime(date.parse("01-09-2016"));
        Calendar effTill = Calendar.getInstance();
        effTill.setTime(date.parse("13-11-2016"));
        String line;
        String[] arrayOfLines = new String[100];
        int numOfLines = 0;
        while ((line = br.readLine()) != null)
        {
           arrayOfLines[numOfLines] = line;
           numOfLines++;
        }
        ArrayList<Flight> flightInternational = new ArrayList<Flight>();
        for (int i=3; i<numOfLines;i++)
        {
            String[] subStrings = arrayOfLines[i].split("\\|");
            String flightDestination = "SINGAPORE";
            String flightSource = subStrings[0].split(" ")[0].toUpperCase();
            String flightName = subStrings[2];
            String flightVia = "None";
            int[] flightDays = preProcessingDays(subStrings[1]);
            Calendar flightArrivalTime = Calendar.getInstance();
            flightArrivalTime.setTime(time.parse(subStrings[3].split("/")[1]));
            Calendar flightDepartureTime = Calendar.getInstance();
            flightDepartureTime.setTime(time.parse(subStrings[3].split("/")[0]));
            Calendar flightEffectiveFrom = effFrom;
            Calendar flightEffectiveTill = effTill;
            Flight flight = new Flight(flightName, flightSource, flightDestination, flightVia, flightArrivalTime, flightDepartureTime, flightEffectiveFrom, flightEffectiveTill, flightDays);
            flightInternational.add(flight);
            //flight.printFlightInfo();
        }
        return flightInternational;
    }

    /*public static void main(String args[]) throws Exception
    {
     DataManager dataMgr = new DataManager();
     String spiceJetCsvPath = "2016.spicejet.csv";
     String silkAirCsvPath = "2016.silkair.csv";
     ArrayList<Flight> Domestic = dataMgr.readSpiceJet(spiceJetCsvPath);
     ArrayList<Flight> International = dataMgr.readSilkAir(silkAirCsvPath);
   }*/
}
