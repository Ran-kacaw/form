import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Koneksi {

    private static final String URL = "jdbc:mysql://localhost:3307/db_penggajian?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection configDB() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                null,
                "Koneksi MySQL gagal: " + e.getMessage(),
                "Kesalahan Koneksi",
                JOptionPane.ERROR_MESSAGE
            );
            throw e;
        }
    }
}