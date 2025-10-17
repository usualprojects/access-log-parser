import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogString {
    public String ip;
    public String twoProperty;
    public String dateTime;
    public String methodAndPath;
    public String codeHttp;
    public String size;
    public String path;
    public String userAgent;

    public LogString(String str){
        parseString(str);
    }

    public String getInputFromUserAgent()
    {
        String s="", input="";
        Pattern pat = Pattern.compile("\\((.*?)\\)");
        Matcher m = pat.matcher(this.userAgent);

        if(m.find()) s = m.group(1);
        String[] parts = s.split(";");
        if(parts.length>=2) input = parts[1].strip().split("/")[0];
        return input;
    }

    private void parseString(String str) {
        String s[] = str.split(" ",2);
        this.ip = s[0];
//        System.out.println("ip=" + ip);
        s = s[1].split("\\[", 2);
        this.twoProperty = s[0];
//        System.out.println("property=" + twoProperty);
        s = s[1].split("\\]", 2);
        this.dateTime = s[0];
//        System.out.println("dateTime="+dateTime);
        s = s[1].split("\"", 3);
        this.methodAndPath = s[1];
//        System.out.println("method="+methodAndPath);
        s = s[2].split(" ", 5);
//        System.out.println("split "+ Arrays.toString(s));
        this.codeHttp = s[1];
//        System.out.println("code="+codeHttp);
        this.size = s[2];
//        System.out.println("size="+size);
        this.path = s[3];
//        System.out.println("path="+path);
        this.userAgent = s[4];
        System.out.println("user-agent="+userAgent);
    }
}
