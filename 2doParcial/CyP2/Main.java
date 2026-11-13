
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Main {

    // Funcion para sumar dos numeros
    public static double sumar(double num1, double num2) {
        return num1 + num2;
    }

    // Funcion para dividir dos numeros
    public static double dividir(double num1, double num2) {
        return num1 / num2;
    }

    public static void main(String[] args) {
        try (Reader r = new InputStreamReader(System.in);
             BufferedReader br = new BufferedReader(r)) {

            String ch;

           System.out.print("Hola -> ");

            do {
                ch = br.readLine();

                if (ch != null && !ch.equalsIgnoreCase("exit()")) {

                    if (ch.equalsIgnoreCase("sumar")) {
                        System.out.print("Ingresa el primer numero: ");
                        double num1 = Double.parseDouble(br.readLine());

                        System.out.print("Ingresa el segundo numero: ");
                        double num2 = Double.parseDouble(br.readLine());

                        System.out.println("Resultado: " + sumar(num1, num2));

                    } else if (ch.equalsIgnoreCase("dividir")) {
                        System.out.print("Ingresa el primer numero: ");
                        double num1 = Double.parseDouble(br.readLine());

                        System.out.print("Ingresa el segundo numero: ");
                        double num2 = Double.parseDouble(br.readLine());

                        if (num2 != 0) {
                            System.out.println("Resultado: " + dividir(num1, num2));
                        } else {
                            System.out.println("Error: No se puede dividir entre cero.");
                        }

                    } else {
                        System.out.println("> Comando " + ch.length());
                    }
                }

            } while (ch != null && !ch.equalsIgnoreCase("exit()"));

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Debes ingresar un numero valido.");
        }
    }
}
