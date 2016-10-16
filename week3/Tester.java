
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog1_log");
        logAnalyzer.printAll();
        System.out.println("No of unique ids : " + logAnalyzer.countUniqueIPs());
        System.out.println("countUniqueIPsInRange");
        System.out.println(logAnalyzer.countUniqueIPsInRange(300,399)); 
    }
    
    public void testHigherThanNum(){
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog1_log");
        int value = 400;
        System.out.println("Records Higher than " + value + " :");
        logAnalyzer.printAllHigherThanNum(value);
    }
    
    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog2_log");
        System.out.println("uniqueIPVisitsOnDay");
        for (String ip : logAnalyzer.uniqueIPVisitsOnDay("Sep 27")){
            System.out.println(ip);
        }
         System.out.println("size" +logAnalyzer.uniqueIPVisitsOnDay("Sep 27").size());
    }
    
    public void testCountUniqueIPsInRange(){
        LogAnalyzer log = new LogAnalyzer();
        log.readFile("weblog2_log");
        System.out.println("countUniqueIPsInRange");
        System.out.println(log.countUniqueIPsInRange(400,499)); 
    }
    
    public void testUniqueIP(){
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("short-test_log");
        System.out.println("No of unique ips : " + logAnalyzer.countUniqueIPs());
    }
    
    public void test(){
        System.out.println("Testing countVisitsPerIP");
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
        
        System.out.println("Testing mostNumberVisitsByIP");
        System.out.println("MostNumberVisitsByIP : " + la.mostNumberVisitsByIP(counts));
        
        System.out.println("Testing iPsMostVisits");
        ArrayList<String> ips = la.iPsMostVisits(counts);
        for (String ip : ips)
            System.out.println(ip);
        
        System.out.println("Testing iPsForDays");
        HashMap <String, ArrayList<String>> map =la.iPsForDays();
        System.out.println(map);
        
        System.out.println("Testing dayWithMostIPVisits");
        System.out.println("dayWithMostIPVisits : " + la.dayWithMostIPVisits(map) );
        
        
        System.out.println("Testing iPsWithMostVisitsOnDay ");
        ArrayList<String> ipList = la.iPsWithMostVisitsOnDay(map, "Sep 29");
        for (String ip : ipList)
            System.out.println(ip);
        
        
    }
    
    
    
}
