import java.util.Scanner;
import java.util.regex.Pattern;

public class TerminalStreams {

    // Credenciales de prueba (puedes cambiarlas)
    private static final String USUARIO_VALIDO = "emmanuel";
    private static final String PASSWORD_VALIDA = "CyP2024";
    
    private static boolean sesionIniciada = false;

    public static void main(String[] args) {
        // 1. Personalizar la Terminal 
        System.out.println("========================================");
        System.out.println("     TERMINAL CY P - EMMANUEL 75449     ");
        System.out.println("========================================");
        System.out.println("Escribe 'help' para ver los comandos.");
        
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            // 2. Prompt personalizado (Cambia según si hay sesión o no)
            if (sesionIniciada) {
                System.out.print(USUARIO_VALIDO + "@terminal:~/CyP$ ");
            } else {
                System.out.print("guest@terminal:~/CyP$ ");
            }

            // Usamos Streams para procesar la entrada del usuario de forma limpia
            String input = scanner.nextLine().trim();
            
            // Si la línea está vacía, ignorar y pedir de nuevo
            if (input.isEmpty()) continue;

            // Separar el comando de los argumentos
            String[] partes = input.split("\\s+", 2);
            String comando = partes[0].toLowerCase();
            String argumentos = (partes.length > 1) ? partes[1] : "";

            switch (comando) {
                case "help":
                    mostrarHelp();
                    break;
                case "login":
                    hacerLogin(argumentos, scanner);
                    break;
                case "clear":
                    // Secuencia de escape ANSI para limpiar pantalla en Linux/Ubuntu
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    break;
                case "exit":
                    System.out.println("Cerrando sesión en la terminal...");
                    salir = true;
                    break;
                default:
                    System.out.println("Comando no reconocido: '" + comando + "'. Escribe 'help'.");
            }
        }
        scanner.close();
    }

    // --- COMANDO HELP ---
    private static void mostrarHelp() {
        System.out.println("\n--- COMANDOS DISPONIBLES ---");
        System.out.println("help  : Muestra este menú de ayuda.");
        System.out.println("login : Inicia sesión. Uso: login <usuario> <contraseña>");
        System.out.println("clear : Limpia la pantalla de la terminal.");
        System.out.println("exit  : Cierra la terminal.");
        System.out.println("----------------------------\n");
    }

    // --- COMANDO LOGIN CON REGEX ---
    private static void hacerLogin(String argumentos, Scanner scanner) {
        if (sesionIniciada) {
            System.out.println("Ya has iniciado sesión como " + USUARIO_VALIDO);
            return;
        }

        System.out.println("\n--- INICIO DE SESIÓN ---");
        
        // Si no pasaron argumentos, los pedimos interactivamente
        String usuario = argumentos;
        if (usuario.isEmpty()) {
            System.out.print("Usuario: ");
            usuario = scanner.nextLine().trim();
        }

        
        String regexUsuario = "^[a-zA-Z][a-zA-Z0-9_]{2,15}$";
        
        if (!Pattern.matches(regexUsuario, usuario)) {
            System.out.println("❌ Error: Formato de usuario inválido (Debe empezar con letra y tener entre 3 y 16 caracteres).");
            return;
        }

        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        // Verificar credenciales
        if (usuario.equals(USUARIO_VALIDO) && password.equals(PASSWORD_VALIDA)) {
            sesionIniciada = true;
            System.out.println("✅ Acceso concedido. ¡Bienvenido, " + usuario + "!\n");
        } else {
            System.out.println("❌ Acceso denegado. Usuario o contraseña incorrectos.\n");
        }
    }
}