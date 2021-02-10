package br.com.sankhyamt.integracaotarget.model.database;

import br.com.sankhyamt.integracaotarget.exception.DatabaseException;
import br.com.sankhyamt.integracaotarget.util.LogFile;

import java.sql.*;

/**
 * Classe de conexão com o banco de dados.
 *
 * @since v1.0
 */
public class ConnectionSQLServer {

    /**
     * @return Retorna uma conexão aberta com o banco de dados.
     */
    public static Connection connection() throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

        String connectionUrl = "jdbc:sqlserver://10.40.3.241:1433;databaseName=SETRANSPORTES_TST;" +
                "user=SANKHYA;password=2oie7tn6";

        try {
            Connection connection = DriverManager.getConnection(connectionUrl);

            return connection;

        } catch (SQLException e) {

            LogFile.logger.info("Erro ao conectar com o banco de dados. Mensagem do erro: " + e.getMessage());
        }

        return null;
    }

    public static void checkConnectionDB(){

        try {
            Connection connection = connection();

            PreparedStatement statement = connection().prepareStatement("SELECT 1 FROM DUAL");

            ResultSet rs = statement.executeQuery();

            LogFile.logger.info("Conectado com o banco de dados");

        } catch (SQLException e) {

            LogFile.logger.info("Erro ao conectar com o banco de dados. Mensagem do erro: " + e.getMessage());

            throw new DatabaseException(e.getMessage());

        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {

            LogFile.logger.info("Erro ao conectar com o banco de dados. Mensagem do erro: " + e.getMessage());

        }
    }
}