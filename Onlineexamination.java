import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class OnlineExam {
    static String username = "user";
    static String password = "pass";
    static int score = 0;
    static boolean isLoggedIn = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Online Examination System");

        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    static void login(Scanner scanner) {
        System.out.print("Enter username: ");
        String userInput = scanner.next();
        System.out.print("Enter password: ");
        String passInput = scanner.next();

        if (userInput.equals(username) && passInput.equals(password)) {
            isLoggedIn = true;
            System.out.println("Login successful!");
            examMenu(scanner);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    static void examMenu(Scanner scanner) {
        while (isLoggedIn) {
            System.out.println("1. Update Profile");
            System.out.println("2. Start Exam");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    updateProfile(scanner);
                    break;
                case 2:
                    startExam(scanner);
                    break;
                case 3:
                    isLoggedIn = false;
                    System.out.println("Logged out successfully!");
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    static void updateProfile(Scanner scanner) {
        System.out.print("Enter new username: ");
        username = scanner.next();
        System.out.print("Enter new password: ");
        password = scanner.next();
        System.out.println("Profile updated successfully!");
    }

    static void startExam(Scanner scanner) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time's up! Exam submitted.");
                System.out.println("Your score: " + score);
                isLoggedIn = false;
                System.exit(0);
            }
        }, 30000); // 30 seconds

        System.out.println("Question 1: What is the capital of France?");
        System.out.println("A) Paris");
        System.out.println("B) London");
        System.out.println("C) Berlin");
        System.out.println("D) Rome");
        System.out.print("Enter your answer: ");
        String answer = scanner.next();
        if (answer.equalsIgnoreCase("A")) {
            score++;
        }

        System.out.println("Your score: " + score);
    }
}

