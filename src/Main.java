import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите путь к файлу");
        String path = new Scanner(System.in).nextLine();
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
        int countStr=0, shortStr=1000, longStr=0;
        boolean veryLong=false;
        try (FileReader fileReader = new FileReader(path))
        {
            BufferedReader reader =
                    new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null)
            {
                int length = line.length();
                countStr++;
                if(length < shortStr) shortStr=length;
                if(length > longStr) longStr=length;
                if(length > 1024) {
                    veryLong=true;
                    break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if(veryLong){
            throw new VeryLongStringException("Cтрока длиннее 1024 символов");
            // либо создаем экземпляр RuntimeException throw new RuntimeException("Cтрока длиннее 1024 символов");
        }
        System.out.println("Количество строк в файле " + countStr);
        System.out.println("Длина самой длинной строки в файле " + longStr);
        System.out.println("Длина самой короткой строки в файле " + shortStr);
    }
}
