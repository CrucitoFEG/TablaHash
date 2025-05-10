import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        UsuarioDAO dao = new UsuarioDAO();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("1. Registrar");
            System.out.println("2. Iniciar sesión");
            System.out.print("Opción: ");
            int opcion = Integer.parseInt(reader.readLine());

            System.out.print("Usuario: ");
            String user = reader.readLine();

            System.out.print("Contraseña: ");
            String pass = reader.readLine();

            if (opcion == 1) {
                dao.registrarUsuario(user, pass);
            } else if (opcion == 2) {
                dao.iniciarSesion(user, pass);
            } else {
                System.out.println("Opción inválida");
            }

        } catch (IOException e) {
            System.out.println("Error al leer entrada: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un número válido.");
        }
    }
}

