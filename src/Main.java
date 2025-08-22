import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите первое число:");
        int firstNumber = new Scanner(System.in).nextInt();
        System.out.println("Введите второе число:");
        int secondNumber = new Scanner(System.in).nextInt();

        int sum = firstNumber + secondNumber;
        System.out.println("Сумма равна: " + sum);

        int diff = firstNumber - secondNumber;
        System.out.println("Разность равна: " + diff);

        int multiplication = firstNumber * secondNumber;
        System.out.println("Произведение равно: " + multiplication);

        double quotient = (double) firstNumber / secondNumber;
        System.out.println("Частное равно: " + quotient);
    }
}
