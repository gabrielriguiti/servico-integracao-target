package br.com.sankhyamt.integracaotarget.util;

import br.com.sankhyamt.integracaotarget.model.database.ConnectionSQLServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogSankhya {

    public static void inserirLog(String erroEsp, String response,
                                  String request) throws SQLException {

        Connection connection = null;

        String sql = "INSERT INTO AD_SMTLIT (NULOG, ERROESP, RESPONSE,\n" +
                "                       REQUEST, DHREQUEST)\n" +
                "                       VALUES (NEXT VALUE FOR SQ_AD_SMTLIT,\n" +
                "                               ?, ?, ?, ?)";

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        try {

            connection = ConnectionSQLServer.connection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,  erroEsp);
            preparedStatement.setString(2, response);
            preparedStatement.setString(3, request);
            preparedStatement.setString(4, sdf.format(new Date()));
            preparedStatement.executeUpdate();


        } catch (IllegalAccessException | InstantiationException |
                ClassNotFoundException | SQLException e) {

            LogFile.logger.info("Erro ao gravar Log: ".concat(e.getMessage()));

        } finally {

            if (connection != null){

                connection.close();
            }
        }
    }
}
