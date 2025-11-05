import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class LogEntry {
    final private String ipAddr;
    final private String twoProperty;
    final private LocalDateTime dateTime;
    final private HttpMethod method;
    final private String path;
    final private int codeHttp;
    final private int size;
    final private String referer;
    final private UserAgent agent;

    public LogEntry(String str){
        String s[] = str.split(" ",2);
        this.ipAddr = s[0];
        s = s[1].split("\\[", 2);
        this.twoProperty = s[0];
        s = s[1].split("\\]", 2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/LLL/yyyy:H:mm:ss Z").withLocale(Locale.UK);
        this.dateTime = LocalDateTime.parse(s[0], formatter);
//        System.out.println("dateTime="+dateTime);
        s = s[1].split("\"", 3);
        this.method = HttpMethod.valueOf(s[1].split(" ",2)[0]);
        this.path = s[1].split(" ",3)[1];
        s = s[2].split(" ", 5);
        this.codeHttp = Integer.parseInt(s[1]);
        this.size = Integer.parseInt(s[2]);;
        this.referer = s[3];
//        System.out.println("user-agent="+ s[4]);
        this.agent = new UserAgent(s[4]);
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public String getTwoProperty() {
        return twoProperty;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getMethod() {
        return method.toString();
    }

    public String getPath() {
        return path;
    }

    public int getSize() {
        return size;
    }

    public String getReferer() {
        return referer;
    }

    public UserAgent getAgent() {
        return agent;
    }

    public int getCodeHttp() {
        return codeHttp;
    }

}
