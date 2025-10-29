import java.time.Duration;
import java.time.LocalDateTime;

public class Statistics {
    private double totalTraffic;
    private LocalDateTime minTime, maxTime;

    public Statistics(){
        this.totalTraffic = 0;
        this.maxTime=LocalDateTime.MIN;
        this.minTime=LocalDateTime.MAX;
    }

    public void addEntry(LogEntry log){
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
//        System.out.println("minTime "+minTime);
//        System.out.println("maxTime "+maxTime);
        Duration duration = Duration.between(this.minTime, this.maxTime);
        long diffInHours = duration.toHours();
//        System.out.println("Total traffic "+ totalTraffic);
//        System.out.println("Duration "+duration);
//        System.out.println("Duration hours "+diffInHours);
        return (double) totalTraffic / diffInHours;
    }
}
