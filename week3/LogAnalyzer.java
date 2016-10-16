
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()){
             WebLogParser webLogParser = new WebLogParser();
             LogEntry logEntry = webLogParser.parseEntry(line);
             records.add(logEntry);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> uniqueIds = new ArrayList<String>();
         for (LogEntry entry : records){
             String ipAddress = entry.getIpAddress();
             if(!uniqueIds.contains(ipAddress))
                uniqueIds.add(ipAddress);
         }
         return uniqueIds.size();
     }
     
     public void printAllHigherThanNum(int num){
         for (LogEntry entry : records){
             int statusCode = entry.getStatusCode();
             if(statusCode > num)
                System.out.println(entry);
         }
     }
     
     public ArrayList<String> uniqueIPVisitsOnDay(String someDay){
         ArrayList<String> uniqueIds = new ArrayList<String>();
         for (LogEntry entry : records){
             String day = entry.getAccessTime().toString();
             String ipAddress = entry.getIpAddress();
             day = day.substring(4, 4+6);
             if (day.equals(someDay))
                if(!uniqueIds.contains(ipAddress))
                    uniqueIds.add(ipAddress);
         }
         return uniqueIds;
     }
     
     public int countUniqueIPsInRange(int low, int high){
         ArrayList<String> uniqueIds = new ArrayList<String>();
         for (LogEntry entry : records){
             int statusCode = entry.getStatusCode();
             String ipAddress = entry.getIpAddress();
             if (statusCode <= high && statusCode >= low)
                if(!uniqueIds.contains(ipAddress))
                    uniqueIds.add(ipAddress);
         }
         return uniqueIds.size();
     }
     // return number of visits per IP.
     public HashMap<String, Integer> countVisitsPerIP(){
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for (LogEntry le : records){
             String ipAddress = le.getIpAddress();
             if (!counts.containsKey(ipAddress))
                 counts.put(ipAddress, 1);
             else
                counts.put(ipAddress, counts.get(ipAddress) + 1);
         }
         return counts;
     }
       // return the number of most visits by IP.
     public int mostNumberVisitsByIP(HashMap<String, Integer> map){
         int max = 0;
         int count = 0;
         for (String ip : map.keySet()){
             count = map.get(ip);
             if(count > max)
                max = count;
         }
         return max;
     }
      //return the IP list with most visit numer above.
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map){
         int max = mostNumberVisitsByIP(map);
         int count = 0;
         ArrayList<String> ips = new ArrayList<String>();
         for (String ip : map.keySet()){
             count = map.get(ip);
             if(count == max)
                ips.add(ip);
         }
         return ips;         
     }
     // returns ips for each day 
     public HashMap<String, ArrayList<String>> iPsForDays(){
         HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
         for (LogEntry entry : records){
             String day = entry.getAccessTime().toString();
             day = day.substring(4, 4+6);
             map.put(day, uniqueIPVisitsOnDay(day));
         }
         return map;
    }
     // return the day with most IP visits.
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map){
        String maxDay = "";
        int count = 0;
        int max = 0;
        for (String day : map.keySet()){
             count = map.get(day).size();
             if(count > max){
                 max = count;
                 maxDay = day;
             }
         }
        return maxDay;
    }
    // return the IP list with most visits on a given day.
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String day){
        ArrayList<String> ipsList = new ArrayList<String>();
        HashMap<String, Integer> count = new HashMap<String, Integer>();
		
        for (LogEntry entry : records){
             String time = entry.getAccessTime().toString();
             String ipAddress = entry.getIpAddress();
             time = time.substring(4, 4+6);
             if (time.equals(day))
                 ipsList.add(ipAddress);
         }
        
        for (String ip: ipsList){
			if (!count.containsKey(ip))
				count.put(ip, 1);
				count.put(ip, count.get(ip)+1);
		}

		return iPsMostVisits(count);
    }
}
