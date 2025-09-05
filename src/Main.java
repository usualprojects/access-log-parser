import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int i = 1;
       while (true) {
           System.out.println("Введите путь к файлу");
           String path = new Scanner(System.in).nextLine();
           File file = new File(path);
           boolean fileExists = file.exists();
           boolean isDirectory = file.isDirectory();
           if(!fileExists) {
               System.out.println("Введен путь к несуществующему файлу!Попробуйте снова.");
               continue;}
           if(isDirectory) {
               System.out.println("Введен путь к папке, а не файлу!Попробуйте снова.");
               continue;}
           System.out.println("Путь указан верно.");
           System.out.println("Это файл номер " + i + "");
           i++;

       }
    }
}
