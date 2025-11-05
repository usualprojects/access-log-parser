import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class Statistics {
    private double totalTraffic;
    private LocalDateTime minTime, maxTime;
    private HashSet<String> pages;
    private HashMap<String, Integer> countOs;

    public Statistics(){
        this.totalTraffic = 0;
        this.maxTime=LocalDateTime.MIN;
        this.minTime=LocalDateTime.MAX;
        this.pages = new HashSet<>();
        this.countOs = new HashMap<>();
    }

    public void addEntry(LogEntry log){
        if(log.getCodeHttp()==200) {
            pages.add(log.getPath());
//            System.out.println("path =" +log.getPath());
        }
        String os = log.getAgent().getOs();
        if(this.countOs.containsKey(os)){
            this.countOs.put(os, this.countOs.get(os)+1);
        }
        else {
            this.countOs.put(os, 1);
        }
//        System.out.println("size =" +log.getSize());
        this.totalTraffic += log.getSize();
//        System.out.println("Total " + this.totalTraffic);
        if(log.getDateTime().isBefore(minTime)) {
            minTime=log.getDateTime();
//            System.out.println("min");
        }
        if(log.getDateTime().isAfter(maxTime)){
            maxTime=log.getDateTime();
//            System.out.println("max");
        }
    }
    public double getTrafficRate(){
        Duration duration = Duration.between(this.minTime, this.maxTime);
        long diffInHours = duration.toHours();
        return (double) totalTraffic / diffInHours;
    }

    public List<String> getExistPages() {
        return new ArrayList<>(this.pages);
    }

    public HashMap<String, Double> getStatisticOs(){
        int countAllOs = 0;
//        Double check = 0.0;
        for (Map.Entry<String, Integer> entry: this.countOs.entrySet()) {
            countAllOs += entry.getValue();
        }
        HashMap<String, Double> StatisticOs = new HashMap<>();
        for (Map.Entry<String, Integer> entry: this.countOs.entrySet()) {
            Double part = (double) entry.getValue()/countAllOs;
            StatisticOs.put(entry.getKey(), part);
//            check += part;
        }
//        System.out.println("Check " + check);
        return StatisticOs;
    }
}
