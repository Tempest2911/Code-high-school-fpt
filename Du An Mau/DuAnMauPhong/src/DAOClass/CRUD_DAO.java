package DAOClass;

import java.sql.*;


public class CRUD_DAO {

    private static final String CONNECTION_URL = "jdbc:sqlserver://TEMPEST:1433;databaseName=DU_AN_MAU;user=sa;password=123456;trustServerCertificate=true";

    // Kết nối CSDL
    public static Connection getConnect() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL);
    }

    public static ResultSet executeQuery(String query, Object... params) throws SQLException {
        Connection conn = getConnect();
        PreparedStatement stm = conn.prepareStatement(query);

        for (int i = 0; i < params.length; i++) {
            stm.setObject(i + 1, params[i]);
        }

        return stm.executeQuery(); // Lưu ý: Cần đóng ResultSet & Connection ở ngoài!
    }

    public static int executeUpdate(String query, Object... params) throws SQLException {
        try (Connection conn = getConnect(); PreparedStatement stm = conn.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                stm.setObject(i + 1, params[i]);
            }
            return stm.executeUpdate();
        }
    }

}
