/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TH_Java;
import java.sql.*;
/**
 *
 * @author Admin
 */
public class ConnectDB {
    public static Connection getConnection()  {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/DienVien";

            String username = "root";
            String password = "12345";
            conn = DriverManager.getConnection(url, username, password);
            if(conn != null){
                System.out.println("Kết nối thành công!");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver không tìm thấy: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Kết nối thất bại: " + e.getMessage());
        }
        return conn;
    }
}
