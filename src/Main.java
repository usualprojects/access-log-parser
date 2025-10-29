import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите путь к файлу");
        String path = new Scanner(System.in).nextLine();
//        String path = "./src/access.log";
        File file = new File(path);
        boolean fileExists = file.exists();
        boolean isDirectory = file.isDirectory();
        if (!fileExists) {
            System.out.println("Введен путь к несуществующему файлу!Попробуйте снова.");
            return;
        }
        if (isDirectory) {
            System.out.println("Введен путь к папке, а не файлу!Попробуйте снова.");
            return;
        }
        System.out.println("Путь указан верно.");

        int countStr = 0;
        Statistics st = new Statistics();

        try (FileReader fileReader = new FileReader(path))
        {
            BufferedReader reader =
                    new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                int length = line.length();
                countStr++;
                if (length > 1024) {
                    throw new VeryLongStringException("Cтрока длиннее 1024 символов");
                    // либо создаем экземпляр RuntimeException throw new RuntimeException("Cтрока длиннее 1024 символов");
                }
                LogEntry s1 = new LogEntry(line);
                st.addEntry(s1);
            }
            System.out.println("Количество строк в файле " + countStr);
            System.out.println("TrafficRate " + (double) st.getTrafficRate());
        }
        catch (VeryLongStringException ex){
            System.err.println("Ошибка: " + ex.getMessage());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
