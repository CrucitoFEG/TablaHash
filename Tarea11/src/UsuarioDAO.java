import java.sql.*;

public class UsuarioDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/usuarios_db";
    private static final String USUARIO = "usuario"; // Cambia si usas otro usuario
    private static final String PASSWORD = "abc123";    // Cambia si tienes contraseña

    public void registrarUsuario(String username, String passwordPlano) {
        String passwordHash = HashUtil.hashSHA256(passwordPlano);

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, PASSWORD)) {
            String sql = "INSERT INTO usuarios (username, password_hash) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, passwordHash);
            stmt.executeUpdate();
            System.out.println("Usuario registrado con éxito.");
        } catch (SQLException e) {
            System.out.println("Error al registrar: " + e.getMessage());
        }
    }

    public void iniciarSesion(String username, String passwordPlano) {
        String passwordHash = HashUtil.hashSHA256(passwordPlano);

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, PASSWORD)) {
            String sql = "SELECT * FROM usuarios WHERE username = ? AND password_hash = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, passwordHash);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Inicio de sesión exitoso.");
            } else {
                System.out.println("Usuario o contraseña incorrectos.");
            }
        } catch (SQLException e) {
            System.out.println("Error al iniciar sesión: " + e.getMessage());
        }
    }
}
