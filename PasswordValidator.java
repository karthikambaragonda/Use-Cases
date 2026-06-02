import java.util.Scanner;

public class PasswordValidator {
    public static void main(String args[]) {
        System.out.println("SafeLog Passoword Validator");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter password");
            String userPassword = sc.nextLine();
            if (userPassword == null || userPassword.length() < 8) {
                System.out
                        .println("Password is too short. Minimum 8 characters needed.Please Reenter the new passwrod");
                System.out.println();
            } else {
                boolean hasDigit = false;
                boolean isCapital = false;
                for (int i = 0; i < userPassword.length(); i++) {
                    if (Character.isDigit(userPassword.charAt(i))) {
                        hasDigit = true;
                    }
                    if (Character.isUpperCase(userPassword.charAt(i))) {
                        isCapital = true;
                    }
                }
                if (hasDigit && isCapital) {
                    System.out.println();
                    System.out.println("---------Passowrd Validated.--------");
                    System.out.println("Your Strong password is:  " + userPassword);
                    System.out.println("------------------------------------");
                    break;
                } else if (!hasDigit && !isCapital) {
                    System.out.println();
                    System.out.println(
                            "Your Password must Contain a digit and a capital letter.Please Reenter the new password");

                } else if (hasDigit) {
                    System.out.println();
                    System.out.println(
                            "Atleast One capital letter required for Password.Please re enter the new password.");
                } else {
                    System.out.println();
                    System.out.println("Atleast One digit is required for password.Please re enter the new password.");
                }

            }

        }
        sc.close();
    }

}
