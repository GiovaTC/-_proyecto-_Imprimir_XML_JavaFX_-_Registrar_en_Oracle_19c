package com.example.xmlprinteroracle;

import java.io.StringReader;
import java.sql.*;

public class DBHelper {

    // EDITAR: coloque aquí los datos reales de su conexión Oracle
    private static final String JDBC_URL = "jdbc:oracle:thin:@//localhost:1521/orcl";
    private static final String DB_USER = "system";
    private static final String DB_PASSWORD = "Tapiero123";

    public DBHelper() throws ClassNotFoundException {
        // Cargar driver (opcional en Java 8+ si el driver está en el classpath)
        // Class.forName("oracle.jdbc.driver.OracleDriver");
    }

    public void insertXml(String filename, String uploadedBy, String xmlContent) throws SQLException {
        String sql = "INSERT INTO xml_store (filename, uploaded_by, xml_content) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            conn.setAutoCommit(false);
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, filename);
                pstmt.setString(2, uploadedBy);

                // Para CLOB grande, usar setCharacterStream
                StringReader reader = new StringReader(xmlContent);
                pstmt.setCharacterStream(3, reader, xmlContent.length());

                pstmt.executeUpdate();
                conn.commit();
            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            }
        }
    }
}