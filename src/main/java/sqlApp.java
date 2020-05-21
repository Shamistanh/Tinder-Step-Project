import javax.servlet.ServletOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.sql.*;

public class sqlApp {
    private final static String URL = "jdbc:postgresql://localhost:5432/sql-lesson";
    private final static String UNAME = "postgres";
    private final static String PWD = "sh7513244";

    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, UNAME, PWD);
        String SQL = "select * from users order by id desc";
        PreparedStatement stmt = conn.prepareStatement(SQL);
        ResultSet rset = stmt.executeQuery();
        while (rset.next()) {
            int id = rset.getInt("id");
            int age = rset.getInt("age");
            String name = rset.getString("name");
//      ByteArrayInputStream is = new ByteArrayInputStream(rset.getBytes("file"));
//      ServletOutputStream os = resp.getOutputStream();
//      Files.copy(is, os);
            System.out.printf("%5d : %5d : %20s\n", id, age, name);
        }
    }
}
