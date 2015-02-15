package dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Retorna a conexão com o banco de dados 'salao_beleza'
 *
 * @throws Exception
 *
 */
public class Conecta {

    public static Connection getConexao() throws Exception {
        Connection con;

        String serverName = "localhost";
        String mydatebase = "salao_beleza";
        String username = "root";
        String password = "";

        String driverName = "com.mysql.jdbc.Driver";
        Class.forName(driverName);

        String url = "jdbc:mysql://" + serverName + "/" + mydatebase;
        con = DriverManager.getConnection(url, username, password);
        return con;
    }
}
