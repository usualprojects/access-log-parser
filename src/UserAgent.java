import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAgent {
    final private String userAgent;
    final private String os;
    final private String browser;

    public UserAgent(String userAgent){
        this.userAgent=userAgent;
        String s="", os="", br="";
        Pattern pat = Pattern.compile("\\((.*?)\\)");
        Matcher m = pat.matcher(this.userAgent);

        if(m.find()) {
            s = m.group(1);
            os = getOperatingSystem(s);
            String[] st = userAgent.split("\\)", 2);
            if(st.length >=2){
                s = st[1];
                br = getBrowser(s);
            }
        }
        this.os = os;
        this.browser = br;
    }

    public static String getOperatingSystem(String str) {
        if (str.contains("Win")) {
            return "Windows";
        } else if (str.contains("Mac")) {
            return "macOS";
        } else if (str.contains("Linux")) {
            return "Linux";
        }
        return "Other";
    }

    public static String getBrowser(String str) {
        if (str.contains("OPR") || str.contains("Opera")) {
            return "Opera";
        } else if (str.contains("Edg")) {
            return "Edge";
        } else if (str.contains("Gecko/")) {
            return "Mozila";
        } else if (str.contains("Chrome")) {
            return "Chrome";
        } else if (str.contains("Safari")) {
            return "Safari";
        }
        return "Other";
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getOs() {
        return os;
    }

    public String getBrowser() {
        return browser;
    }
}
