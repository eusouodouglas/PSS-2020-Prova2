package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Douglas
 */
public class ConexaoSQLiteFunc implements Conexao {

    private Connection connection = null;
    private static ConexaoSQLiteFunc instance;
    private Statement stmt = null;
    private String url = "jdbc:sqlite:src/main/java/data/usuario.db";

    

    private ConexaoSQLiteFunc() throws SQLException {
        try {

            this.connection = DriverManager.getConnection(this.url);
            this.stmt = this.connection.createStatement();
           

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public static ConexaoSQLiteFunc getInstance() throws Exception {
        if (instance == null) {
            instance = new ConexaoSQLiteFunc();
        }
        return instance;
    }

    public Connection connect() throws SQLException {
        return this.connection;
    }

    public void desconect() throws SQLException {
        connection.close();
    }

    public void desconect(Connection connetion, Statement statement) throws SQLException {
        connetion.close();
        statement.close();
    }

    public Statement getStatment() {
        return this.stmt;
    }

    public Connection getConexao() {
        return this.connection;
    }

}
