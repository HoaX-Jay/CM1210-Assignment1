/*
 * Name: Jayden-John Peters
 * Student number: 21091175
 */
import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;





public class Assignment1 {

  public static void main(String[] args) {
    //creates array and checks what args is - either file or date object
    ArrayList<SimpleDate> dates = new ArrayList<>();
    String input = args[0];
    File file = new File(input);
    if (file.exists()) {
      dates = printDates(input);

    }else{

        String argsInput = args[0];
        SimpleDate date = parseDate(argsInput);
        if (date != null) {
          dates.add(date);
        }else{
          System.out.println("INVALID ENTRY");
          return;
        }
    }
    // picks day to count and counts them and tells most frequent day of the week
    String dow = "Sat";
    int count = countDatesOnDay(dates, dow);
    String mostFrequent = mostFrequentDayOfWeek(dates);
    System.out.println(dates);
    System.out.println("Number of dates falling on "+ dow + " is : "+ count);
    System.out.println("The most frequent day is : " + mostFrequent);

  }




  public static ArrayList<SimpleDate> printDates(String fileName){
    // combines print dates and file reader , puts each line of file through parseDate 
    ArrayList<SimpleDate> dates = new ArrayList<>();
    try {
      BufferedReader reader = new BufferedReader(new FileReader(fileName));
      String line = reader.readLine();
      while (line != null) {
        SimpleDate date = parseDate(line);
        if (date != null) {
          dates.add(date);
          System.out.println(date);
        } else {
          System.out.println("INVALID ENTRY: " + line);
        }
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
      System.out.println("Error reading file: " + fileName);

    }
    return dates;

  }



    public static SimpleDate parseDate(String argsInput){
    //allows any of three date formats to be put into SimpleDate
      String [] formats = {"dd/MM/yyyy","dd-MM-yyyy","d MMMM yyyy"};
      String [] suffix = {"st","nd","rd","th"};
      for (String format:formats) {
        //remove date suffix eg. 15th - th
        if (format == "d MMMM yyyy") {
          String [] splitString = argsInput.split(" ");
          for (int i = 0;i < splitString.length ;i++ ) {
            if (i == 0) {
              String tidyDay = splitString[i].replaceAll("\\D", "");
              splitString[i] = tidyDay;


            }
          }

          argsInput = String.join(" ",splitString);
          System.out.println(argsInput);

        }

        try{
          java.util.Date date = new java.text.SimpleDateFormat(format).parse(argsInput);
          java.util.Calendar cal = java.util.Calendar.getInstance();
          cal.setTime(date);
          int year = cal.get(java.util.Calendar.YEAR);
          int month = cal.get(java.util.Calendar.MONTH)+1;
          int day = cal.get(java.util.Calendar.DAY_OF_MONTH);
          return new SimpleDate(year,month,day);
        }catch (java.text.ParseException e){
        }
      }
      return  null;

    }



    public static String dayOfWeek( SimpleDate date ) {
      String [] nameOfDays = {"Sat", "Sun","Mon", "Tue", "Wed", "Thu", "Fri"};

      int Day = date.getDay();
      int Month = date.getMonth();
      int Year = date.getYear();
      if (Month < 3) {
        Month += 12;
        Year -= 1;
      }
      int C = Year%100;
      int D = Year/100;
      int W = (13*(Month+1))/5;
      int X = C/4;
      int Y = D/4;
      int Z = W + X + Y + Day + C - 2 * D;
      int dayNum = Z%7;
      if (dayNum < 0) {
        dayNum += 7;

      }
      return (nameOfDays[dayNum]);



    }



    public static int countDatesOnDay( ArrayList<SimpleDate> dates, String dayOfWeek ) {
      //counts the dates on chosen day of the week
         int count = 0;
         for (SimpleDate date : dates ) {
           String dow = dayOfWeek(date);
           if (dow.equals(dayOfWeek)) {
             count++;

           }

         }


        return count;
    }


    public static String mostFrequentDayOfWeek( ArrayList<SimpleDate> dates ) {

        if (dates.size() == 0) {
          return null;

        }
        int[] countDays = new int[7];
        String [] nameOfDays = {"Mon", "Tue", "Wed", "Thu", "Fri","Sat", "Sun"};

        for (SimpleDate date : dates ) {
          String dow = dayOfWeek(date);
          for (int i = 0; i < nameOfDays.length; i++ ) {
            if (nameOfDays[i].equals(dow)) {
              countDays[i]++;
              break;

            }

          }

        }
        int maxCount = countDays[0];
        int maxIndex = 0;
        for (int i = 0; i <countDays.length; i++ ) {
          if (countDays[i] > maxCount) {
            maxCount = countDays[i];
            maxIndex = i;

          }

        }
        for (int i =0; i <countDays.length; i++ ) {
          if (i != maxIndex && countDays[i] == maxCount) {
            if (i < maxIndex && i >= 0 && i <=4) {
              maxIndex = i;

            }

          }

        }
        return nameOfDays[maxIndex];


    }
}
