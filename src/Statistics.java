import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class Statistics {
    private double totalTraffic;
    private LocalDateTime minTime, maxTime;
    private HashSet<String> existPages;
    private HashSet<String> nonexistPages;
    private HashMap<String, Integer> countOs;
    private HashMap<String, Integer> countBrowser;

    public Statistics(){
        this.totalTraffic = 0;
        this.maxTime=null;
        this.minTime=null;
        this.existPages = new HashSet<>();
        this.nonexistPages = new HashSet<>();
        this.countOs = new HashMap<>();
        this.countBrowser = new HashMap<>();
    }

    public void addEntry(LogEntry log){
        sortPages(log);
        counting(log.getAgent().getBrowser(), this.countBrowser);
        counting(log.getAgent().getOs(), this.countOs);
        this.totalTraffic += log.getSize();
        if(minTime == null || log.getDateTime().isBefore(minTime)) {
            minTime=log.getDateTime();
        }
        if(maxTime == null || log.getDateTime().isAfter(maxTime)){
            maxTime=log.getDateTime();
        }
    }

    private void counting(String key, HashMap<String, Integer> hashCount){
        if(hashCount.containsKey(key)){
            hashCount.put(key, hashCount.get(key)+1);
        }
        else {
            hashCount.put(key, 1);
        }
    }

    private void sortPages(LogEntry log) {
        if(log.getCodeHttp()==200) {
            existPages.add(log.getPath());
        }
        else {
            if (log.getCodeHttp()==404) {
                nonexistPages.add(log.getPath());
            }
        }
    }

    public double getTrafficRate(){
        Duration duration = Duration.between(this.minTime, this.maxTime);
        long diffInHours = duration.toHours();
        return (double) totalTraffic / diffInHours;
    }

    public List<String> getExistPages() {
        return new ArrayList<>(this.existPages);
    }

    public HashMap<String, Double> getStatisticOs(){
        return getStatistic(this.countOs);
    }

    public HashMap<String, Double> getStatisticBrowser(){
        return getStatistic(this.countBrowser);
    }

    private HashMap<String, Double> getStatistic(HashMap<String, Integer> hashCount){
        int countAll = 0;
//        Double check = 0.0;
        for (Map.Entry<String, Integer> entry: hashCount.entrySet()) {
            countAll += entry.getValue();
        }
        HashMap<String, Double> Statistic = new HashMap<>();
        for (Map.Entry<String, Integer> entry: hashCount.entrySet()) {
            Double part = (double) entry.getValue()/countAll;
            Statistic.put(entry.getKey(), part);
//            check += part;
        }
//        System.out.println("Check " + check);
        return Statistic;
    }

    public List<String> getNonexistPages() {
        return new ArrayList<>(this.nonexistPages);
    }
}
