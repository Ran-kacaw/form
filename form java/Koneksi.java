import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Koneksi {

    private static final String URL = "jdbc:postgresql://localhost:5432/db_penggajian";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static Connection configDB() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                null,
                "Koneksi PostgreSQL gagal: " + e.getMessage(),
                "Kesalahan Koneksi",
                JOptionPane.ERROR_MESSAGE
            );
            throw e;
        }
    }
}
